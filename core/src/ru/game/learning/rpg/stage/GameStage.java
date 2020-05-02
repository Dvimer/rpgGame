package ru.game.learning.rpg.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import ru.game.learning.rpg.actor.Enemy;
import ru.game.learning.rpg.actor.Ground;
import ru.game.learning.rpg.actor.Player;
import ru.game.learning.rpg.actor.Wall;
import ru.game.learning.rpg.inputadapter.PlayerInputAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class GameStage extends Stage {
    public static int FIELD_SCALE = 50;
    public static final int VIEWPORT_WIDTH = FIELD_SCALE * 10;
    public static final int VIEWPORT_HEIGHT = FIELD_SCALE * 10;

    private Player player;
    private Enemy enemy;
    private List<Wall> walls;
    private OrthographicCamera camera;

    public GameStage() {
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        setupCamera();
        setupGround();
        setupPlayer();
        setupEnemy();
//        setupText();
        setupWall();
    }

    private void setupWall() {
        walls = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j = j + 9) {
                Wall actor = new Wall(i * FIELD_SCALE, j * FIELD_SCALE, FIELD_SCALE, FIELD_SCALE);
                Wall actor1 = new Wall(j * FIELD_SCALE, i * FIELD_SCALE, FIELD_SCALE, FIELD_SCALE);
                addActor(actor);
                addActor(actor1);
                walls.add(actor);
                walls.add(actor1);
            }
        }
    }

//    private void setupText() {
//        TextField usernameTextField = new TextField("5", new TextField.TextFieldStyle());
//        usernameTextField.setPosition(0, 0);
//        usernameTextField.setSize(50, 50);
//
//        addActor(usernameTextField);
//
//    }

    private void setupGround() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                addActor(new Ground(i * FIELD_SCALE, j * FIELD_SCALE, FIELD_SCALE, FIELD_SCALE));
            }
        }
    }

    private void setupPlayer() {
        player = new Player(5 * FIELD_SCALE, 5 * FIELD_SCALE, FIELD_SCALE, FIELD_SCALE);
        addActor(player);
        Gdx.input.setInputProcessor(new PlayerInputAdapter(player, camera));
    }

    private void setupEnemy() {
        enemy = new Enemy(MathUtils.random(1, 8) * FIELD_SCALE, MathUtils.random(1, 8) * FIELD_SCALE, FIELD_SCALE, FIELD_SCALE);
//        if (enemy.getRectangle().getX() == player.getRectangle().getX() && enemy.getRectangle().getY() == player.getRectangle().getY()) {
//            setupEnemy();
//        }
        addActor(enemy);
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
            setupEnemy();


//            enemy = new Enemy(MathUtils.random(0, 10) * FIELD_SCALE, MathUtils.random(0, 10) * FIELD_SCALE, FIELD_SCALE, FIELD_SCALE);
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
