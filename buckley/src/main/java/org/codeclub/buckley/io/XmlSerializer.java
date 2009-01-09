package org.codeclub.buckley.io;

import com.thoughtworks.xstream.XStream;
import org.codeclub.buckley.*;

import java.io.*;


public class XmlSerializer {
    private XStream xstream = new XStream();

    public XmlSerializer() {
        xstream.alias("textField", TextField.class);
        xstream.alias("checkboxField", CheckboxField.class);
        xstream.alias("page", Page.class);
        xstream.alias("document", Document.class);
        xstream.alias("embeddedFont", EmbeddedFont.class);
        xstream.useAttributeFor(int.class);
        xstream.useAttributeFor(float.class);
        xstream.useAttributeFor(long.class);
        xstream.useAttributeFor(double.class);
        xstream.useAttributeFor(boolean.class);
        xstream.useAttributeFor(String.class);
        xstream.useAttributeFor(Double.class);
        xstream.useAttributeFor(Integer.class);
        xstream.useAttributeFor(Float.class);
        xstream.useAttributeFor(Long.class);
    }

    public String serialize(Document document) {
        return xstream.toXML(document);
    }

    public void serialize(Document document, OutputStream output) {
        xstream.toXML(document, output);
    }

    public Document deserialize(String xml) {
        return deserialize(new ByteArrayInputStream(xml.getBytes()));
    }

    public Document deserialize(InputStream input) {
        return (Document) xstream.fromXML(input);
    }

    public Document deserialize(File file) {
        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
            return (Document) xstream.fromXML(input);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {

                }
            }
        }
    }

    public static void main(String args[]) {
        XmlSerializer serializer = new XmlSerializer();

        Document doc = new Document();

        doc.getFontRegistry().registerFont(new EmbeddedFont("Blah", "Blah", new byte[]{1, 2, 3, 4}));

        Page page1 = new Page(1);
        page1.addField(new TextField("field1", 0, 0, 0, 0));

        TextField field = new TextField("field2", 0, 0, 0, 0, "Courier", 12);

        page1.addField(field);
        page1.addField(new TextField("field3", 0, 0, 0, 0));

        doc.addPage(page1);

        System.out.println(serializer.serialize(doc));
    }

}
