package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.game.learning.rpg.map.FiledType;

public class Ground extends GameActor {
    private final TextureRegion textureRegion;
    private Rectangle textureRegionBounds1;
    private FiledType filedType = FiledType.GROUND;

    public Ground() {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("ground.png")));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds1.y, textureRegionBounds1.width,
                textureRegionBounds1.height);
    }

    public FiledType getFiledType() {
        return filedType;
    }
}
