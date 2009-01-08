package org.codeclub.buckley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Page {
    private int number;
    private List<Field> fields = new ArrayList<Field>();

    public Page(int number) {
        this.number = number;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public List<Field> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public int getNumber() {
        return number;
    }
}
