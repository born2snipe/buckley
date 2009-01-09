package org.codeclub.buckley.compile;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfAcroForm;
import junit.framework.TestCase;
import org.codeclub.buckley.TextField;
import static org.mockito.Mockito.*;

import java.io.IOException;


public class TextFieldAdderTest extends TestCase {
    private PdfAcroForm form;
    private FontRegistry fontRegistry;
    private TextFieldAdder adder;
    private BaseFont font;

    public void test_add_NoFont() throws DocumentException, IOException {
        TextField field = new TextField("name", 1, 2, 3, 4);

        when(fontRegistry.getDefaultFont()).thenReturn(font);
        when(fontRegistry.getDefaultFontSize()).thenReturn(12.0f);

        adder.add(form, field, 100);

        verify(form).addSingleLineTextField(field.getName(), "", font, 12.0f, 1.0f, 94.0f, 4.0f, 98.0f);
        verify(fontRegistry).getDefaultFont();
        verify(fontRegistry).getDefaultFontSize();
    }
    
    public void test_add_SpecificFont() throws DocumentException, IOException {
        TextField field = new TextField("name", 1, 2, 3, 4, "Courier", 12);

        when(fontRegistry.getFont("Courier")).thenReturn(font);

        adder.add(form, field, 100);

        verify(form).addSingleLineTextField(field.getName(), "", font, 12.0f, 1.0f, 94.0f, 4.0f, 98.0f);
        verify(fontRegistry).getFont("Courier");
    }

    protected void setUp() throws Exception {
        super.setUp();
        form = mock(PdfAcroForm.class);
        fontRegistry = mock(FontRegistry.class);
        adder = new TextFieldAdder(fontRegistry);
        font = BaseFont.createFont();
    }
}
