package webdriver;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_WebBrowser {
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
	@Test public void TC_01_() {
		driver.get("https://www.mitsubishicleansui.vn/");
		driver.get("https://www.mitsubishicleansui.vn/");
		driver.get("https://www.mitsubishicleansui.vn/");
		driver.close(); // /*
		driver.quit(); // /**
		 
		driver.findElement(By.xpath("//input[@id='']")); // get 1 element // /**
		List<WebElement> checkboxes = driver.findElements(By.xpath("")); // get a list of elements
		
		
		driver.getCurrentUrl();// return a string of url // /**
		Assert.assertEquals(driver.getCurrentUrl(),"url to be verified" );
		
		driver.getPageSource();// return page's source
		Assert.assertTrue(driver.getPageSource().contains("Facebook giúp bạn kết nối và chia sẻ với mọi người")); // verify tương đối với page source
		
		driver.getTitle(); // return page's title
		Assert.assertTrue(driver.getTitle().contains("Facebook - Đăng nhập hoặc đăng ký")); // verify title
		
		String loginWindowID = driver.getWindowHandle(); // return ID of window/tab which is active // /*
		Set<String> allIDs = driver.getWindowHandles(); // return set of windows/tabs (set not included duplicated returns) // /*
		
		Options opt = driver.manage(); // return interface Option
		opt.getCookies(); // return cookies (cookies allow to save login info for future login of web page) // /*
		
		Timeouts time = opt.timeouts(); //return interface Timeouts
		time.implicitlyWait(5000, TimeUnit.SECONDS); //5s = 5000ms , allow driver to wait x timeUnit to find element // /*
		time.pageLoadTimeout(5, TimeUnit.MINUTES); //allow driver to wait while page is loading
		time.setScriptTimeout(5, TimeUnit.MINUTES); // wait til the script loaded
		
		Window win = opt.window(); //return the interface for managing the current window.
		win.fullscreen(); //fullscreen borderless
		win.setPosition(null); // set Position for window
		win.getPosition(); // return window position
		win.maximize(); //maximize window // /**
		win.getSize();// return height and width of window
		
		Navigation nav = driver.navigate(); // to move forward,backward,refresh webpage
		
		TargetLocator tar = driver.switchTo(); // return A TargetLocator which can be used to select a frame or window
		//WebDriver API = alert/Authenication alert (Alert library) // /**
		tar.alert();
		//WebDriver API - Frame/Iframe(Frame library) // /**
		tar.frame("");
		//WebDriver API - Windows/Tabs // /**
		tar.window("");
		
		
		
		
		
		


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
