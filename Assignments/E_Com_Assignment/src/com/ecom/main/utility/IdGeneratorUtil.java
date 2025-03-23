package com.ecom.main.utility;

public class IdGeneratorUtil 
{
	public int getRandomId()
	{
		/*Generate random primary keys for an Id */
		double random = Math.random() * 10000000; // 0.00  0.99  * 10000000
		return (int) random;
	}
}
