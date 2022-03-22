# HUST JAVA experiment 
## The Memory-based inverted index
## 1.pre
An inverted index is an index DS storing a mapping from the content.
In the normal time, the index tells your what storaged in this things,
while the inverted index storaged in which place.

The inverted indexes can be divided into two types
- A record-level inverted index, which contains a list of the reference 
to documents for each word
- A word-level inverted index, which additionally contains the positions 
of each word within the document. 

In this experiment, I suggested you using the word-level inverted index.
additionally, we add the frequency to the node.

## 2.The struction
The inverted index is consist of the Dictionary, Posting List, Posting
- Dictionary : The set of all of the terms in the doc. We can sort it according 
to the dictionary 
- Posting List: The link list consist of the post
- Posting: The id of the document where the term appear, the position and the 
frequency.

## 3.The Space usage 
According to the Heaps theorm. If a set of the term contains n terms, the
number of the words is $O(n^{\beta}),\beta: 0.4~0.6$

If we consider all of the English words, just 300 thousan.So the dictionary 
can be storaged in the RAM. After sort them, we can use the binary search to
find the result.

The length of the Posting list can be very big, so it is usually storaged in the
disk. In this lab, the message size is small, so we can storage it directly in
the RAM.

## 4. The API for the project
```shell
❯ tree -L 2
.
├── index
│   ├── AbstractDocumentBuilder.java
│   ├── AbstractDocument.java
│   ├── AbstractIndexBuilder.java
│   ├── AbstractIndex.java
│   ├── AbstractPosting.java
│   ├── AbstractPostingList.java
│   ├── AbstractTerm.java
│   ├── AbstractTermTuple.java
│   ├── FileSerializable.java
│   ├── impl
│   └── package-info.java
├── parse
│   ├── AbstractTermTupleFilter.java
│   ├── AbstractTermTupleScanner.java
│   ├── AbstractTermTupleStream.java
│   ├── impl
│   └── package-info.java
├── query
│   ├── AbstractHit.java
│   ├── AbstractIndexSearcher.java
│   ├── impl
│   ├── package-info.java
│   └── Sort.java
├── run
│   ├── package-info.java
│   ├── TestBuildIndex.java
│   └── TestSearchIndex.java
└── util
    ├── Config.java
    ├── FileUtil.java
    ├── package-info.java
    ├── StopWords.java
    └── StringSplitter.java

```
In the \*.java, they introduce the function and the definition, them you can 
complete them in the impl Dictionary. 


