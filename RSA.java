import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;


public class RSA {
	static SimpleCrypto mCrypto = new SimpleCrypto();
	
	//Function to generate keys - takes number of bits as argument
	public static void KeyGen(int bitlength) throws IOException {
		BigInteger p, q, n, phi;
		Random rand = new Random();
		
		//genrate two distinct primes of size bitlength
		p = new BigInteger (bitlength, 128, rand);
		do {
			q = new BigInteger(bitlength, 128, rand);
		} while (p.compareTo(q) == 0);
		
		//compute modulus n
		n = p.multiply(q);
		
		//compute m = phi(n)
		BigInteger pMinus1 = p.subtract(BigInteger.valueOf(1));
		BigInteger qMinus1 = q.subtract(BigInteger.valueOf(1));
		BigInteger m = pMinus1.multiply(qMinus1);
		phi = m;
		
		//get e relatively prime to m
		BigInteger e = BigInteger.valueOf(3);
		while (e.gcd(m).compareTo(BigInteger.valueOf(1)) > 0) {
			e = e.add(BigInteger.valueOf(2));
		}
		
		//compute d the decryption exponent
		BigInteger d = e.modInverse(m);
		
		//print out values to user
		StdOut.println("~~~~~~~~~~ KeyGen ~~~~~~~~~~");
		StdOut.println("Modulus n = " + n);
		StdOut.println("The value of PHI = " + phi);
		StdOut.println("The value of e = " + e);
		StdOut.println("The value of d = " + d);
		
		//write to public and private files
		File publicFile = new File("public.txt");
		File privateFile = new File("private.txt");
		
		if (!publicFile.exists()) {
			publicFile.createNewFile();
		}
		
		if (!privateFile.exists()) {
			privateFile.createNewFile();
		}
		
		FileWriter publicFW = new FileWriter(publicFile.getAbsoluteFile());
		FileWriter privateFW = new FileWriter(privateFile.getAbsoluteFile());
		BufferedWriter publicBW = new BufferedWriter(publicFW);
		BufferedWriter privateBW = new BufferedWriter(privateFW);
		
		publicBW.write(n.toString());
		publicBW.write("\n");
		publicBW.write(e.toString());
		privateBW.write(n.toString());
		privateBW.write("\n");
		privateBW.write(d.toString());
		
		publicBW.close();
		privateBW.close();
		
	}
	
	//Function to encode file - takes filename as argument
	public static void Encode(String mFile) throws IOException {
		String currString;
		long c;
		char[] inputArray;
		int lineCounter = 0;
		BigInteger n = null;
		BigInteger e = null;
		char mChar;
		String input = "";
		
		BufferedReader keyReader = new BufferedReader(new FileReader("public.txt"));
		
		//get key values from file
		while((currString = keyReader.readLine()) != null) {
			if (lineCounter == 0) {
				n = new BigInteger(currString);
				lineCounter++;
			} else if (lineCounter == 1) {
				e = new BigInteger(currString);
			}
		}
		
		StdOut.println(e + ", " + n);
		keyReader.close();
		
		//read input file and break into characters for encoding
		BufferedReader fileBR = new BufferedReader(new FileReader(mFile));
		while ((currString = fileBR.readLine()) != null) {
			input = input + currString;
		}
		
		inputArray = input.toCharArray();
		
		//initialize ability to write to file
		File encodedFile = new File(mFile + ".enc");
		if (!encodedFile.exists()) {
			encodedFile.createNewFile();
		}
		BufferedWriter encBW = new BufferedWriter(new FileWriter(encodedFile));
			
		//encode and write to file
		for (int i = 0; i < inputArray.length; i++) {
			mChar = inputArray[i];
			c = mCrypto.expomod(mChar, e.intValue(), n.intValue());
			encBW.write(Long.toString(c));
			encBW.write("\n");
			//StdOut.print(c + " ");
		}
		encBW.close();
		
		StdOut.println("Wrote encoded message to file.");
	}
	
	//function to decode file - takes filename as argument
	public static void Decode(String mFile) throws IOException {
		BigInteger n = null;
		BigInteger d = null;
		int lineCounter = 0;
		String currString;
		char mChar;
		
		//read in keys from private key file
		BufferedReader keyReader = new BufferedReader(new FileReader("private.txt"));
		while((currString = keyReader.readLine()) != null) {
			if (lineCounter == 0) {
				n = new BigInteger(currString);
				lineCounter++;
			} else if (lineCounter == 1) {
				d = new BigInteger(currString);
			}
		}
		keyReader.close();
		
		//read in encoded integers and put in array list
		ArrayList<String> inputList = new ArrayList<String>();
		BufferedReader fileBR = new BufferedReader(new FileReader(mFile));
		while ((currString = fileBR.readLine()) != null) {
			inputList.add(currString);
		}
		
		//decode and write to file
		String newFileName = mFile.substring(0, mFile.lastIndexOf('.'));
		File decodedFile = new File(newFileName + ".cop");
		if (!decodedFile.exists()) {
			decodedFile.createNewFile();
		}
		BufferedWriter decBW = new BufferedWriter(new FileWriter(decodedFile));
		
		for (int i = 0; i < inputList.size(); i++) {
			BigInteger c = new BigInteger(inputList.get(i));
			mChar = (char)mCrypto.expomod(c.intValue(), d.intValue(), n.intValue());
			decBW.write(mChar);
			//StdOut.print(mChar);
		}
		decBW.close();
		
		StdOut.println("Decoded message to file.");
	}
	
	public static void main(String[] args) throws IOException {
		//KeyGen(8);
		//Encode("test.txt");
		//Decode("test.txt.enc");
		
		if(args[0].equalsIgnoreCase("-encrypt")) {
			Encode(args[1]);
		} else if (args[0].equalsIgnoreCase("-decrypt")) {
			Decode(args[1]);
		} else {
			KeyGen(Integer.parseInt(args[0]));
		}
	}

}
