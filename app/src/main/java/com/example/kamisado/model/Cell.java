package com.example.kamisado.model;

/**
 * Created by danil on 21/02/2015.
 */
public class Cell {
    int row, column;
    Color color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (column != cell.column) return false;
        if (row != cell.row) return false;
        if (color != cell.color) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    public Cell(int i, int j) {
        this.row = i;
        this.column = j;
        this.color = Color.BLUE;

    }
}
