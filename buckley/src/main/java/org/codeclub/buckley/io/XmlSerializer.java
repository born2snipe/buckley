/**
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.codeclub.buckley.io;

import com.thoughtworks.xstream.XStream;
import org.codeclub.buckley.*;
import org.codeclub.buckley.TextField;

import java.awt.*;
import java.io.*;


public class XmlSerializer {
    private XStream xstream = new XStream();

    public XmlSerializer() {
        xstream.alias("textField", TextField.class);
        xstream.alias("checkboxField", CheckboxField.class);
        xstream.alias("page", Page.class);
        xstream.alias("document", Document.class);
        xstream.alias("embeddedFont", EmbeddedFont.class);
        xstream.alias("border", Border.class);
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
        xstream.useAttributeFor(Alignment.class);
        xstream.useAttributeFor(Color.class);
        xstream.registerConverter(new AlignmentValueConverter());
        xstream.registerConverter(new ColorValueConverter());
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

        TextField field = new TextField("field2", 0, 0, 0, 0, "Courier", 12.0f);
        field.setAlignment(Alignment.RIGHT);
        field.setBorder(new Border(1.0f, Color.black));
        field.setBackgroundColor(Color.yellow);

        page1.addField(field);
        page1.addField(new TextField("field3", 0, 0, 0, 0));

        doc.addPage(page1);

        System.out.println(serializer.serialize(doc));
    }

}
