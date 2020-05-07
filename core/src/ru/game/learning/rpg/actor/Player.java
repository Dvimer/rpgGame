package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.game.learning.rpg.map.FieldMap;

public class Player extends GameActor {
    private final TextureRegion textureRegion;
    private Rectangle rectangle;

    private Vector2 temp;
    private FieldMap fieldMap;


    public Player(float x, float y, FieldMap fieldMap) {
        this.fieldMap = fieldMap;
        position = new Vector2(x, y);
        rectangle = new Rectangle(x, y, FIELD_SIZE, FIELD_SIZE);
        direction = new Vector2(0, 0);

        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("droplet.png")));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        checkScreenBounds();
        batch.draw(textureRegion, position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                FIELD_SIZE);

        wallOnCells();
    }

    private void wallOnCells() {
        int tempX = (int) (position.x + direction.x);
        int tempY = (int) (position.y + direction.y);
        if (fieldMap.getData()[tempX][tempY] == 0) {
            if (direction.x != 0.0f || direction.y != 0.0f) {
                position.add(direction.x, direction.y);
                direction.x = 0;
                direction.y = 0;
            }
        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
