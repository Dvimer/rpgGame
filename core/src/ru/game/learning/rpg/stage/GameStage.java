package ru.game.learning.rpg.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import ru.game.learning.rpg.actor.Enemy;
import ru.game.learning.rpg.actor.Player;
import ru.game.learning.rpg.inputadapter.PlayerInputAdapter;
import ru.game.learning.rpg.map.FieldMap;

public class GameStage extends Stage {

    private Player player;
    private Enemy enemy;
    private OrthographicCamera camera;
    private FieldMap fieldMap;

    public GameStage() {
        super(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
        setupCamera();
        setupGround();
        setupPlayer();
        setupEnemy();
    }

    private void setupGround() {
        fieldMap = new FieldMap();
        addActor(fieldMap);
    }

    private void setupPlayer() {
        player = new Player(10, 5, fieldMap);
        addActor(player);
        Gdx.input.setInputProcessor(new PlayerInputAdapter(player));
    }

    private void setupEnemy() {
        for (int i = 0; i < 10; i++) {
        int tempX = MathUtils.random(3, 8);
        int tempY = MathUtils.random(3, 8);
        if (fieldMap.getData()[tempX][tempY].equals(" ")) {
                enemy = new Enemy(tempX, tempY, fieldMap);
                addActor(enemy);
            }else
                i--;
        }
    }

    private void setupCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
