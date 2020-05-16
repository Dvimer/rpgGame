package ru.game.learning.rpg.service;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.game.learning.rpg.actor.GameActor;

public interface WalkService {

    void draw(Batch batch, GameActor gameActor);
}
