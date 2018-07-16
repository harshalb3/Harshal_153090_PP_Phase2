package com.cg.mypaymentapp.pl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class Client
{
	private Scanner console = new Scanner(System.in);
	private Customer customer;
	private Map<String, Customer> data;
	//private Wallet wallet;
	private WalletService serviceObject;
	
	
	public Client() 
	{
		customer = new Customer();
		serviceObject = new WalletServiceImpl();	
	}
	public void menu()
	{
		int choice = 0;
		
		System.out.println("-------Welcome to Your Wallet-------");
		System.out.println();
		System.out.println("1) Create Account");
		System.out.println("2) Show Balance");
		System.out.println("3) Fund Transfer");
		System.out.println("4) Deposit");
		System.out.println("5) Withdraw");
		System.out.println("6) Show Transactions");
		System.out.println("7) Exit");
		System.out.println();
		System.out.print("Enter your choice: ");
		
		choice = console.nextInt();
		
		switch (choice) {
		case 1:
			//data = new HashMap<String, Customer>();
			String customerName = null;
			String customerMobileNo = null;
			BigDecimal amount = null;
			System.out.println("Enter the Details: ");
			System.out.println();
			
			System.out.print("Enter your Name: ");
			customerName = console.next();
			System.out.println();
			System.out.print("Enter your Mobile Number: ");
			customerMobileNo = console.next();
			System.out.println();
			System.out.print("Enter the amount of balance: ");
			amount = console.nextBigDecimal();
			System.out.println();
			
			customer = serviceObject.createAccount(customerName, customerMobileNo, amount);
			
			System.out.println("Your account is created with details : ");	
			
			System.out.println(customer);
			
			break;
		case 2:
				System.out.println("Enter Your Registered Mobile Number : ");
				String mob=console.next();
			
				customer=serviceObject.showBalance(mob);
				
				
				System.out.println("Customer Name         : "+customer.getName());
				System.out.println("Customer Phone Number : "+customer.getMobileNo());
				System.out.println("Your balance is       : "+customer.getWallet().getBalance());
				System.out.println();
				
			break;
		
		case 3:
				System.out.println("Enter Your Registered Mobile Number : ");
				String mob1=console.next();
				
				
				System.out.println("Enter the number to which you want to transfer : ");
				String mob4=console.next();
				
				System.out.println("Enter the amount you want to transfer : ");
				BigDecimal amt4=console.nextBigDecimal();
				
				customer=serviceObject.fundTransfer(mob1, mob4, amt4);
				
				System.out.println("Amount successfully transfered");
				System.out.println(customer);
				
			break;
			
		case 4:
				System.out.println("Enter Your Registered Mobile Number :");
				String mob2=console.next();
				
				System.out.println("Enter the amount you want to deposit :");
				BigDecimal amt1=console.nextBigDecimal();
				
				customer=serviceObject.depositAmount(mob2, amt1);
			
				System.out.println("Deposit Successfull");
				
				System.out.println("New Details :");
				System.out.println(customer);
			break;
			
		case 5:
			System.out.println("Enter Your Registered Mobile Number :");
			String mob3=console.next();
			
			System.out.println("Enter the amount you want to withdraw :");
			BigDecimal amt2=console.nextBigDecimal();
			
			customer=serviceObject.withdrawAmount(mob3, amt2);
			
			System.out.println("Withdraw Successfull");
			
			System.out.println("New Details :");
			System.out.println(customer);
			break;
			
		case 6:
			System.out.println("Enter Your Registered Mobile Number :");
			String mob5=console.next();
			
			List<String> list=new ArrayList<String>();
			 
			list=serviceObject.showTransaction(mob5);
			
			Iterator it=list.iterator();
			
			while(it.hasNext())
			{
				System.out.println(it.next());
			}
			break;
			
		case 7:System.out.println("Thankyou For Using The Wallet");
				System.exit(0);
			break;
			
		default:
		       System.out.println("Please, enter the right choice!!!");
			break;
		}
		
	}
    public static void main(String[] args)
    {
    	Scanner console = new Scanner(System.in);
		Client client = new Client();
		
		while(true)	
			client.menu();
			
	}
}
