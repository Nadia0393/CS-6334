package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateTree {
    public static class Node {

        public List<Node> children = new ArrayList<>();
    }

    public static Node buildDepth0(int value, int depth) {
        return new Node();
    }

    public static Node buildDepth1() {
        Node root = new Node();
        Node c = new Node();
        Node d = new Node();
        root.children.add(c);
        root.children.add(d);
        return root;
    }

    public static Node buildDepth2() {
        Node n=buildDepth1();
        Node n1=buildDepth1();
        Node root = new Node();
        root.children.add(n);
        root.children.add(n1);
        return root;
    }
    public static Node buildDepth3() {
        Node n3=buildDepth2();
        Node n4=buildDepth2();
        Node root = new Node();
        root.children.add(n3);
        root.children.add(n4);
        return root;
    }
    public static Node buildTree(int depth) {
        if(depth==0)
        {
            return new Node();
        }
        else {
            Node root=new Node();
            for(int i = 0; i<new Random().nextInt(100); i++) {
                root.children.add(buildTree(depth - 1));
            }
            return root;
        }


    }

    public static void main(String[] args) {


        Node n5=buildTree(4);
//        Node n3=new Node();
//        n3=buildDepth2(8,2);
//        Node n4=new Node();
//        n4=buildDepth2(10,2);
//        root.children.add(new Node(2));
//        root.children.add(new Node(3));
//        root.children.get(0).children.add(new Node(4));
//        root.children.get(0).children.add(new Node(5));
//        root.children.get(1).children.add(new Node(6));
//        root.children.get(1).children.add(new Node(7));

    }
}
