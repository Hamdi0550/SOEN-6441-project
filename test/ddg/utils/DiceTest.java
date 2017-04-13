package ddg.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ddg.model.entity.Chest;
import ddg.model.item.BaseItem;

/**
 * This class is to test Dice rolling
 * 
 * @author yufei
 * @date Mar 3, 2017
 *
 */
public class DiceTest {
	
	@Test
	public void testD46Roll(){
		assertTrue(Dice.d46Roll() >= 3 && Dice.d46Roll() <= 18);
		assertFalse(Dice.d46Roll() < 3 && Dice.d46Roll() > 18);
	}
	
	@Test
	public void testD10Roll(){
		assertTrue(Dice.d10Roll() >= 1 && Dice.d10Roll() <= 10);
		assertFalse(Dice.d10Roll() < 1 && Dice.d10Roll() > 10);
	}
	
	@Test
	public void testD20Roll(){
		assertTrue(Dice.d20Roll() >= 1 && Dice.d20Roll() <= 20);
		assertFalse(Dice.d20Roll() < 1 && Dice.d20Roll() > 20);
	}
}
