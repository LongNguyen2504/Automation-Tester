package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_FindElement_FindElements {
	WebDriver driver;
	
	
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
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Open browser
		//Tương tác qua Browser thì sẽ thông qua WebDriver driver
		//Tương tác qua Element thì sẽ thông qua WebElement element
		
	}
	//Topic này đánh giá sự khác biệt của findElement và findElements
	//Kết luận topic : FindElement/FindElements bị ảnh hưởng bởi implicityWait và ngược lại
	@Test 
	public void TC_01_FindElement() {
		//Tìm thấy 1 node/element
		//Tìm thấy và thao tác trực tiếp lên node đó
		//Nếu k tìm thấy trong HTML thì cần phải chờ hết 10s timeout của implicity
		driver.findElement(By.cssSelector("input#email"));
		
		//Tìm thấy nhiều hơn 1 node/element
		//Nó sẽ return ra 1 node đầu tiên và bỏ qua các node còn lại
		//Trong trường hợp locator sai thì sẽ return ra element sai
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("asd@gmail.com");
		
		
		//Không tìm thấy node/element nào
		//Có cơ chế tìm lại của implicity,sẽ tìm lại mỗi 0.5s cho đến khi reach timeout của implicity
		//Nếu trong thời gian tìm lại mà thấy element trong html thì thỏa mãn Đk và pass
		//Nếu hết timeout implicity 10s mà vẫn k thấy element thì
		//+Đánh fail test case này tại step này
		//+Throw ra 1 ngoại lệ :  NoSuchElementException(lỗi này khá phổ biến với các case như element nằm trong window/frame khác,timeout quá sớm dẫn đến k load kịp element trong html...)
		//+Các lệnh phía sau step gây lỗi NoSuchElement sẽ k dc thực thi
		driver.findElement(By.cssSelector("input[type='check']"));
	  }
	@Test 
	public void TC_02_FindElements() {
		//Tìm thấy 1 node/element
		// Tìm thấy và lưu vào 1 list kiểu WebElement
		// Vì nó tìm thấy nên ko cần phải chờ hết timeout 10s của implicity
		List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number = " + elements.size());
		
		
		//Tìm thấy nhiều hơn 1 node/element
		// Tìm thấy và lưu tất cả vào list = element tương ứng
		elements = driver.findElements(By.cssSelector("input"));
		System.out.println("List element number = " + elements.size());
				
				
		//Không tìm thấy node/element nào
		//Có cơ chế tìm lại với mỗi 0.5s của implicity
		//Nếu timeout ma thấy element thì sẽ dc add vào list và pass testcase
		//Nếu timeout mà k tìm thấy element thì
		//+Ko đánh fail test  case và chạy dc các step phía dưới
		//+Trả về 1 list rỗng,size = 0
		elements = driver.findElements(By.cssSelector("input[type='check']"));
		System.out.println("List element number = " + elements.size());		
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
