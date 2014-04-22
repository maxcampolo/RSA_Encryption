import java.math.BigInteger;
import java.util.Random;
 
public class BigIntegerRSA {
 
	public static void main(String args[]) {
 
		 // get the number of bits in the primes
		 int primeBits = Integer.parseInt(args[0]);
		 
		 // get a random number
		 Random rnd = new Random();
		 
		 // get two distinct primes of size primeBits
		 BigInteger p = new BigInteger(primeBits,128,rnd);
		 BigInteger q;
		 do q = new BigInteger(primeBits,128,rnd);
		 while(p.compareTo(q) == 0);
		 // compute the modulus
		 BigInteger n = p.multiply(q);
		 // compute m = phi(n)
		 BigInteger pMinus1 = p.subtract(BigInteger.valueOf(1));
		 BigInteger qMinus1 = q.subtract(BigInteger.valueOf(1));
		 BigInteger m = pMinus1.multiply(qMinus1);
		 // get e relatively prime to m
		 BigInteger e = BigInteger.valueOf(3);
		 while(e.gcd(m).compareTo(BigInteger.valueOf(1)) > 0)
		 e = e.add(BigInteger.valueOf(2));
		 // compute d the decryption exponent
		 BigInteger d = e.modInverse(m);
		 System.out.println("e = " + e + "\nd = " + d + "\nn=" + n);
	 }
}