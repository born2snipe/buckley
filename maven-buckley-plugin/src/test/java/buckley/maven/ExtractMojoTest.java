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
package buckley.maven;

import buckley.Document;
import buckley.extract.Extractor;
import buckley.io.DocumentWriter;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.mockito.Mockito.*;


public class ExtractMojoTest {
    private ExtractMojo mojo;
    private Extractor extractor;
    private File file;
    private Document document;
    private DocumentWriter writer;
    private Log log;

    @Before
    public void setUp() throws Exception {
        extractor = mock(Extractor.class);
        writer = mock(DocumentWriter.class);
        log = mock(Log.class);
        file = new File("test.pdf");

        mojo = new ExtractMojo(extractor, writer);
        mojo.setPdf(file);
        mojo.setLog(log);

        document = new Document();
    }

    @Test
    public void test_specifyTemplateName() throws MojoExecutionException, MojoFailureException {
        when(extractor.extract(file)).thenReturn(document);
        mojo.setTemplate(new File("template.xml"));

        mojo.execute();

        verify(writer).write(new File("template.xml"), document);
        verify(log).info("Extracting template (template.xml) from test.pdf");
    }

    @Test
    public void test_uppercase_filename() throws MojoExecutionException, MojoFailureException {
        file = new File("test.PDF");
        mojo.setPdf(file);
        when(extractor.extract(file)).thenReturn(document);

        mojo.execute();

        verify(writer).write(new File("test.buckley"), document);
        verify(log).info("Extracting template (test.buckley) from test.PDF");
    }

    @Test
    public void test_lowercase_filename() throws MojoExecutionException, MojoFailureException {
        when(extractor.extract(file)).thenReturn(document);

        mojo.execute();

        verify(writer).write(new File("test.buckley"), document);
        verify(log).info("Extracting template (test.buckley) from test.pdf");
    }


}
