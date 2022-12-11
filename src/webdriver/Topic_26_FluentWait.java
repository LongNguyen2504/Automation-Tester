package webdriver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_FluentWait {
	WebDriver driver;
	
	FluentWait<WebDriver> fluentWebDriver;
	FluentWait<WebElement> fluentWebElement;
	long timeOutWait = 10;
	long miliPollingTimeWait = 1000; // 1000 mili = 1s
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
	}
	//@Test 
	public void TC_01_Fluent_With_WebDriver_Parameter() {
		//Demo hàm fluent dùng webdriver là tham số của hàm apply(để driver.findElement) và WebElement là Return của hàm apply
		//Hàm fluent custom bên dưới
		driver.get("https://automationfc.github.io/dynamic-loading/");
		findElementWithFluent("//div[@id='start']/button").click(); // do hàm findElementWithFluent return webElement nên có thể click luôn
		Assert.assertEquals(findElementWithFluent("//div[@id='finish']/h4").getText(), "Hello World!");
		
		  
	  }
	@Test 
	public void TC_02_Fluent_With_WebElement_Parameter() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement countDownClock = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		
		fluentWebElement = new FluentWait<WebElement>(countDownClock);
		
		fluentWebElement.withTimeout(Duration.ofSeconds(59)) // set 59s để tránh trường hợp vừa run clock đang ở giây > 10 sẽ bị throw lỗi sớm
		.pollingEvery(Duration.ofMillis(100)) // 1/10 giây sẽ polling 1 lần để check kết quả
		.ignoring(NoSuchElementException.class);
		
		fluentWebElement.until(new Function<WebElement, Boolean>() {
			@Override
			public Boolean apply(WebElement element) {
				// TODO Auto-generated method stub
				String text = element.getText();
				System.out.println(text); // in ra giây hiện tại của đồng hồ với polling time = 0.1s
				return text.endsWith("00");
			}
		});
		
		  
	  }

	public WebElement findElementWithFluent(String xpathLocator) {
		fluentWebDriver = new FluentWait<WebDriver>(driver);
		fluentWebDriver.withTimeout(Duration.ofSeconds(timeOutWait))
		.pollingEvery(Duration.ofMillis(miliPollingTimeWait))
		.ignoring(NoSuchElementException.class); 
		// có NoSuchElementException của java.util và của selenium -> dùng của selenium 
		//-> nếu dùng của java.util thì sẽ bị throw lỗi ngay khi polling đầu tiên nếu k tìm thấy element
		
		return fluentWebDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver t) {
			// TODO Auto-generated method stub
			return driver.findElement(By.xpath(xpathLocator));
		}
		});
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
