package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.game.learning.rpg.map.FieldType;

public class Wall extends GameActor {
    private final TextureRegion textureRegion;
    private Rectangle rectangle;
    private FieldType fieldType = FieldType.WALL;

    public Wall() {
        position = new Vector2(MathUtils.random(0, 9) * FIELD_SIZE, MathUtils.random(0, 16) * FIELD_SIZE);
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg")));
        rectangle = new Rectangle(position.x, position.y, FIELD_SIZE, FIELD_SIZE);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, rectangle.x, rectangle.y, rectangle.width,
                rectangle.height);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public FieldType getFieldType() {
        return fieldType;
    }
}
