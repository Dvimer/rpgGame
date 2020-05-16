package ru.game.learning.rpg.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import ru.game.learning.rpg.actor.GameActor;

import static ru.game.learning.rpg.actor.GameActor.FIELD_SIZE;

public class HpService {
    private Texture textureHp;
    private BitmapFont font24;

    public HpService() {
        textureHp = new Texture("hp.png");
        font24 = new BitmapFont(Gdx.files.internal("font/font24.fnt"));
    }

    public void draw(Batch batch, GameActor gameActor) {
        batch.setColor(1, 1, 1, 1);
        batch.draw(textureHp, gameActor.getPosition().x * FIELD_SIZE, gameActor.getPosition().y * FIELD_SIZE + FIELD_SIZE, 0, 0, FIELD_SIZE, 12, 1, 1, 0, 0, 0, FIELD_SIZE, 20, false, false);
        batch.setColor(1, 0, 0, 1);
        batch.draw(textureHp, gameActor.getPosition().x * FIELD_SIZE, gameActor.getPosition().y * FIELD_SIZE + FIELD_SIZE, 0, 0, gameActor.getHp() / gameActor.getHpMax() * FIELD_SIZE, 12, 1, 1, 0, 0, 0, FIELD_SIZE, 20, false, false);
        batch.setColor(1, 1, 1, 1);
        font24.draw(batch, String.valueOf((int) gameActor.getHp()), gameActor.getPosition().x * FIELD_SIZE, gameActor.getPosition().y * FIELD_SIZE + FIELD_SIZE+18, 80, 1, false);
    }
}
