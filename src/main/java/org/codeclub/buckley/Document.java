package org.codeclub.buckley;

import org.codeclub.buckley.compile.FontRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Document {
    private List<Page> pages = new ArrayList<Page>();
    private FontRegistry fontRegistry = new FontRegistry();

    public void addPage(Page page) {
        pages.add(page);
    }

    public List<Page> getPages() {
        return Collections.unmodifiableList(pages);
    }

    public FontRegistry getFontRegistry() {
        return fontRegistry;
    }

    public Page getPage(int pageNumber) {
        for (Page page : pages) {
            if (page.getNumber() == pageNumber) {
                return page;
            }
        }
        return null;
    }
}
