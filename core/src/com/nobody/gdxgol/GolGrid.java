package com.nobody.gdxgol;

import java.util.Random;

/**
 * Created by nihal on 6/19/17.
 */
public class GolGrid {

    private boolean[][] cells;

    private GolRules rules = new GolRules();

    public GolGrid(int width, int height) {
        cells = new boolean[width][height];
    }

    public void step() {
        // duplicate grid
        boolean[][] newCells = new boolean[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                newCells[i][j] = cells[i][j];
            }
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                int n = neighbors(i, j);
                if (cells[i][j] && !rules.survival[n]) {
                    newCells[i][j] = false;
                }
                if (!cells[i][j] && rules.birth[n]) {
                    newCells[i][j] = true;
                }
            }
        }
        cells = newCells;
    }

    private int neighbors(int y, int x) {
        int n = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int ii = y + i;
                int jj = x + j;
                if (ii < 0) ii = cells.length - 1;
                if (ii >= cells.length) ii = 0;
                if (jj < 0) jj = cells[ii].length - 1;
                if (jj >= cells[ii].length) jj = 0;
                if (cells[ii][jj]) n++;
            }
        }
        return n;
    }

    public void randomize() {
        Random r = new Random();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = r.nextBoolean();
            }
        }
    }

    public void clear() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = false;
            }
        }
    }

    public boolean[][] getCells() {
        return cells;
    }

    public GolRules getRules() {
        return rules;
    }
}
