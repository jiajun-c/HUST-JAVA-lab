package hust.cs.javacourse.search.query.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.query.AbstractHit;
import javax.print.DocPrintJob;
import java.util.Map;

public class Hit extends AbstractHit {
    public Hit(int docId, String docName) {
        super(docId,docName);
    }
    public Hit(int docId, String docPath, Map<AbstractTerm, AbstractPosting> termPostingMapping){
        super(docId, docPath, termPostingMapping);
    }
    @Override
    public int getDocId() {
        return docId;
    }

    @Override
    public String getDocPath() {
        return docPath;
    }


    @Override
    public String getContent() {
        return content.toString();
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public double getScore() {
        return score;
    }

    @Override
    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public Map<AbstractTerm, AbstractPosting> getTermPostingMapping() {
        return termPostingMapping;
    }

    @Override
    public String toString() {
        String contentHighlight = content;
        for(AbstractTerm key : this.termPostingMapping.keySet()){
            String regex = key.getContent();
            contentHighlight = contentHighlight.replaceAll(regex, "\033[47;4m" + regex + "\033[0m");
        }
        return "\ndocPath:" + this.docPath + "\ncontent:" + contentHighlight + "\npost:" + this.termPostingMapping.values();
    }

    @Override
    public int compareTo(AbstractHit o) {
        return (int) (getScore() - o.getScore());
    }
}
