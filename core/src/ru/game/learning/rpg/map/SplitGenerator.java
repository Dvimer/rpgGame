package ru.game.learning.rpg.map;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class SplitGenerator implements MapGenerator {
    public static List<Node> list = new ArrayList<>();
    private static String[][] field;
    public static final int WIGHT = 80;
    public static final int HEIGHT = 45;
    private static int offset;
    public static final int DEEP = 5;

    @Override
    public String[][] generate() {

        field = new String[WIGHT][HEIGHT];
        initField(WIGHT, HEIGHT);

        Node node = new Node(WIGHT, HEIGHT, DEEP);
        splitNode(node, DEEP);

        setWayBetweenNode(node);
//        printField();
        return field;
    }

    private static void setWayBetweenNode(Node node) {
        if (node.deep > 1) {
            setWayOnField(node);
            drawRectangleOnField(node.way);

            System.out.println(node.way);
            setWayBetweenNode(node.right);
            setWayBetweenNode(node.left);
        }
    }

    private static void printField() {
        for (int i = 0; i < WIGHT; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void initField(int wight, int height) {
        for (int i = 0; i < wight; i++) {
            for (int j = 0; j < height; j++) {
                field[i][j] = "*";
            }
        }
    }

    public static void splitNode(Node node, int deep) {
        int tempDeep = --deep;

        if (deep > 0) {
            splitArea(node);

            Node right = node.right;
            right.deep = deep;
            workWithNode(right, deep, tempDeep);
            Node left = node.left;
            left.deep = deep;
            workWithNode(left, deep, tempDeep);
        }
    }

    private static void workWithNode(Node node, int deep, int tempDeep) {
        if (deep == 1) {
            list.add(node);

            rewriteCoordinateAndSize(node);
            drawRectangleOnField(node);
        }

        splitNode(node, tempDeep);
    }

    private static void setWayOnField(Node node) {
        Node left = node.left;
        Node right = node.right;

        int newXL = left.x + left.wight / 2;
        int newYL = left.y + left.height / 2;

        if (node.isHorisontal) {
            int newYR = right.y + right.height / 2;
            int newHeight = newYR - newYL;
            int newWight = 1;
            node.way = new Node(newXL, newYL, newWight, newHeight);
        } else {
            int newXR = right.x + right.wight / 2;
            int newHeight = 1;
            int newWight = newXR - newXL;
            node.way = new Node(newXL, newYL, newWight, newHeight);
        }
    }

    private static void drawRectangleOnField(Node node) {
        for (int i = node.x; i < node.x + node.wight; i++) {
            for (int j = node.y; j < node.y + node.height; j++) {
                field[i][j] = " ";
            }
        }
    }

    private static void rewriteCoordinateAndSize(Node node) {
        offset = MathUtils.random(1, 3);
        node.x = node.x + offset;
        node.y = node.y + offset;
        node.wight = node.wight - offset;
        node.height = node.height - offset;
    }

    private static void splitArea(Node node) {
        if (node.wight < node.height) {
            splitHorizontal(node);
        } else {
            splitVertical(node);
        }
    }

    private static void splitVertical(Node node) {
        node.isHorisontal = false;
        int randomWeight = MathUtils.random((int) (node.wight / 100.f * 40), (int) (node.wight / 100.f * 60));
        node.left = new Node(node.x, node.y, randomWeight, node.height);
        node.right = new Node(node.x + randomWeight, node.y, node.wight - randomWeight, node.height);
    }

    private static void splitHorizontal(Node node) {
        node.isHorisontal = true;
        int randomHeight = MathUtils.random((int) (node.height / 100.f * 40), (int) (node.height / 100.f * 60));
        node.left = new Node(node.x, node.y, node.wight, randomHeight);
        node.right = new Node(node.x, node.y + randomHeight, node.wight, node.height - randomHeight);
    }
}


