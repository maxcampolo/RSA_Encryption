import java.util.Scanner;


public class DecodeInts {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long d, n;
		String intString;
		int numInts, c;
		String[] inputArray;
		char mChar;
		
		//initialize instance of SimpleCrypto
		SimpleCrypto mCrypto = new SimpleCrypto();
		
		//get user input
		Scanner input = new Scanner(System.in);
		StdOut.println("Enter the decoding exponent d: ");
		d = input.nextLong();
		
		StdOut.println("Enter the modulus n: ");
		n = input.nextLong();
		
		StdOut.println("Enter the number of integers to decode: ");
		numInts = input.nextInt();
		
		input.nextLine();
		
		StdOut.println("Enter the integers separated by a space");
		intString = input.nextLine();
		inputArray = intString.split(" ");
		
		for (int i = 0; i < inputArray.length; i++) {
			c = Integer.parseInt(inputArray[i]);
			mChar = (char)mCrypto.expomod(c, d, n);
			StdOut.print(mChar);
		}

	}

}
