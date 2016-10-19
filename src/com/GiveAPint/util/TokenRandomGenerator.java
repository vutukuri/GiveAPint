package com.GiveAPint.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TokenRandomGenerator {

	private static TokenRandomGenerator instance = null;
	private SecureRandom random = null;
	
	//Think of the uses of private constructor in this case.
	//Basically we are allowing at maximum of one instance for this class.
	private TokenRandomGenerator()
	{
		this.random = new SecureRandom();
	}
	
	public static TokenRandomGenerator getInstance()
	{
		if( instance == null )
		{
			instance = new TokenRandomGenerator();
		}
		return instance;
	}
	
	public String getRandomToken()
	{
		// Constructs a randomly generated BigInteger, uniformly distributed
		// over the range 0 to (2numBits - 1), inclusive.
		return new BigInteger(64, random).toString(32);
	}
}
 