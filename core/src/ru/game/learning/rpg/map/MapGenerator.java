package ru.game.learning.rpg.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.game.learning.rpg.actor.GameActor;

public interface MapGenerator {
    GameActor[][] generate();
}
