package webdriver;
import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
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
	@Test public void TC_01_() {
		WebElement elmt = driver.findElement(null);
		
		elmt.clear(); // clear value (textbox,dropdownlist (editable not selectable),textarea -> erase data value of element) /*
		elmt.sendKeys(); //send value to element(mentioned above) /**
		elmt.click(); // action click on btn,link,checkbox,radio,... 
		
		driver.findElement(By.cssSelector("div.class a")); // tương đương driver.findElement(By.cssSelector("div.class a")).findElement(By.cssSelector("a"));
		String searchAttrb = elmt.getAttribute("placeholder"); //return value of attribute of elemt /**
		
		//GUI test Font/Size/Color/Location/Position,...
		String cssValue = elmt.getCssValue("backgroundColor"); // get CSS value  /**
		
		Point point = elmt.getLocation();// get location of element on web page(bên ngoài)
		point.x = 324;
		point.y = 324;
		
		
		
		Dimension di = elmt.getSize(); // get element size
		di.getHeight();
		di.getWidth();
		
		Rectangle rec = elmt.getRect(); // get area of Element (=location + size)
		
		elmt.getScreenshotAs(OutputType.BASE64); // get screenshot when testcase is failed(return phụ thuộc vào loại hình output như base64,files để import vào báo cáo) /*
		
		
		String nametag = elmt.getTagName();//Get the tag name of this element. -> used with locator id,class,name,cssSelector
		driver.findElement(By.xpath("//" + nametag + "[@id='email']")); //case of using nametag to find element
		
		elmt.getText(); //get text of elemnt(error/success message,...) /**
		//note -> text nằm bên ngoài element thì dùng getText , bên trong dùng attribute
		// tất cả các hàm trên sẽ hay sử dụng với assertEqual để verify text
		
		elmt.isDisplayed(); // check xem elemt có hiển thị lên k -> dùng với assertFalse,assertTrue , contains...
		//Phạm vi tất cả các element
		Assert.assertTrue(elmt.isDisplayed()); // /**
		Assert.assertFalse(elmt.isDisplayed());
		
		elmt.isEnabled(); // check elmt có dc phép thao tác hay k 
		//Phạm vi tất cả các element
		Assert.assertTrue(elmt.isEnabled());
		Assert.assertFalse(elmt.isEnabled());
		
		elmt.isSelected(); // dùng để verify element dc chọn hay chưa 
		//Phạm vi -> checkbox,radiobtn,default dropdown
		Assert.assertTrue(elmt.isSelected()); // /*
		Assert.assertFalse(elmt.isSelected());
		
		elmt.submit();// dùng cho các elmt nằm trong thẻ form -> ít dùng vì sẽ dùng hàm click() nhiều hơn để có độ chính xác
		
		//assert phạm vi tất cả các element
		assertTrue(false); // mong đợi kết quả trả về là True
		assertFalse(false); // mong đợi kq trả về là False
		//so sánh expected result và actual result thì dùng các hàm assertEqual() tùy loại thích hợp
		}
	@Test public void TC_02_() {
		  
	  }
	@Test public void TC_03_() {
		  
	  }
	@Test public void TC_04_() {
		  
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
