package org.codeclub.buckley;

import com.lowagie.text.pdf.PdfReader;
import junit.framework.TestCase;
import org.codeclub.buckley.compile.Compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;


public class AddFieldTest extends TestCase {

    public void test_() throws IOException, URISyntaxException {
        File dir = new File("/Users/dan/Desktop");
        File file = new File(dir, "send.pdf");
        File compiled = new File(dir, "send_compiled.pdf");

        Compiler compiler = new org.codeclub.buckley.compile.Compiler();

        File compiledPdf = compiler.compile(file, compiled, createTextFieldDocument("blah"));

        PdfReader reader = new PdfReader(new FileInputStream(compiledPdf));
        assertNotNull(reader.getAcroForm().getField("blah"));
    }

    private Document createTextFieldDocument(String fieldName) {
        Page page = new Page(1);
        page.addField(new TextField(fieldName, 10, 10, 100, 20));
        Document doc = new Document();
        doc.addPage(page);
        return doc;
    }
}
