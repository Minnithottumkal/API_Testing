package com.simplogics.testcases;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.simplogics.base.BaseData;
import com.simplogics.utilities.TestUtil;

public class Loginpage extends BaseData{
	@Test(dataProviderClass=TestUtil.class,dataProvider = "dp",priority = 1)
	public void loginpage(String email,String password,String Y) throws InterruptedException, MalformedURLException, IOException{
		test=extent.createTest("Loginpage");
		if(!(TestUtil.isTestRunnable("Loginpage", excel))){
			
			throw new SkipException("Skipping the test "+"Loginpage".toUpperCase()+ "as the Run mode is NO");
		}
		click("Signin_CSS");
		Thread.sleep(3000);
		type("email_CSS",(email));
		type("password_CSS",(password));
		click("loginbtn_CSS");
		log.debug("login successfully");
		Thread.sleep(5000);
		
		
	}
}
