package com.example;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Data
//@Builder
public class BoardState {
    private List<Position> positions;

    public BoardState(String boardState) {
        if (boardState.length() != 18) {
            throw new RuntimeException("invalid board-state input");
        }
        if (!boardState.matches("[xWB]*")) {
            throw new RuntimeException("invalid board-state contains invalid characters");
        }

        positions = Stream.of(boardState.split("(?!^)"))
                .map(positionValue -> {
                    if (positionValue.equals("x")) {
                        return Position.EMPTY;
                    } else if (positionValue.equals("W")) {
                        return Position.WHITE;
                    } else {
                        return Position.BLACK;
                    }
                })
                .collect(toList());
    }

    public void set(int i, String w) {
    }
    public List<Position> getPositions()
    {
        return this.positions;
    }
    public BoardState clone()
    {
        BoardState b=new BoardState();
        //List<BoardState> b = new ArrayList<>(Arrays.asList());
        //b.setPositions(List.copyOf(this.getPositions()));
        List<Position> p = new ArrayList<>();
        p.addAll(this.getPositions());
        b.setPositions(p);
        return b;
    }
 BoardState()
 {

 }

    public static enum Position {
        WHITE,
        BLACK,
        EMPTY
    }

}
