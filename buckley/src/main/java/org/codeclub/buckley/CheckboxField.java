package org.codeclub.buckley;


public class CheckboxField extends Field {
    private String value;

    public CheckboxField(String name, String value, float x, float y, float width, float height) {
        super(name, x, y, width, height);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
