package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_06_Parameter_Environment {
	//Demo tính năng parameter của testNG với tham số environment từ xml truyền vào,tham số environment thể hiện môi trường test của dev,test,live
	// Nếu cần môi trường nào thì set value của tham số environment trong xml
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	
	
	@Parameters("browser") // do bên xml file đã khai báo parameter có tên browser chứa các value làm tham số truyền vào đây
	@BeforeClass
	public void beforeClass(@Optional("firefox") String browser) { 
		// annotation @Optional set giá trị backup cho parameter Browser từ xml truyền vào
		//khi parameter ko được set/pass vào thì testNG sẽ dùng value của @Optional để thay thế
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
			System.out.println("browser optional : "+ browser);
			throw new RuntimeException("Wrong browser name parameter");
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	@Parameters("environment")// do bên xml file đã khai báo parameter có tên env chứa các value làm tham số truyền vào đây
	@Test
	public void TC_01_LoginToSystem(@Optional("live") String envValue){
		String envUrl = getEnvirontmentUrl(envValue);
		System.out.println(envUrl);
		driver.get(envUrl);
		driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("111111");
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}
	public String getEnvirontmentUrl(String envValue) {
		switch (envValue) {
		case "live":
			
			return "http://live.techpanda.org/index.php/customer/account/login/";
		case "dev":
			return "http://dev.techpanda.org/index.php/customer/account/login/"; // url demo
		case "test":
			return "http://test.techpanda.org/index.php/customer/account/login/"; // url demo
		default:
			return null;
		}
	}

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  /*--------XML Topic 06 ---------------
   * <?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite parallel="false" name="Suite">
	<!--Global parameter goes here-->
	<parameter name="environment" value="live"></parameter> <!--environment = live | dev | test-->
  	<test name="Test Browser Parameter on Firefox">
  		<parameter name="browser" value="firefox" />
    	<classes>
    		<class name="testNG.Topic_06_Parameter_Environment" />
    	</classes>	
	</test>
	<test name="Test Browser Parameter on Chrome">
		<parameter name="browser" value="chrome" />
    	<classes>
    		<class name="testNG.Topic_06_Parameter_Environment" />
    	</classes>	
	</test>
	<test name="Test Browser Parameter on Edge">
		<parameter name="browser" value="edge" />
    	<classes>
    		<class name="testNG.Topic_06_Parameter_Environment" />
    	</classes>	
	</test>
	</suite> 

   * 
   * 
   * */

}
