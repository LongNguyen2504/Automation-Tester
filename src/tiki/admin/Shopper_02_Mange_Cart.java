package tiki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Shopper_02_Mange_Cart {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	@BeforeTest(alwaysRun =  true) // nếu k bật alwaysRun = true thì khi run file xml sẽ k bật browser lên khi gọi <class name="tiki.admin.Shopper_02_Mange_Cart" />
	public void initBrowser() {
		System.out.println("-------------Open browser--------------");
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
	@Test(groups ={"admin_1","Name_Of_Second_Group"}) // có thể gán nhiều tên group cho 1 test method vì groups là 1 mảng
	public void Cart_01_Create_Visa() {
		System.out.println("group admin 1 - Shopper_02_Mange_Cart");
	}
	@Test
	public void Cart_02_View_Visa() {
		
	}
	@Test
	public void Cart_03_Update_Visa() {
		
	}
	@Test
	public void Cart_04_Delete_Visa() {
		
	}
	@AfterTest(alwaysRun =  true)
	public void cleanBrowser() {
		System.out.println("-------------Quit browser--------------");
		driver.quit();
	}
	

}
