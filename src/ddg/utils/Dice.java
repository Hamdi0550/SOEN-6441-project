package ddg.utils;

/**
 * 
 * This class is to simulate rolling a dice by d20 rules.
 * 
 * @author Fei Yu
 * @date Mar 3, 2017
 *
 */
public class Dice {
	
	/**
	 * Return a d20 roll number
	 * @return d20 roll number
	 */
	public static int d20Roll(){
		int diceNumber = ((int) (Math.random()*100)) % 20;
		return (diceNumber + 1);
	}
	
	/**
	 * Return a d6 roll number
	 * @return d6 roll number
	 */
	public static int d6Roll(){
		int diceNumber = ((int) (Math.random()*100)) % 6;
		return (diceNumber + 1);
	}
	
	/**
	 * Return a d10 roll number
	 * @return d10 roll number
	 */
	public static int d10Roll(){
		int diceNumber = ((int) (Math.random()*100)) % 10;
		return (diceNumber + 1);
	}
	
	/**
	 * Return a 4d6 roll number
	 * @return 4d6 roll number
	 */
	public static int d46Roll(){
		int[] diceNumbers = new int[4];
		for(int i = 0; i < 4; i++){			
			diceNumbers[i] = d6Roll();
		}
		
		int sum = 0;
		int min = diceNumbers[0];
		for(int i = 0; i < 4; i++){
			sum += diceNumbers[i];
			if (diceNumbers[i] < min){
				min = diceNumbers[i];
			}
		}
		sum = sum - min;
		return sum;
	}
}
