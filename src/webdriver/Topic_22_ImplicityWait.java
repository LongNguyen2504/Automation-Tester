package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_ImplicityWait {
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
		// Cho các cách sau -> Hỏi set up nào sẽ work
		//1
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().window().maximize();
		//2
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//2
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//4
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// Kết luận : cả 4 cách đều work vì từng command đều k ảnh hưởng gì đến nhau
		
	}
	//Coi thêm trong google doc của thầy
	//@Test 
	public void TC_01_NotEnoughTime() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // nếu set line 45 này thì line 36 sẽ bị skip và apply line 45 này thay thế => command set implicity gần nhất sẽ dc apply và thay thế command set implicity trước
		//=> timeout = 3s và Hello World cần 5s để load => case này bị fail
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//Click start btn
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World");
		  
	  }
	@Test 
	public void TC_02_EnoughTime() {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // nếu set line 59 này thì line 36 sẽ bị skip và apply line 59 này thay thế => command set implicity gần nhất sẽ dc apply và thay thế command set implicity trước
		//=> timeout = 3s và Hello World cần 5s để load => case này vừa đủ 5s để loading Hello World => pass
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//Click start btn
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		  
		  
	  }
	@Test 
	public void TC_03_ExtraTime() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // nếu set line 72 này thì line 36 sẽ bị skip và apply line 72 này thay thế => command set implicity gần nhất sẽ dc apply và thay thế command set implicity trước
		//=> timeout = 15s và Hello World cần 5s để load => case này dư  10s(vì cơ chế của wait là chỉ cần tìm thấy thì thời gian còn lại của wait sẽ k cần phải chờ) và chỉ cần 5s để loading Hello World => pass within 5s
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//Click start btn
		driver.findElement(By.cssSelector("div#start>button")).click();
		
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
