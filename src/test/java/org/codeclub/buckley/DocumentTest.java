package org.codeclub.buckley;

import junit.framework.TestCase;


public class DocumentTest extends TestCase {
    private Document doc;

    public void test_getPage_SinglePage() {
        Page page = new Page(1);

        doc.addPage(page);

        assertSame(page, doc.getPage(1));
    }

    public void test_getPage_MultiplePages() {
        doc.addPage(new Page(1));
        Page page = new Page(2);
        doc.addPage(page);

        assertSame(page, doc.getPage(2));
    }

    public void test_getPage_PageDoesNotExist() {
        assertNull(doc.getPage(0));
    }

    protected void setUp() throws Exception {
        super.setUp();
        doc = new Document();
    }
}
