package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.game.learning.rpg.map.FieldMap;
import ru.game.learning.rpg.service.HpService;

public class Enemy extends GameActor {
    private TextureRegion[] textureUp;
    private TextureRegion[] textureRight;
    private TextureRegion[] textureLeft;
    private TextureRegion[] textureDown;
    private Texture texture;
    private Rectangle rectangle;
    private Player player;
    private Weapon weapon;
    private float secondPerFrame;
    private float activityZone;
    private float animationTimer;
    private float walkTimer;
    private float dt;
    private HpService hpService;


    public Enemy(float x, float y, FieldMap fieldMap, Player player) {
        this.fieldMap = fieldMap;
        this.player = player;
        this.hpMax = 50.0f;
        this.hp = hpMax;
        this.weapon = new Weapon("Claws", "near", 2, 5, 3.0f);
        texture = new Texture("Enemy80.png");
        textureUp = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[0];
        textureRight = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[1];
        textureLeft = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[2];
        textureDown = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[3];
        position = new Vector2(x, y);
        rectangle = new Rectangle(x, y, FIELD_SIZE, FIELD_SIZE);
        direction = new Vector2(0, 0);
        secondPerFrame = 0.3f;
        activityZone = 3.0f;
        dt = Gdx.graphics.getDeltaTime();
        hpService = new HpService();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        checkScreenBounds();
        hpService.draw(batch, this);
//        paintEnemy(batch);
        batch.draw(texture, position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                FIELD_SIZE);
        attack();
        walkEnemy();
    }

//    private void paintEnemy(Batch batch) {
//        int frameIndex = (int) (animationTimer / secondPerFrame) % textureRight.length;
//        if (direction.x == 0 && direction.y == 0) {
//            batch.draw(textureRight[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
//                    FIELD_SIZE);
//        }
//        if (direction.x > 0) {
//            batch.draw(textureRight[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
//                    FIELD_SIZE);
//        }
//        if (direction.x < 0) {
//            batch.draw(textureLeft[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
//                    FIELD_SIZE);
//        }
//        if (direction.y > 0) {
//            batch.draw(textureUp[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
//                    FIELD_SIZE);
//        }
//        if (direction.y < 0) {
//            batch.draw(textureDown[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
//                    FIELD_SIZE);
//        }
//    }

    public void walkEnemy() {
        changeDirection();
        goWithMoveTimer();
    }

    private void changeDirection() {
        float dst = player.getPosition().dst(this.position);
        walkTimer -= Gdx.graphics.getDeltaTime();
        if (dst < activityZone) {
            if (walkTimer < 0.0f) {
                walkTimer = MathUtils.random(1.0f, 1.0f);
                goToPlayer();
            }
        } else if (walkTimer < 0.0f) {
            walkTimer = MathUtils.random(1.0f, 1.0f);
            goToRandomVector(MathUtils.random(-1.0f, 1.0f), 0, 0);
        }
        direction.nor();
    }

    private void goToRandomVector(float random, float i, float i2) {
        if (MathUtils.randomBoolean(0.5f)) {
            direction.set(random, i);
        } else {
            direction.set(i2, random);
        }
    }

    private void goToPlayer() {
        direction.set(player.getPosition()).sub(this.position);
        goToRandomVector(0, direction.y, direction.x);
    }

    public void attack() {
        float dst = player.getPosition().dst(this.position);
        if (dst < weapon.getDistance()) {
            attackTimer += Gdx.graphics.getDeltaTime();
            if (attackTimer >= weapon.getSpeedAttack()) {
                player.takeDamage(weapon.getAttack());
                attackTimer = 0.0f;
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
