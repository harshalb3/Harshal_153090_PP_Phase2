package com.cg.mypaymentapp.beans;

public class Customer 
{
	private String name;
	private String mobileNo;
	private Wallet wallet;
	private Transactions transaction;
	
	
	public Customer() 
	{
		
	}
	
	public Customer(String name, String mobileNo, Wallet wallet,Transactions transaction) {
		this.name=name;
		this.mobileNo=mobileNo;
		this.wallet=wallet;
		this.transaction=transaction;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	
	
	public Transactions getTransaction() {
		return transaction;
	}

	public void setTransaction(Transactions transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Customer name=" + name + ", mobileNo=" + mobileNo
				 + wallet;
	}
	
	
}
