package hust.cs.javacourse.search.query.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.query.AbstractHit;
import hust.cs.javacourse.search.query.Sort;
/**
 * sort the result
 */
public class SimpleSorter implements Sort{

     /**
     * sort according to the score of the doc.
     * @param hits ：the set of the result
     */
    @Override
    public void sort(List<AbstractHit> hits) {        
        Collections.sort(hits,Comparator.reverseOrder());
    }
    /**
     * get the score of the result
     * @param hits : the set of the result
     * @return : the score
     */
    @Override
    public double score(AbstractHit hit) {
        double score = 0;
        Set<AbstractTerm>keys = hit.getTermPostingMapping().keySet(); // get the keyset of the result 
        Iterator iter = keys.iterator();
        while(iter.hasNext()) {
            score += hit.getTermPostingMapping().get(iter.next()).getFreq();
        }
        return score;
    }
    
}
