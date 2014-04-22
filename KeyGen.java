import java.util.Scanner;


public class KeyGen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleCrypto mCrypto = new SimpleCrypto();
		long p, q, n, phi, e, d;
		
		//get user inputs for p and q
		Scanner input = new Scanner(System.in);
		StdOut.println("Please enter a prime number: ");
		p = input.nextLong();
		
		StdOut.println("Please enter another prime number greater than the first: ");
		q = input.nextLong();
		
		//compute n
		n = p*q;
		
		//compute phi
		phi = (p - 1)*(q - 1);
		
		//compute e
		e = mCrypto.findfirstnocommon(phi);
		d = mCrypto.findinverse(e, phi);
		
		//print values to user
		StdOut.println("~~~~~~~~~~ Values ~~~~~~~~~~");
		StdOut.println("p = " + p + " , q = " + q);
		StdOut.println("The value of n = " + n);
		StdOut.println("The value of PHI = " + phi);
		StdOut.println("The public exponent = " + e);
		StdOut.println("The private key = " + d);
		StdOut.println(" (d) (e) mod PHI = " + (d*e) % phi);
		StdOut.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	}

}
