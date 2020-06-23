package test.ds.testcases;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.Issue;
import java.io.IOException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ds.entity.BaseTest;
import com.ds.pages.LoginPageElements;
import com.ds.util.Capabilities;
import com.ds.util.ToolUt;

public class Test登陆_密码正确和不正确 extends BaseTest  {
	private StringBuffer verificationErrors = new StringBuffer();
	AndroidDriver<WebElement> driver = null;
	
	@BeforeClass( description = "测试初始化")
	@Feature("登陆退出")
	@Description("测试初始化")
	public void setUp() throws IOException {
		DesiredCapabilities capabilities = Capabilities.getCapabilities();

		try {
			
			driver = new AndroidDriver<WebElement>(new URL(Capabilities.getUrl()),
					capabilities);
			
			this.setDrivername(driver);
			this.setDriver(driver);
			Thread.sleep(15000);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot("APP启动失败 :"+e.toString(), this.getDrivername());
			Assert.fail("测试不通过");		}
	

	}

	/*
	 * 测试登录
	 */
	@Test(priority = 1, description = "测试账号正常登录和退出")
	@Parameters({ "username", "password" })
	 @Issue("AG-2759823")
//	@Features(Feature.)
	@Feature("登陆退出")
	@Story("登陆退出")
	@Severity(SeverityLevel.TRIVIAL)
	public void test_AccountLoginAndLogout(String username, String password) throws IOException {
		try {
			click(By.name(LoginPageElements.loginByAcc));

			enterKey(By.id(LoginPageElements.tv_phone),username);
//			enterKey(By.id(LoginPageElements.pw),password);
//
//			click(By.id(LoginPageElements.bt_login));
//
//			verifyElement(By.name(LoginPageElements.my));
//
//			click(By.name(LoginPageElements.my));
//
//			click(By.name(LoginPageElements.seting));
//			click(By.name(LoginPageElements.logout));

		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot("系统报错:"+e.toString(), this.getDrivername());
			Assert.fail("测试不通过");
		}

	}


	/*
	 * 测试登录
	 */
	@Test(priority = 2, description = "测试账号验证码错误登录")
	@Parameters({ "username", "Wpassword" })
	 @Issue("AG-2759823")
	@Feature("登陆退出")
	@Story("登陆退出")
	@Severity(SeverityLevel.TRIVIAL)
	public void test_AccountLoginWithWrongPassword(String username, String Wpassword) throws IOException {
		try {
		

			enterKey(By.id(LoginPageElements.tv_phone),username);
			enterKey(By.id(LoginPageElements.pw),Wpassword);

			click(By.id(LoginPageElements.bt_login));

			verifyElement(By.name(LoginPageElements.my));
		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot("系统报错:"+e.toString(), driver);
			Assert.fail("测试不通过");
		}

	}

	
	@AfterClass(description = "测试结束")
	@Feature("登陆退出")
	@Description("测试结束")
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}


}
