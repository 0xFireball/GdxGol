package com.nobody.gdxgol;

import com.badlogic.gdx.Game;

public class GdxGol extends Game {
    @Override
    public void create() {
        setScreen(new GolScreen());
    }
}
