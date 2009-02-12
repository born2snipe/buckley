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
package org.codeclub.buckley;

import com.lowagie.text.pdf.*;
import junit.framework.TestCase;
import org.codeclub.buckley.compile.Compiler;
import org.codeclub.buckley.compile.FontRegistry;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;


public class AddFieldTest extends TestCase {

    public void test_() throws IOException, URISyntaxException {
        File dir = new File("/Users/dan/Desktop/pdfs/");
        File file = new File(dir, "ApplicationForPool2008-2009.pdf");
        File compiled = new File(dir, "compiled.pdf");

        Compiler compiler = new org.codeclub.buckley.compile.Compiler(new FontRegistry());

        Document document = new Document();

        Page page = new Page(2);
        page.addField(new TextField("blah", 106, 131, 240, 10));

        document.addPage(page);

        File compiledPdf = compiler.compile(file, compiled, document);

        PdfReader reader = new PdfReader(new FileInputStream(compiledPdf));
        AcroFields fields = reader.getAcroFields();
        assertNotNull(fields.getField("blah"));
    }

    public void test_x() throws IOException {
        File dir = new File("/Users/dan/Desktop/pdfs/");
        File file = new File(dir, "ApplicationForPool2008-2009.pdf");

        PdfReader reader = new PdfReader(new FileInputStream(file));

        for (int i = 1; i < 1000; i++) {
            PdfObject object = reader.getPdfObject(i);
            if (object instanceof PdfDictionary && object.toString().equals("Dictionary of type: /Font")) {
                PdfDictionary dictionary = (PdfDictionary) object;
                for (Object key : dictionary.getKeys()) {
                    System.out.println(key + " -- " + dictionary.get((PdfName) key));
                }
                System.out.println("*****************");
            }
        }
    }

    public void test_Y() throws IOException {
        File dir = new File("/Users/dan/Desktop/pdfs/");
        File file = new File(dir, "ApplicationForPool2008-2009.pdf");

        PdfReader reader = new PdfReader(new FileInputStream(file));

        AcroFields fields = reader.getAcroFields();
        HashMap z = fields.getFields();
        for (Object y : z.keySet()) {
            System.out.println("y = " + y);
            for (float i : fields.getFieldPositions((String) y)) System.out.println("i = " + i);

            AcroFields.Item item = (AcroFields.Item) z.get(y);

            for (Object value : item.values) {
                System.out.println("value = " + value);
                if (value instanceof PdfDictionary) {
                    PdfDictionary dict = (PdfDictionary) value;
                    for (Object o : dict.getKeys()) {
                        System.out.println(value+" >> "+o+" -- "+dict.get((PdfName) o));
                    }
                }
            }
            System.out.println("========================");
        }

    }


    private Document createTextFieldDocument(String fieldName) {
        Page page = new Page(1);
        page.addField(new TextField(fieldName, 10, 10, 100, 25));
        page.addField(new TextField("blah3", 10, 60, 100, 25, BaseFont.TIMES_ROMAN, 12.0f));
        page.addField(new CheckboxField("blah2", "On", 10, 40, 20, 20));
        Document doc = new Document();
        doc.addPage(page);
        return doc;
    }
}
