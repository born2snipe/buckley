package org.codeclub.buckley.compile;

import com.lowagie.text.pdf.PdfAcroForm;
import org.codeclub.buckley.Field;


public interface FieldAdder<T extends Field> {
    void add(PdfAcroForm form, T field);
}
