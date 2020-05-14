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
    private Texture texture;
    private Texture textureHp;
    private Rectangle rectangle;
    private Player player;
    private Weapon weapon;
    private FieldMap fieldMap;
    private float secondPerFrame;
    private float activityZone;
    private float animationTimer;
    private float moveTimer;
    private float dt;


    public Enemy(float x, float y, FieldMap fieldMap, Player player) {
        this.fieldMap = fieldMap;
        this.player = player;
        this.hpMax = 50.0f;
        this.hp = hpMax;
        this.weapon = new Weapon("Claws","near", 2,5,3.0f);
        texture = new Texture("Enemy80.png");
        textureUp = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[0];
        textureRight = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[1];
        textureLeft = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[2];
        textureDown = new TextureRegion(new Texture(Gdx.files.internal("deadman.png"))).split(100, 100)[3];
        textureHp = new Texture("hp.png");
        position = new Vector2(x, y);
        rectangle = new Rectangle(x, y, FIELD_SIZE, FIELD_SIZE);
        direction = new Vector2(0, 0);
        secondPerFrame = 0.3f;
        activityZone = 3.0f;
        dt = Gdx.graphics.getDeltaTime();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        checkScreenBounds();
        batch.setColor(1,0,0,1);
        batch.draw(textureHp,position.x * FIELD_SIZE, position.y * FIELD_SIZE + FIELD_SIZE,0,0,hp / hpMax * FIELD_SIZE,12,1,1,0,0,0, FIELD_SIZE, 20, false, false);
        batch.setColor(1,1,1,1);
//        paintEnemy(batch);
        batch.draw(texture, position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        attack();
        talkEnemy();
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

    public void talkEnemy() {
        direction.set(0.0f, 0.0f);
        moveTimer -= Gdx.graphics.getDeltaTime();
        animationTimer += Gdx.graphics.getDeltaTime();
        float dst = player.getPosition().dst(this.position);
        if (dst < activityZone) {
            if (moveTimer < 0.0f) {
                moveTimer = MathUtils.random(1.0f, 4.0f);
                direction.set(player.getPosition()).sub(this.position).nor();
                if (MathUtils.randomBoolean(0.5f)) {
                    direction.set(0, direction.y);
                } else
                    direction.set(direction.x, 0);
            }
        } else if (moveTimer < 0.0f) {
            moveTimer = MathUtils.random(1.0f, 1.0f);
            if (MathUtils.randomBoolean(0.5f)) {
                direction.set(MathUtils.random(-1.0f, 1.0f), 0);
            } else {
                direction.set(0, MathUtils.random(-1.0f, 1.0f));
            }
        }
        direction.nor();
        int tempX = (int) (position.x + direction.x);
        int tempY = (int) (position.y + direction.y);
        if (fieldMap.getData()[tempX][tempY].equals(" ") &&
                (player.getPosition().x != tempX || player.getPosition().y != tempY) ) {
            position.x = position.x + direction.x;
            position.y = position.y + direction.y;
        }
    }

    public void attack(){
        float dst = player.getPosition().dst(this.position);
        if (dst < weapon.getDistance()){
            attackTimer += Gdx.graphics.getDeltaTime();
            if(attackTimer >= weapon.getSpeedAttack()){
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
