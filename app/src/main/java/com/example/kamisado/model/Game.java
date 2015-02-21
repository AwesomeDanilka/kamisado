package com.example.kamisado.model;

import android.support.annotation.NonNull;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;
import java.util.Set;

/**
 * Created by danil on 21/02/2015.
 */
public class Game {

    HashBiMap<Cell, Wizard> field;

    public Game(){
        this.field = HashBiMap.create();
        for (int i=0;i<8;i++) {
            for (int j=0;j<8;j++) {
                this.field.put(new Cell(i, j), new Wizard());
            }
        }

    }

    public BiMap<Cell, Wizard> getField() {
        return this.field;
    }

    static int FIELD_SIZE = 8;
    static Color fieldColors[][];

    static void genFieldColors() {
        fieldColors = new Color[FIELD_SIZE][FIELD_SIZE];
        int perm[] = { 1, 3, 5, 7, 1, 3, 5, 7 };
        int nextPerm[] = new int[FIELD_SIZE];
        fieldColors[0][0] = Color.BROWN;
        fieldColors[0][1] = Color.CYAN;
        fieldColors[0][2] = Color.BLUE;
        fieldColors[0][3] = Color.YELLOW;
        fieldColors[0][4] = Color.MAGENTA;
        fieldColors[0][5] = Color.RED;
        fieldColors[0][6] = Color.GREEN;
        fieldColors[0][7] = Color.ORANGE;
        for ( int i = 1; i < FIELD_SIZE; i++ ) {
            for ( int j = 0; j < FIELD_SIZE; j++ ) {
                nextPerm[ (j + perm[ j ]) % FIELD_SIZE] = perm[ j ];
            }
            for ( int j = 0; j < FIELD_SIZE; j++ ) {
                fieldColors[ i ][ (j + perm[ j ]) % FIELD_SIZE ] = fieldColors[ i - 1 ][ j ];
                perm[ j ] = nextPerm[ j ];
            }
        }
    }
}

/*
colorMap :: Map Position Color
colorMap = M.fromList $ zip (coords >>= zip coords . repeat) rows
  where
    f = (A.listArray range [1, 3, 5, 7, 1, 3, 5, 7] A.!)
    goNext r = A.elems $ A.array range $ zipWith ( \x i -> (i +++ f x, x) ) r coords
    rows = concatMap ( map toColor ) $ take (unCoord fieldSize) $ iterate goNext coords
    toColor = (A.listArray range [ Brown, Cyan, Blue, Yellow, Magenta, Green, Red, Orange ] A.!)
    (+++) x y = (x + y) `mod` fieldSize
    range = (0,maxCoord)
*/
