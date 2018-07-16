package com.cg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cg.mypaymentapp.DBUtil.DBUtil;
import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InvalidInputException;

public class WalletRepoImpl implements WalletRepo 
{
	private Map<String, Customer> data; 
	private Map<String,ArrayList<String>> trans;
	
	
	public WalletRepoImpl()
	{
		data = new HashMap<String, Customer>();
	}

	public WalletRepoImpl(Map<String, Customer> data) 
	{
		super();
		this.data = data;
	}
	
	@Override
	public boolean save(Customer customer) 
	{
		
		try(Connection con=DBUtil.getConnection())
		{
			try
			{
				PreparedStatement pstm=con.prepareStatement("insert into Wallet values(?,?,?)");
				
				pstm.setString(1, customer.getName());
				pstm.setString(2, customer.getMobileNo());
				pstm.setBigDecimal(3, customer.getWallet().getBalance());
				
				pstm.execute();		
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return true;
	}
	

	@Override
	public Customer findOne(String mobileNo) 
	{
		Customer cust=null;;
		try(Connection con=DBUtil.getConnection())
		{
			try
			{
				PreparedStatement pstm=con.prepareStatement("select * from Wallet where c_mobile_no=?");
				
				pstm.setString(1,mobileNo);
				
				
				ResultSet res=pstm.executeQuery();
				
				if(res.next()==false)
					throw new InvalidInputException("Customer not found with mobile number : "+mobileNo);
			
				cust=new Customer();
				
				cust.setName(res.getString(1));
				cust.setMobileNo(res.getString(2));
				Wallet wallet=new Wallet(res.getBigDecimal(3));
				cust.setWallet(wallet);
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return cust;
	
}
	
	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		
		Customer cust=null;
		
		
		try(Connection con=DBUtil.getConnection())
		{
			try
			{
				PreparedStatement pstm=con.prepareStatement("select * from Wallet where c_mobile_no=?");
				
				pstm.setString(1, mobileNo);
			
				ResultSet res=pstm.executeQuery();
				
				if(res.next()==false)
				{
					throw new InvalidInputException("No customer found with mobile number "+mobileNo);
				}
				
				
				cust=new Customer();
				
				cust.setName(res.getString(1));
				cust.setMobileNo(res.getString(2));
				Wallet wallet=new Wallet(res.getBigDecimal(3));
				cust.setWallet(wallet);
				
				BigDecimal bal1;
				
				bal1=cust.getWallet().getBalance();
				
				bal1=bal1.add(amount);
				
				wallet=new Wallet(bal1);
				
				cust.setWallet(wallet);
				
				PreparedStatement pstm1=con.prepareStatement("update Wallet set c_balance=? where c_mobile_no=?");
				
				pstm1.setBigDecimal(1, bal1);
				pstm1.setString(2, mobileNo);
				
				pstm1.execute();
				
				PreparedStatement ps=con.prepareStatement("insert into Transaction values(?,?)");
				
				ps.setString(1,mobileNo);
				ps.setString(2, amount+" deposited");
				
				ps.execute();
			
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return cust;
	}
		
	
	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		
Customer cust=null;
		
		
		try(Connection con=DBUtil.getConnection())
		{
			try
			{
				PreparedStatement pstm=con.prepareStatement("select * from Wallet where c_mobile_no=?");
				
				pstm.setString(1, mobileNo);
			
				ResultSet res=pstm.executeQuery();
				
				if(res.next()==false)
				{
					throw new InvalidInputException("No customer found with mobile number "+mobileNo);
				}
				
				
				cust=new Customer();
				
				cust.setName(res.getString(1));
				cust.setMobileNo(res.getString(2));
				Wallet wallet=new Wallet(res.getBigDecimal(3));
				cust.setWallet(wallet);
				
				BigDecimal bal1;
				
				bal1=cust.getWallet().getBalance();
				
				bal1=bal1.subtract(amount);
				
				wallet=new Wallet(bal1);
				
				cust.setWallet(wallet);
				
				PreparedStatement pstm1=con.prepareStatement("update Wallet set c_balance=? where c_mobile_no=?");
				
				pstm1.setBigDecimal(1, bal1);
				pstm1.setString(2, mobileNo);
				
				pstm1.execute();
				
				PreparedStatement ps=con.prepareStatement("insert into Transaction values(?,?)");
				
				ps.setString(1,mobileNo);
				ps.setString(2, amount+" withdrawn");
				
				ps.execute();
				
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return cust;
		
	}

	
	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {
		
		Customer cust=null;

		
		try(Connection con=DBUtil.getConnection())
		{
			try
			{
				
				cust=withdrawAmount(sourceMobileNo,amount);
				depositAmount(targetMobileNo,amount); 
				
				PreparedStatement ps=con.prepareStatement("insert into Transaction values(?,?)");
				
				ps.setString(1,sourceMobileNo);
				ps.setString(2, amount+" transfered to "+targetMobileNo);
				
				ps.execute();
		
				PreparedStatement ps1=con.prepareStatement("insert into Transaction values(?,?)");
				
				ps1.setString(1,targetMobileNo);
				ps1.setString(2, amount+" added from "+sourceMobileNo);
				
				ps1.execute();
			}
			
			catch(Exception e)
			{
				e.getMessage();
			}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
		return cust;
	}


	@Override
	public List<String> showTransaction(String mobileNo) {

		Customer cust=null;
		ArrayList<String> message=new ArrayList<String>();

		try(Connection con=DBUtil.getConnection())
		{
			try
			{
				 PreparedStatement pstm=con.prepareStatement("select * from Transaction where c_mobile_no=?");
				 
				 pstm.setString(1, mobileNo);
			
				 ResultSet rs=pstm.executeQuery();
				 
				 if(rs.next()==false)
					 throw new InvalidInputException("No customer found with mobile number "+mobileNo);
						
				 message.add(rs.getString(2));
				 while(rs.next()) {
					 message.add(rs.getString(2));	
				}
				 
				}
			
			catch(Exception e)
			{
				e.getMessage();
			}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
		return message;

	}
	
	
	public Map<String, ArrayList<String>> getTrans() {
		return trans;
	}

	public void setTrans(Map<String, ArrayList<String>> trans) {
		this.trans = trans;
	}

	

	
	
	
	
	

	
	
}
