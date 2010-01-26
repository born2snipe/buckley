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
package buckley.compile;

import buckley.*;
import buckley.TextField;
import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Compiler {
    private Map<Class, ITextFieldFactory<?>> fieldFactories = new HashMap<Class, ITextFieldFactory<?>>();
    private List<FieldAttributeModifier> modifiers = new ArrayList<FieldAttributeModifier>();
    private FieldSizeFactory fieldSizeFactory = new FieldSizeFactory();

    public Compiler() {
        fieldFactories.put(TextField.class, new TextFieldFactory());
        fieldFactories.put(CheckboxField.class, new CheckboxFieldFactory());

        modifiers.add(new TextModifier());
        modifiers.add(new BorderModifier());
        modifiers.add(new BackgroundColorModifier());
        modifiers.add(new ColorModifier());
        modifiers.add(new AlignmentModifier());
    }

    public void compile(InputStream pdfTemplate, OutputStream output, final buckley.Document doc) {
        final Pdf pdf = new Pdf(pdfTemplate, output);
        try {
            pdf.eachPage(new Pdf.PageHandler() {
                public void handlePage(int number, PdfReader reader, PdfWriter writer, Document document, PdfAcroForm form) {
                    PdfContentByte content = writer.getDirectContent();
                    PdfImportedPage importedPage = writer.getImportedPage(reader, number);
                    content.addTemplate(importedPage, 0.0f, 0.0f);
                    if (doc.hasFields(number)) {
                        Page page = doc.getPage(number);
                        for (Field field : page.getFields()) {
                            Rectangle fieldSize = fieldSizeFactory.build(field);
                            ITextFieldFactory factory = fieldFactories.get(field.getClass());
                            BaseField iTextField = factory.build(writer, fieldSize, field.getName());
                            for (FieldAttributeModifier modifier : modifiers) {
                                if (modifier.canModify(field)) {
                                    modifier.modify(iTextField, field, doc);
                                }
                            }
                            try {
                                writer.addAnnotation(factory.buildFormField(iTextField));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    document.newPage();
                }
            });
        } finally {
            pdf.close();
        }
    }

    public File compile(File pdfTemplate, File compiledPdfTemplate, buckley.Document doc) {
        try {
            compile(new FileInputStream(pdfTemplate), new FileOutputStream(compiledPdfTemplate), doc);
            return compiledPdfTemplate;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
