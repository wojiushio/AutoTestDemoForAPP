package com.ds.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.qameta.allure.Attachment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.TestListenerAdapter;

public class ToolUt extends TestListenerAdapter{
	
	 @Attachment(value = "{0},截图", type = "image/png")
	 public static byte[] takeScreenShot(String methodName,TakesScreenshot drivername) throws IOException {

	    	 return drivername.getScreenshotAs(OutputType.BYTES); 
	    	 }
    @Attachment(value = "Failure in method {1}", type = "image/png")

	 public static void snapshot(TakesScreenshot drivername, String filename)
	  {
	        // Now you can do whatever you need to do with it, for example copy somewhere
	        try {
	    	    File scrFile = (File) drivername.getScreenshotAs(OutputType.FILE);

	        	  String projectRoot = getProjectRoot();
	        	  System.out.println("save snapshot path is:"+projectRoot+"\\test-output\\html\\snapshot\\"+filename+new Date().toString());
		            FileUtils.copyFile(scrFile, new File(projectRoot+"\\test-output\\html\\snapshot\\"+filename));

		            
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            System.out.println("Can't save screenshot");
	            e.printStackTrace();
	        }
	        finally
	        {
	            System.out.println("screen shot finished");
	        }
	  }
	//截图
		 public static String getSnapshot(TakesScreenshot drivername, String path)
		  {
		      // this method will take screen shot ,require two parameters ,one is driver name, another is file name
			    File scrFile = (File) drivername.getScreenshotAs(OutputType.FILE);
			    Date currentTime = new Date();
		     	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		     	String dateString = formatter.format(currentTime);
		     	String filename = path+"screenshot_"+formatter.format(currentTime)+".png";

		        // Now you can do whatever you need to do with it, for example copy somewhere
		        try {
		        	  System.out.println("save snapshot path is:"+filename);
			            FileUtils.copyFile(scrFile, new File(filename)); 
			          
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		            System.out.println("Can't save screenshot");
		            e.printStackTrace();
		        }
		        finally
		        {
		            System.out.println("screen shot finished");
		        }
		        return filename;
		  }
	 
	 public static boolean isElementExsit(WebDriver driver, By locator) {  
	        boolean flag = false;  
	        try {  
	            WebElement element=driver.findElement(locator);  
	            flag=null!=element;  
	        } catch (NoSuchElementException e) {  
	            System.out.println("Element:" + locator.toString()  
	                    + " is not exsit!");  
	        }  
	        return flag;  
	    }
	 

	 
	 
	
	  public static void setFirefox(){
	          System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe"); 

	  }
	  
	  public static void setChromeBrowser(){
   	 
		  System.setProperty(
                  "webdriver.chrome.driver",
                  "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe");
	  }
		  
	      
	  
	
	
	  
	  public  static boolean isTextPresent(WebDriver driver,String what) {
		  try{
		  return driver.findElement(By.tagName("body")).getText().contains(what);// 遍历body节点下的所有节点 取其文本值 并判断是否包含 文本 what
		  }
		  catch (Exception e){
		  return false;// 没有该文本 则 返回 false
		  }
		  }
	  
