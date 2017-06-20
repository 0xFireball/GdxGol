package com.nobody.gdxgol;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by nihal on 6/19/17.
 */
public class GolRenderer {

    private ShapeRenderer r = new ShapeRenderer();
    private OrthographicCamera cam2d;

    private GolGrid grid;
    private Vector2 renderSize;

    public GolRenderer(GolGrid golGrid, Vector2 surfaceSize) {
        grid = golGrid;
        renderSize = surfaceSize;

        cam2d = new OrthographicCamera(surfaceSize.x, surfaceSize.y);
        cam2d.position.set(cam2d.viewportWidth / 2f, cam2d.viewportHeight / 2f, 0);
        cam2d.update();
        r.setProjectionMatrix(cam2d.combined);
    }

    public void drawGrid() {
        boolean[][] cells = grid.getCells();
        // calculate cell size
        int cellW = ((int)renderSize.x) / cells.length;
        int cellH = ((int)renderSize.y) / cells[0].length;
        // calculate offset
        int xOff = (int)((renderSize.x) - cellW * cells.length) / 2;
        int yOff = (int)((renderSize.y) - cellH * cells[0].length) / 2;
        // draw grid
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                int cy = yOff + i * cellH;
                int cx = xOff + j * cellW;
                // draw outline
                r.begin(ShapeRenderer.ShapeType.Line);
                r.setColor(Color.CYAN);
                r.rect(cx, cy, cellW, cellH);
                r.end();
                // fill for living cells
                if (cells[i][j]) {
                    r.begin(ShapeRenderer.ShapeType.Filled);
                    r.setColor(Color.CYAN);
                    r.rect(cx, cy, cellW, cellH);
                    r.end();
                }
            }
        }
    }
}
