package com.example;

import java.io.IOException;
import java.util.List;

import static com.example.Main.generateMovesOpening;

public class MiniMaxOpening {

//    public static Node generateTree(BoardState boardState, int depth, BoardState.Position currentPlayer) {
//        Node root = new Node();
//        Node n=new Node();
//        Node t=new Node();
//        root.boardState = boardState;
//        if (depth == 0) {
//            root.value = getEstimation(boardState);
//            root.nodesCount++;
//            return root;
//        }
//        //Node q = new Node();
////        root.value = (currentPlayer == BoardState.Position.WHITE) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
////        List<BoardState> b = (currentPlayer == BoardState.Position.WHITE)?generateMovesOpening(boardState, switchPlayer(currentPlayer)):generateMovesOpening(boardState,currentPlayer);
//
//        if (currentPlayer == BoardState.Position.WHITE) {
//
//            n.boardState = boardState;
//            List<BoardState> b = generateMovesOpening(boardState,currentPlayer);
//            n.boardState = boardState;
//            n.value=Integer.MIN_VALUE;
//            for (int i = 0; i < b.size(); i++) {
//                BoardState bs = b.get(i);
//                Node q = generateTree(bs, depth - 1, switchPlayer(currentPlayer));
//
//                n.nodesCount += q.nodesCount;
//                if (q.value > n.value) {
//                    n.value = q.value;
//                }
//                //root.boardState=boardState;
//                n.children.add(q);
//
//            }
//            return n;
//
//        }
//        else
//        {
//            List<BoardState> c = generateMovesOpening(boardState,currentPlayer);
//            t.boardState = n.boardState;
//            t.value=Integer.MAX_VALUE;
//            for (int i = 0; i < c.size(); i++) {
//                BoardState bs = c.get(i);
//                Node q = generateTree(bs, depth - 1, switchPlayer(currentPlayer));
//
//                t.nodesCount += q.nodesCount;
//                if (q.value < t.value) {
//                    t.value = q.value;
//                }
//                //root.boardState = boardState;
//                root.children.add(q);
//
//            }
//
//        }
//        return t;
//    }

    public static Node MiniMax(BoardState boardState,int depth,BoardState.Position currentPlayer){
        Node output = new Node();

        if(depth == 0){
            output.value = getEstimation(boardState);
            output.boardState=boardState;
            output.nodesCount++;
            return output;
        }

        Node in = new Node();
        //BoardState.Position currentPlayer=currentPlayer;
        List<BoardState> nextMoves = (currentPlayer == BoardState.Position.WHITE) ? generateMovesOpening(boardState) : generateMovesOpening(boardState,switchPlayer(BoardState.Position.WHITE));
        output.value = (currentPlayer == BoardState.Position.WHITE) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i=0;i< nextMoves.size();i++){
            if (currentPlayer == BoardState.Position.WHITE){

                in = MiniMax(nextMoves.get(i),depth - 1, switchPlayer(currentPlayer));
                output.nodesCount += in.nodesCount;
                if (in.value > output.value){
                    output.value = in.value;
                    output.boardState = nextMoves.get(i);
                }
            }
            else{
                in = MiniMax(nextMoves.get(i),depth -1 ,switchPlayer(currentPlayer));
                output.nodesCount += in.nodesCount;
                output.nodesCount++;
                if (in.value < output.value){
                    output.value = in.value;
                    output.boardState = nextMoves.get(i);
                }
            }
        }
        return output;
    }
    public static Integer getEstimation(BoardState boardState) {
        //boardState.setPositions(Arrays.asList(BoardState.Position.WHITE, BoardState.Position.BLACK, BoardState.Position.WHITE, BoardState.Position.WHITE, BoardState.Position.BLACK, BoardState.Position.EMPTY, BoardState.Position.WHITE, BoardState.Position.EMPTY, BoardState.Position.WHITE, BoardState.Position.BLACK, BoardState.Position.WHITE, BoardState.Position.BLACK, BoardState.Position.WHITE, BoardState.Position.WHITE, BoardState.Position.BLACK, BoardState.Position.WHITE, BoardState.Position.EMPTY, BoardState.Position.EMPTY));
        Long numWhitePieces = boardState.getPositions().stream()
                .filter(BoardState.Position.WHITE::equals)
                .count();
        Long numBlackPieces = boardState.getPositions().stream()
                .filter(BoardState.Position.BLACK::equals)
                .count();

        int v=0;

        v = Math.toIntExact((numWhitePieces - numBlackPieces));
        return v;

    }
    private static BoardState.Position switchPlayer(BoardState.Position currentPlayer) {
        if (currentPlayer == BoardState.Position.WHITE) {
            return BoardState.Position.BLACK;

        } else {
            return BoardState.Position.WHITE;
        }
    }


    public static void main(String[] args) throws IOException {
        BoardState boardState = new BoardState("WxxxxWxxBxxxxBBxxx");
        Node n2 = MiniMax(boardState, 9, BoardState.Position.WHITE);
    }

}
