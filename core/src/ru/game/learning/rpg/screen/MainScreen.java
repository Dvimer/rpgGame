package ru.game.learning.rpg.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import ru.game.learning.rpg.stage.MenuStage;

public class MainScreen implements Screen {
    private MenuStage stage;

    public MainScreen(Game myGame) {
        stage = new MenuStage(myGame);
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
        stage.dispose();
    }


}
