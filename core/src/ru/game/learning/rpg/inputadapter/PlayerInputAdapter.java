package ru.game.learning.rpg.inputadapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import ru.game.learning.rpg.actor.Player;

import static ru.game.learning.rpg.actor.GameActor.FIELD_SCALE;


public class PlayerInputAdapter extends InputAdapter {

    private Player player;
//    private Rectangle screenLeftSide;
//    private Rectangle screenRightSide;

    //    private Vector3 touchPoint;
    private Camera camera;


    public PlayerInputAdapter(Player player, Camera camera) {
        this.player = player;
        this.camera = camera;
//        touchPoint = new Vector3();
//        screenLeftSide = new Rectangle(0, 0, camera.viewportWidth / 2, camera.viewportHeight);
//        screenRightSide = new Rectangle(camera.viewportWidth / 2, 0, camera.viewportWidth / 2,
//                camera.viewportHeight);
    }

//    @Override
//    public boolean touchDown(int x, int y, int pointer, int button) {
//
//        // Need to get the actual coordinates
//        translateScreenToWorldCoordinates(x, y);
//
//        if (rightSideTouched(touchPoint.x, touchPoint.y)) {
//            runner.jump();
//        } else if (leftSideTouched(touchPoint.x, touchPoint.y)) {
//            runner.dodge();
//        }
//
//        return super.touchDown(x, y, pointer, button);
//    }

    @Override
    public boolean keyDown(int keyCode) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.getRectangle().x -= FIELD_SCALE;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.getRectangle().x += FIELD_SCALE;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) player.getRectangle().y += FIELD_SCALE;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.getRectangle().y -= FIELD_SCALE;

        return super.keyDown(keyCode);
    }

    @Override
    public boolean keyUp(int keyCode) {
        if (Input.Keys.LEFT == keyCode) {
//            player.stopDodge();
        }
        return super.keyDown(keyCode);
    }


//    @Override
//    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//
//        if (runner.isDodging()) {
//            runner.stopDodge();
//        }
//
//        return super.touchUp(screenX, screenY, pointer, button);
//    }


//    private boolean rightSideTouched(float x, float y) {
//        return screenRightSide.contains(x, y);
//    }
//
//    private boolean leftSideTouched(float x, float y) {
//        return screenLeftSide.contains(x, y);
//    }


    /**
     * Helper function to get the actual coordinates in my world
     *
     * @param x
     * @param y
     */
//    private void translateScreenToWorldCoordinates(int x, int y) {
//        camera.unproject(touchPoint.set(x, y, 0));
//    }
}
