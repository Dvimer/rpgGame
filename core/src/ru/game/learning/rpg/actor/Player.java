package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends GameActor {
    private final TextureRegion textureRegion;

    public Player(int x, int y) {
        super(x,y,ActorType.PLAYER);
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("droplet.png")));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, rectangle.x, rectangle.y, rectangle.width,
                rectangle.height);
    }
}
