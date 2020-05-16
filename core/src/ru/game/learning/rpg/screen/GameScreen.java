package ru.game.learning.rpg.screen;

import com.badlogic.gdx.Screen;
import ru.game.learning.rpg.stage.GameStage;

public class GameScreen implements Screen {
    private GameStage stage;

    public GameScreen() {
        stage = new GameStage();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


}
