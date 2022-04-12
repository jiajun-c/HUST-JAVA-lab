package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.parse.impl.LengthTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.PattenTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.StopWordTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.TermTupleScanner;

import java.io.*;

public class DocumentBuilder extends AbstractDocumentBuilder{
    @Override
    public AbstractDocument build(int docId, String docPath, AbstractTermTupleStream termTupleStream) throws IOError, IOException {
        // Using the IOError to catch the I/O error
        AbstractDocument document = new Document(docId,docPath);
        AbstractTermTuple tuple = termTupleStream.next();
        while (tuple!=null) {
            document.addTuple(tuple);
            tuple = termTupleStream.next();
        }
        // add all the tuples into the document
        termTupleStream.close();
        return document;
    }

    @Override
    public AbstractDocument build(int docId, String docPath, File file) {
        AbstractDocument document = null;
        AbstractTermTupleStream ts = null; // Add the tuple according to the file
        try {
            ts = new TermTupleScanner(new BufferedReader(new InputStreamReader(new FileInputStream(file))));
            ts = new StopWordTermTupleFilter(ts); //The stop words
            ts = new LengthTermTupleFilter(ts);    // The length of the word
            ts = new PattenTermTupleFilter(ts);     // The rgx
            document = build(docId,docPath,ts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            ts.close();
        }
        // The pre_work of it is the parse, so we need to build it first
        return document;
    }
}
