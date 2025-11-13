
public class DieArgument {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final int six_sides = 6;
		final int twenty_sides = 20;
		
		Die sixDie = new Die(six_sides);
		Die twentyDie = new Die(twenty_sides);
		
		rollDie(sixDie);
		rollDie(twentyDie);
		
		
	}
	
	public static void rollDie(Die d) {
		
		System.out.println("Rolling a " + d.getSides() + " sided died");
		
		d.roll();
	}

}

