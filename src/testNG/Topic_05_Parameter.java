package testNG;

import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_05_Parameter {
	//Demo tính năng parameter của testNG
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		switch (browser) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;

		default:
			throw new RuntimeException("Wrong browser name parameter");
			
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	
	@Test
	public void TC_01_LoginToSystem(){
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("111111");
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}


	

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  /*-------------XML Topic 05---------------
   * <?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite parallel="false" name="Suite">
	<!--Global parameter goes here-->
	<parameter name="environment" value="live"></parameter> <!--environment = live | dev | test-->
  	<test name="Test Browser Parameter on Firefox">
  		<parameter name="browser" value="firefox" />
    	<classes>
    		<class name="testNG.Topic_05_Parameter" />
    	</classes>	
	</test>
	<test name="Test Browser Parameter on Chrome">
		<parameter name="browser" value="chrome" />
    	<classes>
    		<class name="testNG.Topic_05_Parameter" />
    	</classes>	
	</test>
	<test name="Test Browser Parameter on Edge">
		<parameter name="browser" value="edge" />
    	<classes>
    		<class name="testNG.Topic_05_Parameter" />
    	</classes>	
	</test>
</suite> 


   * 
   * 
   * */

}
