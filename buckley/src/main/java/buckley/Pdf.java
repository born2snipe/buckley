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
package buckley;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfAcroForm;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;


public class Pdf {
    private PdfReader reader;
    private PdfWriter writer;
    private Document document;
    private PdfAcroForm pdfAcroForm;

    public Pdf(InputStream templateInputStream, OutputStream outputStream) {
        boolean errorOccured = false;
        try {
            reader = new PdfReader(templateInputStream);
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

    public PdfWriter getWriter() {
        return writer;
    }

    public PdfAcroForm getAcroForm() {
        return pdfAcroForm;
    }

    public static interface PageHandler {
        void handlePage(int number, PdfReader reader, PdfWriter writer, Document document, PdfAcroForm form);
    }
}
