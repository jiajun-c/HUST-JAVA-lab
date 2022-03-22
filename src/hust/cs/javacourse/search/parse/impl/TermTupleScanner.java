package hust.cs.javacourse.search.parse.impl;
import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.*;
import hust.cs.javacourse.search.util.*;
import hust.cs.javacourse.search.index.impl.*;
import hust.cs.javacourse.search.index.impl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class TermTupleScanner extends AbstractTermTupleScanner{

    public TermTupleScanner(BufferedReader input) {super(input);}
    Queue<AbstractTermTuple>buffer = new LinkedList<>();
    int pos = 0; // Record the index of the String
    @Override
    public AbstractTermTuple next() throws IOException {
        if(buffer.isEmpty()) {
            String string = input.readLine();
            if(string==null) return null;
            StringSplitter splitter = new StringSplitter();
            splitter.setSplitRegex(Config.STRING_SPLITTER_REGEX); // Set the split rules
            for (String word:splitter.splitByRegex(string)) {

            }
        }
        return buffer.poll();
    }
}
