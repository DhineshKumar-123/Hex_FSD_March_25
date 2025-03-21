package com.javamarchhex.main.utility.FactoryPattern;

public class PaymentController 
{
	public static void main(String args[])
	{
		/*
		 * We are calling the payment factory to perform the action of getPayment Instance
		 * Because the implementation is in factory not present in controller itself
		 * It is good to write this way of coding
		 * */
		// the returned object is sent to payment interface 
		//then it goes to implementation then provide the output for us
		Payment payment = PaymentFactory.getPaymentInstance(PaymentType.UPI);
		
		//we are calling the method through the payment object 
		System.out.println(payment.transactionCost());
		
		//We are doing the same thing for NEFT also
				payment = PaymentFactory.getPaymentInstance(PaymentType.NEFT);
				System.out.println(payment.transactionCost());//100
	}

}
