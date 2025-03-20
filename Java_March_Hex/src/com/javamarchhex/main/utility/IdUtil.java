package com.javamarchhex.main.utility;

public class IdUtil 
{
	public int getRandomId(){
		/*Generate random primary keys for an Id */
		double random = Math.random() * 10000000; // 0.00  0.99  * 10000000
		return (int) random;
	}

}
