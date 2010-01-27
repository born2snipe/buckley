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

import buckley.compile.Compiler;
import buckley.io.XmlDocumentHandler;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;

/**
 * @goal compile
 * @requiresProject false
 */

public class CompileMojo extends AbstractMojo {
    private XmlDocumentHandler serializer;
    private Compiler compiler;


    /**
     * @required
     * @parameter expression="${pdfTemplate}"
     */
    private File pdfTemplate;

    /**
     * @required
     * @parameter expression="${buckleyXml}"
     */
    private File buckleyXml;

    public CompileMojo() {
        this(new XmlDocumentHandler(), new Compiler());
    }

    protected CompileMojo(XmlDocumentHandler serializer, Compiler compiler) {
        this.serializer = serializer;
        this.compiler = compiler;
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        File compiledPdf = new File(pdfTemplate.getName().replaceAll("(.+)\\.(.+)", "$1_compiled.$2"));
        compiler.compile(pdfTemplate, compiledPdf, serializer.read(buckleyXml));
    }

    protected void setPdfTemplate(File pdfTemplate) {
        this.pdfTemplate = pdfTemplate;
    }

    protected void setBuckleyFile(File buckleyXml) {
        this.buckleyXml = buckleyXml;
    }
}
