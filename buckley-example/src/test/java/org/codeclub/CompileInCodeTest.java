/**
 * Copyright 2008-2009 the original author or authors.
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
package org.codeclub;

import com.lowagie.text.pdf.PRAcroForm;
import com.lowagie.text.pdf.PdfDictionary;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfReader;
import junit.framework.TestCase;
import org.codeclub.buckley.Document;
import org.codeclub.buckley.Field;
import org.codeclub.buckley.Page;
import org.codeclub.buckley.TextField;
import org.codeclub.buckley.compile.Compiler;
import org.codeclub.buckley.io.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public class CompileInCodeTest extends TestCase {

    public void test() throws IOException, URISyntaxException {
        XmlSerializer serializer = new XmlSerializer();

        String xml = "<document>\n" +
                "  <pages>\n" +
                "    <page number=\"1\">\n" +
                "      <fields>\n" +
                "        <textField name=\"firstName\" x=\"262.0\" y=\"55.0\" width=\"130.0\" height=\"15.0\"/>\n" +
                "        <textField name=\"lastName\" x=\"85.0\" y=\"55.0\" width=\"130.0\" height=\"15.0\"/>\n" +
                "        <textField name=\"middleInitial\" x=\"430.0\" y=\"55.0\" width=\"15.0\" height=\"15.0\"/>\n" +
                "        <textField name=\"line1\" x=\"90.0\" y=\"96.0\" width=\"300.0\" height=\"15.0\"/>\n" +
                "        <textField name=\"line2\" x=\"90.0\" y=\"111.0\" width=\"300.0\" height=\"15.0\"/>\n" +
                "        <textField name=\"city\" x=\"85.0\" y=\"125.0\" width=\"125.0\" height=\"15.0\"/>\n" +
                "        <textField name=\"state\" x=\"265.0\" y=\"125.0\" width=\"20.0\" height=\"15.0\"/>\n" +
                "        <textField name=\"zip\" x=\"327.0\" y=\"125.0\" width=\"60.0\" height=\"15.0\"/>\n" +
                "      </fields>\n" +
                "    </page>\n" +
                "  </pages>\n" +
                "  <fontRegistry defaultFontName=\"Helvetica\" defaultFontSize=\"10.0\"/>\n" +
                "</document>";

        Document document = serializer.deserialize(xml);
        Compiler compiler = new Compiler(document.getFontRegistry());

        File originalPdf = new File(Thread.currentThread().getContextClassLoader().getResource("original.pdf").toURI());
        File compiledPdf = new File(originalPdf.getParent(), "compiled.pdf");

        compiler.compile(originalPdf, compiledPdf, document);

        PdfReader pdfReader = new PdfReader(new FileInputStream(compiledPdf));

        PRAcroForm form = pdfReader.getAcroForm();
        for (Page page : document.getPages()) {
            for (Field field : page.getFields()) {
                assertField(field, form);
            }
        }
    }

    private void assertField(Field expectedField, PRAcroForm actualForm) {
        PRAcroForm.FieldInformation fieldInfo = actualForm.getField(expectedField.getName());
        assertNotNull("Field (" + expectedField.getName() + ") was not found on acroform", fieldInfo);
        PdfDictionary dictionary = fieldInfo.getInfo();
        assertEquals(expectedField.getName(), dictionary.get(PdfName.T).toString());
        if (expectedField instanceof TextField) {
            assertEquals(PdfName.TX, dictionary.get(PdfName.FT));
        }
    }
}
