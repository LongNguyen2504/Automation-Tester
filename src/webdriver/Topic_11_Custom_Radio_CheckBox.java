package webdriver;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Radio_CheckBox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
	}
	//@Test 
	public void TC_01_CustomCheckBox() {
		driver.get("https://material.angular.io/components/checkbox/overview");
		sleepInSecond(2);
		
		/* Case 1 : 
		 * Thẻ input : bị ẩn nên k click dc
		 * Thẻ input : có thể verify
		 * 
		 * Case 2 :
		 * Ko dùng thẻ input để click - thay thế bằng 1 thẻ đang hiển thị đại
		 * diện cho Checkbox/Radio = span/div...
		 * Nhưng Span/div ko verify dc
		 * 
		 * 
		 * Case 3: 
		 * Thẻ span để click
		 * Thẻ input để verify
		 * Trong 1 dự án mà 1 element cần tới 2 locator để define thì sinh ra nhiều code/ cần phải
		 * maintain nhiều -> gây hiểu nhầm cho người review code
		 * 
		 * 
		 * 
		 * Case 4: Work-around
		 * Thẻ input để click + verify = hàm của javascript
		 * Hàm click() của WebElement k click vào element bị ẩn dc
		 * Hàm click() của JavascriptExecutor để click : k quan tâm element bị ẩn hay k
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * */
		
		//Code case 1
		//driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).click();
		// kinh nghiệm khi chọn xpath của các thẻ bị ẩn thì phải dùng kỹ thuật axes của xpath -> chọn các thẻ có label,title,text cố định
		//rồi sau đó mới đi đến các thẻ input,checkbox,...
		//sleepInSecond(3);
		
		//verify
		//Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).isSelected());
		 // Lưu ý : isSelected chỉ dành cho thẻ input,checkbox,radio với điều kiện phải dc hiển thị
		
		//Code case 2:
		//driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).click();
		//sleepInSecond(3);
		
		//Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).isSelected());
		  
		//Code case 3:
		//driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).click();
		//sleepInSecond(3);
		
		//Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).isSelected());
		
		
		//Code case 4:
		
		By checkedCheckBox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkedCheckBox));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).isSelected());
		
		// line 98 tương đương với command sau trong tab console javascript
		// var checkedCheckbox = $x("//span[text()='Checked']/preceding-sibling::span/input")[0];
		// checkedCheckbox.click();
	  }
	//@Test 
	public void TC_02_Custom_RadioButton() {
		driver.get("https://material.angular.io/components/radio/examples");
		sleepInSecond(2);
		
		By springRadio = By.xpath("//span[contains(text(),'Spring')/preceding-sibling::span/input");
		//driver.findElement(springRadio).click(); // sẽ bị lỗi vì thẻ input đã bị che bơi các thẻ khác do đó cần dùng javasript
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(springRadio));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(springRadio).isSelected());
	  }
	//@Test 
	public void TC_03_Custom_VnDirect() {
		
		driver.get("https://account-v2.vndirect.com.vn/");
		sleepInSecond(2);
		
		By termCheckbox = By.xpath("//input[@name='acceptTerms']");
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(termCheckbox));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(termCheckbox).isSelected());
	  }
	@Test
	public void TC_04_Google_Radio() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(2);
		// do google k dùng radio hay checkbox mà sẽ sử dụng các div với các thuộc tính tương tự checkbox/radio 
		// -> vì vậy sẽ sử dụng các thuộc tính cụ thể để click và verify
		//Verify trước khi click
		By canThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
		driver.findElement(canThoRadio).getAttribute("aria-checked");
		// với google doc, element đã click là element có thuộc tính aria-checked = true
		Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "false"); 
		
		//click vao
		driver.findElement(canThoRadio).click();
		sleepInSecond(3);
		
		//verify sau khi click
		Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "true"); 
		
		By miQuangCheckBox = By.xpath("//div[@aria-label='Mì Quảng']");
		
		checkToCheckBox("//div[@aria-label='Mì Quảng']");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(miQuangCheckBox).getAttribute("aria-checked"), "true");
		
		
		
		unCheckToCheckBox("//div[@aria-label='Mì Quảng']");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(miQuangCheckBox).getAttribute("aria-checked"), "false");
		
		
		  
	  }
	public void checkToCheckBox(String xpathLocator) { // chưa click thì mới dc click tránh trường hợp ngược lại
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if(element.getAttribute("aria-checked").equals("false")) {
			element.click();
		}
	}
	public void unCheckToCheckBox(String xpathLocator) { 
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if(element.getAttribute("aria-checked").equals("true")) {
			element.click();
		}
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
