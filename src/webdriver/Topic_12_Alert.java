package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
	}
	//@Test 
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		// switch to alert chỉ khi alert đang xuất hiện
		alert = driver.switchTo().alert();
		
		//Verify alert title trước khi accept alert ()
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		
		alert.accept(); // accept -> ok
		
		//alert.dismiss(); // cancel
		
		//alert.getText(); // get alrt title -> verify
		
		//alert.sendKeys("abc"); // send value
		
		//Verify alert dc accept thành công
		Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked an alert successfully");
		
		//Exception 1 : Alert k xuất hiện nhưng vẫn switch(Bỏ line 42 để demo) -> báo lỗi NoAlertPresentException
		
		// Excep 2 : Xuất hiện alert nhưng k switch qua (bỏ line 44 để demo)-> báo lỗi nullPointerException
		
		// Excep 3 : nếu accept trước thì alert sẽ biến mất và k verify dc title -> báo lỗi noAlertPresent
		  
	  }
	//@Test 
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		// switch to alert chỉ khi alert đang xuất hiện
		alert = driver.switchTo().alert();
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked: Cancel");
		  
	  }
	//@Test 
	public void TC_03_Prompt_Alert() {
		String key = "Selenium driver";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		
		
		// switch to alert chỉ khi alert đang xuất hiện
		alert = driver.switchTo().alert();
		
		//Verify alert title trước khi accept alert ()
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		//send key
		alert.sendKeys(key);
		
		//click ok
		alert.accept();
		//verify result
		Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You entered: " + key);
		  
	  }
	//@Test
	public void TC_04_Alert() {
		
		driver.get("https://demo.guru99.com/v4/index.php");
		sleepInSecond(2);
		  
		driver.findElement(By.name("btnLogin")).click();
		
		alert = driver.switchTo().alert();
		sleepInSecond(2);
		
		//verify title
		Assert.assertEquals(alert.getText(),"User or Password is not valid");
		sleepInSecond(2);
		
		alert.accept();
		sleepInSecond(2);
		
		//verify url
		Assert.assertEquals(driver.getCurrentUrl(),"https://demo.guru99.com/v4/index.php");
		sleepInSecond(2);
		
	
		
	  }
	//@Test 
	public void TC_05_Authentication() {
		//Pass hẳn user/pass vào Url trước khi mở url
		// Url : https://the-internet.herokuapp.com/basic_auth
		//Pass : Username/ password vào Url
		// https://username:password@the-internet.herokuapp.com/basic_auth
		
		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText().contains("Congratulations! You must have the proper credentials."));
		
		  
		  
	  }
	@Test 
	public void TC_06_() {
		driver.get("https://the-internet.herokuapp.com/");
		sleepInSecond(2);
		String basicAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href"); // thuộc tính href chứ link navigate của element đó
		// -> click vào đây để lấy href nhằm trigger alert authen của web bởi vì alert bị động
		
		driver.get(getAuthenticationUrl(basicAuthenUrl, "admin", "admin"));
		
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText().contains("Congratulations! You must have the proper credentials."));
		  
	  }
	// Case Exceptional : Sẽ có web k thể handle dc login credential alert -> dùng autoIT để handle -> chỉ dùng hco
	//window -> coi tài liệu cụ thể topic 09
	@Test 
	public void TC_07_() {
		  
	  }

	public String getAuthenticationUrl(String basicAuthUrl,String userName,String passWord) {
		String[] authenUrlArray = basicAuthUrl.split("//");
		basicAuthUrl = authenUrlArray[0] + "//" + userName 
				+ ":" + passWord + "@" + authenUrlArray[1];
		return basicAuthUrl;
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000); // chờ load trang sau khi click -> thư viện của java
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());;
		}
	}
	
	
	
	/*
	 * @Test public void TC_0_() {
	 * 
	 * }
	 * 
	 * @Test public void TC_09_() {
	 * 
	 * }
	 * 
	 * @Test public void TC_10_() {
	 * 
	 * }
	 * 
	 * @Test public void TC_11_() {
	 * 
	 * }
	 */

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
