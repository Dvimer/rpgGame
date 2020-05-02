package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Wall extends Actor {
    private final TextureRegion textureRegion;
    private Rectangle rectangle;

    public Wall(float x, float y, float width, float height) {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg")));
        rectangle = new Rectangle(x, y, width, height);
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

}
