package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractTerm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class Term extends AbstractTerm {
    public Term() {}
    public Term(String s) {setContent(s);}
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Term) {
            return Objects.equals(this.content,((Term) obj).content);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.content;
    }

    @Override
    public String getContent() {
       return this.content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(AbstractTerm o) {
        return this.content.compareTo(o.getContent());
        /* CompareTo is a function used to check whether two strings is equal
        * If is not equal, it will return the gap of the ASCII .
        * */
    }

    @Override
    public void writeObject(ObjectOutputStream out) {
        try{
            out.writeObject(this.content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* It will write to the file, the method can be found in the FileSerializable */
    }

    @Override
    public void readObject(ObjectInputStream in)  {
        try{
            this.content = (String) in.readObject();
        }
         catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
