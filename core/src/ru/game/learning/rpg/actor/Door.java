package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class Door extends GameActor {
    private final TextureRegion textureRegion;

    public Door(int x, int y) {
        super(x, y, ActorType.DOOR);
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("Door.jpg")));
    }

    public Door(Rectangle rectangle) {
        super(rectangle, ActorType.DOOR);
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("Door.jpg")));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, rectangle.x, rectangle.y, rectangle.width,
                rectangle.height);
    }
}
