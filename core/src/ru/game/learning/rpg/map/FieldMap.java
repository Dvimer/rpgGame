package ru.game.learning.rpg.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FieldMap extends Actor {
    public static final int CELL_SIZE = 80;

    private Texture textureGrass;
    private Texture textureWall;
    private String[][] data;

    public FieldMap() {
        data = new GeneratorMap().generate();
        textureGrass = new Texture("ground.png");
        textureWall = new Texture("badlogic.jpg");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                batch.draw(textureGrass, i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                if (data[i][j].equals("*")) {
                    batch.draw(textureWall, i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    public String[][] getData() {
        return data;
    }
}
