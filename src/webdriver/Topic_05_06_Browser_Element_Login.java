package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_06_Browser_Element_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
	@Test public void TC_01_Empty_Login() {
		driver.get("http://live.techpanda.org/"); // get browser
		sleepInSecond(3);
		//Click vào my account ở footer
		driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		
		//Verify message error
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");
	  }
	@Test public void TC_02_Login_Invalid_Email() {
		
		driver.get("http://live.techpanda.org/"); // get browser
		sleepInSecond(3);
		
		//Click vào my account ở footer
		driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("1234141@12313223.1231233");
		driver.findElement(By.id("pass")).sendKeys("123445");
		
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		
		//Verify error message
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
		  
	  }
	@Test public void TC_03_Pass_Invalid_Login() {
		driver.get("http://live.techpanda.org/"); // get browser
		sleepInSecond(3);
		
		//Click vào my account ở footer
		driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("1");
		
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		
		//Verify error message
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
		  
	  }
	@Test public void TC_04_Invalid_Email_Pwd_Login() {
		driver.get("http://live.techpanda.org/"); // get browser
		sleepInSecond(3);
		
		//Click vào my account ở footer
		driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("automation12312@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123");
		
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		
		//Verify error message
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");
		  
	  }
	@Test public void TC_05_() {
		  
	  }
	@Test public void TC_06_() {
		  
	  }
	@Test public void TC_07_() {
		  
	  }
	@Test public void TC_08_() {
		  
	  }
	@Test public void TC_09_() {
		  
	  }
	@Test public void TC_10_() {
		  
	  }
	@Test public void TC_11_() {
		  
	  }
	@Test public void TC_12_() {
		  
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
