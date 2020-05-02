package ru.game.learning.rpg.labels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

public class TextLabelSimple {

    public Label label1;
    int row_height = Gdx.graphics.getWidth() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;

    public TextLabelSimple() {

        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("bitmapfont/Amble-Regular-26.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.RED;

        label1 = new Label(0 + "", label1Style);
        label1.setSize(Gdx.graphics.getWidth(), row_height);
        label1.setPosition(0, Gdx.graphics.getHeight() - row_height * 2);
        label1.setAlignment(Align.center);
    }

    public void updateText(String text) {
        label1.setText(text);
    }
}
