package ru.game.learning.rpg.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.game.learning.rpg.actor.GameActor;

import static ru.game.learning.rpg.actor.GameActor.FIELD_SIZE;

public class EnemyWalkService implements WalkService {

    private TextureRegion[] textureStop;
    private TextureRegion[] textureDown;
    private TextureRegion[] textureUp;
    private TextureRegion[] textureRight;
    private TextureRegion[] textureLeft;
    private float animationTimer;
    private float secondPerFrame;

    public EnemyWalkService() {
        textureStop = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[0];
        textureUp = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[0];
        textureRight = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[1];
        textureLeft = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[2];
        textureDown = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[3];
        secondPerFrame = 0.2f;
    }

    @Override
    public void draw(Batch batch, GameActor gameActor) {
        batch.draw(getCurrentTexture(gameActor), gameActor.getPosition().x * FIELD_SIZE, gameActor.getPosition().y * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE);
    }

    public TextureRegion getCurrentTexture(GameActor gameActor) {
        animationTimer += Gdx.graphics.getDeltaTime();
        int frameIndex = (int) (animationTimer / secondPerFrame) % textureDown.length;
        if (gameActor.getDirection().y > 0) {
            return textureUp[frameIndex];
        } else if (gameActor.getDirection().y < 0) {
            return textureDown[frameIndex];
        } else if (gameActor.getDirection().x > 0) {
            return textureRight[frameIndex];
        } else if (gameActor.getDirection().x < 0) {
            return textureLeft[frameIndex];
        } else {
            return textureStop[0];
        }
    }
}
