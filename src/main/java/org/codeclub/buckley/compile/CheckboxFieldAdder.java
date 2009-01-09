package org.codeclub.buckley.compile;

import com.lowagie.text.pdf.PdfAcroForm;
import org.codeclub.buckley.CheckboxField;


public class CheckboxFieldAdder extends AbstractFieldAdder<CheckboxField> {

    protected void addField(PdfAcroForm form, CheckboxField field, float x, float y, float offX, float offY) {
        form.addCheckBox(field.getName(), field.getValue(), false, x, y, offX, offY);
    }
}
