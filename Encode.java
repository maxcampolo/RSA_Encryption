import java.util.Scanner;


public class Encode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String key, toBeEncoded;
		long e, n, c;
		char m;
		String[] charArray;
		
		//initialize instance of SimpleCrypto
		SimpleCrypto mCrypto = new SimpleCrypto();
		
		//get user input for public key pair
		Scanner input = new Scanner(System.in);
		StdOut.println("Enter the public key pair separated by a comma i.e. e,n: ");
		key = input.nextLine().trim();
		charArray = key.split(",");
		
		//get e and n from user input
		e = Integer.parseInt(charArray[0].trim());
		n = Integer.parseInt(charArray[1].trim());
		
		//get user input for character to be encoded m
		StdOut.println("Enter a single character to be encoded: ");
		toBeEncoded = input.nextLine().trim();
		m = toBeEncoded.charAt(0);
		
		//compute encoded value
		c = mCrypto.expomod(m, e, n);
		
		//print to user
		StdOut.println("Transmitting encoded character " + m + " as " + c);
	}

}
