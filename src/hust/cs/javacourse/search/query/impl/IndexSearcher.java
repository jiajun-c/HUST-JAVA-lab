package hust.cs.javacourse.search.query.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractPostingList;
import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.impl.Index;
import hust.cs.javacourse.search.query.AbstractHit;
import hust.cs.javacourse.search.query.AbstractIndexSearcher;
import hust.cs.javacourse.search.query.Sort;
import hust.cs.javacourse.search.util.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class IndexSearcher extends AbstractIndexSearcher {
    @Override
    public void open(String indexFile) {
        try {
            this.index.load(new File(indexFile));
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    @Override
    public AbstractHit[] search(AbstractTerm queryTerm, Sort sorter) {
        if(Config.IGNORE_CASE) {
            queryTerm.setContent(queryTerm.getContent().toLowerCase(Locale.ROOT));
        }
        AbstractPostingList indexSearch = index.search(queryTerm);
        if(indexSearch==null) {
            return new Hit[0];
        }
        List<AbstractHit> result = new ArrayList<>();
        for (int i = 0;i < indexSearch.size();i++) {
            AbstractPosting posting = indexSearch.get(i);
            AbstractHit hit = new Hit(posting.getDocId(), index.getDocName(posting.getDocId()));
            hit.getTermPostingMapping().put(queryTerm,posting);
            hit.setScore(sorter.score(hit));
            result.add(hit);
        }

        sorter.sort(result);
        AbstractHit[] Result = new AbstractHit[result.size()];
        return result.toArray(Result);
    }

    @Override
    public AbstractHit[] search(AbstractTerm queryTerm1, AbstractTerm queryTerm2, Sort sorter, LogicalCombination combine) {
        AbstractPostingList indexSearchResult1= index.search(queryTerm1);
        AbstractPostingList indexSearchResult2 = index.search(queryTerm2);
        if (indexSearchResult1 == null && indexSearchResult2 == null) {
            return new Hit[0];
        }
        // if two of them are both null
        List<AbstractHit> result = new ArrayList<>();
        if (combine==LogicalCombination.ADN) {
            if(indexSearchResult1 == null || indexSearchResult2 == null) {
                return new Hit[0];
            }
            // If any one of them is the null
            for (int i = 0; i < Objects.requireNonNull(indexSearchResult1).size(); i++) {
                int docId = indexSearchResult1.get(i).getDocId();
                int sub_index = indexSearchResult2.indexOf(docId);
                if (sub_index != -1) {
                    AbstractHit hit = new Hit(docId, index.getDocName(docId));
                    hit.getTermPostingMapping().put(queryTerm1, indexSearchResult1.get(i));
                    hit.getTermPostingMapping().put(queryTerm2, indexSearchResult2.get(sub_index));
                    hit.setScore(sorter.score(hit));
                    result.add(hit);
                }
            }
        }
        else if(combine == LogicalCombination.OR) {
            if(indexSearchResult1==null){
                return search(queryTerm2,sorter);
            }
            if(indexSearchResult2 == null) {
                return search(queryTerm1,sorter);
            }
            for (int i = 0;i < indexSearchResult1.size();i++) {
               int docId = indexSearchResult1.get(i).getDocId(); 
               int sub_index = indexSearchResult2.indexOf(docId);
               // If it do not exist in the result2
               if (sub_index==-1) {
                   AbstractHit hit = new Hit(docId,index.getDocName(docId));
                   hit.getTermPostingMapping().put(queryTerm1, indexSearchResult1.get(i));
                   hit.setScore(sorter.score(hit));
                   result.add(hit);
               }
               else {
                   AbstractHit hit = new Hit(docId, index.getDocName(docId));
                   hit.getTermPostingMapping().put(queryTerm1, indexSearchResult1.get(i));
                   hit.getTermPostingMapping().put(queryTerm2, indexSearchResult2.get(sub_index));
                   hit.setScore(sorter.score(hit));
                   result.add(hit);
               }
            }
        }
        sorter.sort(result);
        AbstractHit[] Result = new AbstractHit[result.size()];
        return result.toArray(Result);
    }
}
