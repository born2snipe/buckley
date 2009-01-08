package org.codeclub.buckley.compile;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.*;
import org.codeclub.buckley.Field;
import org.codeclub.buckley.TextField;
import org.codeclub.buckley.io.XmlSerializer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class Compiler {
    private XmlSerializer serializer = new XmlSerializer();
    private Map<Class, AddField> fieldAdders = new HashMap<Class, AddField>();

    public Compiler() {
        fieldAdders.put(TextField.class, new AddTextField());
    }

    public File compile(File pdfTemplate, File compiledPdfTemplate, File xmlFile) {
        try {
            return compile(pdfTemplate, compiledPdfTemplate, serializer.deserialize(new FileInputStream(xmlFile)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public File compile(File pdfTemplate, File compiledPdfTemplate, org.codeclub.buckley.Document doc) {
        PdfReader reader = null;
        PdfWriter writer = null;
        try {
            reader = new PdfReader(pdfTemplate.toURI().toURL());
            Document document = new Document(reader.getPageSizeWithRotation(1));
            writer = PdfWriter.getInstance(document, new FileOutputStream(compiledPdfTemplate));
            PdfAcroForm form = new PdfAcroForm(writer);
            document.open();
            PdfContentByte content = writer.getDirectContent();
            try {
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    PdfImportedPage importedPage = writer.getImportedPage(reader, i);
                    content.addTemplate(importedPage, 0.0f, 0.0f);
                    for (Field field : doc.getPage(i).getFields()) {
                        createFieldAdder(field).add(form, field);
                    }
                    document.newPage();
                }
            } finally {
                document.close();
            }

            return pdfTemplate;
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    private AddField createFieldAdder(Field field) {
        return fieldAdders.get(field.getClass());
    }

    private static class AddTextField implements AddField<org.codeclub.buckley.TextField> {
        public void add(PdfAcroForm form, org.codeclub.buckley.TextField field) {
            try {
                BaseFont font = BaseFont.createFont();
                float fontSize = field.getFont() == null ? 0.0f : field.getFont().getSize();
                form.addSingleLineTextField(field.getName(), "", font, fontSize, field.getX(), field.getY(), field.getWidth(), field.getHeight());
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static interface AddField<T extends Field> {
        void add(PdfAcroForm form, T field);
    }
}
