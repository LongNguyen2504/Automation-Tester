package webdriver;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_WebBrowser_Ex {
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
	@Test public void TC_01_Url(){
		driver.get("http://live.techpanda.org/"); // get browser
		//Click vào my account ở footer
		driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
		
		sleepInSecond(2);
		
		//verify url
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		
		//click button creat account
		driver.findElement(By.cssSelector("div.buttons-set a.button")).click();
		
		sleepInSecond(2);		
		//verify url
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
		
		
		
		  
	  }
	@Test public void TC_02_Title() {
		
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
		
		sleepInSecond(2);		
		Assert.assertEquals(driver.getTitle(),"Customer Login");
		
		driver.findElement(By.cssSelector("div.buttons-set a.button")).click();
		
		sleepInSecond(2);		
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
		
		 
	  }
	@Test public void TC_03_Navigate() {
		driver.get("http://live.techpanda.org/"); // get browser
		//Click vào my account ở footer
		driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
		//click button creat account
		driver.findElement(By.cssSelector("div.buttons-set a.button")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.navigate().forward();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
		
		  
	  }
	@Test public void TC_04_Page_Source_HTML() {
		driver.get("http://live.techpanda.org/"); // get browser
		//Click vào my account ở footer
		driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		  
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
