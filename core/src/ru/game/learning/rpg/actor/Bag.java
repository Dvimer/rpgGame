package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.game.learning.rpg.map.FieldMap;
import ru.game.learning.rpg.map.FieldType;

import java.util.ArrayList;
import java.util.List;

import static ru.game.learning.rpg.actor.GameActor.FIELD_SIZE;

public class Bag extends Actor {
    private Player player;
    private FieldMap fieldMap;
    private int tempX;
    private int tempY;
    private List<String>bag;
    private BitmapFont font24;

    public Bag(){
        this.bag = new ArrayList<>();
        font24 = new BitmapFont(Gdx.files.internal("font/font24.fnt"));
    }
    void bagPicking(){
            bag.add("Chest");
    }

    public void draw(Batch batch) {
        font24.draw(batch, String.valueOf(bag), 1 * FIELD_SIZE, 1 * FIELD_SIZE, 80, 1, false);
    }

    public List<String> getBag() {
        return bag;
    }

    public void setBag(List<String> bag) {
        this.bag = bag;
    }
}
