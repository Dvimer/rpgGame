package ru.game.learning.rpg.stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import ru.game.learning.MyGame;
import ru.game.learning.rpg.button.GameButton;
import ru.game.learning.rpg.screen.GameScreen;

public class MenuStage extends Stage {

    private OrthographicCamera camera;

    private GameButton gameButton;

    public MenuStage(final Game game) {
        super(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
        Gdx.input.setInputProcessor(this);
        gameButton = new GameButton();
        createTable(game);
        setupCamera();
    }

    private void createTable(Game game) {
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();
        mainTable.add(gameButton.create(game));
        mainTable.row().pad(100);
        mainTable.add(gameButton.create(game));
        mainTable.row();
        mainTable.add(gameButton.create(game));
        mainTable.row();
        addActor(mainTable);
    }

    private void setupCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }
}

