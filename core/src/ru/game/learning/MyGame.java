package ru.game.learning;

import com.badlogic.gdx.Game;
import ru.game.learning.rpg.screen.GameScreen;

public class MyGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
