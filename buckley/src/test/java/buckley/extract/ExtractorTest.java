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
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ExtractorTest {
    private PdfReaderFactory pdfReaderFactory;
    private PdfReader pdfReader;
    private AcroFields acroFields;
    private Extractor extractor;
    private ByteArrayInputStream input;
    private HashMap fieldNames;
    private FieldFactory fieldFactory;
    private Field field;

    @Test
    public void x() {
        Extractor extractor = new Extractor();

        Document document = extractor.extract(Thread.currentThread().getContextClassLoader().getResourceAsStream("checkbox.pdf"));

        assertNotNull(document);

        Page page = document.getPage(1);
        List<Field> fields = page.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        assertEquals(1, fields.size());
    }

    @Test
    public void test_multipleFields_OnDifferentPages() {
        fieldNames.put("field-1", null);
        fieldNames.put("field-2", null);

        when(fieldFactory.build(AcroFields.FIELD_TYPE_TEXT)).thenReturn(field);
        when(acroFields.getFieldPositions("field-1")).thenReturn(new float[]{1.0f});
        when(acroFields.getFieldPositions("field-2")).thenReturn(new float[]{3.0f});

        Document document = extractor.extract(input);

        assertEquals(2, document.getPages().size());
        assertEquals(1, document.getPage(1).getFields().size());
        assertEquals(1, document.getPage(3).getFields().size());
    }
    
    @Test
    public void test_multipleFields_OnSamePage() {
        fieldNames.put("field-1", null);
        fieldNames.put("field-2", null);

        when(fieldFactory.build(AcroFields.FIELD_TYPE_TEXT)).thenReturn(field);
        when(acroFields.getFieldPositions("field-1")).thenReturn(new float[]{1.0f});
        when(acroFields.getFieldPositions("field-2")).thenReturn(new float[]{1.0f});

        Document document = extractor.extract(input);

        assertEquals(1, document.getPages().size());
        Page page = document.getPage(1);
        assertNotNull(page);

        assertEquals(2, page.getFields().size());
    }

    @Test
    public void test_singleField() {
        fieldNames.put("field-1", null);

        when(fieldFactory.build(AcroFields.FIELD_TYPE_TEXT)).thenReturn(field);
        when(acroFields.getFieldPositions("field-1")).thenReturn(new float[]{1.0f});

        Document document = extractor.extract(input);

        assertNotNull(document);
        assertEquals(1, document.getPages().size());
        Page page = document.getPage(1);
        assertNotNull(page);

        List<Field> fields = page.getFields();
        assertEquals(1, fields.size());
    }

    @Before
    public void setUp() throws Exception {
        pdfReaderFactory = mock(PdfReaderFactory.class);
        pdfReader = mock(PdfReader.class);
        acroFields = mock(AcroFields.class);
        fieldFactory = mock(FieldFactory.class);
        field = mock(Field.class);

        extractor = new Extractor(pdfReaderFactory, fieldFactory);

        input = new ByteArrayInputStream(new byte[0]);
        fieldNames = new HashMap();

        when(pdfReaderFactory.build(input)).thenReturn(pdfReader);
        when(pdfReader.getAcroFields()).thenReturn(acroFields);
        when(acroFields.getFields()).thenReturn(fieldNames);
    }
}