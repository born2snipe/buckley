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
import buckley.EmbeddedFont;
import buckley.io.DocumentReader;
import buckley.io.DocumentWriter;
import buckley.io.XmlDocumentHandler;
import com.lowagie.text.pdf.BaseFont;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;

/**
 * @goal embed-font
 * @requiresProject false
 */
public class EmbedFontMojo extends AbstractMojo {
    /**
     * @parameter expression="${template}
     * @required
     */
    private String template;

    /**
     * @parameter expression="${fontFile}
     * @required
     */
    private String fontFile;

    /**
     * @parameer expression="${fontEncoding}"
     */
    private String fontEncoding = BaseFont.WINANSI;

    private DocumentReader reader;
    private DocumentWriter writer;
    private FileUtil fileUtil;

    public EmbedFontMojo() {
        this(new XmlDocumentHandler(), new XmlDocumentHandler(), new FileUtil());
    }

    protected EmbedFontMojo(DocumentReader documentReader, DocumentWriter writer, FileUtil fileUtil) {
        this.reader = documentReader;
        this.writer = writer;
        this.fileUtil = fileUtil;
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        if (template == null) throw new MojoFailureException("Please specify the buckley template for embedding");
        if (fontFile == null) throw new MojoFailureException("Please specify the font to be embedded");

        Document document = reader.read(new File(template));

        File font = new File(fontFile);
        EmbeddedFont embeddedFont = new EmbeddedFont(font.getName(), fontEncoding, fileUtil.read(font));
        document.getFontRegistry().registerFont(embeddedFont);

        writer.write(new File(template), document);
    }

    protected void setTemplate(String template) {
        this.template = template;
    }

    protected void setFontFile(String fontFile) {
        this.fontFile = fontFile;
    }

    protected void setFontEncoding(String fontEncoding) {
        this.fontEncoding = fontEncoding;
    }
}
