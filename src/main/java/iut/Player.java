package iut;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Player {
    private int health;
    private boolean alive;
    private List<Faction> factions;
    private int attackDistance;
    private FighterType fighterType;
    private Point position;

    public Player() {
        this.health = 100;
        this.alive = true;
        this.factions = new ArrayList<>();
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getAttackDistance() {
        return attackDistance;
    }

    public void setAttackDistance(int attackDistance) {
        this.attackDistance = attackDistance;
    }

    public FighterType getFighterType() {
        return fighterType;
    }

    public void setFighterType(FighterType fighterType) {
        this.fighterType = fighterType;
    }

    public void hit(Player opponent) {
        if (!opponent.isAlive()) {
            return;
        }

        if (this.isAlly(opponent)) {
            return;
        }

        if (this.isInRange(opponent)) {
            opponent.receiveDamage(10);
        }
    }

    public void receiveDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0;
            this.alive = false;
        }
    }

    public void heal(Player player) {
        if (!player.isAlive()) {
            return;
        }

        player.health += 10;
        if (player.health > 100) {
            player.health = 100;
        }
    }

    public void joinFaction(Faction faction) {
        if (this.factions.contains(faction)) {
            return;
        }

        this.factions.add(faction);
        faction.addPlayer(this);
    }

    public void leaveFaction(Faction faction) {
        if (!this.factions.contains(faction)) {
            return;
        }

        this.factions.remove(faction);
        faction.removePlayer(this);
    }

    public boolean isAlly(Player player) {
        for (Faction faction : this.factions) {
            if (faction.hasPlayer(player)) {
                return true;
            }
        }

        return false;
    }

    public boolean isInRange(Player opponent) {
        int distance = Math.abs(this.getAttackDistance() - opponent.getAttackDistance());
        if (this.fighterType == FighterType.RANGED) {
            return distance <= 20;
        } else {
            return distance <= 2;
        }
    }

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }
}

enum FighterType {
    MELEE,
    RANGED
}