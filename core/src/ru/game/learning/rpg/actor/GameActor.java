package ru.game.learning.rpg.actor;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameActor extends Actor {
    public ActorType actorType;
    public Rectangle rectangle;

    public static int FIELD_SCALE = 30;

    public GameActor(int x, int y, ActorType actorType) {
        rectangle = new Rectangle(x * FIELD_SCALE, y * FIELD_SCALE, FIELD_SCALE, FIELD_SCALE);
        this.actorType = actorType;
    }

    public GameActor(Rectangle rectangle, ActorType actorType) {
        this.rectangle = rectangle;
        this.actorType = actorType;

    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setCoodrinate(int x, int y) {
        rectangle.x = x * FIELD_SCALE;
        rectangle.y = y * FIELD_SCALE;
    }
}
