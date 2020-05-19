package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.game.learning.rpg.map.FieldMap;
import ru.game.learning.rpg.map.FiledType;
import ru.game.learning.rpg.service.HpService;
import ru.game.learning.rpg.service.PlayerWalkService;
import ru.game.learning.rpg.service.WalkService;

public class Player extends GameActor {
    private Texture textureRIP;
    private Rectangle rectangle;

    //    private Vector2 temp;
    private float secondPerFrame;
    private float animationTimer;
    private float speed;
    private boolean alive;

    private HpService hpService;
    private WalkService walkService;

    public Player(float x, float y, FieldMap fieldMap) {
        this.fieldMap = fieldMap;
        filedType = FiledType.PLAYER;
        fieldMap.getData()[(int) x][(int) y] = filedType;
        hpMax = 100.0f;
        hp = hpMax;
        textureRIP = new Texture("RIP.jpg");
        position = new Vector2(x, y);
        rectangle = new Rectangle(x, y, FIELD_SIZE, FIELD_SIZE);
        direction = new Vector2(0, 0);
        secondPerFrame = 0.2f;
        speed = FIELD_SIZE;
        moveTimer = 0.0f;
        hpService = new HpService();
        walkService = new PlayerWalkService();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        checkScreenBounds();
        hpService.draw(batch, this);
        walkService.draw(batch, this);
        goWithMoveTimer();
        if (isMotion) {
            getStage().getCamera().translate(direction.x * Gdx.graphics.getDeltaTime() * 1 * FIELD_SIZE, direction.y * Gdx.graphics.getDeltaTime() * FIELD_SIZE * 1, 0);
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
