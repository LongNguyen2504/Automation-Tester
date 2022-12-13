package testNG;

import org.testng.annotations.Test;

public class Topic_03_Priority {
	@Test(priority = 1) // nếu k set thì khi run nó sẽ k chạy các function theo thứ tự trên xuống mà theo 0-9 Alphabet -> k đúng business mình mong
	//Trong thực tế sẽ k cần set priority mà ta sẽ đặt số thứ tự trong tên của test case luôn như EndUser_01_Create_New_Employee / EndUser_02_View_Employee
	public void EndUser_Create_New_Employee() {
		
		
	}
	@Test(priority = 2)
	public void EndUser_View_Employee() {
		
	}
	@Test(priority = 3)
	public void EndUser_Edit_Employee() {
		
	}
	@Test(priority = 4)
	public void EndUser_Move_Employee() {
		
	}
	@Test(enabled = true) // nếu bật false thì testcase này sẽ k dc run
	public void EndUser_Test_Enable1_Employee() {
		
	}
	@Test (description = "JIRA_0787 - Create a new Employee and verify and employee created success")
	//Description sẽ giúp tester đọc source code biết dc test case này có chức năng gì và id nào để quản lý trên TSM : testcase system management với ticket tương ứng với id đó -> dễ quản lý
	//Khi run thì description cũng sẽ hiển thị trên console của ide rằng testcase này đang bị fail và liên quan tới id đã note -> report dễ hơn
	//Description giúp bản thân người tester sau 1 thời gian khi review lại code sẽ dễ nhớ lại và check
	public void EndUser_Test_Description_Employee() {
		
	}

}
