package com.ds.entity;

import static org.testng.AssertJUnit.assertEquals;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;

import com.ds.util.ToolUt;


/**
 * 测试基类
 */
public class BaseTest implements IHookable {
	TakesScreenshot drivername;
	AndroidDriver<WebElement> driver = null;

 
 
	public AndroidDriver<WebElement> getDriver() {
		return driver;
	}
	public void setDriver(AndroidDriver<WebElement> driver) {
		this.driver = driver;
	}
	public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        //从iTestResult中获取method    
        ConstructorOrMethod method = iTestResult.getMethod().getConstructorOrMethod();
    
        String name = method.getName();
        
        System.out.println("测试method是 "+name);
        System.out.println("开始执行~");
        iHookCallBack.runTestMethod(iTestResult); 
        System.out.println("结束~");

        
    }
    @Attachment(value = "Failure in method {0}", type = "image/png")
    private byte[] takeScreenShot(String methodName,TakesScreenshot drivername) throws IOException {

    	 return drivername.getScreenshotAs(OutputType.BYTES);    }
    public TakesScreenshot getDrivername() {
 		return drivername;
 	}
 	public void setDrivername(TakesScreenshot drivername) {
 		this.drivername = drivername;
 	}
 	
 	
 	
 	
 	
 	/*
 	 * 以下是steps
 	 */
 	@Step("点击输入框:{0},然后输入 {1} 。")
	public void enterKey(By by,String key) throws IOException{
		
		try {
			
			driver.findElement(by).click();
			driver.findElement(by).sendKeys(key);

			Thread.sleep(2000);
			driver.hideKeyboard();
			ToolUt.takeScreenShot("输入:"+key, driver);

		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot(e.toString(), driver);
//			Assert.assertEquals(true, false, e.toString());
			Assert.fail("输入失败，找不到元素"+by);


		}
		
	}
 	
 	

	@Step("点击控件:{0} 。")
	public void click(By by) throws IOException{
		
		try {
			driver.findElement(by).click();
			Thread.sleep(2000);
			ToolUt.takeScreenShot("点击控件::"+by, driver);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot(e.toString(), driver);
			Assert.fail("点击失败，找不到元素"+by);

		}
		
	}
	
	@Step("拖动页面往上滑动 。")
	public void swipeToUp() throws IOException{
		
		try {
			ToolUt.swipeToUp(driver, 1000, 1);
			Thread.sleep(1000);
			ToolUt.takeScreenShot("上滑 ", driver);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot(e.toString(), driver);
			Assert.fail("上滑失败");
		}
		
	}
	
	
	@Step("验证元素:{0} 是否存在 。")
	public void verifyElement(By by) throws IOException{
		
		try {
			assertEquals(true, ToolUt.isElementExsit(driver, by));
				ToolUt.takeScreenShot("验证成功:元素"+by+"已找到", driver);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot("验证失败:元素"+by+"找不到", driver);
			Assert.fail("验证失败:元素"+by+"找不到");
		}
		
	}
	
	
 	
}
