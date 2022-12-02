package webdriver;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_PopUp_Fixed {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsEx;
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		action = new Actions(driver);
		jsEx = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
	}
	//@Test 
	public void TC_01_ngoaingu24h() {
		//Trước khi handle popup hãy kiểm tra trigger nó lúc vừa load page và sau khi đóng có sự khác biệt gì ko,có số lượng popup >1 hay ko để xử lý từng case cụ thể
		driver.get("https://ngoaingu24h.vn/");
		sleepInSecond(3);
		
		//Vì là có 2 popup đều có class như nhau(đã có trong html) khi chưa kích hoạt -> ko có sự khác nhau của 2 popup trong html -> dùng index
		WebElement popUpLogin = driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//div[@class='modal-content']"));
		
		//verify dispaly
		Assert.assertFalse(popUpLogin.isDisplayed());
		
		//Click login
		driver.findElement(By.cssSelector("button.login_")).click();
		
		//verify pop up hiển thị
		Assert.assertTrue(popUpLogin.isDisplayed());
		
		//Send key login
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='account-input']")).sendKeys("automationFC");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='password-input']")).sendKeys("automationFC");
		
		//click login
		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//button[contains(@class,'btn-login-v1')]")).click();
		
		//verify error
		Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.row.error-login-panel")).getText(),"Tài khoản không tồn tại!" );

		
		  
	  }
	//@Test 
	public void TC_02_KyNaVN() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(3);
		
		//Vì popup ko dc load trong HTML nếu chưa trigger nên sẽ dùng findElements.size() để verify
//		Assert.assertEquals(driver.findElements(By.cssSelector("div.k-popup-account-mb-content")).size(),0); // -> =0 là popup chưa dc laod trong HTML và chưa dc trigger => popup chưa hiển thị
		
		//click đăng nhập
		driver.findElement(By.cssSelector("a.login-btn")).click();
		// Kiểm tra popUp hiển thị
		
//		Assert.assertTrue(driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.k-popup-account-mb-content")).isDisplayed());
		//Send login
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		
		
		sleepInSecond(5); // chờ push notification hiện lên để click chặn = maunual
		//Click btn login - ko thể click vì có 1 push notification của web hiện đúng lúc chuẩn bị click login -> bypass = manual -> noted để quay lại
		driver.findElement(By.xpath("//div[@class='button-submit']//button")).click();
		sleepInSecond(5);
		//Verify message

		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
		//Click btn close
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		//Veirfy popup đã close
		Assert.assertFalse(driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.k-popup-account-mb-content")).isDisplayed());
		
		
		
	  }
	@Test 
	public void TC_03_Tiki() {
		driver.get("https://tiki.vn/");
		sleepInSecond(3);
		
		//Verify pop up sau khi click đăng nhập -> do vừa load web thì pop up k load trong html -> dùng findElements
		Assert.assertEquals(driver.findElements(By.xpath("//button[@class='btn-close']//parent::div")).size(), 0); // verify k hiển thị pop up
		
		//Click btn login
		driver.findElement(By.xpath("//img[@class='profile-icon']//following-sibling::span//span[text()='Đăng Nhập / Đăng Ký']")).click();
		//Verify pop up hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//button[@class='btn-close']//parent::div")).isDisplayed());
		//Click login with Email
		driver.findElement(By.xpath("//p[@class='login-with-email']")).click();
		//Click log in button
		driver.findElement(By.xpath("//p[@class='forgot-pass']//preceding-sibling::form//button")).click();
		//Verify message error
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='forgot-pass']//preceding-sibling::form//span[1]")).getText(), "Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='forgot-pass']//preceding-sibling::form//span[2]")).getText(), "Mật khẩu không được để trống");
		  
		//click close pop up
		driver.findElement(By.xpath("//button[@class='btn-close']")).click();
		
	  }
	@Test
	public void TC_04_() {
		driver.get("https://www.facebook.com/");
		sleepInSecond(3);
		
		//Click creat new account btn
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		
		//Verify register pop up display
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký']//parent::div//parent::div")).isDisplayed());
		
		
		//clicl close btn of pop up
		driver.findElement(By.xpath("//div[text()='Đăng ký']//parent::div//parent::div/img")).click();
		
		
		//verify register pop up is not display
		// lúc sau khi tắt pop up thì html của pop up sẽ k còn trong cây html nữa nên phải dùng cách này
		Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Đăng ký']//parent::div//parent::div")).size(), 0); 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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
