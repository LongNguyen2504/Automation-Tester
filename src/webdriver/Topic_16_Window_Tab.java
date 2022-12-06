package webdriver;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Window_Tab {
	WebDriver driver;
	JavascriptExecutor javaEx;
	
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
		javaEx = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
	}
//	@Test 
	public void TC_01_Two_WindowOrTab_at_sameTime() {
		//Parent page
		driver.get("https://automationfc.github.io/basic-form/");
		
		//Get current ID of active window/tab
		String parentPageWindowID = driver.getWindowHandle();
		System.out.println("active : " + parentPageWindowID);
		
		//Click vào google link để ra 1 tab google mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		//Get all of ID of tabs or windows
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		//Dùng for để chọn ra id của tab/window còn lại
		
		for (String id : allWindowIDs) {
			if(!(id.equals(parentPageWindowID))) {
				driver.switchTo().window(id);
				break;
				
			}
			
		}
		sleepInSecond(2);
		//Send key vào input tab mới switch sang
		driver.findElement(By.cssSelector("input[name='q']")).sendKeys("Selenium");
		sleepInSecond(4);
		
		
		
		//Case 1 : There are only 2 windows or 2 tabs at the same time
		
		//Case 2 : More than 2 windows/tabs at the same time
		  
	  }
//	@Test 
	public void TC_01_Multi_Tabs_With_Title() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		String parentTitle = driver.getTitle();
		//Click in google link 
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(1);
		//Switch to tab GOOGLE to verify title
		switchIDWindowByTitle("Google");
		sleepInSecond(1);
		Assert.assertEquals(driver.getTitle(),"Google");
		//Switch back to parrent automation
		switchIDWindowByTitle(parentTitle);
		sleepInSecond(1);
		//Click facebook link
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(1);
		//Switch to facebook
		switchIDWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		sleepInSecond(1);
		Assert.assertEquals(driver.getTitle(),"Facebook - Đăng nhập hoặc đăng ký");
		//Switch back to parrent automation
		switchIDWindowByTitle(parentTitle);
		sleepInSecond(1);
		//Click TIki link
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(1);		
		//Switch to tiki tab
		switchIDWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInSecond(1);
		Assert.assertEquals(driver.getTitle(),"Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
		//Close all tab without parent
		closeAllWithoutParentTab(parentTitle);
		  
	  }
	@Test 
	public void TC_02_KyNa() {
		//Page này có thể có popup -> khi run phải update close pop up
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(3);
		String parentID = driver.getWindowHandle();
		String parentTitle = driver.getTitle();
		
		//Scroll to bottom page
		javaEx.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		//Click logo FB,Youtube
		driver.findElement(By.cssSelector("div#k-footer div.social a img[alt='facebook']")).click();
		sleepInSecond(1);
		//Switch to FB tab
		switchIDWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		sleepInSecond(1);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		//switch về parent
		driver.switchTo().window(parentID);
		sleepInSecond(1);
		
		//Click youtube tab
		driver.findElement(By.cssSelector("div#k-footer div.social a img[alt='youtube']")).click();
		sleepInSecond(1);
		//Switch to youtube tab
		switchIDWindowByTitle("Kyna.vn - YouTube");
		sleepInSecond(1);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		//switch về parent
		driver.switchTo().window(parentID);
		sleepInSecond(1);
		//Close all tabs without parent
		closeAllWithoutParentTab(parentTitle);
		//Verify title kyna
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
		//Note khi verify title kyna nên dùng xpath //title để get text và verify -> lí do ??
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
	public void switchIDTabWindow(String currentID) {
		//only for 2 windows or tabs at same time
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if(!(id.equals(currentID))) {
				driver.switchTo().window(id);
				break;
				
			}
			
		}
	}
	public void switchIDWindowByTitle(String titleToSwitch) {
		//For multiple tabs or windows at same time
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			sleepInSecond(2);
			String currentTitle = driver.getTitle();
			if(currentTitle.equals(titleToSwitch)) {
				driver.switchTo().window(id);
				sleepInSecond(2);
				break;
				
			}
			
		}
	}
	public void closeAllWithoutParentTab(String parentTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			sleepInSecond(1);
			if(!(driver.getTitle().equals(parentTitle))) {
				driver.close(); // tắt 1 tab -> nếu dùng driver.quit thì sẽ tắt cả cửa sổ chứa tất cả các tab trong đó
			}
		}
		//switch về tab parent vì sau khi close driver cũ vẫn nhận tab đã bị đóng và chưa active tab parent để thao thác
		switchIDWindowByTitle(parentTitle);
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
