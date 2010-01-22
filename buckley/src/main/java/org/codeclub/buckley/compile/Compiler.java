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
package org.codeclub.buckley.compile;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.*;
import org.codeclub.buckley.*;
import org.codeclub.buckley.TextField;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class Compiler {
    private Map<Class, FieldAdder> fieldAdders = new HashMap<Class, FieldAdder>();

    public Compiler(FontRegistry fontRegistry) {
        fieldAdders.put(TextField.class, new TextFieldAdder(fontRegistry));
        fieldAdders.put(CheckboxField.class, new CheckboxFieldAdder());
    }

    public void compile(InputStream pdfTemplate, OutputStream output, final org.codeclub.buckley.Document doc) {
        Pdf pdf = null;
        try {
            pdf = new Pdf(pdfTemplate, output);
            pdf.eachPage(new Pdf.PageHandler() {
                public void handlePage(int number, PdfReader reader, PdfWriter writer, Document document, PdfAcroForm form) {
                    PdfContentByte content = writer.getDirectContent();
                    PdfImportedPage importedPage = writer.getImportedPage(reader, number);
                    content.addTemplate(importedPage, 0.0f, 0.0f);
                    Page page = doc.getPage(number);
                    if (page != null) {
                        for (Field field : page.getFields()) {
                            createFieldAdder(field).add(form, field, importedPage.getHeight());
                        }
                    }
                    document.newPage();
                }
            });
        } finally {
            if (pdf != null)
                pdf.close();
        }
    }

    public File compile(File pdfTemplate, File compiledPdfTemplate, org.codeclub.buckley.Document doc) {
        try {
            compile(new FileInputStream(pdfTemplate), new FileOutputStream(compiledPdfTemplate), doc);
            return compiledPdfTemplate;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private FieldAdder createFieldAdder(Field field) {
        return fieldAdders.get(field.getClass());
    }

}
