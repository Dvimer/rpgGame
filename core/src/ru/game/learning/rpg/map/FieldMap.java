package ru.game.learning.rpg.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FieldMap extends Actor {
    public static final int CELLS_X = 16;
    public static final int CELLS_Y = 9;
    public static final int CELL_SIZE = 80;

    private Texture textureGrass;
    private Texture textureWall;
    private byte[][] data;

    public FieldMap() {
        data = new byte[CELLS_X][CELLS_Y];
        for (int i = 0; i < 15; i++) {
            data[MathUtils.random(0, CELLS_X - 1)][MathUtils.random(0, CELLS_Y - 1)] = 1;
        }
        textureGrass = new Texture("ground.png");
        textureWall = new Texture("badlogic.jpg");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < CELLS_X; i++) {
            for (int j = 0; j < CELLS_Y; j++) {
                batch.draw(textureGrass, i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                if (data[i][j] == 1) {
                    batch.draw(textureWall, i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    public byte[][] getData() {
        return data;
    }
}
