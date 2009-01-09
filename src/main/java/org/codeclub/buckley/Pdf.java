package org.codeclub.buckley;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfAcroForm;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;


public class Pdf {
    private PdfReader reader;
    private PdfWriter writer;
    private Document document;
    private PdfAcroForm pdfAcroForm;

    public Pdf(File template, OutputStream outputStream) {
        boolean errorOccured = false;
        try {
            reader = new PdfReader(template.toURI().toURL());
            document = new Document(reader.getPageSizeWithRotation(1));
            writer = PdfWriter.getInstance(document, outputStream);
            pdfAcroForm = new PdfAcroForm(writer);
            document.open();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (errorOccured) {
                close();
            }
        }
    }

    public void eachPage(PageHandler handler) {
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            handler.handlePage(i, reader, writer, document, pdfAcroForm);
        }
    }


    public void close() {
        if (reader != null) {
            reader.close();
        }
        if (document != null) {
            document.close();
        }
        if (writer != null) {
            writer.close();
        }
    }

    public static interface PageHandler {
        void handlePage(int number, PdfReader reader, PdfWriter writer, Document document, PdfAcroForm form);
    }
}
