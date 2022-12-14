package listener;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.IListeners;

import com.google.common.io.Files;

import org.testng.Reporter;

import testNG.Topic_09_Listener;

public class ExtendReportListener implements ITestListener{
	//phải implement lại các method của interface ITestListener -> cần method nào thì implement method đó

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		TakesScreenshot t = (TakesScreenshot) Topic_09_Listener.driver;
		File srcFile = t.getScreenshotAs(OutputType.FILE);
		
		try {
			File destFile = new File("./screenshot/" + result.getName() + ".jpg"); 
			//tạo 1 folder trong source tree tên screenshot chứa ảnh screenshot
			//Sẽ có 1 report với format default tên emailable-report.html trong filder test-output sau khi chạy xong 
			//-> sau bài framework sẽ dùng các format tốt hơn như reportNG,extendReport,Alure...
			
			// khi cần take screenshot cho nhiều testcase thì phải có cơ chế đánh tên file cho từng testcase ở đây
			FileUtils.copyFile(srcFile, destFile);
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
				+ "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
