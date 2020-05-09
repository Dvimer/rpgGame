package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.game.learning.rpg.map.FieldMap;

public class Enemy extends GameActor {
    private TextureRegion[] textureUp;
    private TextureRegion[] textureRight;
    private TextureRegion[] textureLeft;
    private TextureRegion[] textureDown;
    private Rectangle rectangle;


    private FieldMap fieldMap;
    private float secondPerFrame;
    private float activityZone;
    private float animationTimer;
    private float moveTimer;
    private float dt;


    public Enemy(float x, float y, FieldMap fieldMap) {
        this.fieldMap = fieldMap;
        textureUp = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[0];
        textureRight = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[1];
        textureLeft = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[2];
        textureDown = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[3];
        position = new Vector2(x, y);
        rectangle = new Rectangle(x, y, FIELD_SIZE, FIELD_SIZE);
        direction = new Vector2(0, 0);
        secondPerFrame = 0.2f;
        activityZone = FIELD_SIZE;
        dt += Gdx.graphics.getDeltaTime();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        checkScreenBounds();
        int frameIndex = (int) (animationTimer / secondPerFrame) % textureRight.length;
        if(direction.x == 0 && direction.y == 0) {
            batch.draw(textureRight[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
        if(direction.x > 0) {
            batch.draw(textureRight[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
        if(direction.x < 0) {
            batch.draw(textureLeft[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
        if(direction.y > 0) {
            batch.draw(textureUp[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
        if(direction.y < 0) {
            batch.draw(textureDown[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
        talkEnemy();
    }

    public void talkEnemy() {
        moveTimer -= Gdx.graphics.getDeltaTime();
        animationTimer += Gdx.graphics.getDeltaTime();
        if (moveTimer < 0.0f) {
            moveTimer = MathUtils.random(1.0f, 1.0f);
            if (MathUtils.randomBoolean(0.5f)) {
                direction.set(MathUtils.random(-1.0f, 1.0f), 0);
            } else {
                direction.set(0, MathUtils.random(-1.0f, 1.0f));
            }
            direction.nor();
            int tempX = (int) (position.x + direction.x);
            int tempY = (int) (position.y + direction.y);
            if (fieldMap.getData()[tempX][tempY].equals(" ")) {
                position.x = position.x + direction.x;
                position.y = position.y + direction.y;
            }

        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
