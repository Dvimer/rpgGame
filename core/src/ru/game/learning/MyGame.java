package ru.game.learning;

import com.badlogic.gdx.Game;
import ru.game.learning.rpg.screen.GameScreen;
import ru.game.learning.rpg.screen.MainScreen;

public class MyGame extends Game {

    @Override
    public void create() {
        setScreen(new MainScreen(this));
    }
}
