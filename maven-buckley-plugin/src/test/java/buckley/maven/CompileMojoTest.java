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
import junit.framework.TestCase;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;

import java.io.File;

import static org.mockito.Mockito.*;


public class CompileMojoTest extends TestCase {
    private Compiler compiler;
    private XmlDocumentHandler serializer;
    private ShuntCompileMojo mojo;
    private Log log;

    protected void setUp() throws Exception {
        super.setUp();
        compiler = mock(Compiler.class);
        serializer = mock(XmlDocumentHandler.class);
        log = mock(Log.class);

        mojo = new ShuntCompileMojo(serializer, compiler);
        mojo.setLog(log);
    }

    public void test_execute_MultipleCompilables() throws MojoFailureException, MojoExecutionException {
        CompilablePdf compilable = new CompilablePdf(new File("template"), new File("xml"), new File("compiled"));
        CompilablePdf compilable2 = new CompilablePdf(new File("template2"), new File("xml2"), new File("compiled2"));

        mojo.setCompilables(new CompilablePdf[]{compilable, compilable2});

        Document document = new Document();
        when(serializer.read(compilable.getXml())).thenReturn(document);

        Document document2 = new Document();
        when(serializer.read(compilable2.getXml())).thenReturn(document2);

        mojo.execute();

        verify(compiler).compile(compilable.getPdfTemplate(), compilable.getCompiledPdf(), document);
        verify(log).info("Compiling template against xml output to compiled");

        verify(compiler).compile(compilable2.getPdfTemplate(), compilable2.getCompiledPdf(), document2);
        verify(log).info("Compiling template2 against xml2 output to compiled2");
    }

    public void test_execute_SingleCompilable() throws MojoFailureException, MojoExecutionException {
        CompilablePdf compilable = new CompilablePdf(new File("template"), new File("xml"), new File("compiled"));

        mojo.setCompilables(new CompilablePdf[]{compilable});

        Document document = new Document();
        when(serializer.read(compilable.getXml())).thenReturn(document);

        mojo.execute();

        verify(compiler).compile(compilable.getPdfTemplate(), compilable.getCompiledPdf(), document);
        verify(log).info("Compiling template against xml output to compiled");
    }

    public void test_execute_NoCompilables() throws MojoFailureException, MojoExecutionException {
        mojo.setCompilables(new CompilablePdf[0]);

        mojo.execute();

        verify(log).info("No pdf compilables given");
        verifyZeroInteractions(compiler, serializer);
    }


    private static class ShuntCompileMojo extends CompileMojo {
        private Compiler compiler;

        private ShuntCompileMojo(XmlDocumentHandler serializer, buckley.compile.Compiler compiler) {
            super(serializer);
            this.compiler = compiler;
        }

        @Override
        protected Compiler initializeCompiler(Document document) {
            return compiler;
        }
    }
}
