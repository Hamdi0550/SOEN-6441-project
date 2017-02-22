package ddg.utils;

public class Dice {
	public Dice(){		
		
	}
	
	public int d20Roll(){
		int diceNumber = ((int) (Math.random()*100)) % 20;
//		System.out.println(diceNumber);
		return diceNumber;
	}
	
	public int d6Roll(){
		int diceNumber = ((int) (Math.random()*100)) % 6;
//		System.out.println(diceNumber);
		return diceNumber;
	}
	
	public int d10Roll(){
		int diceNumber = ((int) (Math.random()*100)) % 10;
//		System.out.println(diceNumber);
		return diceNumber;
	}
	
	public int d46Roll(){
		int[] diceNumbers = new int[4];
		for(int i = 0; i < 4; i++){			
			diceNumbers[i] = d6Roll();
			System.out.println(diceNumbers[i]);
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
	
	public static void main(String[] args) {
		Dice d = new Dice();
		int temp = 0;
		int modifier = 0;
		for(int i = 0; i < 100; i++){
			temp = d.d20Roll();
			modifier = (temp / 2 - 5);
			System.out.println(temp + "   " + modifier);
		}
		d.d10Roll();
		System.out.println("======================");
		int d4 = d.d46Roll();
		System.out.println("d4= " + d4);
	}

}
