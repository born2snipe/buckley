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
        TextField.Font font = field.getFont();
        BaseFont pdfFont;
        float fontSize;
        if (font != null) {
            pdfFont = fontRegistry.getFont(font.getName());
            fontSize = font.getSize();
        } else {
            pdfFont = fontRegistry.getDefaultFont();
            fontSize = fontRegistry.getDefaultFontSize();
        }
        form.addSingleLineTextField(field.getName(), "", pdfFont, fontSize, field.getX(), field.getY(), field.getWidth(), field.getHeight());
    }
}
