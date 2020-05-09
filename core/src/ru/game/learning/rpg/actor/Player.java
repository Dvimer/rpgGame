package ru.game.learning.rpg.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.game.learning.rpg.map.FieldMap;

public class Player extends GameActor {
//    private final TextureRegion[] textureRegion;
    private TextureRegion[] textureStop;
    private TextureRegion[] textureDown;
    private TextureRegion[] textureUp;
    private TextureRegion[] textureRight;
    private TextureRegion[] textureLeft;
    private Rectangle rectangle;

//    private Vector2 temp;
    private FieldMap fieldMap;
    private float secondPerFrame;
    private float animationTimer;
    private float speed;


    public Player(float x, float y, FieldMap fieldMap) {
        this.fieldMap = fieldMap;
        textureStop = new TextureRegion(new Texture(Gdx.files.internal("Player_Go.png"))).split(100,100)[0];
        textureDown = new TextureRegion(new Texture(Gdx.files.internal("Player_Go.png"))).split(100,100)[1];
        textureUp = new TextureRegion(new Texture(Gdx.files.internal("Player_Go.png"))).split(100,100)[2];
        textureRight = new TextureRegion(new Texture(Gdx.files.internal("Player_Go.png"))).split(100,100)[3];
        textureLeft = new TextureRegion(new Texture(Gdx.files.internal("Player_Go.png"))).split(100,100)[4];
        position = new Vector2(x, y);
        rectangle = new Rectangle(x, y, FIELD_SIZE, FIELD_SIZE);
        direction = new Vector2(0, 0);
        secondPerFrame = 0.2f;
        speed = FIELD_SIZE;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        animationTimer += Gdx.graphics.getDeltaTime();
        super.draw(batch, parentAlpha);
        checkScreenBounds();
        int frameIndex = (int) (animationTimer / secondPerFrame) % textureDown.length;
        if(direction.x == 0 && direction.y == 0) {
            batch.draw(textureStop[0], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
        if(direction.y > 0) {
            batch.draw(textureUp[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
        if(direction.y < 0) {
            batch.draw(textureDown[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
        if(direction.x > 0) {
            batch.draw(textureRight[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
        if(direction.x < 0) {
            batch.draw(textureLeft[frameIndex], position.x * FIELD_SIZE, position.y * FIELD_SIZE, FIELD_SIZE,
                    FIELD_SIZE);
        }
        wallOnCells();
    }

    private void wallOnCells() {
        int tempX = (int) (position.x + direction.x);
        int tempY = (int) (position.y + direction.y);
        if (fieldMap.getData()[tempX][tempY].equals(" ")) {
            if (direction.x != 0.0f || direction.y != 0.0f) {
                position.set(position).add(direction.x, direction.y);
                getStage().getCamera().translate( direction.x * FIELD_SIZE,direction.y * FIELD_SIZE,0);
                direction.x = 0;
                direction.y = 0;
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
