package webdriver;
import java.util.Random;
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

public class Topic_18_Java_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
//		if (osName.contains("Mac OS")) {
//		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
//		}
//		else {
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		}
		
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
	}
	//@Test 
	public void TC_01() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(3);
		//verify domain
		Assert.assertEquals(jsExecutor.executeScript("return document.domain"), "live.techpanda.org");
		//Verify URL
		Assert.assertEquals(executeForBrowser("return document.URL;"), "http://live.techpanda.org/");
		//Open mobile link
		hightlightElement("//a[text()='Mobile']");
		sleepInSecond(1);
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(1);
		//Add samsung galaxy to cart
		hightlightElement("//a[text()='Samsung Galaxy']//parent::h2/following-sibling::div[@class='actions']//button");
		sleepInSecond(1);
		clickToElementByJS("//a[text()='Samsung Galaxy']//parent::h2/following-sibling::div[@class='actions']//button");
		sleepInSecond(3);
		//verify message dc hiển thị
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		//click customer service
		hightlightElement("//a[text()='Customer Service']");
		sleepInSecond(1);
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(2);
		//Verify title page
		Assert.assertEquals(executeForBrowser("return document.title"), "Customer Service");
		sleepInSecond(1);
		//Scroll to bottom
		scrollToBottomPage();
		//Nhập email
		hightlightElement("//input[@name='email']");
		sleepInSecond(1);
		sendkeyToElementByJS("//input[@name='email']", "afc"+ getRandNumb() + "@gmail.net");
		
		//Click subcribe
		hightlightElement("//span[text()='Subscribe']");
		sleepInSecond(1);
		clickToElementByJS("//span[text()='Subscribe']");
		sleepInSecond(2);
		
		
		//Verify text co hiển thị
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		sleepInSecond(1);
		
		//Navigate tới domain http://demo.guru99.com/v4/
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(2);
		
		//Verify domain sau khi  navigate
		Assert.assertEquals(jsExecutor.executeScript("return document.domain"), "demo.guru99.com");
		sleepInSecond(2);
	  }
	@Test 
	public void TC_02_Warranty_Rode() {
		driver.get("https://warranty.rode.com/");
		sleepInSecond(3);
		String registerBtn = "//button[contains(text(),'Register')]";
		String firstNameTxtBox = "//div[contains(text(),'Register')]//following-sibling::div//input[@id='firstname']";
		String surNameTxtBox = "//div[contains(text(),'Register')]//following-sibling::div//input[@id='surname']";
		String emailTxtBox = "//div[contains(text(),'Register')]//following-sibling::div//input[@id='email']";
		String pswTxtBox = "//div[contains(text(),'Register')]//following-sibling::div//input[@id='password']";
		String confPwdTxtBox = "//div[contains(text(),'Register')]//following-sibling::div//input[@id='password-confirm']";
		
		//Click register
		hightlightElement("//button[contains(text(),'Register')]");
		sleepInSecond(1);
		clickToElementByJS("//button[contains(text(),'Register')]");
		sleepInSecond(2);
		
		//Verify
		Assert.assertEquals(getElementValidationMessage(firstNameTxtBox), "Vui lòng điền vào trường này.");
		
		sendkeyToElementByJS(firstNameTxtBox, "Automation");
		clickToElementByJS(registerBtn);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(surNameTxtBox), "Vui lòng điền vào trường này.");
		
		sendkeyToElementByJS(surNameTxtBox, "Test");
		clickToElementByJS(registerBtn);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(emailTxtBox), "Vui lòng điền vào trường này.");
		
		sendkeyToElementByJS(emailTxtBox, "Test123123@gmail.com");
		clickToElementByJS(registerBtn);
		sleepInSecond(2);
		
		
		  
	  }
	@Test 
	public void TC_03_() {
		  
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
	public int getRandNumb() {
		return new Random().nextInt(99999);
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000); // chờ load trang sau khi click -> thư viện của java
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());;
		}
	}
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 4px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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
