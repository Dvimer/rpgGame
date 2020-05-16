package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.game.learning.rpg.map.FieldMap;
import ru.game.learning.rpg.map.FiledType;

import static ru.game.learning.rpg.map.FieldMap.CELL_SIZE;

public class GameActor extends Actor {
    protected Vector2 position;
    protected Vector2 direction;
    protected float hp;
    protected float hpMax;
    protected float attackTimer;
    public float moveTimer;
    public final static int FIELD_SIZE = CELL_SIZE;
    protected FieldMap fieldMap;


    public void checkScreenBounds() {
        if (position.x > Gdx.graphics.getWidth() - FIELD_SIZE) {
            position.x = Gdx.graphics.getWidth() - FIELD_SIZE;
        }
        if (position.x < 0.0f) {
            position.x = 0.0f;
        }
        if (position.y > Gdx.graphics.getHeight() - FIELD_SIZE) {
            position.y = Gdx.graphics.getHeight() - FIELD_SIZE;
        }
        if (position.y < 0.0f) {
            position.y = 0.0f;
        }
    }

    protected void goWithMoveTimer() {
        int tempX = (int) (position.x + direction.x);
        int tempY = (int) (position.y + direction.y);
        if (FiledType.GROUND == fieldMap.getData()[tempX][tempY]) {
            if (direction.x != 0.0f || direction.y != 0.0f) {
                if (moveTimer < .5f) {
                    moveTimer += Gdx.graphics.getDeltaTime();
                    position.set(position).add(direction.x * Gdx.graphics.getDeltaTime() * 2, direction.y * Gdx.graphics.getDeltaTime() * 2);
                } else {
                    moveTimer = 0.0f;
                    direction.x = 0;
                    direction.y = 0;
                }
            }
        } else {
            direction.set(0, 0);
            moveTimer = 0.0f;
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(int x, int y) {
        direction.x = x;
        direction.y = y;
    }

    public void takeDamage(float amount) {
        hp -= amount;
    }

    public float getHp() {
        return hp;
    }

    public float getHpMax() {
        return hpMax;
    }
}
