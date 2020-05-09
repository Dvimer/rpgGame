package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Ground extends Actor {
    private final TextureRegion textureRegion;
    private Rectangle textureRegionBounds1;

    public Ground(float x, float y, float width, float height) {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("ground.png")));
        textureRegionBounds1 = new Rectangle(x, y, width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds1.y, textureRegionBounds1.width,
                textureRegionBounds1.height);
    }
}
