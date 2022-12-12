package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Page_Ready {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
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
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
	}
	//@Test 
	public void TC_01_OrangeHRM() {
		//Hiện tại page này dính cache nên console (window.jQuerry != null) && (jQuerry.active === 0) vẫn return false => k tiếp tục các bước sau dc
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		//Sau khi click thì chờ page tiếp theo load xong -> có thể dùng implicit hoặc explicit để wait 
		Assert.assertTrue(isPageLoadedSuccess());
		//Verify màn hình sau
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Employees on Leave Today']")).isDisplayed());
	  
		driver.findElement(By.cssSelector("a#menu_pim_viewPimModule")).click();
		//CLick xong chuyển qua trang mới
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys("Peter Mac");
		driver.findElement(By.id("searchBtn")).click();
		
		//Load lại 1 phần trang
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + "Peter Mac" + "']")).isDisplayed());
	
	
	
	
	
	
	}
	@Test 
	public void TC_02_TestProjectBlog() {
		//Page này cũng bị issue tương tự page ở testcase 1 -> querry page return false mặc dù có hơn 100 requests ?
		driver.get("https://blog.testproject.io/");
		
		String keyword = "Selenium";
		
		//Handle pop-up nếu có (nên check là popup cố định hay random -> random phải dùng list)
//		if(driver.findElement(By.cssSelector("div.mailch-wrap")).isDisplayed()) {
//			System.out.println("Close pop-up");
//			driver.findElement(By.cssSelector("div#close-mailch")).click();
//		}
		
		//Hover chuột vào filed Search - Action này sẽ trigger page load thêm 1 lần nữa
		System.out.println("Hover mouse to search box");
		action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		
		System.out.println("Wait cho page ready"); // vì action trên vừa trigger thêm loading cho page -> check tab network ở browser ở trạng thái trước và sau khi hover
		Assert.assertTrue(isPageLoadedSuccess());
		
		System.out.println("Enter value to search textbox");
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(keyword);
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		//Verify span text hiển thị để check xem đã load dc màn hình sau khi search chưa
		Assert.assertTrue(driver.findElement(By.cssSelector("h2.page-title>span")).isDisplayed());
		//Có thể verify bằng explicit thay thế assert line 107
//		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.page-title>span")));
		Assert.assertTrue(isPageLoadedSuccess());
		//Verify results of search action
		List<WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement title : postTitles) {
			String postTitleText = title.getText();
			System.out.println(postTitleText);
			Assert.assertTrue(postTitleText.contains(keyword));
			
		}
		
		
		
		
		  
	  }

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000); // chờ load trang sau khi click -> thư viện của java
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());;
		}
	}
	
	
	


	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		ExpectedCondition<Boolean> jQuerryLoad = new ExpectedCondition<Boolean>() {
			
			@Override
			public Boolean apply(WebDriver arg0) {
				// TODO Auto-generated method stub
				return (Boolean) jsExecutor.executeScript("return (window.jQuerry != null) && (jQuerry.active === 0);");
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQuerryLoad) && explicitWait.until(jsLoad);
		
	
	}

}
