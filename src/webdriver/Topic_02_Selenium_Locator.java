package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
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
		driver.get("https://demo.nopcommerce.com/register");
	}
	
	//Note here
/*	<input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName">
 * nếu tìm thấy 1 element thì mới chính xác,tìm = id,class,name là nhanh nhất -> do selenium hỗ trợ
 * 
 * 
 * 
 * 
 * 
 * */

	@Test
	public void TC_01_getIDandSendkey() {
		//Thao tác lên element nào thì phải tìm được chính xác element(là duy nhất) đó : sử dụng findElement (truyền id,class,name,...của element)
		//Sau đó thao tác lên element đó: click/sendkey,....
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
	}

	@Test
	public void TC_02_setTextBox() {
		driver.get("https://demo.nopcommerce.com/search");//mở màn hình search
		driver.findElement(By.className("search-text")).sendKeys("Macbook");//Nhập text Macbook vào search textbox * dùng findElement not findElement(s)
	}

	@Test
	public void TC_03_Checkbox_Click() {
		driver.findElement(By.name("advs")).click(); // click vào checkbox advance search
		
	}
	@Test
	public void TC_04_sizeOfElement() { // tìm ra số lượng thẻ input trong màn hình
		System.out.println(driver.findElements(By.tagName("input")).size()); // dùng elements( tại tìm số lượng)
		
	}
	@Test
	public void TC_05_Checkbox_Click() { // click vào đường link Addresses tuyệt đối
		driver.findElement(By.linkText("Addresses")).click(); // gõ chính xác đường link
		
	}
	@Test
	public void TC_06_Checkbox_Click() { // click vào đường link  tương đối
		driver.findElement(By.partialLinkText("vendor account")).click(); // gõ tương đối text chứa đường link -> khuyết điểm : chậm
		
	}
	@Test
	public void TC_07_locateCSS() {
		driver.get("https://demo.nopcommerce.com/register"); // quay lại trang register
		
		//Cách 1 
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");
		
		//Cách 2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");
		
		//Cách 3
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("automation");
		
	}
	@Test
	public void TC_12_XPath() {
		driver.get("https://demo.nopcommerce.com/register"); // quay lại trang register
		
		//Cách 1 
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Selenium"); // or driver.findElement(By.cssSelector("//input[@data-val-required='First name is required.']")).sendKeys("Selenium");
		
		//Cách 2
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Locator"); //xpath và css chỉ khác nhau ký tự // và @(xpath) #(CSS)
		
		//Cách 3
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("automation"); // locate label Email xong /following-sibling::input là
		//locate input textbox của email
		
	}
	/*
	 * @Test public void TC_08_() {
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
