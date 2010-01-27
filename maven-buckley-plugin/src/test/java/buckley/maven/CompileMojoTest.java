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
import buckley.compile.Compiler;
import buckley.io.XmlDocumentHandler;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.mockito.Mockito.*;


public class CompileMojoTest {
    private Compiler compiler;
    private XmlDocumentHandler serializer;
    private CompileMojo mojo;
    private Log log;

    @Before
    public void setUp() throws Exception {
        compiler = mock(Compiler.class);
        serializer = mock(XmlDocumentHandler.class);
        log = mock(Log.class);

        mojo = new CompileMojo(serializer, compiler);
        mojo.setLog(log);
    }

    @Test
    public void test() throws MojoExecutionException, MojoFailureException {
        Document document = new Document();
        File buckleyFile = new File("form.buckley");
        File templatePdf = new File("template.pdf");

        mojo.setBuckleyFile(buckleyFile);
        mojo.setPdfTemplate(templatePdf);

        when(serializer.read(buckleyFile)).thenReturn(document);

        mojo.execute();

        verify(compiler).compile(templatePdf, new File("template_compiled.pdf"), document);
    }
}
