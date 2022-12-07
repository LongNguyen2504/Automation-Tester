package webdriver;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_User_Interraction_Actions {
	WebDriver driver;
	Actions action;
	
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jspExecutor;

	@BeforeClass
	public void beforeClass() {
		
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		
//		if (osName.contains("Mac OS")) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
//		}
//		else {
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		}
		driver = new FirefoxDriver();
//		driver = new ChromeDriver();
		//explicitWait = new WebDriverWait(driver, 30); // chờ element load trong html tree
		action = new Actions(driver);
		jspExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
	}
	//@Test 
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip//");
		sleepInSecond(1);
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(1); // phải wait để element dc trigger sau đó mới verify dc
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
		
		  
		//Lưu ý : WebElement clicl -> phải là element clickable dc -> ko hover trước khi click
		
		// Action click -> k cần clickable vẫn click vào tọa độ element dc -> hover trước khi click
		// trong thực tế thì actions chỉ sử dụng để hoverElement là nhiều nhất
	}
	//@Test 
	public void TC_02_Hover_Myntra() {
		driver.get("https://www.myntra.com/aw22");
		sleepInSecond(3);
		
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLinks']//a[text()='Kids']"))).perform();
		sleepInSecond(2);
		
		//action.click().perform();
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click(); // lưu ý : phải wait vài giây ngay sau khi hover để sub menu có thời gian load trước
		//khi click vào link cần chọn vì nếu không wait sẽ k kịp load sub menu để click (do trình biên dịch xử lý nhanh hơn thời gian load sub menu khi hover) -> ra lỗi
		//could not be scroll into view
		sleepInSecond(2);
		
		//H
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(), "Kids Home Bath");
		// Lưu ý : dùng WebElement để click là tốt nhất vì sẽ giống với native event -> giống với action của user nhất
	}
	//@Test 
	public void TC_03_Fahasa() {
		driver.get("https://www.fahasa.com/");
		sleepInSecond(6);
		driver.findElement(By.cssSelector("div#desktopBannerWrapped button#moe-dontallow_button")).click(); // hủy pop up subcribe
		
		sleepInSecond(6);
		//Do popup nằm trong iframe nên phải tìm iframe và switch sang để bắt element
		WebElement iframePopup = driver.findElement(By.xpath("//iframe[contains(@id,'moe-onsite')]"));
		
		driver.switchTo().frame(iframePopup);
//		action.moveToElement(driver.findElement(By.cssSelector("div.root-container button#close-icon img"))).perform();
		driver.findElement(By.cssSelector("div.root-container button#close-icon img")).click(); //hủy pop up khuyến mãi
		//leaving a frame
		driver.switchTo().defaultContent();
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Kỹ Năng Sống']")).click();
		
		//Hàm get text của WebElement sẽ trả về text hiển thị y chang trên UI -> khi assert phải cho expected text là copy text của UI
		Assert.assertEquals(driver.findElement(By.xpath("//ol[@class='breadcrumb']/li/strong")).getText(), "KỸ NĂNG SỐNG");
		
		
		// hàm isDisplay với element có text = '' thì text này phải là text của HTML để có thể tìm đến chính xác element và verify
		Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']/li/strong[text()='Kỹ năng sống']")).isDisplayed());
		//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='root-container']//button[@id='close-icon']")));
		
		
	
		
		//jspExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='root-container']//button[@id='close-icon']//img")));
		//action.click(driver.findElement(By.xpath("//div[@class='root-container']//button[@id='close-icon']//img"))).perform();
		//  
	}
	//@Test
	public void TC_04_click_hold_element() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		sleepInSecond(3);
		
		//Bắt tất cả 12 el
		List<WebElement> listElements = driver.findElements(By.cssSelector("ol#selectable li"));
		
		// Cách 1
		//click và hold ở số 1 -> index 0
		action.clickAndHold(listElements.get(0)).perform();
		sleepInSecond(1);
		//drag mouse to number 11-> index 10
		action.release(listElements.get(10)).perform();
		sleepInSecond(1);
		// Cách 2 : 
		//action.dragAndDrop(listElements.get(0), listElements.get(10)).perform();
		//sleepInSecond(3);
		
		
		//Verify số lượng element đã chọn
		
		// Do các element li sau khi chọn sẽ có thêm 1 class ui-selected -> lưu vào 1 list để lấy size đi so sánh assert với số lượng thực tế đã chọn
		List<WebElement> listSelected = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(listSelected.size(), 9);
		
		
		// Cách verify = text -> tham khảo -> khi nào framwork thì mới cần
		// Tạo ra 1 array chứa các test expected
		/*String[] listExpectedText = {"1","2","3","5","6","7","9","10","11"};
		// tạo ra 1 ArrayList chứa các text actual của các element trong listSelected để 1 tí nữa asssert với array String listExpectedText
		ArrayList<String> listActualText = new ArrayList<>();
		for (WebElement element : listSelected) {
			listActualText.add(element.getText());
		}
		//Assert list actual text và expected -> phải convert listExpectedText từ array sang list để assert có thể so sánh collection với collection chp hù hợp
		Assert.assertEquals(listActualText, Arrays.asList(listExpectedText));
		*/
		
	  }
	//@Test 
	public void TC_05_click_hold_random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		sleepInSecond(3);
		//Bắt tất cả 12 el
		List<WebElement> listElements = driver.findElements(By.cssSelector("ol#selectable li"));
		
		//Nhấn phím CONTROL trên window hoặc COMMAND trên MacOS
		Keys key = null;
		if (osName.contains("Mac OS")) {
			key = Keys.COMMAND;
		}
		else {
			key = Keys.CONTROL;
		}
		//Nhấn phím control xún để chọn mutiple
		action.keyDown(key).perform();
		// click các element cần chọn
		action.click(listElements.get(0)).click(listElements.get(5)).click(listElements.get(7)).perform(); // chọn 1 6 8
		sleepInSecond(3);
		
		//Nhả control ra
		action.keyUp(key).perform();
				
		//Verify
		List<WebElement> listSelected = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(listSelected.size(), 3);
	  }
	@Test 
	public void TC_06_doubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		
		// do firefox k scroll dc đến element nên k tìm thấy element -> lỗi portview màn hình UI chưa hiển thị element
		// nếu chuyển sang chrome thì chrome sẽ tự scroll đến element
		
		if(driver.toString().contains("firefox")) {
			//scroll tới element trước
			jspExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()=\"Double click me\"]")));
		// else là chrome sẽ tự scroll và click
		}
		//double click của user interraction thì cần phải scroll xún để UI hiển thị element -> nếu dùng driver.click thì k cần scroll nhưng chỉ single click
		action.doubleClick(driver.findElement(By.xpath("//button[text()=\"Double click me\"]"))).perform(); 
		
		//Verify text
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
		
		
		
		
		
		
	  }
	//@Test 
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		sleepInSecond(3);
		
		//click chuột phải
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		// hover
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
		//verify quit display
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Quit']")).isDisplayed());
		//verify quit có trạng thái hover - visible
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover.context-menu-visible")).isDisplayed());
		//click vào quit
		action.click(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
		//handle alert
		driver.switchTo().alert().accept();
		//verify quit ở trạng thái invisible chưa
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		
		
		  
	  }
	//@Test 
	public void TC_08_Drag_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		sleepInSecond(3);
		  
		WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
		WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
		
		action.dragAndDrop(smallCircle,bigCircle ).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		
		String rgbaColor = bigCircle.getCssValue("background-color");
		System.out.println("RGB color: "+ rgbaColor);
		
		//convert fgbaColor to Hexa color
		String hexaColor = org.openqa.selenium.support.Color.fromString(rgbaColor).asHex().toUpperCase();
		System.out.println("Hexa color: "+ hexaColor);
		
		
		//Verify bg color
		Assert.assertEquals(hexaColor, "#03A9F4");
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
