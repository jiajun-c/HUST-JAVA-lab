package hust.cs.javacourse.search.index.impl;
import hust.cs.javacourse.search.index.*;

import java.util.Objects;

public class TermTuple extends AbstractTermTuple{

    public TermTuple() {}
    public TermTuple(AbstractTerm term,int pos) {
        this.curPos = pos;
        this.term = term;
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TermTuple)) {return false;}
        if(((TermTuple) obj).curPos!=this.curPos) {return false;}
        return Objects.equals(this.term, ((TermTuple) obj).term);
    }

    @Override
    public String toString() {
        return "{Term: " + term + ":CurPos" + curPos +",Freq:" + freq + "}\n";
    }
}
