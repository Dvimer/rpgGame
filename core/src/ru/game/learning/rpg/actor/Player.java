package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.game.learning.rpg.map.FieldMap;
import ru.game.learning.rpg.map.FieldType;
import ru.game.learning.rpg.service.HpService;
import ru.game.learning.rpg.service.PlayerWalkService;
import ru.game.learning.rpg.service.WalkService;

import java.util.ArrayList;
import java.util.List;

public class Player extends GameActor {
    private Rectangle rectangle;
    private HpService hpService;
    private WalkService walkService;
    private List<String> bag;

    public Player(float x, float y, FieldMap fieldMap) {
        this.fieldMap = fieldMap;
        bag = new ArrayList<>();
        fieldType = FieldType.PLAYER;
        fieldMap.getData()[(int) x][(int) y] = this;
        hpMax = 100.0f;
        hp = hpMax;
        position = new Vector2(x, y);
        rectangle = new Rectangle(x, y, FIELD_SIZE, FIELD_SIZE);
        direction = new Vector2(0, 0);
        moveTimer = 0.0f;
        hpService = new HpService();
        walkService = new PlayerWalkService();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        checkScreenBounds();
        hpService.draw(batch, this);
        walkService.draw(batch, this);
        goWithMoveTimer();
        checkChest();
        if (isMotion) {
            getStage().getCamera().translate(direction.x * Gdx.graphics.getDeltaTime() * 1 * FIELD_SIZE, direction.y * Gdx.graphics.getDeltaTime() * FIELD_SIZE * 1, 0);
        }
    }

    private void checkChest() {
        if (direction.x != 0.0f && !isMotion || direction.y != 0.0f && !isMotion) {
            tempX = (int) (position.x + direction.x);
            tempY = (int) (position.y + direction.y);
            if (FieldType.CHEST == fieldMap.getData()[tempX][tempY].getFieldType()) {
                fieldMap.getData()[tempX][tempY].action();
                bag.add("Chest");
            }
        }
    }
    public void takeDamage(float amount) {
        hp -= amount;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public List<String> getBag() {
        return bag;
    }
}
