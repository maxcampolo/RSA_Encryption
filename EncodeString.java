import java.util.Scanner;


public class EncodeString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long e, n, c;
		String toEncode;
		char[] inputArray;
		char mChar;
		
		//initialize instance of SimpleCrypto
		SimpleCrypto mCrypto = new SimpleCrypto();
		
		//get user input
		Scanner input = new Scanner(System.in);
		StdOut.println("Enter the encoding exponent e: ");
		e = input.nextLong();
		
		StdOut.println("Enter the modulus n: ");
		n = input.nextInt();
		
		input.nextLine();
		
		StdOut.println("Enter the string to encode: ");
		toEncode = input.nextLine();
		inputArray = toEncode.toCharArray();
		
		//compute and print encoded message
		for (int i = 0; i < inputArray.length; i++) {
			mChar = inputArray[i];
			c = mCrypto.expomod(mChar, e, n);
			StdOut.print(" " + c);
		}

	}

}
