package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.StopWords;

import java.io.IOException;
import java.util.Arrays;

/*
* This is the class used to filter the stop used word
*
*/
public class StopWordTermTupleFilter extends AbstractTermTupleFilter {
    public StopWordTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }
    @Override
    public AbstractTermTuple next() throws IOException {
        AbstractTermTuple termTuple = input.next();
        if (termTuple == null) {
            return null;
        }
        while (Arrays.asList(StopWords.STOP_WORDS).contains(termTuple.term.getContent())) {
            termTuple = input.next();
            if (termTuple == null) return null;
        }
        return termTuple;
    }
}