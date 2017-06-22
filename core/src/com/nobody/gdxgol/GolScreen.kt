package com.nobody.gdxgol

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Vector2

/**
 * Created by nihal on 6/19/17.
 */
class GolScreen : Screen {

    private val DEFAULT_GRID_SIZE = 64

    private var grid: GolGrid? = null
    private var r: GolRenderer? = null

    private var playing = false

    override fun show() {
        grid = GolGrid(DEFAULT_GRID_SIZE, DEFAULT_GRID_SIZE)
        r = GolRenderer(grid, Vector2(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()))

        init()
    }

    private fun init() {

    }

    override fun render(delta: Float) {
        update(delta)
        draw()
    }

    fun update(dt: Float) {
        // process input
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            // randomize grid
            grid!!.randomize()
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            // randomize grid
            grid!!.clear()
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyPressed(Input.Keys.N)) {
            // manual step and disable play
            playing = false
            grid!!.step()
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            playing = !playing
        }

        // continue if playing
        if (playing) {
            grid!!.step()
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }

        if (Gdx.input.isTouched && (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || Gdx.input.isButtonPressed(Input.Buttons.RIGHT))) {
            // take input for manually setting cells
            var cX = Gdx.input.x
            var cY = Gdx.input.y
            if (cX > r!!.gridDrawOffset.x
                    && cX < r!!.gridDrawOffset.x + r!!.cellDrawSize.x * grid!!.cells.size
                    && cY > r!!.gridDrawOffset.y
                    && cY < r!!.gridDrawOffset.y + r!!.cellDrawSize.y * grid!!.cells[0].size) {
                cX -= r!!.gridDrawOffset.x.toInt()
                cY -= r!!.gridDrawOffset.y.toInt()
                val cellX = (cX / r!!.cellDrawSize.x).toInt()
                val cellY = (grid!!.cells[0].size - cY / r!!.cellDrawSize.y).toInt()
                // toggle cell state
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    grid!!.cells[cellY][cellX] = true
                } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                    grid!!.cells[cellY][cellX] = false
                }
            }
        }
    }

    fun draw() {
        Gdx.gl.glClearColor(250f, 250f, 250f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // draw grid with renderer
        r!!.drawGrid()
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {

    }
}
