package hust.cs.javacourse.search.index.impl;
import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.parse.*;

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
        AbstractDocument document = new Document(docId, docPath);
        AbstractTermTuple tuple = null; // Add the tuple according to the file
//        try {
//           tuple = new AbstractTermTupleScanner(new BufferedReader(
//                    new InputStreamReader(new FileInputStream(file))));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        return document;
    }
}