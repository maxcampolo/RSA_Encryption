import java.util.Scanner;


public class Decode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String key;
		long d, n, c;
		char m;
		String[] charArray;
		
		//initialize instance of SimpleCrypto
		SimpleCrypto mCrypto = new SimpleCrypto();
		
		//get user input for public key pair
		Scanner input = new Scanner(System.in);
		StdOut.println("Enter the private key pair separated by a comma i.e. d,n: ");
		key = input.nextLine().trim();
		charArray = key.split(",");
		
		//get e and n from user input
		d = Integer.parseInt(charArray[0].trim());
		n = Integer.parseInt(charArray[1].trim());
		
		//get user input for integer to be decoded c
		StdOut.println("Enter the integer to be decoded: ");
		c = input.nextInt();
		
		//compute decoded character m
		m = (char)mCrypto.expomod(c, d, n);
		
		//print decoded value to user
		StdOut.println("Decoding " + c + " to " + m);
	}

}
