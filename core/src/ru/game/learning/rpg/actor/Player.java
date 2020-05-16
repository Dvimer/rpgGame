package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.game.learning.rpg.map.FieldMap;
import ru.game.learning.rpg.service.HpService;

public class Player extends GameActor {
    //    private final TextureRegion[] textureRegion;
    private TextureRegion[] textureStop;
    private TextureRegion[] textureDown;
    private TextureRegion[] textureUp;
    private TextureRegion[] textureRight;
    private TextureRegion[] textureLeft;
    private Texture textureHp;
    private Texture textureRIP;
    private Rectangle rectangle;

    //    private Vector2 temp;
    private float secondPerFrame;
    private float animationTimer;
    private float speed;
    private boolean alive;

    private HpService hpService;

    public Player(float x, float y, FieldMap fieldMap) {
        this.fieldMap = fieldMap;
        hpMax = 100.0f;
        hp = hpMax;
        textureStop = new TextureRegion(new Texture(Gdx.files.internal("Player_Go.png"))).split(100, 100)[0];
        textureDown = new TextureRegion(new Texture(Gdx.files.internal("Player_Go.png"))).split(100, 100)[1];
        textureUp = new TextureRegion(new Texture(Gdx.files.internal("Player_Go.png"))).split(100, 100)[2];
        textureRight = new TextureRegion(new Texture(Gdx.files.internal("Player_Go.png"))).split(100, 100)[3];
        textureLeft = new TextureRegion(new Texture(Gdx.files.internal("Player_Go.png"))).split(100, 100)[4];
        textureHp = new Texture("Hp.png");
        textureRIP = new Texture("RIP.jpg");
        position = new Vector2(x, y);
        rectangle = new Rectangle(x, y, FIELD_SIZE, FIELD_SIZE);
        direction = new Vector2(0, 0);
        secondPerFrame = 0.2f;
        speed = FIELD_SIZE;
        moveTimer = 0.0f;
        hpService = new HpService();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        animationTimer += Gdx.graphics.getDeltaTime();
        super.draw(batch, parentAlpha);
        checkScreenBounds();
        drawPlayer(batch);
        goWithMoveTimer();
        getStage().getCamera().translate(direction.x * Gdx.graphics.getDeltaTime() * 2 * FIELD_SIZE, direction.y * Gdx.graphics.getDeltaTime() * FIELD_SIZE * 2, 0);
    }

    public void drawPlayer(Batch batch) {
        hpService.draw(batch, this);
        int frameIndex = (int) (animationTimer / secondPerFrame) % textureDown.length;
        if (direction.x == 0 && direction.y == 0) {
            batch.draw(textureStop[0], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
        if (direction.y > 0) {
            batch.draw(textureUp[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
        if (direction.y < 0) {
            batch.draw(textureDown[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
        if (direction.x > 0) {
            batch.draw(textureRight[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
        if (direction.x < 0) {
            batch.draw(textureLeft[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
    }

    public void takeDamage(float amount) {
        hp -= amount;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }


}
