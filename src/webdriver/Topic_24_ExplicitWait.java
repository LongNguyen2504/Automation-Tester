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
	//@Test 
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
	//@Test 
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
	
	//@Test
	public void TC_06_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait = new WebDriverWait(driver, 15);
		
		//Wait khung dateTime dc hiển thị vì vừa load page thì cần time để ajax hiển thị đầy đủ dateTime
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar ")));
		
		//Verify selected Date là chưa có ngày dc chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		//Wait ngày 10 ở  dateTime dc phép click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td[title='Saturday, December 10, 2022']")));
		
		//Click chọn ngày 10
		driver.findElement(By.cssSelector("td[title='Saturday, December 10, 2022']")).click();
		
		//Wait laoding icon biến mất invisibility
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadAjaxLoadingPanel1ctl00'] div.raDiv")));
		
		//Wait ngày 10 vừa dc chọn là dc phép click trở lại (vì khi chọn ngày 10 thì ngày 10 đã dc chọn trong html chứ k chờ loading icon chạy xong)
		//Làm thêm step này để test case càng chi tiết thì càng tăng sự ổn định
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='10']")));
		
		//Verify span text selected dates đã hiển thị và đúng text 
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Saturday, December 10, 2022");
		
		//Note : Nếu gán sẵn 1 webelement selectedDate = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"))
		//Và apply biến selectedDate cho 2 line 88 và 104 thì sau khi run sẽ bị throw lỗi staleElementException
		//=> nghĩa là tại line 104 đã k còn tìm thấy locator span trong html nữa mặc dù cùng 1 locator ở line 88
		//=> Do sau khi pick date thì ajax đã refresh cái pickDate (k phải refresh cả page) -
		//> dẫn đến ajax update lại các locator có liên quan trong đó có locator span#ctl00_ContentPlaceholder1_Label1
		
		
		  
	  }
	@Test 
	public void TC_07_Upload_File() {
		String ct1 = "ct1.png";
		String ct2 = "ct2.png";
		String uploadFilePathCt1 = projectPath + "\\uploadFile\\" + ct1;
		String uploadFilePathCt2 = projectPath + "\\uploadFile\\" + ct2;
		driver.get("https://gofile.io/uploadFiles");
		explicitWait = new WebDriverWait(driver, 15);
		//Wait cho button Add Files visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#rowUploadButton button")));
		
		//Sendkey và upload file vào element input[@type='file']
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(uploadFilePathCt1 + "\n" + uploadFilePathCt2);
		
		//Wait bar loading process của các file biến mất -> dùng invisibility của elements với locator chung của các file
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#rowUploadProgress-list div.progress-bar"))));
		
		//Wait cho message successfully dc visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		
		//Verify message thành công(phải có bước wait chờ message này hiển thị trước khi verify)
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		//Wait + click cho show file button dc clickable ()
		//Do hàm method elementToBeClickable sẽ trả về webElement khi nó đã clickable nên có thể viết gộp như này
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles"))).click();
		
		//Wait + verify : cho file name vs btn dowload hiển thị
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='ct1.png']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='ct2.png']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
	
		//Wait + verify : cho file name vs btn play hiển thị
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='ct1.png']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='ct2.png']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
		
		
	
	
	
	
	}
	@Test 
	public void TC_05_() {
		  
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
