package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import ru.game.learning.rpg.map.FieldMap;


public class Tree extends GameActor {
    private TextureRegion textureRegion;
    private Rectangle textureRegionBounds;

    public Tree(FieldMap fieldMap) {
        this.fieldMap = fieldMap;
        int tempX = MathUtils.random(5, 10);
        int tempY = MathUtils.random(5, 10);
        fieldMap.getData()[tempX][tempY] = this;
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("Tree.jpg")));
        textureRegionBounds = new Rectangle(tempX, tempY, FIELD_SIZE, FIELD_SIZE);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, textureRegionBounds.x * FIELD_SIZE, textureRegionBounds.y * FIELD_SIZE, textureRegionBounds.width, textureRegionBounds.height);
    }

    @Override
    public void action() {
        fieldMap.getData()[(int) textureRegionBounds.getX()][(int) textureRegionBounds.getY()] = new Ground();
        remove();
    }
}
