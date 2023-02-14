package iut;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int health;
    private boolean alive;
    private List<Faction> factions;

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

    public void hit(Player opponent) {
        if (!this.isAlive()) {
            return;
        }

        if (this.isAlly(opponent)) {
            return;
        }

        opponent.receiveDamage(10);
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
}