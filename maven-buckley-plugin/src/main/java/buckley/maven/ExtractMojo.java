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

import buckley.extract.Extractor;
import buckley.io.DocumentWriter;
import buckley.io.XmlDocumentHandler;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;

/**
 * @goal extract
 * @requiresProject false
 */
public class ExtractMojo extends AbstractMojo {
    private Extractor extractor;
    private DocumentWriter writer;

    /**
     * @parameter expression="${pdf}
     * @required
     */
    private File pdf;

    /**
     * @parameter expression="${template}"
     */
    private File template;

    public ExtractMojo() {
        this(new Extractor(), new XmlDocumentHandler());
    }

    protected ExtractMojo(Extractor extractor, DocumentWriter writer) {
        this.extractor = extractor;
        this.writer = writer;
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        if (template == null) {
            String templateFilename = pdf.getName().replaceAll("(?i)(.+)\\.pdf", "$1.buckley");
            template = new File(templateFilename);
        }
        getLog().info("Extracting template (" + template.getName() + ") from " + pdf.getName());
        writer.write(template, extractor.extract(pdf));
    }

    protected void setPdf(File pdf) {
        this.pdf = pdf;
    }

    protected void setTemplate(File template) {
        this.template = template;
    }
}
