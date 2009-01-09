package org.codeclub.buckley.maven;

import junit.framework.TestCase;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.codeclub.buckley.Document;
import org.codeclub.buckley.compile.Compiler;
import org.codeclub.buckley.io.XmlSerializer;
import static org.mockito.Mockito.*;

import java.io.File;


public class CompileMojoTest extends TestCase {
    private Compiler compiler;
    private XmlSerializer serializer;
    private ShuntCompileMojo mojo;
    private Log log;

    protected void setUp() throws Exception {
        super.setUp();
        compiler = mock(Compiler.class);
        serializer = mock(XmlSerializer.class);
        log = mock(Log.class);

        mojo = new ShuntCompileMojo(serializer, compiler);
        mojo.setLog(log);
    }

    public void test_execute_MultipleCompilables() throws MojoFailureException, MojoExecutionException {
        CompilablePdf compilable = new CompilablePdf(new File("template"), new File("xml"), new File("compiled"));
        CompilablePdf compilable2 = new CompilablePdf(new File("template2"), new File("xml2"), new File("compiled2"));

        mojo.setCompilables(new CompilablePdf[]{compilable, compilable2});

        Document document = new Document();
        when(serializer.deserialize(compilable.getXml())).thenReturn(document);

        Document document2 = new Document();
        when(serializer.deserialize(compilable2.getXml())).thenReturn(document2);

        mojo.execute();

        verify(serializer).deserialize(compilable.getXml());
        verify(compiler).compile(compilable.getPdfTemplate(), compilable.getCompiledPdf(), document);
        verify(log).info("Compiling template against xml output to compiled");

        verify(serializer).deserialize(compilable2.getXml());
        verify(compiler).compile(compilable2.getPdfTemplate(), compilable2.getCompiledPdf(), document2);
        verify(log).info("Compiling template2 against xml2 output to compiled2");
    }

    public void test_execute_SingleCompilable() throws MojoFailureException, MojoExecutionException {
        CompilablePdf compilable = new CompilablePdf(new File("template"), new File("xml"), new File("compiled"));

        mojo.setCompilables(new CompilablePdf[]{compilable});

        Document document = new Document();
        when(serializer.deserialize(compilable.getXml())).thenReturn(document);

        mojo.execute();

        verify(serializer).deserialize(compilable.getXml());
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

        private ShuntCompileMojo(XmlSerializer serializer, Compiler compiler) {
            super(serializer);
            this.compiler = compiler;
        }

        @Override
        protected org.codeclub.buckley.compile.Compiler initializeCompiler(Document document) {
            return compiler;
        }
    }
}
