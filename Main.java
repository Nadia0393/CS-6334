package com.example;

import com.example.staticestimationfunction.DumbStaticEstimationFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BoardState boardState = new BoardState("xxxxxxxWxxWBBBBxxB");
        List L=generateMovesOpening(boardState);
        DumbStaticEstimationFunction dumbStaticEstimationFunction = new DumbStaticEstimationFunction();
        Long estimation = dumbStaticEstimationFunction.getEstimation(boardState, GameStage.OPENING);
        //System.out.println(L);
    }

    /**
     * boardState = [x,x,x,x,x,x,x,W,x,x,W,B,B,B,B,x,x,B,x,x,x]
     * List<BoardState> =[W,x,x,x,x,x,x,W,x,x,W,B,B,B,B,x,x,B,x,x,x],[x,W,x,x,x,x,x,W,x,x,W,B,B,B,B,x,x,B,x,x,x],[W,x,x,x,x,x,x,W,x,x,W,B,B,B,B,x,x,B,x,x,x]
     *
     * @param boardState
     * @return
     */
    public static List<BoardState> generateMovesOpening(BoardState boardState, BoardState.Position position)
    {
        List<BoardState> L = generateAdd(boardState, position);
        return L;
    }
    public static List<BoardState> generateMovesOpening(BoardState boardState)
    {
        List<BoardState> L = generateAdd(boardState, BoardState.Position.WHITE);
        return L;
    }
    public static List<BoardState> generateMovesMidgameEndgame(BoardState boardState)
    {
        List<BoardState.Position> boardStates=boardState.getPositions();
        if(boardState.getPositions().stream()
            .filter(BoardState.Position.WHITE::equals)
            .count()==3)
        {
            List<BoardState> H=generateHopping(boardState,boardStates);
            return H;
        }
        else
        {
            List<BoardState> M=generateMove(boardState,boardStates);
            return M;
        }
    }
    public static List<BoardState> generateAdd(BoardState boardState, BoardState.Position position) {
        // TODO implement generateAdd method
        List<BoardState> L = new ArrayList<>(Arrays.asList());
        for (int i=0;i<17;i++)
        {
            if(boardState.getPositions().get(i).equals(BoardState.Position.EMPTY))
            {
                BoardState b=  boardState.clone();
                b.getPositions().set(i,position);
                if(closeMill(i,b))
                {
                    generateRemove(b,L);
                }
                else
                {
                    L.add(b);
                }
            }
        }

        return L;
    }
    public static List<BoardState> generateRemove(BoardState boardState, List<BoardState> boardStates) {
        // TODO implement generateRemove method

        for(int i=0;i<=17;i++)
        {
            if(boardState.getPositions().get(i).equals(BoardState.Position.BLACK))
            {
                if(!closeMill(i,boardState))
                {
                    BoardState b=boardState.clone();
                    b.getPositions().set(i, BoardState.Position.EMPTY);
                    boardStates.add(b);
                }


            }
        }
        return boardStates;
    }

    public static List<BoardState> generateMove(BoardState boardState, List<BoardState.Position> boardStates)
    {
        List<BoardState> L = new ArrayList<>(Arrays.asList());
        for(int i=0;i<17;i++)
        {
            if(boardState.getPositions().get(i).equals(BoardState.Position.WHITE))
            {
                List<Integer> n=getNeighbours(i);
                for(int j=0;j<n.size();j++)
                {
                    if(boardState.getPositions().get(j)==BoardState.Position.EMPTY)
                    {
                        BoardState b=boardState.clone();
                        b.getPositions().set(i,BoardState.Position.EMPTY);
                        b.getPositions().set(j,BoardState.Position.WHITE);
                        if(closeMill(j,b))
                        {
                            generateRemove(b,L);
                        }
                        else
                        {
                            L.add(b);
                        }

                    }
                }
            }
        }
        return L;
    }
