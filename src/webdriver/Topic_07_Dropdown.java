package webdriver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Select select;
	Random rand;

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
		rand = new Random();
		
		
	}
	@Test 
	public void TC_01_Default_Dropdown() {
		int randNumber = rand.nextInt(9999);
		driver.get("https://demo.nopcommerce.com/");
		sleepInSecond(3);
		driver.findElement(By.className("ico-register")).click();
		sleepInSecond(2);
		driver.findElement(By.id("FirstName")).sendKeys("Joe");
		driver.findElement(By.id("LastName")).sendKeys("Biden");
		
		//Select dropdown
		select = new Select(driver.findElement(By.name("DateOfBirthDay"))); // chỉ có thể khởi tạo trong màn hình có element dropdown
		select.selectByVisibleText("13"); // thông dụng nhất do có thể kiểm soát được mọi trường hợp khi có thay đổi
		
		//select.selectByIndex(0); // k nên dùng vì dễ bị edit -> thay đổi index
		//select.selectByValue(osName); // do dev quy định, có thể k có 
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth"))); // chỉ có thể khởi tạo trong màn hình có element dropdown
		select.selectByVisibleText("May"); // thông dụng nhất do có thể kiểm soát được mọi trường hợp khi có thay đổi
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear"))); // chỉ có thể khởi tạo trong màn hình có element dropdown
		select.selectByVisibleText("1965"); // thông dụng nhất do có thể kiểm soát được mọi trường hợp khi có thay đổi

		driver.findElement(By.id("Email")).sendKeys("JoeBiden"+randNumber +"@hotmail.com");
		driver.findElement(By.id("Company")).sendKeys("white house");
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.id("register-button")).click();
		sleepInSecond(3);
		
		//verify message succeed
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");

		driver.findElement(By.className("ico-account")).click();
		sleepInSecond(2);
		
		//verify my account
		
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), "Joe");
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), "Biden");
		//Cách verify giá trị đã chọn
		select = new Select(driver.findElement(By.name("DateOfBirthDay"))); // phải khởi tạo và get element ra trước vì đã thay đổi url(cho dù same html source)
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "13"); // lấy ra giá trị đã dc chọn và hiển thị tại dropdownlist
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth"))); // phải khởi tạo và get element ra trước vì đã thay đổi url(cho dù same html source)
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May"); // lấy ra giá trị đã dc chọn và hiển thị tại dropdownlist
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear"))); // phải khởi tạo và get element ra trước vì đã thay đổi url(cho dù same html source)
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1965"); // lấy ra giá trị đã dc chọn và hiển thị tại dropdownlist
		
		
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), "JoeBiden"+ randNumber+"@hotmail.com"); // lấy ra giá trị đã dc chọn và hiển thị tại dropdownlist
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), "white house"); // lấy ra giá trị đã dc chọn và hiển thị tại dropdownlist
		
		//verify 1 dropdown là single hay multiple | single -> false , multi -> true
		//Assert.assertTrue(select.isMultiple()); // ít khi dùng trong dự án
		
		//deselect -> ít dùng,chỉ dùng khi chọn dc nhiều giá trị
		
		
		  
	  }
	@Test public void TC_02_() {
		  driver.get("https://rode.com/en/support/where-to-buy");
		  sleepInSecond(2);
		  select = new Select(driver.findElement(By.id("country")));
		  select.selectByVisibleText("Vietnam");
		  sleepInSecond(2);
		  
		  Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		  
		  List<WebElement> dealers = driver.findElements(By.cssSelector("div#map h4"));
		  for (WebElement webElement : dealers) {
			System.out.println(webElement.getText());
		}
	  }
	@Test public void TC_03_() {
		  
	  }
	@Test public void TC_04_Default_Dropdown_04() {
		
		  
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
