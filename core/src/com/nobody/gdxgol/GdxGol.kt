package com.nobody.gdxgol

import com.badlogic.gdx.Game

class GdxGol : Game() {
    override fun create() {
        setScreen(GolScreen())
    }
}
