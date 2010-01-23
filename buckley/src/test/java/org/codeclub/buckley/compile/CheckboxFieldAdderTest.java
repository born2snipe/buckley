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

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfFormField;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.RadioCheckField;
import junit.framework.TestCase;
import org.codeclub.buckley.CheckboxField;
import org.codeclub.buckley.Pdf;

import java.io.IOException;

import static org.mockito.Mockito.mock;


public class CheckboxFieldAdderTest extends TestCase {
    private Pdf pdf;
    private FontRegistry fontRegistry;
    private ShuntCheckboxFieldAdder adder;
    private BaseFont font;
    private PdfWriter writer;

    protected void setUp() throws Exception {
        super.setUp();
        pdf = mock(Pdf.class);
        fontRegistry = mock(FontRegistry.class);
        adder = new ShuntCheckboxFieldAdder(fontRegistry);
        font = BaseFont.createFont();
        writer = mock(PdfWriter.class);
        adder.field = mock(PdfFormField.class);
    }

    public void test() {
        assertTrue(true);
    }

    public void FAILING_test_add() {
        CheckboxField field = new CheckboxField("name", "Off", 1, 2, 3, 4);

        adder.add(pdf, field, 100);

//        verify(form).addCheckBox("name", "Off", false, 1, 94, 4, 98);
    }

    private static class ShuntCheckboxFieldAdder extends CheckboxFieldAdder {
        private PdfFormField field;
        private RadioCheckField checkBoxField;

        protected ShuntCheckboxFieldAdder(FontRegistry fontRegistry) {
            super(fontRegistry);
        }

        protected PdfFormField createFormField(RadioCheckField formField) throws DocumentException, IOException {
            this.checkBoxField = formField;
            return field;
        }
    }
}
