package ddg.utils;

public class Dice {
	
	public int d20Roll(){
		int diceNumber = ((int) (Math.random()*100)) % 20;
		return (diceNumber + 1);
	}
	
	public static int d6Roll(){
		int diceNumber = ((int) (Math.random()*100)) % 6;
		return (diceNumber + 1);
	}
	
	public static int d10Roll(){
		int diceNumber = ((int) (Math.random()*100)) % 10;
		return (diceNumber + 1);
	}
	
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
