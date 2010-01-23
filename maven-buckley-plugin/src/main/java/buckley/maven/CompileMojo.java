/**
 * Copyright 2008-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package buckley.maven;

import buckley.Document;
import buckley.compile.Compiler;
import buckley.io.XmlDocumentHandler;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @goal compile
 */

public class CompileMojo extends AbstractMojo {
    private XmlDocumentHandler serializer;

    /**
     * List of files to exclude. Specified as fileset patterns.
     *
     * @required
     * @parameter
     */
    private CompilablePdf[] compilables;

    public CompileMojo() {
        this(new XmlDocumentHandler());
    }

    public CompileMojo(XmlDocumentHandler serializer) {
        this.serializer = serializer;
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        if (compilables.length == 0) {
            getLog().info("No pdf compilables given");
            return;
        }

        for (CompilablePdf pdf : compilables) {
            getLog().info("Compiling " + pdf.getPdfTemplate() + " against " + pdf.getXml() + " output to " + pdf.getCompiledPdf());
            try {
                Document document = serializer.read(pdf.getXml());
                buckley.compile.Compiler compiler = initializeCompiler(document);
                compiler.compile(pdf.getPdfTemplate(), pdf.getCompiledPdf(), document);
            } catch (Exception err) {
                throw new MojoExecutionException("A problem occurred while trying to compile template (" + pdf.getPdfTemplate() + ")", err);
            }
        }
    }

    protected Compiler initializeCompiler(Document document) {
        return new Compiler();
    }

    public void setCompilables(CompilablePdf[] compilables) {
        this.compilables = compilables;
    }
}