public static List<BoardState> generateHopping(BoardState boardState, List<BoardState.Position> boardStates)
{
    List<BoardState> L = new ArrayList<>(Arrays.asList());
    for(int i=0;i<17;i++)
    {
        if(boardState.getPositions().get(i).equals(BoardState.Position.WHITE))
        {
            for(int j=0;j<17;j++)
            {
                if(boardState.getPositions().get(j).equals(BoardState.Position.EMPTY))
                {
                   BoardState b=boardState.clone();
                   b.getPositions().set(i,BoardState.Position.EMPTY);
                   b.getPositions().set(j,BoardState.Position.WHITE);
                   if(closeMill(j,b))
                   {
                       generateRemove(b,L);
                   }
                   else
                   {
                       L.add(b);
                   }
                }
            }
        }
    }
    return L;
}

    public static Boolean closeMill(int j, BoardState boardState) {
        // TODO implement  method
        Boolean check=false;
        BoardState.Position C = boardState.getPositions().get(j);
        while(!C.equals(BoardState.Position.EMPTY))
        {
        switch (j) {
            case 0:
                if (C.equals(boardState.getPositions().get(2)) && boardState.getPositions().get(4).equals(C)) {
                    return true;
                } else {
                    return false;

                }
            case 1:
                if (boardState.getPositions().get(3).equals(C) && boardState.getPositions().get(5).equals(C) || boardState.getPositions().get(8).equals(C) && boardState.getPositions().get(17).equals(C)) {
                    return true;
                } else {
                    return false;

                }
            case 2:
                if (boardState.getPositions().get(7).equals(C) && boardState.getPositions().get(15).equals(C) || boardState.getPositions().get(4).equals(C) && boardState.getPositions().get(0).equals(C)) {
                    return true;
                } else {
                    return false;

                }
            case 3:
                if (boardState.getPositions().get(0).equals(C) && boardState.getPositions().get(4).equals(C)) {
                    return true;
                } else {
                    return false;
                }
            case 4:
                if (boardState.getPositions().get(2).equals(C) && boardState.getPositions().get(0).equals(C)) {
                    return true;
                } else {
                    return false;

                }
            case 5:
                if (boardState.getPositions().get(6).equals(C) && boardState.getPositions().get(11).equals(C) || boardState.getPositions().get(3).equals(C) && boardState.getPositions().get(1).equals(C)) {
                    return true;
                } else {
                    return false;

                }
            case 6:
                if (boardState.getPositions().get(7).equals(C) && boardState.getPositions().get(8).equals(C) || boardState.getPositions().get(11).equals(C) && boardState.getPositions().get(5).equals(C)) {
                    return true;
                } else {
                    return false;

                }
            case 7:
                if (boardState.getPositions().get(6).equals(C) && boardState.getPositions().get(8).equals(C) || boardState.getPositions().get(14).equals(C) && boardState.getPositions().get(3).equals(C)) {
                    return true;
                } else {
                    return false;

                }
            case 8:
                if (boardState.getPositions().get(17).equals(C) && boardState.getPositions().get(1).equals(C) || boardState.getPositions().get(7).equals(C) && boardState.getPositions().get(6).equals(C)) {
                    return true;
                } else {
                    return false;

                }
            case 9:
                if (boardState.getPositions().get(10).equals(C) && boardState.getPositions().get(11).equals(C) || boardState.getPositions().get(12).equals(C) && boardState.getPositions().get(15).equals(C)) {
                    return true;
                } else {
                    return false;
                }
            case 10:
                if (boardState.getPositions().get(13).equals(C) && boardState.getPositions().get(16).equals(C) || boardState.getPositions().get(9).equals(C) && boardState.getPositions().get(11).equals(C)) {
                    return true;
                } else {
                    return false;

                }
            case 11:
                if (boardState.getPositions().get(14).equals(C) && boardState.getPositions().get(17).equals(C) || boardState.getPositions().get(10).equals(C) && boardState.getPositions().get(9).equals(C) || boardState.getPositions().get(10).equals(C) && boardState.getPositions().get(9).equals(C)) {
                    return true;
                } else {
                    return false;

                }
            case 12:
                if (boardState.getPositions().get(15).equals(C) && boardState.getPositions().get(9).equals(C) || boardState.getPositions().get(10).equals(C) && boardState.getPositions().get(11).equals(C)) {
                    return true;
                } else {
                    return false;

                }
            case 13:
                if (boardState.getPositions().get(12).equals(C) && boardState.getPositions().get(14).equals(C) || boardState.getPositions().get(16).equals(C) && boardState.getPositions().get(10).equals(C)) {
                    return true;
                } else {
                    return false;
                }
            case 14:
                if (boardState.getPositions().get(17).equals(C) && boardState.getPositions().get(11).equals(C) || boardState.getPositions().get(7).equals(C) && boardState.getPositions().get(3).equals(C)) {
                    return true;
                } else {
                    return false;

                }
            case 15:
                if (boardState.getPositions().get(16).equals(C) && boardState.getPositions().get(17).equals(C) || boardState.getPositions().get(12).equals(C) && boardState.getPositions().get(9).equals(C)) {
                    return true;
                } else {
                    return false;

                }
            case 16:
                if (boardState.getPositions().get(15).equals(C) && boardState.getPositions().get(17).equals(C) || boardState.getPositions().get(13).equals(C) && boardState.getPositions().get(10).equals(C)) {
                    return true;
                } else {
                    return false;

                }
            case 17:
                if(boardState.getPositions().get(16).equals(C) && boardState.getPositions().get(15).equals(C)|| boardState.getPositions().get(8).equals(C) && boardState.getPositions().get(1).equals(C) || boardState.getPositions().get(14).equals(C) && boardState.getPositions().get(11).equals(C)){
                    return true;
                } else {
                    return false;

                }
        }


    }
return check;

    }

public static List<Integer> getNeighbours(int j)
{
    List<Integer> neighbours = new ArrayList<>(Arrays.asList());
    switch(j){
        case 0: neighbours.addAll(Arrays.asList(15,1,2));break;
        case 1: neighbours.addAll(Arrays.asList(3,0,8));break;
        case 2: neighbours.addAll(Arrays.asList(12,4,3));break;
        case 3: neighbours.addAll(Arrays.asList(5,7,2));break;
        case 4: neighbours.addAll(Arrays.asList(9,2,5));break;
        case 5: neighbours.addAll(Arrays.asList(6,4,3));break;
        case 6: neighbours.addAll(Arrays.asList(11,5,7));break;
        case 7: neighbours.addAll(Arrays.asList(6,14,3,8));break;
        case 8: neighbours.addAll(Arrays.asList(7,17,1));break;
        case 9: neighbours.addAll(Arrays.asList(12,10,4));break;
        case 10: neighbours.addAll(Arrays.asList(9,13,11));break;
        case 11: neighbours.addAll(Arrays.asList(10,14,6));break;
        case 12: neighbours.addAll(Arrays.asList(15,9,13,2));break;
        case 13: neighbours.addAll(Arrays.asList(12,16,14,10));break;
        case 14: neighbours.addAll(Arrays.asList(17,13,7,11));break;
        case 15: neighbours.addAll(Arrays.asList(0,16,12));break;
        case 16: neighbours.addAll(Arrays.asList(15,17,13));break;
        case 17: neighbours.addAll(Arrays.asList(16,8,14));break;

    }
    return neighbours;
}

}
