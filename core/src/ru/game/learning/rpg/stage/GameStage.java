package ru.game.learning.rpg.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import ru.game.learning.rpg.actor.*;
import ru.game.learning.rpg.inputadapter.PlayerInputAdapter;
import ru.game.learning.rpg.labels.TextLabelSimple;

import java.util.ArrayList;
import java.util.List;

import static ru.game.learning.rpg.actor.GameActor.FIELD_SCALE;

public class GameStage extends Stage {

    public static final int COUNT_FIELD = 20;
    public static final int VIEWPORT_WIDTH = FIELD_SCALE * COUNT_FIELD;
    public static final int VIEWPORT_HEIGHT = FIELD_SCALE * COUNT_FIELD;

    private Player player;
    private Enemy enemy;
    private Door door;
    private TextLabelSimple textLabelSimple;
    private List<Wall> walls;
    private OrthographicCamera camera;
    private int score = 0;

    public GameStage() {
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        setupCamera();
        setupGround();
        setupPlayer();
        setupEnemy();
        setupWall();
        setupDoor();
        setupLabel();
    }

    private void setupLabel() {
        textLabelSimple = new TextLabelSimple();
        addActor(textLabelSimple.label1);
    }

    private void setupDoor() {
        final Wall hole = walls.get(MathUtils.random(walls.size()));
        door = new Door(hole.getRectangle());
        hole.remove();
        addActor(door);
    }

    private void setupWall() {
        walls = new ArrayList<>();
        for (int i = 0; i < COUNT_FIELD; i++) {
            for (int j = 0; j < COUNT_FIELD; j = j + COUNT_FIELD - 1) {
                Wall actor = new Wall(i, j);
                Wall actor1 = new Wall(j, i);
                addActor(actor);
                addActor(actor1);
                walls.add(actor);
                walls.add(actor1);
            }
        }
    }

    private void setupGround() {
        for (int i = 0; i < COUNT_FIELD; i++) {
            for (int j = 0; j < COUNT_FIELD; j++) {
                addActor(new Ground(i, j));
            }
        }
    }

    private void setupPlayer() {
        player = new Player(COUNT_FIELD / 2, COUNT_FIELD / 2);
        addActor(player);
        Gdx.input.setInputProcessor(new PlayerInputAdapter(player, camera));
    }

    private void setupEnemy() {
        enemy = new Enemy(MathUtils.random(1, COUNT_FIELD - 2), MathUtils.random(1, COUNT_FIELD - 2));
        addActor(enemy);
        //     if (enemy.getRectangle().getX() == player.getRectangle().getX() && enemy.getRectangle().getY() == player.getRectangle().getY()) {
//            setupEnemy();
//        }
    }


    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (player.getRectangle().overlaps(enemy.getRectangle())) {
            enemy.remove();
            textLabelSimple.updateText(++score + "");
            setupEnemy();
//            enemy = new Enemy(MathUtils.random(0, 10) * FIELD_SCALE, MathUtils.random(0, 10) * FIELD_SCALE, FIELD_SCALE, FIELD_SCALE);
        }
        if (player.getRectangle().overlaps(door.getRectangle())) {
            player.setCoodrinate(COUNT_FIELD / 2, COUNT_FIELD / 2);
        }
//        for (Wall wall : walls) {
//            if (player.getRectangle().overlaps(wall.getRectangle())) {
//
//            }
//
//        }


    }

    @Override
    public void draw() {
        super.draw();
    }
}
