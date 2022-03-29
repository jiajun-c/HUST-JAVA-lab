package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.util.Config;

import java.io.IOException;
/*
* This function is used to filter the things that don't match the regx
* */
public class PattenTermTupleFilter extends AbstractTermTupleFilter {
    @Override
    public AbstractTermTuple next() throws IOException {
        AbstractTermTuple t = input.next();
        if(t == null){
            return null;
        }
        while (!t.term.getContent().matches(Config.TERM_FILTER_PATTERN)) {
            t = input.next();
            if(t == null) {
                return null;
            }
        }
        return t;
    }
}
