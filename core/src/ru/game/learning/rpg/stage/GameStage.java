package ru.game.learning.rpg.stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import ru.game.learning.rpg.actor.Chest;
import ru.game.learning.rpg.actor.Enemy;
import ru.game.learning.rpg.actor.Player;
import ru.game.learning.rpg.actor.Tree;
import ru.game.learning.rpg.inputadapter.PlayerInputAdapter;
import ru.game.learning.rpg.map.FieldMap;
import ru.game.learning.rpg.map.FieldType;
import ru.game.learning.rpg.screen.MainScreen;

import java.util.ArrayList;
import java.util.List;

public class GameStage extends Stage {

    private Player player;
    private Enemy enemy;
    private List<Enemy> enemies;
    private OrthographicCamera camera;
    private FieldMap fieldMap;
    private List<Chest> chests;
    private List<Tree> trees;
    private Game game;

    public GameStage(Game game) {
        super(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
        this.game = game;
        setupCamera();
        setupGround();
        setupPlayer();
        setupEnemy();
        setupChest();
        setupTree();
    }

    private void setupChest() {
        Chest chest = new Chest(fieldMap);
        chests = new ArrayList<>();
        chests.add(chest);
        addActor(chest);
    }

    private void setupTree() {
        Tree tree = new Tree(fieldMap);
        trees = new ArrayList<>();
        trees.add(tree);
        addActor(tree);
    }

    private void setupGround() {
        fieldMap = new FieldMap();
        addActor(fieldMap);
    }

    private void setupPlayer() {
        player = new Player(5, 5, fieldMap);
        addActor(player);
        Gdx.input.setInputProcessor(new PlayerInputAdapter(player));

    }

    private void setupEnemy() {
        for (int i = 0; i < 100; i++) {
            int tempX = MathUtils.random(1, 50);
            int tempY = MathUtils.random(1, 44);
            if (FieldType.GROUND == fieldMap.getData()[tempX][tempY].getFieldType()) {
                Enemy enemy = new Enemy(tempX, tempY, fieldMap, player);
                enemies = new ArrayList<>();
                enemies.add(enemy);
                addActor(enemy);
            }
        }
        fieldMap.setEnemies(enemies);

//        for (int i = 0; i < 10; i++) {
//            int tempX = MathUtils.random(1, 10);
//            int tempY = MathUtils.random(1, 10);
//            if (FieldType.GROUND == fieldMap.getData()[tempX][tempY].getFieldType()) {
//                enemy = new Enemy(tempX, tempY, fieldMap, player);
//                addActor(enemy);
//            } else
//                i--;
//        }

    }

    private void setupCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (player.getHp() < 0) {
            game.setScreen(new MainScreen(game));
        }
    }

    @Override
    public void draw() {
        super.draw();
    }


}
