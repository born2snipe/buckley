package org.codeclub.buckley;

import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.BaseFont;
import junit.framework.TestCase;
import org.codeclub.buckley.compile.Compiler;
import org.codeclub.buckley.compile.FontRegistry;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;


public class AddFieldTest extends TestCase {

    public void test_() throws IOException, URISyntaxException {
        File dir = new File("/Users/dan/Desktop");
        File file = new File(dir, "send.pdf");
        File compiled = new File(dir, "send_compiled.pdf");

        Compiler compiler = new org.codeclub.buckley.compile.Compiler(new FontRegistry());

        File compiledPdf = compiler.compile(file, compiled, createTextFieldDocument("blah"));

        PdfReader reader = new PdfReader(new FileInputStream(compiledPdf));
        AcroFields fields = reader.getAcroFields();
        assertNotNull(fields.getField("blah"));
    }

    private Document createTextFieldDocument(String fieldName) {
        Page page = new Page(1);
        page.addField(new TextField(fieldName, 10, 10, 100, 25));
        page.addField(new TextField("blah3", 10, 60, 100, 25, BaseFont.TIMES_ROMAN, 12.0f));
        page.addField(new CheckboxField("blah2", "On", 10, 40, 20, 20));
        Document doc = new Document();
        doc.addPage(page);
        return doc;
    }
}
