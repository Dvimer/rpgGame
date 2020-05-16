package ru.game.learning.rpg.map;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class AddRoomGenerator implements MapGenerator {

    public static List<Node> list = new ArrayList<>();
    private static String[][] field;
    public static final int WIGHT = 80;
    public static final int HEIGHT = 80;
    private static int offset;
    public static final int DEEP = 5;
    private Node[][] roomField;
    private int maxRoom = 10;
    private int roomSize = 10;


    @Override
    public String[][] generate() {
        maxRoom = 10;
        roomField = new Node[DEEP][DEEP];
        field = new String[DEEP * roomSize][DEEP * roomSize];
        initField(field.length, field[0].length);
        int firstX = 0;
        int firstY = 0;
        roomField[firstX][firstY] = new Node(MathUtils.random(0, 3), MathUtils.random(0, 3), MathUtils.random(roomSize - 5, roomSize - 1));
        Coodrinate coodrinate = addRoom(firstX, firstY);
        while (maxRoom > 0) {
            maxRoom--;
            coodrinate = addRoom(coodrinate.x, coodrinate.y);
        }
        for (int i = 0; i < roomField.length; i++) {
            for (int j = 0; j < roomField[0].length; j++) {
                if (roomField[i][j] != null) {
                    drawRectangleOnField(roomField[i][j]);
                }
            }
        }
        return field;
    }

    private static void initField(int wight, int height) {
        for (int i = 0; i < wight; i++) {
            for (int j = 0; j < height; j++) {
                field[i][j] = "*";
            }
        }
    }

    private static void drawRectangleOnField(Node node) {
        for (int i = node.x; i < node.x + node.wight; i++) {
            for (int j = node.y; j < node.y + node.height; j++) {
                field[i][j] = " ";
            }
        }
    }

    private Coodrinate addRoom(int firstX, int firstY) {
        List<Coodrinate> avaliableCoordinate = getAvaliableCoordinate(firstX, firstY);
        Coodrinate coodrinate = avaliableCoordinate.get(MathUtils.random(0, avaliableCoordinate.size() - 1));
        roomField[coodrinate.x][coodrinate.y] = new Node(coodrinate.x * roomSize + MathUtils.random(1, 3), coodrinate.y * roomSize + MathUtils.random(1, 3), MathUtils.random(roomSize - 5, roomSize - 1), MathUtils.random(roomSize - 5, roomSize - 1));
        return coodrinate;
    }

    private List<Coodrinate> getAvaliableCoordinate(int firstX, int firstY) {
        List<Coodrinate> coodrinates = new ArrayList<>();
        System.out.println("try x = " + firstX + " y = " + firstY);
        int tempRoomY = firstY + 1 < DEEP ? firstY + 1 : firstY;
        if (roomField[firstX][tempRoomY] == null) {
            coodrinates.add(new Coodrinate(firstX, tempRoomY));
        }
        tempRoomY = firstY - 1 > 0 ? firstY - 1 : firstY;
        if (roomField[firstX][tempRoomY] == null) {
            coodrinates.add(new Coodrinate(firstX, tempRoomY));
        }
        int tempRoomX = firstX + 1 < DEEP ? firstX + 1 : firstX;
        if (roomField[tempRoomX][firstY] == null) {
            coodrinates.add(new Coodrinate(tempRoomX, firstY));
        }
        tempRoomX = firstX - 1 > 0 ? firstX - 1 : firstX;
        if (roomField[tempRoomX][firstY] == null) {
            coodrinates.add(new Coodrinate(tempRoomX, firstY));
        }
        for (Coodrinate coodrinate : coodrinates) {
            System.out.println(coodrinate.x + "  " + coodrinate.y);
        }
        return coodrinates;
    }
}

// * * * * *
// * * * * *
// * * * * *
// * * # * *
// # # # * *
//1/1
//2/2
//1/3
class Coodrinate {
    public int x, y;

    public Coodrinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}