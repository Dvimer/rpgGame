package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.game.learning.rpg.map.FieldMap;
import ru.game.learning.rpg.map.FiledType;

import static ru.game.learning.rpg.actor.GameActor.FIELD_SIZE;

public class Chest extends GameActor {
    private TextureRegion textureRegion;
    private Rectangle textureRegionBounds1;


    public Chest(FieldMap fieldMap) {
        filedType = FiledType.CHEST;
        this.fieldMap = fieldMap;
        int tempX = MathUtils.random(5, 10);
        int tempY = MathUtils.random(5, 10);
//        if (fieldMap.getData()[tempX][tempY] == FiledType.GROUND) {
        fieldMap.getData()[tempX][tempY] = this;
//        }
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("ground.png")));
        textureRegionBounds1 = new Rectangle(tempX, tempY, FIELD_SIZE, FIELD_SIZE);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, textureRegionBounds1.x * FIELD_SIZE, textureRegionBounds1.y * FIELD_SIZE, textureRegionBounds1.width, textureRegionBounds1.height);
    }

    @Override
    public void action() {
        fieldMap.getData()[(int) textureRegionBounds1.getX()][(int) textureRegionBounds1.getY()] = new Ground();
        remove();
    }
}
