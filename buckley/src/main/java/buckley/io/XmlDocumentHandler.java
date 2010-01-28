/**
 * Copyright 2008-2010 the original author or authors.
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
package buckley.io;

import buckley.*;
import com.thoughtworks.xstream.XStream;

import java.awt.*;
import java.io.*;


public class XmlDocumentHandler implements DocumentReader, DocumentWriter {
    private XStream xstream = new XStream();

    public XmlDocumentHandler() {
        xstream.alias("text", buckley.TextField.class);
        xstream.alias("checkbox", CheckboxField.class);
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

    public void write(OutputStream output, Document document) {
        xstream.toXML(document, output);
    }

    public void write(File file, Document document) {
        OutputStream output = null;
        try {
            output = new FileOutputStream(file);
            write(output, document);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {

                }
            }
        }
    }

    public Document read(File file) {
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            return read(input);
        } catch (IOException err) {
            throw new RuntimeException(err);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {

                }
            }
        }
    }

    public Document read(InputStream input) {
        return (Document) xstream.fromXML(input);
    }

}
