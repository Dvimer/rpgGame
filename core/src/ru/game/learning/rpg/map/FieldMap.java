package ru.game.learning.rpg.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.game.learning.rpg.stage.GameStage;

public class FieldMap extends Actor {
    public static final int CELL_SIZE = 80;

    private Texture textureGrass;
    private Texture textureWall;
    private FiledType[][] data;
    private String[][] placeEnemy;


    public FieldMap() {
        data = new SplitGenerator().generate();
        textureGrass = new Texture("floor.png");
        textureWall = new Texture("wall.png");

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                batch.draw(textureGrass, i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                if (FiledType.WALL == data[i][j]) {
                    batch.draw(textureWall, i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    public FiledType[][] getData() {
        return data;
    }

    public String[][] getPlaceEnemy() {
        return placeEnemy;
    }
}
