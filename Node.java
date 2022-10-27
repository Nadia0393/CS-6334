package com.example;

import java.util.ArrayList;
import java.util.List;

public class Node {


    //public List<BoardState> a;
    boolean isLeaf;
    public int value;
    public int nodesCount;
    public List<Node> children=new ArrayList<>();;
    int depth;
    public BoardState boardState;


    //private Map<Move,Node> children;

}
