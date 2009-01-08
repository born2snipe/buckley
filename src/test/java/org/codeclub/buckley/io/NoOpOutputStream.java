package org.codeclub.buckley.io;

import java.io.IOException;
import java.io.OutputStream;


public class NoOpOutputStream extends OutputStream {
    public void write(int i) throws IOException {
    }
}
