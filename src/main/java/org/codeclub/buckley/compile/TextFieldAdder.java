package org.codeclub.buckley.compile;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfAcroForm;
import org.codeclub.buckley.TextField;


public class TextFieldAdder implements FieldAdder<TextField> {
    private FontRegistry fontRegistry;

    public TextFieldAdder(FontRegistry fontRegistry) {
        this.fontRegistry = fontRegistry;
    }

    public void add(PdfAcroForm form, TextField field) {
        BaseFont pdfFont;
        float fontSize;
        if (field.getFontName() != null) {
            pdfFont = fontRegistry.getFont(field.getFontName());
            fontSize = field.getFontSize();
        } else {
            pdfFont = fontRegistry.getDefaultFont();
            fontSize = fontRegistry.getDefaultFontSize();
        }
        form.addSingleLineTextField(field.getName(), "", pdfFont, fontSize, field.getX(), field.getY(), field.getWidth(), field.getHeight());
    }
}
