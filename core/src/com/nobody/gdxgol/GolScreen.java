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

    private boolean playing = false;
    private boolean forever = false;

    @Override
    public void show() {
        grid = new GolGrid(DEFAULT_GRID_SIZE, DEFAULT_GRID_SIZE);
        r = new GolRenderer(grid, new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        init();
    }

    private void init() {

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
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            // randomize grid
            grid.clear();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyPressed(Input.Keys.N)) {
            // manual step and disable play
            playing = false;
            grid.step();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            playing = !playing;
        }

        // continue if playing
        if (playing) {
            grid.step();

            if (forever) {
                grid.forever();
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            forever = !forever;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if (Gdx.input.isTouched() &&
                (Gdx.input.isButtonPressed(Input.Buttons.LEFT)
                        || Gdx.input.isButtonPressed(Input.Buttons.RIGHT))) {
            // take input for manually setting cells
            int cX = Gdx.input.getX();
            int cY = Gdx.input.getY();
            if (cX > r.getGridDrawOffset().x
                    && cX < r.getGridDrawOffset().x + r.getCellDrawSize().x * grid.getCells().length
                    && cY > r.getGridDrawOffset().y
                    && cY < r.getGridDrawOffset().y + r.getCellDrawSize().y * grid.getCells()[0].length) {
                cX -= r.getGridDrawOffset().x;
                cY -= r.getGridDrawOffset().y;
                int cellX = (int) (cX / r.getCellDrawSize().x);
                int cellY = (int) (grid.getCells()[0].length - (cY / r.getCellDrawSize().y));
                // toggle cell state
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    grid.getCells()[cellY][cellX] = true;
                } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                    grid.getCells()[cellY][cellX] = false;
                }
            }
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
