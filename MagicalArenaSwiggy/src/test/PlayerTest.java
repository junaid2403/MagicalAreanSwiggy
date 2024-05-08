package test.java.com.swiggy.magicalarena.testing;
import main.java.com.swiggy.magicalarena.code.classes.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testWizardInitialization() {
        Player player = new Player(50, 5, 10);
        assertEquals(50, player.getHealth());
        assertEquals(5, player.getStrength());
        assertEquals(10, player.getAttackPower());
        assertTrue(player.isAlive());
    }

    @Test
    public void testReduceHealth() {
        Player player = new Player(50, 5, 10);
        player.reduceHealth(20);
        assertEquals(30, player.getHealth());
        assertTrue(player.isAlive());
    }

    @Test
    public void testReduceHealthBelowZero() {
        Player player = new Player(50, 5, 10);
        player.reduceHealth(60); // Reducing health beyond zero
        assertEquals(0, player.getHealth());
        assertFalse(player.isAlive());
    }
}
