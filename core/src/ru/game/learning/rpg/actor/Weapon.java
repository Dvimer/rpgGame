package ru.game.learning.rpg.actor;

public class Weapon {
    private String name;
    private String type;
    private float distance;
    private float attack;
    private float speedAttack;

    public Weapon(String name, String type, float distance, float attack, float speedAttack) {
        this.name = name;
        this.type = type;
        this.distance = distance;
        this.attack = attack;
        this.speedAttack = speedAttack;
    }

    public float getDistance() {
        return distance;
    }

    public float getAttack() {
        return attack;
    }

    public float getSpeedAttack() {
        return speedAttack;
    }
}
