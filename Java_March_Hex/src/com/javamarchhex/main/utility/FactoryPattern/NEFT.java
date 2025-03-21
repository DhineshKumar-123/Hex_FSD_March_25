package com.javamarchhex.main.utility.FactoryPattern;

public class NEFT implements Payment
{
	String senderAccountDetails;
	String beneficiaryAccountDetails;

	@Override
	public double transactionCost() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public String[] getMandate() {
		// TODO Auto-generated method stub
		return null;
	}

	public NEFT(String senderAccountDetails, String beneficiaryAccountDetails) {
		super();
		this.senderAccountDetails = senderAccountDetails;
		this.beneficiaryAccountDetails = beneficiaryAccountDetails;
	}
	
	

}
