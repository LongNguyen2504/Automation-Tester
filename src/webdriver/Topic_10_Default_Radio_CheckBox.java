package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Default_Radio_CheckBox {
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
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
	}
	//Default radio/checkbox sẽ hiển thị và k bị che bởi các thẻ khác(span,div) và có kích thước tối thiểu để click
	//Custom  radio/checkbox sẽ bị nằm bên dưới các thẻ khác (span,div) và kích thước rất nhỏ(gần như 1 dấu chấm) do đó không thể click
	// Bất kỳ radio/checkbox đều phải có label hoặc thuộc tính value để phân biết -> để get locator
	//Radio chỉ dc chọn 1 / checkbox sẽ dc chọn nhiều mục
	//@Test 
	public void TC_01_Jotform() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		sleepInSecond(2);
		
		
		
		//Chon checkbox Cancer - Fainting Spells
		
		driver.findElement(By.xpath("//input[@value='Fainting Spells']")).click();
		//driver.findElement(By.xpath("//label[contains(text(),'Fainting Spells')]//preceding-sibling::input")).click();
		
		//Verify nó dc chọn thành công
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Fainting Spells']")).isSelected());
		
		//Chọn radio 5+ days - 1-2 cups/day
		
		driver.findElement(By.xpath("//input[@value='5+ days']")).click();
		
		//verify radio dc chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='5+ days']")).isSelected());
		
		
		//Bỏ chọn checkbox : Cancer - Fainting Spells
		driver.findElement(By.xpath("//input[@value='Fainting Spells']")).click();
		
		//Verify nó dc bỏ chọn thành công
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Fainting Spells']")).isSelected());
		
		//Bỏ chọn radio 5+ days - 1-2 cups/day -> phải chọn radio khác thì mới bỏ chọn dc cái cũ
		driver.findElement(By.xpath("//input[@value='3-4 days']")).click(); // click sang radio 3-4 days
		
		//Verify nó dc chọn thành công
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='5+ days']")).isSelected());
		
		  
	  }
	//@Test 
	public void TC_02_Jotform_SelectAll_Checkboxes() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		sleepInSecond(2);
		
		List<WebElement> listCheckbox = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement webElement : listCheckbox) {
			SelectAndUnselectElement(webElement);
		}
		//verify
		for (WebElement webElement : listCheckbox) {
			
			Assert.assertTrue(webElement.isSelected());
		}
		
		
	}
		  
	  
	@Test 
	public void TC_03_Default_Checkbox_Radio() {
		 driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		 sleepInSecond(2);
		 
		 List<WebElement> listCheckbox = driver.findElements(By.xpath("//div[@class='demo-section']//input"));
		 for (WebElement webElement : listCheckbox) {
				SelectAndUnselectElement(webElement);
			}
		 
	
	
	}
	@Test
	public void TC_04_() {
		  
	  }
	@Test 
	public void TC_05_() {
		  
	  }
	@Test 
	public void TC_06_() {
		  
	  }
	// Hàm click checkbox -> nếu chưa check thì mới check
	 public void selectElement(String XpathLocator) {
		 if(!driver.findElement(By.cssSelector(XpathLocator)).isSelected()) {
			 driver.findElement(By.cssSelector(XpathLocator)).click();
		 }
	 }
	// Hàm kiểm tra checkbox -> nếu đã check thì mới UNcheck
	 public void unselectElement(String XpathLocator) {
		 if(driver.findElement(By.cssSelector(XpathLocator)).isSelected()) {
			 driver.findElement(By.cssSelector(XpathLocator)).click();
		 }
	 }
	 public void SelectAndUnselectElement(WebElement checkBox) {
		 if(checkBox.isEnabled()) {
			 if(checkBox.isSelected()) {
				 checkBox.click(); // uncheck
				 Assert.assertFalse(checkBox.isSelected());
			 }else {
				 checkBox.click();
				 Assert.assertTrue(checkBox.isSelected());
			}
			 
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
