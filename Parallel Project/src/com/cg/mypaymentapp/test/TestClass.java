package com.cg.mypaymentapp.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;



public class TestClass {

	static WalletService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		service= new WalletServiceImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{

		service.createAccount("Amit", "9900112212", new BigDecimal(9000));
		service.createAccount("Ajay", "9963242422", new BigDecimal(6000));
		service.createAccount("Yogini", "9922950519", new BigDecimal(7000));
		
	}

	@After
	public void tearDown() throws Exception {
	}
//  ---------Create Account Testing-----
	@Test
	public void testCreateAccount1() throws InvalidInputException 
	{
		service.createAccount(null, "9942221102", new BigDecimal(1500));
	}


	@Test
	public void testCreateAccount2() throws InvalidInputException 
	{
		service.createAccount("", "9942221102", new BigDecimal(1500));
	}


	@Test
	public void testCreateAccount3() throws InvalidInputException 
	{
		service.createAccount("john", "999", new BigDecimal(1500));
	}


	@Test
	public void testCreateAccount4() throws InvalidInputException 
	{
		service.createAccount("john", "", new BigDecimal(1500));
	}


	@Test
	public void testCreateAccount5() throws InvalidInputException 
	{
		service.createAccount("", "", new BigDecimal(1500));
	}

//	---------------Show Balance Testing----------------

	@Test(expected=InvalidInputException.class)
	public void testShowBalance1() throws InvalidInputException 
	{
		service.showBalance(null);		
	}


	@Test(expected=InvalidInputException.class)
	public void testShowBalance2() throws InvalidInputException 
	{
		service.showBalance("");		
	}


	@Test(expected=InvalidInputException.class)
	public void testShowBalance3() throws InvalidInputException 
	{
		service.showBalance("12345");		
	}


	@Test(expected=InvalidInputException.class)
	public void testShowBalance4() throws InvalidInputException 
	{
		service.showBalance("9900112218");		
	}


	@Test(expected=InvalidInputException.class)
	public void testShowBalance5() throws InvalidInputException 
	{
		service.showBalance("99001122122");		
	}


	@Test(expected=(InvalidInputException.class))
	public void testShowBalance6() throws InvalidInputException 
	{
		Customer customer=service.showBalance("9900112212");
		BigDecimal expectedResult=new BigDecimal(9000);
		BigDecimal obtainedResult=customer.getWallet().getBalance();

		assertEquals(expectedResult, obtainedResult);

	}

// 	-------------------Fund Transfer Testing------------------
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer1() throws InsufficientBalanceException, InvalidInputException 
	{
		service.fundTransfer("9900112212", "9922950519", new BigDecimal(0));		
	}


	@Test(expected=InvalidInputException.class)
	public void testFundTransfer2() throws InsufficientBalanceException, InvalidInputException 
	{
		service.fundTransfer("9900112212", "", new BigDecimal(0));		
	}


	@Test(expected=InvalidInputException.class)
	public void testFundTransfer3() throws InsufficientBalanceException, InvalidInputException 
	{
		service.fundTransfer("", "9922950519", new BigDecimal(500));		
	}
	
	@Test(expected=NullPointerException.class)
	public void testFundTransfer4() throws InsufficientBalanceException, InvalidInputException 
	{
		service.fundTransfer("9922950519", null, new BigDecimal(50));		
	}


	@Test(expected=InvalidInputException.class)
	public void testFundTransfer5() throws InsufficientBalanceException, InvalidInputException 
	{
		service.fundTransfer("9922950519", "9963242422", new BigDecimal(0));		
	}


	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer6() throws InsufficientBalanceException, InvalidInputException 
	{
		service.fundTransfer(null, null, new BigDecimal(0));		
	}


//	-------------------Deposit Amount Testing--------------

	@Test(expected=InvalidInputException.class)
	public void testDepositAmount1() throws InvalidInputException 
	{
		service.depositAmount(null, new BigDecimal(500));
	}


	@Test(expected=InvalidInputException.class)
	public void testDepositAmount2() throws InvalidInputException 
	{
		service.depositAmount("", new BigDecimal(500));
	}


	@Test(expected=InvalidInputException.class)
	public void testDepositAmount3() throws InvalidInputException 
	{
		service.depositAmount("9942221102", new BigDecimal(0));
	}


	@Test(expected=InvalidInputException.class)
	public void testDepositAmount4() throws InvalidInputException 
	{
		service.depositAmount("9922950519", new BigDecimal(-1000));
	}
	
//	------------------Withdraw Amount Testing----------------
	
	
	@Test
	public void testWithdrawAmount1() throws InsufficientBalanceException, InvalidInputException 
	{
		service.withdrawAmount("9900112212", new BigDecimal(15000));	
	}


	@Test(expected=InvalidInputException.class)
	public void testWithdrawAmount2() throws InsufficientBalanceException, InvalidInputException 
	{
		service.withdrawAmount("9900112212", new BigDecimal(0));
	}

	@Test(expected=InvalidInputException.class)
	public void testWithdrawAmount3() throws InsufficientBalanceException, InvalidInputException 
	{
		service.withdrawAmount("9963242422", new BigDecimal(-120));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testWithdrawAmount4() throws InsufficientBalanceException, InvalidInputException 
	{
		service.withdrawAmount(null, new BigDecimal(120));
	}
	
	@Test
	public void testWithdrawAmount5() throws InsufficientBalanceException, InvalidInputException 
	{
		service.withdrawAmount("9922950519", new BigDecimal(120));
	}
	
}
