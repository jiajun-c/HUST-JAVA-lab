package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.util.Config;

import java.io.IOException;

public class LengthTermTupleFilter extends AbstractTermTupleFilter {

    @Override
    public AbstractTermTuple next() throws IOException {
        AbstractTermTuple t = input.next();
        if(t == null) return null;
        while (t.term.getContent().length() > Config.TERM_FILTER_MAXLENGTH|t.term.getContent().length()<Config.TERM_FILTER_MINLENGTH) {
            t = input.next();
            if(t == null) return null;
        }
        return t;
    }
}
