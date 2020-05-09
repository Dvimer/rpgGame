package ru.game.learning.rpg.inputadapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import ru.game.learning.rpg.actor.Player;

public class PlayerInputAdapter extends InputAdapter {

    private Player player;
    private Camera camera;


    public PlayerInputAdapter(Player player, Stage stage) {
        this.player = player;
        this.camera = stage.getCamera();
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.setDirection(1, 0);
            camera.translate(80.0f, 0.0f, 0.0f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.setDirection(-1, 0);
            camera.translate(-80.0f, 0.0f, 0.0f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.setDirection(0, 1);
            camera.translate(0.0f, 80.0f, 0.0f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.setDirection(0, -1);
            camera.translate(0.0f, -80.0f, 0.0f);
        }
        return super.keyDown(keyCode);
    }

    @Override
    public boolean keyUp(int keyCode) {
        return super.keyUp(keyCode);
    }

}
