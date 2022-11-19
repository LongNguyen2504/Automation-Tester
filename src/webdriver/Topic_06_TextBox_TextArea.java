package webdriver;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_TextBox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	String employeeID = String.valueOf(rand.nextInt(99999));
	String passportNumb = "33333-333-22-4242";
	String comment = "This is generated data\nof real people";

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
	@Test public void TC_01_Create_New_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		sleepInSecond(5);
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.className("orangehrm-login-button")).click();
		sleepInSecond(3);
		
		//click tab PIM
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		sleepInSecond(3);
		//click add Employee
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(3);
		//Nhập firstname và lastname
		driver.findElement(By.name("firstName")).sendKeys("Automation");
		driver.findElement(By.name("lastName")).sendKeys("FC");
		
		//nhập id
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.CONTROL,"a",Keys.DELETE);
		sleepInSecond(3);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(employeeID);
		sleepInSecond(3);
		///click create login details -> do thẻ input bị ẩn nên phải bấm span
		driver.findElement(By.xpath("//p[text()='Create Login Details']//parent::div//label/span")).click();
		//sendkey username textbox
		driver.findElement(By.xpath("//label[text()='Username']//parent::div//following-sibling::div/input")).sendKeys("afc"+employeeID);
		//sendkey password textbox
		driver.findElement(By.xpath("//label[text()='Password']//parent::div//following-sibling::div/input")).sendKeys("Password123!!!");
		//sendkey confirm pass
		driver.findElement(By.xpath("//label[text()='Confirm Password']//parent::div//following-sibling::div/input")).sendKeys("Password123!!!");
		//click save
		driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
		sleepInSecond(6);
		//do web sử dụng vueJS nên value sẽ không nằm trong tab attribute
		//verify
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"),"Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"FC");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div//following-sibling::div/input")).getAttribute("value"),employeeID);
		
		//click vào tab immigration
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		//click vào button add đầu tiên
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		//type number passport
		driver.findElement(By.xpath("//label[text()='Number']//parent::div//following-sibling::div/input")).sendKeys(passportNumb);
		//type comment
		driver.findElement(By.xpath("//label[text()='Comments']//parent::div//following-sibling::div/textarea")).sendKeys(comment);		
		//click save
		driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
		sleepInSecond(6);
		
		//click nút edit
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(3);
		
		//verify passport number và comment
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']//parent::div//following-sibling::div/input")).getAttribute("value"),passportNumb);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']//parent::div//following-sibling::div/textarea")).getAttribute("value"),comment);
		
		//--------Log out and re-verify
		
		//click vào ảnh profile góc phải 
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		//click vào logout
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(3);
		
		//login lại
		driver.findElement(By.name("username")).sendKeys("afc"+employeeID);
		driver.findElement(By.name("password")).sendKeys("Password123!!!");
		driver.findElement(By.className("orangehrm-login-button")).click();
		sleepInSecond(5);
		
		//click tab PIM
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		sleepInSecond(5);
		
		//verify trang personal details
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"),"Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"FC");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div//following-sibling::div/input")).getAttribute("value"),employeeID);

		//verify trang immigration
		//click vào tab immigration
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		//click nút edit
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(3);
		//verify passport number và comment
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']//parent::div//following-sibling::div/input")).getAttribute("value"),passportNumb);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']//parent::div//following-sibling::div/textarea")).getAttribute("value"),comment);
		
		
		
		
		
	}
	@Test public void TC_02_Verify_Employee() {
		  
	  }
	@Test public void TC_03_() {
		  
	  }
	@Test public void TC_04_() {
		  
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
