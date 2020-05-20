package ru.game.learning.rpg.inputadapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.game.learning.rpg.actor.Bag;
import ru.game.learning.rpg.actor.Player;

import static ru.game.learning.rpg.actor.GameActor.FIELD_SIZE;

public class PlayerInputAdapter extends InputAdapter {

    private Player player;


    public PlayerInputAdapter(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (player.moveTimer == 0.0f) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                player.setDirection(1, 0);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                player.setDirection(-1, 0);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                player.setDirection(0, 1);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                player.setDirection(0, -1);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.B)) {
                player.getBag();
                System.out.println(player.getBag());
            }
        }
        return super.keyDown(keyCode);
    }

    @Override
    public boolean keyUp(int keyCode) {
        return super.keyUp(keyCode);
    }

}
