package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_XPath_part_I {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Open browser
		
	}
	@Test public void TC_01_EmptyData() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
		
//		driver.get("https://automationfc.github.io/basic-form/");
//		System.out.println("Text h5 = " + driver.findElement(By.xpath("//h5[@id='nested']")).getText());
		//System.out.println("Text h5 = " + driver.findElement(By.xpath('//h5[@id="nested"]')).getText());
		//'//h5[@id="nested"]'
		  
		  }
	@Test public void TC_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Send value
		driver.findElement(By.id("txtFirstname")).sendKeys("J W");
		driver.findElement(By.id("txtEmail")).sendKeys("ng2345@g@g");
		driver.findElement(By.id("txtCEmail")).sendKeys("ng2345@g@g");
		driver.findElement(By.id("txtPassword")).sendKeys("abc123");
		driver.findElement(By.id("txtCPassword")).sendKeys("abc123");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		
		//Action
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
	  }
	@Test public void TC_03_Incorrect_Confirm_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Send value
		driver.findElement(By.id("txtFirstname")).sendKeys("J W");
		driver.findElement(By.id("txtEmail")).sendKeys("ng2345@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("ng2345@gmail.net");
		driver.findElement(By.id("txtPassword")).sendKeys("abc123");
		driver.findElement(By.id("txtCPassword")).sendKeys("abc123");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
				
		//Action
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
				
		//Verify
		
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng"); 
		
	  }
	@Test public void TC_04_Invalid_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Send value
		driver.findElement(By.id("txtFirstname")).sendKeys("J W");
		driver.findElement(By.id("txtEmail")).sendKeys("ng2345@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("ng2345@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
				
		//Action
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
				
		//Verify
		
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự"); 
		  
	  }
	@Test public void TC_05_Incorrect_Confirm_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Send value
		driver.findElement(By.id("txtFirstname")).sendKeys("J W");
		driver.findElement(By.id("txtEmail")).sendKeys("ng2345@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("ng2345@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("abc123");
		driver.findElement(By.id("txtCPassword")).sendKeys("acc123");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
				
		//Action
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
				
		//Verify
		
		
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp"); 
		
		  
	  }
	@Test public void TC_06_Invalid_Phone_Number() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Send value 1
		driver.findElement(By.id("txtFirstname")).sendKeys("J W");
		driver.findElement(By.id("txtEmail")).sendKeys("ng2345@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("ng2345@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("abc123");
		driver.findElement(By.id("txtCPassword")).sendKeys("abc123");
		driver.findElement(By.id("txtPhone")).sendKeys("01234567");
				
		//Action 1
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
				
		//Verify 1
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");
		
		//Send value 2
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("012345678999");
		//Action 2
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Verify 2
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");
		
		//Send value 3
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("1234567899");
		//Action 3
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Verify 3
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

		
		
		
		
		  
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
