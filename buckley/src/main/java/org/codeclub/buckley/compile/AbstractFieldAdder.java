package org.codeclub.buckley.compile;

import com.lowagie.text.pdf.PdfAcroForm;
import org.codeclub.buckley.Field;


public abstract class AbstractFieldAdder<T extends Field> implements FieldAdder<T> {
    public final void add(PdfAcroForm form, T field, float documentHeight) {
        float xOffset = field.getX() + field.getWidth();
        float yOffset = documentHeight - (field.getY() + field.getHeight());
        addField(form, field, field.getX(), yOffset, xOffset, documentHeight - field.getY());
    }

    protected abstract void addField(PdfAcroForm form, T field, float x, float y, float offX, float offY);
}
