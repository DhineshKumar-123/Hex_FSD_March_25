package com.javamarchhex.main.utility.FactoryPattern;

public class UPI implements Payment 
{

	String senderAccountDetails;
	String beneficiaryContactNo; 

	@Override
	public double transactionCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getMandate() {
		// TODO Auto-generated method stub
		return null;
	}

	public void UPIMethod() 
	{
		
	}

	public UPI(String senderAccountDetails, String beneficiaryContactNo) {
		super();
		this.senderAccountDetails = senderAccountDetails;
		this.beneficiaryContactNo = beneficiaryContactNo;
	}
}
