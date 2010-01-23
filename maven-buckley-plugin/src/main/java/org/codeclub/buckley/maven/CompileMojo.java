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
package org.codeclub.buckley.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codeclub.buckley.Document;
import org.codeclub.buckley.compile.Compiler;
import org.codeclub.buckley.io.XmlSerializer;

/**
 * @goal compile
 */

public class CompileMojo extends AbstractMojo {
    private XmlSerializer serializer;

    /**
     * List of files to exclude. Specified as fileset patterns.
     *
     * @required
     * @parameter
     */
    private CompilablePdf[] compilables;

    public CompileMojo() {
        this(new XmlSerializer());
    }

    public CompileMojo(XmlSerializer serializer) {
        this.serializer = serializer;
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        if (compilables.length == 0) {
            getLog().info("No pdf compilables given");
            return;
        }

        for (CompilablePdf pdf : compilables) {
            getLog().info("Compiling " + pdf.getPdfTemplate() + " against " + pdf.getXml() + " output to " + pdf.getCompiledPdf());
            Document document = serializer.deserialize(pdf.getXml());
            Compiler compiler = initializeCompiler(document);
            compiler.compile(pdf.getPdfTemplate(), pdf.getCompiledPdf(), document);
        }
    }

    protected Compiler initializeCompiler(Document document) {
        return new Compiler();
    }

    public void setCompilables(CompilablePdf[] compilables) {
        this.compilables = compilables;
    }
}
