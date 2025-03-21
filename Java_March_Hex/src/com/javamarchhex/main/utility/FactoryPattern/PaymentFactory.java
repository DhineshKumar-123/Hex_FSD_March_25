package com.javamarchhex.main.utility.FactoryPattern;

/*creates the objects for the Project clss 
Here without doing in controller*/
public class PaymentFactory {
	
	//create a method to get Instance of the Payment
public static Payment getPaymentInstance(PaymentType type)// Payment type is for user to select which
//enum they have to select either UPI or NEFT
{
		
		switch(type) {
			
			case PaymentType.UPI:
				return new UPI("123456", "9885475443");
			case PaymentType.NEFT:
				return new NEFT("123456", "3453465");
			default: 
				return null; 
				 
		}
	}

}
