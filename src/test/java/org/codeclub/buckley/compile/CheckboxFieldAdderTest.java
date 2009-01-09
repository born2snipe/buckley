package org.codeclub.buckley.compile;

import com.lowagie.text.pdf.PdfAcroForm;
import junit.framework.TestCase;
import org.codeclub.buckley.CheckboxField;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class CheckboxFieldAdderTest extends TestCase {

    public void test_add() {
        CheckboxFieldAdder adder = new CheckboxFieldAdder();

        PdfAcroForm form = mock(PdfAcroForm.class);

        CheckboxField field = new CheckboxField("name", "Off", 1, 2, 3, 4);

        adder.add(form, field, 100);

        verify(form).addCheckBox("name", "Off", false, 1, 94, 4, 98);
    }
}
