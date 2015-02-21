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


}
