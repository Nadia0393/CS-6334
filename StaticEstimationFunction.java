package com.example.staticestimationfunction;

import com.example.BoardState;
import com.example.GameStage;

public interface StaticEstimationFunction {
    Long getEstimation(BoardState boardState, GameStage gameStage);
}
