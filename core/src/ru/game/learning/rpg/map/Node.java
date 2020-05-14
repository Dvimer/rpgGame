package ru.game.learning.rpg.map;

class Node {
    int x;
    int y;
    int wight;
    int height;
    int deep;
    Node left;
    Node right;
    Node way;
    boolean isHorisontal;


    public Node(int wight, int height, int deep) {
        this.deep = deep;
        this.wight = wight;
        this.height = height;
    }

    public Node(int x, int y, int wight, int height) {
        this.wight = wight;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public void printInfo() {
        System.out.println("deep = " + deep + ", x: " + x + "" +
                ", y: " + y +
                ", height: " + height +
                ", wight: " + wight
        );
    }

    @Override
    public String toString() {

        String sLeft = left == null ? "" : ", \nleft = " + left;
        String sRight = right == null ? "" : ", \nright = " + right;
        return "Node{deep = " + deep + " ," +
                "x=" + x +
                ", y=" + y +
                ", wight=" + wight +
                ", height=" + height + sLeft + sRight +
                '}';
    }
}
