package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Popup_Random {
	WebDriver driver;
	JavascriptExecutor jsEx;
	
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
		jsEx = (JavascriptExecutor) driver;
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
	}
	//@Test 
	public void TC_01_javacodegeeks_in_HTML_DOM() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(12); //wait for pop up
		
		//Yêu cầu :
		//Nếu popup xuất hiện thì click để subcribe
		//Nếu popup k xuất hiện thì qua step tiếp theo
		WebElement popUp = driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style*='display:none'])"));
		
		if(popUp.isDisplayed()) {
			System.out.println("Step 2.1 : GO to handle pop-up");
			//Send email
			driver.findElement(By.cssSelector("input.lepopup-ta-left")).sendKeys("test@gmail.com");
			sleepInSecond(3);
			//Click ok btn
			driver.findElement(By.cssSelector("a.lepopup-button ")).click();
			sleepInSecond(10);// chờ confirm pop-up thank you tắt
			
		}
		  else {
			System.out.println("Step 2.2: Go to step 3");
			
		}
		//Verify pop up not display
		Assert.assertFalse(popUp.isDisplayed());
		
		//send key search texbox
		driver.findElement(By.cssSelector("input#s")).sendKeys("Agile Testing Explained");
		sleepInSecond(2);
		//Click search
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(2);
		//Verify result
		Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='post-title']/a[text()='Agile Testing Explained']")).isDisplayed());
		
		
		
		
	  }
	//@Test 
	public void TC_02_KMplayer_in_HTML_DOM() {
		driver.get("https://kmplayer.com/home");
		sleepInSecond(4); //wait for pop up
		
		WebElement popUp = driver.findElement(By.cssSelector("div.pop-layer"));
		if(popUp.isDisplayed()) {
			System.out.println("Step 2.1 : GO to handle pop-up");
			//Close
			jsEx.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("area#btn-r")));
			sleepInSecond(3);		
		}
		  else {
			System.out.println("Step 2.2: Go to step 3");
			
		}
		//click PC 64x tab
		driver.findElement(By.xpath("//li[@class='g_new']/a[text()='PC 64X']")).click();
		sleepInSecond(4);
		//Verify result
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='sub']/h3[text()='KMPlayer 64X']")).isDisplayed());

	  }
	@Test 
	public void TC_03_DeHieu_Not_in_DOM_HTML() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(4); //wait for pop up
		
		//Popup does not exist in HTML after closed
		//->solution: use list<WebElement> để bắt các pop up vì nếu 0 bắt dc pop up nào thì k bị fail testcase như khi dùng
		//lệnh findElement
		List<WebElement> listPopup = driver.findElements(By.cssSelector("div.popup-content"));
		
		if(listPopup.size()>0 && listPopup.get(0).isDisplayed()) {
			System.out.println("GO to popup and close it -> Move to next steps");
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("John Wick");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys("J123"
					+ "@gmail.com");
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("098562314");
			sleepInSecond(3);
			driver.findElement(By.cssSelector("button#close-popup")).click();
		}else {
			System.out.println("Not go to popup -> move to next step");
			
		}
		//Next step
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		driver.findElement(By.cssSelector("input#search-courses")).sendKeys("Khóa học Thiết kế tủ điện");
		driver.findElement(By.cssSelector("i.fa-search")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("h4.name-course")).getText(), "Khóa học Thiết kế tủ điện");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		  
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
