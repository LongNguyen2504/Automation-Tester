package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Ex {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextBox = By.id("mail");
	By rdioUnder18 = By.id("under_18");
	By txtEducation = By.id("edu");
	By hidUser5Text = By.xpath("//h5[text()='Name: User5']");
	By txtBio = By.id("bio");
	By txtPwd = By.id("disable_password");
	By devCheckbox = By.id("development");

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
	//@Test 
	public void TC_01_isDisplay() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		if(driver.findElement(emailTextBox).isDisplayed() ) {
			driver.findElement(emailTextBox).sendKeys("Automation Testing");
			System.out.println("Email text box is displayed");
			
		
			
		}else {
			System.out.println(" Email text box is not display");
		}
		
		
		if(driver.findElement(txtEducation).isDisplayed() ) {
			
			driver.findElement(txtEducation).sendKeys("Automation Testing");
			System.out.println("text Education is display");
			
		}else {
			System.out.println(" Education text is not display");
		}
		
		if(driver.findElement(rdioUnder18).isDisplayed() ) {
			
			driver.findElement(rdioUnder18).click();
			System.out.println("radio under 18 is display");
			
		}else {
			System.out.println(" radio under 18  is not display");
		}
		
		
		
		
		
		
		
		
		  
	  }
	//@Test 
	public void TC_02_Verify_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		
		//verify is enabled
		if (driver.findElement(emailTextBox).isEnabled()) {
			System.out.println("email is enable");
		} else {
			System.out.println("email is disable");
		}
		if (driver.findElement(rdioUnder18).isEnabled()) {
			System.out.println("rdio under 18 is enable");
		} else {
			System.out.println("rdio under 18 is disable");
		}
		if (driver.findElement(By.id("slider-1")).isEnabled()) {
			System.out.println("slider 1 is enable");
		} else {
			System.out.println("slider 1 is disable");
		}
		
		//verify is disable
		
		if (!driver.findElement(txtPwd).isEnabled()) {
			System.out.println("txt password is disable");
		} else {
			System.out.println("txt password is enable");
		}
		if (!driver.findElement(txtBio).isEnabled()) {
			System.out.println("txt bio is disable");
		} else {
			System.out.println("txt bio is enable");
		}
		
		
		
		  
	  }
	//@Test 
	public void TC_03_Verify_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		
		//verify checkbox/radio are not selected
		
		Assert.assertFalse(driver.findElement(rdioUnder18).isSelected());
		Assert.assertFalse(driver.findElement(devCheckbox).isSelected());
		
		//Click to checkbox/radio
		driver.findElement(rdioUnder18).click();
		driver.findElement(devCheckbox).click();
		sleepInSecond(2);
		
		//verify checkbox/radio are selected
		
		Assert.assertTrue(driver.findElement(rdioUnder18).isSelected());
		Assert.assertTrue(driver.findElement(devCheckbox).isSelected());
		
		
		
		
		  
	  }
	@Test public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		sleepInSecond(2);
		
		driver.findElement(By.id("email")).sendKeys("abc2504@gmail.com");
		
		By txtNewPwd = By.id("new_password");
		
		//Verify One lowercase character
		driver.findElement(txtNewPwd).sendKeys("a");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		//Verify One uppercase character
		driver.findElement(txtNewPwd).clear();
		driver.findElement(txtNewPwd).sendKeys("A");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		
		//Verify One number
		driver.findElement(txtNewPwd).clear();
		driver.findElement(txtNewPwd).sendKeys("1");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		  
		//Verify One special character
		driver.findElement(txtNewPwd).clear();
		driver.findElement(txtNewPwd).sendKeys("@");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		
		//Verify 8 characters minimum
		driver.findElement(txtNewPwd).clear();
		driver.findElement(txtNewPwd).sendKeys("AAAAAAAA");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		//Verify fulfill password case này thỏa mãn pwd đầy đủ nên sẽ k display các check condition passw
		driver.findElement(txtNewPwd).clear();
		driver.findElement(txtNewPwd).sendKeys("Abc123!@");
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
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
