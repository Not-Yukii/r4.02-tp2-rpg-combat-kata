package iut;

public class Player {
    private int healthPoints;
    private boolean alive;

    public Player() {
        this.healthPoints = 100;
        this.alive = true;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
        if (this.healthPoints <= 0) {
            this.healthPoints = 0;
            this.alive = false;
        }
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void hit(Player opponent) {
        opponent.takeDamage(10);
    }

    public void takeDamage(int damage) {
        this.healthPoints -= damage;
        if (this.healthPoints <= 0) {
            this.healthPoints = 0;
            this.alive = false;
        }
    }
}

