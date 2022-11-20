package webdriver;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	

	@BeforeClass
	public void beforeClass() {
		
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		driver = new FirefoxDriver();
		//Khởi tạo wait
		explicitWait = new WebDriverWait(driver, 30); // chờ element load trong html tree
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
	}
	//@Test 
	public void TC_01_() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		sleepInSecond(3);
		
		//1 Click vào 1 phần tử nào đó thuộc dropdown để nó xổ ra
		driver.findElement(By.cssSelector("span#number-button")).click();
		//2 Chờ cho tất cả các item trong dropdown dc load ra xong
		//Lưu ý: k thể dùng sleep cứng -> phải dùng hàm wait nào đó linh động
		// -> nếu như chưa tìm thấy giá trị thì sẻ tìm lại trong khoảng thời gian dc set
		//nếu như tìm thấy rồi thì k phải chờ hết khoảng thời gian này
		//Bắt được 1 locator để đại diện cho tất cả các item
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div"))); //CSS này đại diện cho 19 items trong dropdown list
		// hàm presence chỉ cần bắt dc element load đầy đủ trong HTML tree là được | còn hàm visibility thì bắt phải load trong HTML và hiển thị lên màn hình UI đầy đủ
		
		// 3.1 Nếu item cần chọn đang hiển thị
		// 3.2 Nếu item cần chọn ko hiển thị thì cần cuộn chuột xuống - scroll down
		// 4 kiểm tra text của item-nếu đúng giá trị cần get thì click vào
		// Viết code để duyệt qua từng item và kt theo điều kiện
		// Duyệt qua từng item - lấy ra text và kt nếu nó = với text mình mong muốn thì click vào
		
		//Get ra danh sách item trong HTML
		List<WebElement> listItem =  driver.findElements(By.cssSelector("ul#number-menu div"));
		// duyệt vòng lặp để get text từng item để so sánh với text đang tìm -> tìm thấy thì click vào và break
		for (WebElement webElement : listItem) {
			if(webElement.getText().equals("7")) {
				webElement.click();
				break;
			}
		}
		// --- Dùng hàm----
		sleepInSecond(2);
		selectCustomDropList("span#number-button", "ul#number-menu div", "5");
		//verify
		Select select = new Select(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")));
		assertEquals(select.getFirstSelectedOption().getText(), "5");

		sleepInSecond(2);
		selectCustomDropList("span#files-button", "ul#files-menu div", "Some other file with a very long option text");
		//verify
		select = new Select(driver.findElement(By.cssSelector("span#files-button span.ui-selectmenu-text")));
		assertEquals(select.getFirstSelectedOption().getText(), "Some other file with a very long option text");
		
		sleepInSecond(2);
		selectCustomDropList("span#speed-button", "ul#speed-menu div", "Faster");		//verify
		select = new Select(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")));
		assertEquals(select.getFirstSelectedOption().getText(), "Faster");
	
		
	
	}

	//@Test 
	public void TC_02_JQuerry_02() {
		  
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		sleepInSecond(2);
		
		scrollToElement("img.image-background");
		
		selectCustomDropList("button#selectize-input", "div.dropdown-menu a", "CITY G");
		assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(), "CITY G");
		
		new Select(driver.findElement(By.cssSelector("select#province"))).selectByVisibleText("TP. Hồ Chí Minh");
		assertEquals(new Select (driver.findElement(By.cssSelector("select#province"))).getFirstSelectedOption().getText() , "TP. Hồ Chí Minh");
		
		new Select(driver.findElement(By.cssSelector("select#registration_fee"))).selectByVisibleText("Khu vực I");
		assertEquals(new Select (driver.findElement(By.cssSelector("select#registration_fee"))).getFirstSelectedOption().getText() , "Khu vực I");
		
		
		
		
	  }
	//@Test 
	public void TC_03_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		sleepInSecond(2);
		
		selectCustomDropList("div.dropdown", "div.visible.menu.transition span", "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
	  }
	//@Test 
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		sleepInSecond(2);
		
		selectCustomDropList("li.dropdown-toggle", "ul.dropdown-menu li", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		  
	  }
	@Test public void TC_05_React_Ediable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		sleepInSecond(2);
		
		EditCustomDropList("input.search", "div.visible.menu.transition span", "Albania");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(),"Albania");
		  
	  }
	@Test public void TC_06_() {
		  
	  }
	@Test public void TC_07_() {
		  
	  }
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000); // chờ load trang sau khi click -> thư viện của java
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());;
		}
	}
	public void scrollToElement(String cssLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssLocator))); // scroll true = thao tác lăn chuột xuống 
		//-> mép trên cùng của browser là thước đo -> scroll đến khi mép trên đụng element có cssLocator truyền vào
	}
	public void selectCustomDropList(String parentLocator,String childLocator,String value) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> listItem =  driver.findElements(By.cssSelector(childLocator));
		// duyệt vòng lặp để get text từng item để so sánh với text đang tìm -> tìm thấy thì click vào và break
		for (WebElement webElement : listItem) {
			if(webElement.getText().equals(value)) {
				webElement.click();
				break; // sau khi click dc element thì html tree sẽ ẩn đi do đó cần break;
			}
		}
		
		
	}
	public void EditCustomDropList(String parentLocator,String childLocator,String value) {
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(value);
		sleepInSecond(1);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> listItem =  driver.findElements(By.cssSelector(childLocator));
		// duyệt vòng lặp để get text từng item để so sánh với text đang tìm -> tìm thấy thì click vào và break
		for (WebElement webElement : listItem) {
			if(webElement.getText().equals(value)) {
				webElement.click();
				break; // sau khi click dc element thì html tree sẽ ẩn đi do đó cần break;
			}
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
