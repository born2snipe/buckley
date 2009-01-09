package org.codeclub.buckley.compile;

import com.lowagie.text.pdf.PdfAcroForm;
import org.codeclub.buckley.CheckboxField;


public class CheckboxFieldAdder implements FieldAdder<CheckboxField> {
    public void add(PdfAcroForm form, CheckboxField field) {
        form.addCheckBox(field.getName(), null, false, field.getX(), field.getY(), field.getWidth(), field.getHeight());
    }
}
