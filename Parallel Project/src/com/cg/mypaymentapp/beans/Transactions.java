package com.cg.mypaymentapp.beans;

import java.util.ArrayList;
import java.util.List;

public class Transactions {

	List<String> transaction=new ArrayList<String>();

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transactions(List<String> transaction) {
		super();
		this.transaction = transaction;
	}

	public List<String> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<String> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Transactions [transaction=" + transaction + "]";
	}

	
	
}
