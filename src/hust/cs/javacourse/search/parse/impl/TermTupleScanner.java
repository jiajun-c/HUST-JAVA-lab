package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.index.impl.Term;
import hust.cs.javacourse.search.index.impl.TermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleScanner;
import hust.cs.javacourse.search.util.Config;
import hust.cs.javacourse.search.util.StringSplitter;

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
            if(string==null) {
                return null;
            }
            while(string.trim().length()==0) {
                string = input.readLine();
                if(string == null)  return null;
            }
            StringSplitter splitter = new StringSplitter();
            splitter.setSplitRegex(Config.STRING_SPLITTER_REGEX); // Set the split rules
            for (String word:splitter.splitByRegex(string)) {
                TermTuple t  = new TermTuple();
                t.curPos = pos;
                if(Config.IGNORE_CASE) {
                    t.term = new Term(word.toLowerCase()); // If it ignores the upper.
                }
                else {
                    t.term = new Term(word);
                }
                buffer.add(t);
                pos++;
            }

        }
        return buffer.poll();
    }
}
