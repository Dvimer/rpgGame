package ru.game.learning.rpg.button;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import ru.game.learning.rpg.screen.GameScreen;

public class GameButton {
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;

    public GameButton() {
        myTexture = new Texture(Gdx.files.internal("bar/poisonHp.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
    }

    public ImageButton create(final Game game) {
        ImageButton playButton = new ImageButton(myTexRegionDrawable);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
        return playButton;
    }

    public void dispose() {
        myTexture.dispose();
    }
}
