package org.codeclub.buckley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Document {
    private List<Page> pages = new ArrayList<Page>();

    public void addPage(Page page) {
        pages.add(page);
    }

    public List<Page> getPages() {
        return Collections.unmodifiableList(pages);
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
