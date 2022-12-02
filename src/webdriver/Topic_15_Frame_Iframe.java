package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Frame_Iframe {
	WebDriver driver;
	
	
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
	}
	@Test 
	public void TC_01_Kyna_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(3);
		//Verify iframe Fb hiển thị
		//Facebook iframe vẫn tính là 1 element của trang kyna
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'//www.facebook.com')]")).isDisplayed());
		//Switch to ifram FB - 3 cách switch cho frame
		//driver.switchTo().frame(0); // index của frame trong html khi search sẽ có số thứ tự -1 = index
		//driver.switchTo().frame("nameORid"); // tìm theo name or id
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'//www.facebook.com')]")));
		//Verify số lượt like trong iframe FB
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']//parent::div//following-sibling::div")).getText(),"165K lượt thích" );
		//Switch về default window
		driver.switchTo().defaultContent();
		//Từ main page switch qua iframe chat
		driver.switchTo().frame("cs_chat_iframe");
		
		//Click vào chat để show lên chat support
		driver.findElement(By.cssSelector("div.button_bar")).click();
		
		//send key vào chat sp
		driver.findElement(By.cssSelector("input.input-name")).sendKeys("Joo");
		driver.findElement(By.cssSelector("input.input-phone")).sendKeys("74944964");
		new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.name("message")).sendKeys("Tư vấn");
		sleepInSecond(3);
		//Switch về main
		driver.switchTo().defaultContent();	
		//Search với từ khóa excel
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		//Click icon search
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		//verify kết quả
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement webElement : courseName) {
			Assert.assertTrue(webElement.getText().contains("Excel"));
		}
		
		
	  }
	@Test 
	public void TC_02__HDFC_Bank_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		  
		//Switch qua frame chứa login textbox
		driver.switchTo().frame("login-page");
		
		driver.findElement(By.name("fldLoginUserID")).sendKeys("john");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispID")).isDisplayed());
		driver.findElement(By.cssSelector("input#fldPasswordDispID")).sendKeys("1232155jhon");
	  }
	@Test 
	public void TC_03_() {
		  
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
