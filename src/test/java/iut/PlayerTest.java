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
        player1.setHealthPoints(0);
        assertFalse(player1.isAlive());
    }
    
    @Test
    public void testTakeDamage() {
        Player player1 = new Player();
        Player player2 = new Player();
        player1.takeDamage(50);
        assertEquals(50, player1.getHealthPoints());
        player2.takeDamage(120);
        assertEquals(0, player2.getHealthPoints());
        assertFalse(player2.isAlive());
    }
    
    @Test
    public void testHit() {
        Player player1 = new Player();
        Player player2 = new Player();
        player1.hit(player2);
        assertEquals(90, player2.getHealthPoints());
        assertTrue(player2.isAlive());
        player2.hit(player1);
        assertEquals(90, player1.getHealthPoints());
        assertTrue(player1.isAlive());
        player2.hit(player1);
        assertEquals(80, player1.getHealthPoints());
        assertTrue(player1.isAlive());
    }

    @Test
    public void ShouldCreatePlayer() {
        // GIVEN
        Player hunter = new Player();

        // WHEN
        int initialHealthPoints = hunter.getHealthPoints();

        // THEN
        assertThat(initialHealthPoints).isEqualTo(100);

    }

    @Test
    public void testHeal() {
        Player player1 = new Player();
        player1.setHealthPoints(50);
        player1.heal();
        assertEquals(60, player1.getHealthPoints());
        player1.setHealthPoints(90);
        player1.heal();
        assertEquals(100, player1.getHealthPoints());
        player1.setHealthPoints(0);
        player1.heal();
        assertEquals(0, player1.getHealthPoints());
        assertFalse(player1.isAlive());
    }
    
    @Test
    public void testHealDead() {
        Player player1 = new Player();
        player1.setHealthPoints(0);
        player1.heal();
        assertEquals(0, player1.getHealthPoints());
        assertFalse(player1.isAlive());
    }
}

