package org.codeclub.buckley.io;

import com.thoughtworks.xstream.XStream;
import org.codeclub.buckley.Document;
import org.codeclub.buckley.EmbeddedFont;
import org.codeclub.buckley.Page;
import org.codeclub.buckley.TextField;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class XmlSerializer {
    private XStream xstream = new XStream();

    public XmlSerializer() {
        xstream.alias("textField", TextField.class);
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

    public static void main(String args[]) {
        XmlSerializer serializer = new XmlSerializer();

        Document doc = new Document();

        Page page1 = new Page(1);
        page1.addField(new TextField("field1", 0, 0, 0, 0));

        TextField field = new TextField("field2", 0, 0, 0, 0, "Courier", 12);

        page1.addField(field);
        page1.addField(new TextField("field3", 0, 0, 0, 0));

        doc.addPage(page1);

        System.out.println(serializer.serialize(doc));
    }

}
