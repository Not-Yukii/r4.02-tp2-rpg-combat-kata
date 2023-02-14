package iut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    public void test() {
        assertThat(1 + 2).isEqualTo(3);
    }
    
    @Test
    public void testIsAlive() {
        Player player1 = new Player();
        assertTrue(player1.isAlive());
        player1.receiveDamage(100);
        assertFalse(player1.isAlive());
    }
    
    @Test
    public void testTakeDamage() {
        Player player1 = new Player();
        Player player2 = new Player();
        player1.receiveDamage(50);
        assertEquals(50, player1.getHealth());
        player2.receiveDamage(120);
        assertEquals(0, player2.getHealth());
        assertFalse(player2.isAlive());
    }
    
    @Test
    public void testHit() {
        Player player1 = new Player();
        Player player2 = new Player();
        player1.hit(player2);
        assertEquals(90, player2.getHealth());
        assertTrue(player2.isAlive());
        player2.hit(player1);
        assertEquals(90, player1.getHealth());
        assertTrue(player1.isAlive());
        player2.hit(player1);
        assertEquals(80, player1.getHealth());
        assertTrue(player1.isAlive());
    }

    @Test
    public void ShouldCreatePlayer() {
        // GIVEN
        Player hunter = new Player();
        // WHEN
        int initialHealthPoints = hunter.getHealth();
        // THEN
        assertThat(initialHealthPoints).isEqualTo(100);
    }

    @Test
    public void testHeal() {
        Player player1 = new Player();
        player1.receiveDamage(50);
        player1.heal(player1);
        assertEquals(60, player1.getHealth());
        player1.receiveDamage(20);
        player1.heal(player1);
        assertEquals(50, player1.getHealth());
        player1.receiveDamage(100);
        player1.heal(player1);
        assertEquals(0, player1.getHealth());
        assertFalse(player1.isAlive());
    }
    
    @Test
    public void testHealDead() {
        Player player1 = new Player();
        player1.receiveDamage(100);
        player1.heal(player1);
        assertEquals(0, player1.getHealth());
        assertFalse(player1.isAlive());
    }

    @Test
    public void testJoinAndLeaveFaction() {
        Player player1 = new Player();
        Player player2 = new Player();
        Faction faction = new Faction("Test faction");

        player1.joinFaction(faction);
        player2.joinFaction(faction);

        assertTrue(faction.hasPlayer(player1));
        assertTrue(faction.hasPlayer(player2));

        player1.leaveFaction(faction);

        assertFalse(faction.hasPlayer(player1));
        assertTrue(faction.hasPlayer(player2));
    }

    @Test
    public void testAlliesCannotHitEachOther() {
        Player player1 = new Player();
        Player player2 = new Player();
        Faction faction = new Faction("Test faction");

        player1.joinFaction(faction);
        player2.joinFaction(faction);

        player1.hit(player2);

        assertEquals(100, player2.getHealth());
        assertTrue(player1.isAlive());
        assertTrue(player2.isAlive());
    }

    @Test
    public void testAlliesCanHealEachOther() {
        Player player1 = new Player();
        Player player2 = new Player();
        Faction faction = new Faction("Test faction");

        player1.joinFaction(faction);
        player2.joinFaction(faction);

        player1.receiveDamage(20);
        player2.heal(player1);

        assertEquals(90, player1.getHealth());
        assertEquals(100, player2.getHealth());
    }
}

