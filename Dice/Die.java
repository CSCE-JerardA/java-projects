import java.util.Random;

public class Die {

	private int sides;
	private int value;
	
	
	
	public Die(int numSides) {
		sides = numSides;
		roll();
	}
	
	
	
	public void roll() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		value = rand.nextInt(sides)+1;
	}

	
	public int getSides() {
		return sides;
	}
	
	
	public int getValue() {
		return value;
	}

}
