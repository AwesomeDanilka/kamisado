package com.example.kamisado.model;

import com.google.common.collect.BiMap;

/**
 * Created by danil on 21/02/2015.
 */
public class Game {

    BiMap<Cell, Wizard> field;

    public BiMap<Cell, Wizard> getField() {
        return this.field;
    }


}
