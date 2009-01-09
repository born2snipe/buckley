package org.codeclub.buckley.compile;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.*;
import org.codeclub.buckley.*;
import org.codeclub.buckley.TextField;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class Compiler {
    private Map<Class, FieldAdder> fieldAdders = new HashMap<Class, FieldAdder>();

    public Compiler(FontRegistry fontRegistry) {
        fieldAdders.put(TextField.class, new TextFieldAdder(fontRegistry));
        fieldAdders.put(CheckboxField.class, new CheckboxFieldAdder());
    }

    public File compile(File pdfTemplate, File compiledPdfTemplate, final org.codeclub.buckley.Document doc) {
        Pdf pdf = new Pdf(pdfTemplate, compiledPdfTemplate);
        try {
            pdf.eachPage(new Pdf.PageHandler() {
                public void handlePage(int number, PdfReader reader, PdfWriter writer, Document document, PdfAcroForm form) {
                    PdfContentByte content = writer.getDirectContent();
                    PdfImportedPage importedPage = writer.getImportedPage(reader, number);
                    content.addTemplate(importedPage, 0.0f, 0.0f);
                    Page page = doc.getPage(number);
                    if (page != null) {
                        for (Field field : page.getFields()) {
                            createFieldAdder(field).add(form, field);
                        }
                    }
                    document.newPage();
                }
            });
            return compiledPdfTemplate;
        } finally {
            pdf.close();
        }
    }

    private FieldAdder createFieldAdder(Field field) {
        return fieldAdders.get(field.getClass());
    }

}
