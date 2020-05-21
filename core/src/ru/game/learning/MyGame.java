package ru.game.learning;

import com.badlogic.gdx.Game;
import ru.game.learning.rpg.screen.GameScreen;
import ru.game.learning.rpg.screen.MainScreen;

public class MyGame extends Game {

    @Override
    public void create() {
        setScreen(new MainScreen(this));

        // 1. Инвентарь сундука, окно с содержимым сундука, дважды кликаем и переносим к себе в сундук
        // 2. Ресур - объект на карте , который можно взять или взаимодейстовать
    }
}
