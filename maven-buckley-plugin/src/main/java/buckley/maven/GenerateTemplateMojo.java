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

import buckley.CheckboxField;
import buckley.Document;
import buckley.Page;
import buckley.TextField;
import buckley.io.DocumentWriter;
import buckley.io.XmlDocumentHandler;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;

/**
 * @goal generate-template
 */
public class GenerateTemplateMojo extends AbstractMojo {
    private DocumentWriter writer;

    /**
     * The template filename
     *
     * @parameter expression="${filename}
     */
    private String filename = "template-file.buckley";

    public GenerateTemplateMojo() {
        this(new XmlDocumentHandler());
    }

    protected GenerateTemplateMojo(DocumentWriter writer) {
        this.writer = writer;
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Creating buckley template (" + filename + ")");

        Document document = new Document();

        Page pageOne = new Page(1);
        pageOne.addField(new TextField("field1", 10, 10, 100, 20));
        pageOne.addField(new CheckboxField("field1", null, 10, 10, 100, 20));

        document.addPage(pageOne);

        writer.write(new File(filename), document);
    }
}
