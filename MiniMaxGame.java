package com.example;

import java.io.IOException;
import java.util.List;

import static com.example.Main.generateMovesMidgameEndgame;

public class MiniMaxGame {
    public static Node generateTree(BoardState boardState, int depth) {
        if (depth == 0) {
            Node root = new Node();
            root.boardState = boardState;
            return root;
        } else {
            Node root = new Node();
            root.boardState = boardState;
            List<BoardState> b = generateMovesMidgameEndgame(boardState);
            for (int i = 0; i < b.size(); i++) {
                BoardState bs = b.get(i);
                //BoardState.Position nextPlayer = switchPlayer(currentPlayer);
                Node c = generateTree(bs, depth - 1);
                root.children.add(c);
            }
            return root;
        }
    }
    public static void main(String[] args) throws IOException {
        BoardState boardState = new BoardState("xWxxxxxWxxWBBBBxxB");
        Node n2 = generateTree(boardState, 1);
    }
}
