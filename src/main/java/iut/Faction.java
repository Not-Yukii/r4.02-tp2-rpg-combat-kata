package iut;

import java.util.ArrayList;
import java.util.List;

public class Faction {

    private List<Player> players;

    public Faction(String name) {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.joinFaction(this);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        player.leaveFaction(this);
    }

    public boolean hasPlayer(Player player) {
        return players.contains(player);
    }
    
}