package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static ru.game.learning.rpg.map.FieldMap.CELL_SIZE;

public class GameActor extends Actor {
    protected Vector2 position;
    protected Vector2 direction;
    public final static int FIELD_SIZE = CELL_SIZE;




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


}
