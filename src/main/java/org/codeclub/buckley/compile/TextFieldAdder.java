package org.codeclub.buckley.compile;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfAcroForm;
import org.codeclub.buckley.TextField;


public class TextFieldAdder extends AbstractFieldAdder<TextField> {
    private FontRegistry fontRegistry;

    public TextFieldAdder(FontRegistry fontRegistry) {
        this.fontRegistry = fontRegistry;
    }

    protected void addField(PdfAcroForm form, TextField field, float x, float y, float offX, float offY) {
        BaseFont pdfFont;
        float fontSize;
        if (field.getFontName() != null) {
            pdfFont = fontRegistry.getFont(field.getFontName());
            fontSize = field.getFontSize();
        } else {
            pdfFont = fontRegistry.getDefaultFont();
            fontSize = fontRegistry.getDefaultFontSize();
        }
        form.addSingleLineTextField(field.getName(), "", pdfFont, fontSize, x, y, offX, offY);
    }
}
