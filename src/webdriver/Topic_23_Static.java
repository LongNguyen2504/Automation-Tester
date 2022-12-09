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

public class Topic_23_Static {
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
		
		
		
	}
	//Coi thêm trong google doc của thầy
	//Static wait(Thread.sleep-Java Thread):
	//Chỉ dùng cho các case đặc thù vì các dự án thực tế ta k thể biết chính xác step đó cần wait bao lâu dẫn đến set time wait thiếu chính xác => mất tính hiệu quả dự án
	//Chạy script trên browser là IE/Edge Legacy
	//Switch Windows ( ko có hàm wait cụ thể nên dùng sleep static)
	//Chỉ apple ở mức thử nghiệm/implement testcase - ko dùng bừa bãi
	
	//@Test 
	public void TC_01_NotEnoughTime() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // nếu set line 45 này thì line 36 sẽ bị skip và apply line 45 này thay thế => command set implicity gần nhất sẽ dc apply và thay thế command set implicity trước
		//=> timeout = 3s và Hello World cần 5s để load => case này bị fail
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//Click start btn
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Dùng wait mô phỏng thiếu thời gian wait để cho step sau có thể dc tiến hành
		sleepInSecond(3); // Thực tế loading cần 5s => mô phỏng thiếu thời gian
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
		//Dùng wait mô phỏng vừa đủ thời gian wait để cho step sau có thể dc tiến hành
		sleepInSecond(5); // Thực tế loading cần 5s => mô phỏng vừa đủ thời gian
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
		//Dùng wait mô phỏng dư thời gian wait để cho step sau có thể dc tiến hành
		sleepInSecond(10); // Thực tế loading cần 5s => mô phỏng dư thời gian
		//Kết luận : sleep k dc dùng phổ biến vì đặc thù của nó k phù hợp với thực tế
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
