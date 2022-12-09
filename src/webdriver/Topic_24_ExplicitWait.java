package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_ExplicitWait {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
//		if (osName.contains("Mac OS")) {
//		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
//		}
//		else {
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		}
		
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
		
		
	}
	//Coi thêm trong google doc của thầy
	@Test 
	public void TC_01_VisibilityPracticeCase() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 5);
		//Click start btn
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Set explicit wait để chờ element của step sau dc hiển thị,sau khi hiển thị(quá trình hiển thị cần 5s) thì ta mới verify ở step sau dc
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		//Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		  
	  }
	@Test 
	public void TC_02_InvisibilityPracticeCase() {
		
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 5);
		//Click start btn
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Wait cho loading biến mất(làm ngược lại với testcase trên khi wait cho element h4 hiện lên) để tiến hành đến step verify bên dưới
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		//Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		  
		  
	  }
	
	@Test
	public void TC_04_() {
		  
	  }
	@Test 
	public void TC_05_() {
		  
	  }
	@Test 
	public void TC_06_() {
		  
	  }
	@Test 
	public void TC_07_() {
		  
	  }
	@Test 
	public void TC_08_() {
		  
	  }
	@Test 
	public void TC_09_() {
		  
	  }
	@Test 
	public void TC_10_() {
		  
	  }
	@Test 
	public void TC_11_() {
		  
	  }
	@Test 
	public void TC_12_() {
		  
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
