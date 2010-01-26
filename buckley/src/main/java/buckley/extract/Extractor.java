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
package buckley.extract;

import buckley.Document;
import buckley.Field;
import buckley.Page;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Extractor {
    private PdfReaderFactory pdfReaderFactory;
    private FieldFactory fieldFactory;
    private List<ITextFieldExtractor> fieldExtractors = new ArrayList<ITextFieldExtractor>();

    public Extractor() {
        this(new PdfReaderFactory(), new FieldFactory());
    }

    protected Extractor(PdfReaderFactory pdfReaderFactory, FieldFactory fieldFactory) {
        this.pdfReaderFactory = pdfReaderFactory;
        this.fieldFactory = fieldFactory;
    }

    public Document extract(File file) {
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            return extract(input);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {

                }
            }
        }
    }

    public Document extract(InputStream input) {
        Document document = new Document();
        PdfReader pdfReader = pdfReaderFactory.build(input);

        AcroFields acroFields = pdfReader.getAcroFields();
        if (acroFields == null) throw new IllegalStateException("No form found on pdf");
        HashMap fields = acroFields.getFields();

        for (Object key : fields.keySet()) {
            String fieldName = (String) key;

            float[] positions = acroFields.getFieldPositions(fieldName);
            int pageNumber = (int) positions[0];

            Page page = document.getPage(pageNumber);
            if (page == null) {
                page = new Page(pageNumber);
                document.addPage(page);
            }

            Field field = fieldFactory.build(acroFields.getFieldType(fieldName));
            for (ITextFieldExtractor fieldExtractor : fieldExtractors) {
                if (fieldExtractor.canExtract(field)) {
                    fieldExtractor.extract(field, fieldName, acroFields);
                }
            }
            page.addField(field);
        }

        return document;
    }

    public void addFieldExtractor(ITextFieldExtractor fieldExtractor) {
        fieldExtractors.add(fieldExtractor);
    }
}
