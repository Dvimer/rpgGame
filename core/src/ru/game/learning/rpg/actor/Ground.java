package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ground extends GameActor {
    private final TextureRegion textureRegion;

    public Ground(int x, int y) {
        super(x,y, ActorType.GROUND);
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("ground.png")));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, rectangle.x, rectangle.y, rectangle.width,
                rectangle.height);
    }
}
