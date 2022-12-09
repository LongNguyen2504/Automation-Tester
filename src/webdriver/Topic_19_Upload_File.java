package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_File {
	WebDriver driver;
	
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String ct1 = "ct1.png";
	String ct2 = "ct2.png";
	
	String uploadFilePathCt1 = projectPath + "\\uploadFile\\" + ct1;
	String uploadFilePathCt2 = projectPath + "\\uploadFile\\" + ct2;
	//Original path : F:\Be Coding\QA automation\02-Selenium WebDriver\\uploadFile\ct1.png
	//relative path : F:\Be Coding\QA automation\02-Selenium WebDriver\\uploadFile\\ct1.png trong đó projectPath = F:\Be Coding\QA automation\02-Selenium WebDriver\ , string ct1 = ct1.png

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
	//@Test 
	public void TC_01_one_file_at_aTime() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInSecond(3);
		
		//Load file lên
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(uploadFilePathCt1);
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(uploadFilePathCt2);
		sleepInSecond(1);
		
		//Verify file dc load thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'" + ct1 + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'" + ct2 + "')]")).isDisplayed());
		
		//Click upload
		List<WebElement> listStartBtn = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement webElement : listStartBtn) {
			webElement.click();
			sleepInSecond(1);
		}
		//Verify upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + ct1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + ct2 + "']")).isDisplayed());
		
	  }
	@Test 
	public void TC_01_Multiple_Files_Upload() {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInSecond(3);
		
		//Load file lên
		//Với trường hợp upload nhiều file thì trước tiên thẻ input trong html phải có type = file và attribute multiple thì mới có thể upload nhiều file
		//Khi sendkeys nhiều file thì các file sẽ ngăn cách phải dấu "\n"
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(uploadFilePathCt1 + "\n" + uploadFilePathCt2); //sendkey hoạt động tốt với mọi brownser nhưng browser phải dc update bản mới
		sleepInSecond(2);
		
		
		//Verify file dc load thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'" + ct1 + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'" + ct2 + "')]")).isDisplayed());
		
		//Click upload
		List<WebElement> listStartBtn = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement webElement : listStartBtn) {
			webElement.click();
			sleepInSecond(1);
		}
		//Verify upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + ct1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + ct2 + "']")).isDisplayed());
		  
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
