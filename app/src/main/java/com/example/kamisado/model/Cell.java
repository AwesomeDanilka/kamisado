package com.example.kamisado.model;

/**
 * Created by danil on 21/02/2015.
 */
public class Cell {
    int row, column;
    Color color;

    public Cell(int i, int j) {
        this.row = i;
        this.column = j;
        this.color = Color.BLUE;
    }
}
