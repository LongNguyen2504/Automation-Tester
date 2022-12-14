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
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import listener.ExtendReportListener;

//@Listeners(ExtendReportListener.class) // cách này dùng trong phạm vi class,khi dùng cho package thì edit bên xml và bỏ line này
public class Topic_09_Listener {
	//Listener phục vụ mục đích screenshot,...với các test case có trạng thái fail(fail nên cần screenshot attach vào log,report),pass,skipped
	//Demo tính năng listener của testNG
	public static WebDriver driver; // modifier public để class ExtendReportListener gọi dc driver của class này
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
	
	
	@BeforeClass
	public void beforeClass() {
		String browser = "firefox";
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
		Assert.assertFalse(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
		//Cố tình để false để demo screenshot với testcase fail
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}


	

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
}