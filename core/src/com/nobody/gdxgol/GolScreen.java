package com.nobody.gdxgol;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by nihal on 6/19/17.
 */
public class GolScreen implements Screen {

    private final int DEFAULT_GRID_SIZE = 64;

    private GolGrid grid;
    private GolRenderer r;

    @Override
    public void show() {
        grid = new GolGrid(DEFAULT_GRID_SIZE, DEFAULT_GRID_SIZE);
        r = new GolRenderer(grid, new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
     }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    public void update(float dt) {
        // process input
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            // randomize grid
            grid.randomize();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyPressed(Input.Keys.N)) {
            // step
            grid.step();
        }
    }

    public void draw() {
        Gdx.gl.glClearColor(250, 250, 250, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw grid with renderer
        r.drawGrid();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
