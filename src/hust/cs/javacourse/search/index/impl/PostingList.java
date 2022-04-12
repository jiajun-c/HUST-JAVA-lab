package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractPostingList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

public class PostingList extends AbstractPostingList {

    @Override
    public void add(AbstractPosting posting) {
        if(contains(posting)) {
            return;
        }
        this.list.add(posting);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        boolean flag = false;
        for (AbstractPosting posting:list) {
            if(flag) builder.append("->");
            flag = true;
            builder.append(posting.toString());
        }
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public void add(List<AbstractPosting> postings) {
        for (AbstractPosting t : postings) {
            if (!this.list.contains(t)) {
                this.list.add(t);
            }
        }
    }

    @Override
    public AbstractPosting get(int index) {
        return list.get(index);
    }

    @Override
    public int indexOf(AbstractPosting posting) {
        return list.indexOf(posting);
    }

    @Override
    public int indexOf(int docId) {
        for (int i = 0;i < list.size();i++) {
            if (list.get(i).getDocId()==docId) return i;
        }
        return -1;
    }

    @Override
    public boolean contains(AbstractPosting posting) {
        for (AbstractPosting abstractPosting : this.list) {
            if (abstractPosting.getDocId() == posting.getDocId()) {
                return abstractPosting.equals(posting);
            }
        }
        return false;
    }

    @Override
    public void remove(int index) {
        list.remove(index);
    }

    @Override
    public void remove(AbstractPosting posting) {
        list.remove(posting);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void sort() {
        Collections.sort(list);
    }

    @Override
    public void writeObject(ObjectOutputStream out) {
        try {
            out.writeObject(list.size());
            for (AbstractPosting posting : list) {
                posting.writeObject(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        try {
            int size = (Integer) in.readObject();
            for (int i = 0; i < size; i ++) {
                AbstractPosting posting = new Posting();
                posting.readObject(in);
                list.add(posting);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}