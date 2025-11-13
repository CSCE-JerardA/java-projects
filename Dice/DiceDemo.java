
public class DiceDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		final int die1_sides = 6;
		final int die2_sides = 12;
		final int max_rolls = 5;
		
		
		Die die1 = new Die(die1_sides);
		Die die2 = new Die(die2_sides);
		
		System.out.println("The simulates the rolling of a " + die1_sides + " sided die and a " + die2_sides + " sided die");
		
		System.out.println("Initial value of the dice: ");
		System.out.println(die1.getValue() + " " + die2.getValue());
		
		System.out.println("Rolling the dice " + max_rolls + " times!");
		
		for(int i = 0; i < max_rolls; i++) {
			die1.roll();
			die2.roll();
			
			System.out.println(die1.getValue() + " " + die2.getValue());
		}
		
		
		
	}

}
