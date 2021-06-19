package com.simplogics.testcases;
import java.io.IOException;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.simplogics.base.BaseData;
import com.simplogics.utilities.TestUtil;

public class APIS extends BaseData {
	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 2)
	public void Apis(String TestData, String runmode) throws InterruptedException, MalformedURLException, IOException {
		// test = extent.createTest("AddSubAccountToInvoice");
		WebDriverWait wait = new WebDriverWait(driver, 70);
		if (!runmode.equals("Y")) {
			throw new SkipException("Skipping the testcase as the Runmode for dataset is No");
		}
		test = extent.createTest(TestData);

		type("Searchbox_CSS", (TestData));
		//String content= driver.findElement(By.cssSelector(OR.getProperty("Nocontents_CSS"))).getText();
		Thread.sleep(5000);
		click("itemclick_CSS");
		Thread.sleep(5000);
		// System.out.println(r.getText());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		// Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("Tryit_CSS"))));
		click("Tryit_CSS");
		//Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("Content_CSS"))));
		Actions builder = new Actions(driver);
		WebElement element = driver.findElement(By.cssSelector(OR.getProperty("Scroll_CSS")));
		builder.moveToElement(element).clickAndHold().perform();
		builder.moveToElement(element).clickAndHold().perform();
		click("SendButton_CSS");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("Data_CSS"))));
		// Thread.sleep(5000);
		builder.moveToElement(element).clickAndHold().perform();
		builder.moveToElement(element).clickAndHold().perform();

		String result = driver.findElement(By.cssSelector(OR.getProperty("Data_CSS"))).getText();
		if (result.contains("true") && (result.contains("200 OK"))) {
			test.log(Status.PASS, "TestData Success :" + TestData);
			test.log(Status.PASS, "HTTP Response =" + result);
			verifyEquals("true", "true");

		} else {

			test.log(Status.FAIL, "HTTP Failed Response =" + result);
			test.log(Status.FAIL, "TestData Failed :" + TestData);
			verifyEquals("true", "false");
		}

		Thread.sleep(5000);
		WebElement element2 = driver.findElement(By.cssSelector(OR.getProperty("mousemove_CSS")));
		builder.moveToElement(element2).release().perform();
		builder.moveToElement(element2).release().perform();
		driver.findElement(By.cssSelector(OR.getProperty("close_CSS"))).click();
		Thread.sleep(5000);
		clearthetextboxfield("clear_CSS");
		Thread.sleep(5000);

	}
	}

