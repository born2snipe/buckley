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
import buckley.compile.FontRegistry;
import buckley.io.DocumentReader;
import buckley.io.DocumentWriter;
import com.lowagie.text.pdf.BaseFont;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;


public class EmbedFontMojoTest {
    private DocumentReader reader;
    private DocumentWriter writer;
    private EmbedFontMojo mojo;
    private Document document;
    private FontRegistry fontRegistry;
    private FileUtil fileUtil;
    private byte[] fontData;

    @Before
    public void setUp() throws Exception {
        reader = mock(DocumentReader.class);
        writer = mock(DocumentWriter.class);
        document = mock(Document.class);
        fontRegistry = mock(FontRegistry.class);
        fileUtil = mock(FileUtil.class);

        mojo = new EmbedFontMojo(reader, writer, fileUtil);
        mojo.setFontFile("font-file");
        mojo.setTemplate("template.buckley");
        fontData = new byte[]{0, 1, 2};
    }

    @Test
    public void test() throws MojoExecutionException, MojoFailureException {
        when(reader.read(new File("template.buckley"))).thenReturn(document);
        when(document.getFontRegistry()).thenReturn(fontRegistry);
        when(fileUtil.read(new File("font-file"))).thenReturn(fontData);

        EmbeddedFont font = new EmbeddedFont("font-file", BaseFont.WINANSI, fontData);

        mojo.execute();

        verify(fontRegistry).registerFont(font);
        verify(writer).write(new File("template.buckley"), document);
    }

    @Test
    public void test_specifyFontEncoding() throws MojoExecutionException, MojoFailureException {
        mojo.setFontEncoding("font-encoding");

        when(reader.read(new File("template.buckley"))).thenReturn(document);
        when(document.getFontRegistry()).thenReturn(fontRegistry);
        when(fileUtil.read(new File("font-file"))).thenReturn(fontData);

        EmbeddedFont font = new EmbeddedFont("font-file", "font-encoding", fontData);

        mojo.execute();

        verify(fontRegistry).registerFont(font);
        verify(writer).write(new File("template.buckley"), document);
    }

    @Test
    public void test_noTemplateGiven() throws MojoExecutionException, MojoFailureException {
        mojo.setTemplate(null);

        try {
            mojo.execute();
            fail();
        } catch (MojoFailureException err) {
            assertEquals("Please specify the buckley template for embedding", err.getMessage());
        }
    }

    @Test
    public void test_noFontFileGiven() throws MojoExecutionException, MojoFailureException {
        mojo.setFontFile(null);

        try {
            mojo.execute();
            fail();
        } catch (MojoFailureException err) {
            assertEquals("Please specify the font to be embedded", err.getMessage());
        }
    }


}
