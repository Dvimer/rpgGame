package ru.game.learning.rpg.map;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class AddRoomGenerator implements MapGenerator {

    public static List<Node> list = new ArrayList<>();
    private static String[][] field;
    public static final int WIGHT = 80;
    public static final int HEIGHT = 45;
    private static int offset;
    public static final int DEEP = 5;
    private Node[][] roomField;
    private int maxRoom = 10;
    private int roomSize = 15;


    @Override

    public String[][] generate() {
        maxRoom = 10;
        roomField = new Node[DEEP][DEEP];

        int firstX = 0;
        int firstY = 0;
        roomField[firstX][firstY] = new Node(MathUtils.random(0, 3), MathUtils.random(0, 3), MathUtils.random(roomSize - 5, roomSize - 1));

        int tempRoomX = firstX + 1;
        int tempRoomY = firstY + 1;
        if (roomField[tempRoomX][tempRoomY] != null) {

        }
        return null;
    }
}
