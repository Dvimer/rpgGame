package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends GameActor {
    private final TextureRegion textureRegion;

    public Enemy(int x, int y) {
        super(x, y, ActorType.ENEMY);
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("bucket.png")));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, rectangle.x, rectangle.y, rectangle.width,
                rectangle.height);
    }
}