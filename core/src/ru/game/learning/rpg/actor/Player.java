package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.game.learning.rpg.map.FieldMap;
import ru.game.learning.rpg.map.FieldType;
import ru.game.learning.rpg.service.HpService;
import ru.game.learning.rpg.service.PlayerWalkService;
import ru.game.learning.rpg.service.WalkService;

import java.util.ArrayList;
import java.util.List;

public class Player extends GameActor {
    private Rectangle rectangle;
    private HpService hpService;
    private WalkService walkService;
    private List<String> bag;
    private Weapon weapon;
    private Texture textureDead;
    private Enemy targetEnemy;
    private float minDst = 5.0f;
    private int i;

    public Player(float x, float y, FieldMap fieldMap) {
        textureDead = new Texture("dead.png");
        this.fieldMap = fieldMap;
        weapon = new Weapon("Sword", "near", 1.5f, 7, 0.3f);

        bag = new ArrayList<>();
        fieldType = FieldType.PLAYER;
        fieldMap.getData()[(int) x][(int) y] = this;
        hpMax = 100.0f;
        hp = hpMax;
        position = new Vector2(x, y);
        rectangle = new Rectangle(x, y, FIELD_SIZE, FIELD_SIZE);
        direction = new Vector2(0, 0);
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
        checkChest();
        if (targetEnemy == null) {
            selectEnemy();
        }
        if (isMotion) {
            getStage().getCamera().translate(direction.x * Gdx.graphics.getDeltaTime() * 2 * FIELD_SIZE, direction.y * Gdx.graphics.getDeltaTime() * FIELD_SIZE * 2, 0);
        }
    }

    private void checkChest() {
        if (direction.x != 0.0f && !isMotion || direction.y != 0.0f && !isMotion) {
            tempX = (int) (position.x + direction.x);
            tempY = (int) (position.y + direction.y);
            if (FieldType.CHEST == fieldMap.getData()[tempX][tempY].getFieldType()) {
                fieldMap.getData()[tempX][tempY].action();
                bag.add("Chest");
            }
        }
    }
//todo косяк в том что он его запомнил и бьет даже если мы убежали
    private void selectEnemy() {
        float dst;
        float tempDst = weapon.getDistance();
        for (Enemy enemy : fieldMap.getEnemies()) {
            dst = enemy.getPosition().dst(this.position);
            if (dst < weapon.getDistance()) {
                if (tempDst > dst) {
                    tempDst = dst;
                    targetEnemy = enemy;
                }
            }
        }
    }

    private void hit(Enemy enemy) {
        attackTimer -= Gdx.graphics.getDeltaTime();
        if (attackTimer < 0) {
            enemy.takeDamage(weapon.getAttack());
            attackTimer = weapon.getSpeedAttack();
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (targetEnemy != null && targetEnemy.hp > 0) {
            hit(targetEnemy);
        } else {
            targetEnemy = null;
        }
    }

    //    1 ищем монстра ближайшего +
//    2 если нашли, то запоминаем и бьем его, проверяя дистанцию, только до него +
//    3 если он убежал - ищем ближайшего
//    4 есть возможность сменить на ближайшего (TAB)
//    5 Тапнуть на монстра для выбора атаки, при условии дистанции
//    6 сверху отобразить текущего монстра - его жизни
//    7 после смерти ищем следующего
//
//

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public List<String> getBag() {
        return bag;
    }
}
