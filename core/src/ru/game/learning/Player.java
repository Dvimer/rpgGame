package ru.game.learning;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {
    public Texture texture;
    public Rectangle rectangle;

    public Player() {
        this.texture = new Texture(Gdx.files.internal("pixelcarpack_kenney/PNG/Characters/man.png"));
        rectangle = new Rectangle(20, 20, 9, 15);
    }


}
