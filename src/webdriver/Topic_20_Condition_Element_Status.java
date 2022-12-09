package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Condition_Element_Status {
	WebDriver driver;
	WebDriverWait explicityWait;
	
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
		explicityWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
	}
	//@Test 
	public void TC_01_Visibility_Displayed() {
		driver.get("https://www.facebook.com/");
		// 1.Có hiển thị trên UI(bắt buộc)
		// 2.Có trong HTML(bắt buộc)
		
		//Chờ cho email address txtBox hiển thị trong vòng 10s
		explicityWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	  }
	//@Test 
	public void TC_02_Unvislble_Undisplayed_Invisibility_I() {
		driver.get("https://www.facebook.com/");
		// 1.ko hiển thị trên UI(bắt buộc)
		// 2.Có trong HTML
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		//Chờ cho re-email address txtBox không hiển thị trong vòng 10s
		explicityWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	  }
	public void TC_02_Unvislble_Undisplayed_Invisibility_II() {
		driver.get("https://www.facebook.com/");
		// 1.ko hiển thị trên UI(bắt buộc)
		// 2.ko có trong HTML
		
		//Chờ cho re-email address txtBox không hiển thị trong vòng 10s
		explicityWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	  }
	//@Test 
	public void TC_03_Presence_I() {
		driver.get("https://www.facebook.com/");
		// 1.hiển thị trên UI
		// 2.Có trong HTML(bắt buộc)
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		//Chờ cho re-email address txtBox presence trong HTML trong vòng 10s
		explicityWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	  }
	@Test
	public void TC_04_Presence_II() {
		driver.get("https://www.facebook.com/");
		// 1.ko hiển thị trên UI
		// 2.Có trong HTML(bắt buộc)
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		//Chờ cho re-email address txtBox không hiển thị  trong vòng 10s
		//WebElement reEnter = driver.findElement(By.name("reg_email_confirmation__"))
		explicityWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
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