	  public  static boolean isTextPresentForAPP(WebDriver driver,String what) {
		  try{

		  return driver.findElement(By.name("name")).getText().contains(what);// 遍历body节点下的所有节点 取其文本值 并判断是否包含 文本 what
		  }
		  catch (Exception e){
		  return false;// 没有该文本 则 返回 false
		  }
		  }
	  

	

public static List<String> showAllFiles(File dir, String matcher) throws Exception{
	List<String> list = new ArrayList<String>();
	   File[] fs = dir.listFiles();
	   for(int i=0; i<fs.length; i++){
	   if(fs[i].getName().contains(matcher)){
	     System.out.println(fs[i].getName());
	     list.add(fs[i].getName());
	    }
	    else{}
	   }
	return list;
	}



/** 
 * 上滑 
 *  
 * @param driver 
 * @param during 
 * @param num 
 */  
public static void swipeToUp(AppiumDriver<WebElement> driver,int during, int num) {  
   
        try {
        	 int width = driver.manage().window().getSize().width;  
        	    int height = driver.manage().window().getSize().height;  
        	    for (int i = 0; i < num; i++) {  
        	        driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, during);  
			Thread.sleep(3);
        	    } 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
     
}  
  
/** 
 * 下拉 
 *  
 * @param driver 
 * @param during 
 * @param num 
 */  
public static void swipeToDown(AppiumDriver<WebElement> driver,int during, int num) {  
    int width = driver.manage().window().getSize().width;  
    int height = driver.manage().window().getSize().height;  
    System.out.println(width);  
    System.out.println(height);  
    for (int i = 0; i < num; i++) {  
        driver.swipe(width / 2, height / 4, width / 2, height * 3 / 4, during);  
//        goSleep(3);  
    }  
}  
  
/** 
 * 向左滑动 
 *   
 * @param driver 
 * @param during 
 * @param num 
 */  
public static void swipeToLeft(AppiumDriver<WebElement> driver,int during, int num) {  
   
		try {
			 int width = driver.manage().window().getSize().width;  
			    int height = driver.manage().window().getSize().height;  
			    System.out.println("widthwidthwidthwidthwidthwidth"+width);  
			    System.out.println("heightheightheightheightheight"+height);  
			    for (int i = 0; i < num; i++) {  
//			        driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, during);  
			        driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, during);  

			Thread.sleep(3000);
			    }  
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
}  


/**
 * 通过传坐标来执行滑动操作
 * start(startX,startY)
 * end(endX,endY)
 *
 * @param startX 起始X坐标
 * @param startY 起始Y坐标
 * @param endX   终点X坐标
 * @param endY   终点Y坐标
 */
public static void fff(AppiumDriver<WebElement> driver) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	 int width = driver.manage().window().getSize().width;  
	    int height = driver.manage().window().getSize().height;  
    Map<Object,Object> params = new HashMap<Object, Object>();
    params.put("duration", 2000);
    params.put("fromX", width * 19 / 20);
    params.put("fromY", height / 2);
    params.put("toX", width / 20);
    params.put("toY", height / 2);
//    params.put("element", swipeView.getId());
    js.executeScript("mobile: dragFromToForDuration", params);
}
  

/**
 * description：定位到元素坐标，然后进行滑动操作
 * id: 要操作的元素     *
 * moveX：从起始x位置需要移动的距离（移动到beginX+moveX的位置）
 * moveY：从起始y位置需要移动的距离（移动到beginY+moveY的位置）
 */
public static void swipeToPosition(AppiumDriver<WebElement> driver){

  
    TouchAction ta = new TouchAction(driver);
    int width = driver.manage().window().getSize().width;  
    int height = driver.manage().window().getSize().height;  
  
    ta.press(width * 19 / 20,height / 2).moveTo(width / 20,height / 2).release().perform();
}


/** 
 * 向右滑动 
 *  
 * @param driver 
 * @param during 
 * @param num 
 */  
public static void swipeToRight(AppiumDriver<WebElement> driver,int during, int num) {  
    int width = driver.manage().window().getSize().width;  
    int height = driver.manage().window().getSize().height;  
    for (int i = 0; i < num; i++) {  
        driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, during);  
//        goSleep(3);  
    }  
}  
public static String getProjectRoot() {
	File directory = new File("");//
	String courseFile = null;
	try {
		courseFile = directory.getCanonicalPath();
		System.out.println(courseFile);

	} catch (IOException e) {
		e.printStackTrace();
	}
	return courseFile;

}
public static void clickScreen(int x, int y, int duration,
AppiumDriver drivers) {
JavascriptExecutor js = (JavascriptExecutor) drivers;
HashMap tapObject = new HashMap();
tapObject.put("x", x);
tapObject.put("y", y);
tapObject.put("duration", duration);
js.executeScript("mobile: tap", tapObject);
}


}
