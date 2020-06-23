package com.ds.util;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Capabilities {	   
	    public static DesiredCapabilities getCapabilities()  {  
	        // set up appium 
			String APPpath = "d:\\apptest\\run\\PRO\\apps\\gitchat.apk";

	        DesiredCapabilities capabilities = new DesiredCapabilities();  
	        capabilities.setCapability("platformName", "Android");  
	        capabilities.setCapability("deviceName","Android Emulator");  
	        capabilities.setCapability("platformVersion", "4.4");  
	        capabilities.setCapability("appPackage", "cn.gitbook.gitchat");  
	        capabilities.setCapability("automationName", "Appium");	       
	        capabilities.setCapability("app", APPpath); 
	        capabilities.setCapability("noReset", true);
	        capabilities.setCapability("newCommandTimeout", 120);
	        return capabilities;
	    }
	    
	      
	    public static String getUrl(){
	    	return "http://127.0.0.1:4723/wd/hub";
	    }
	    
	    public String getUserName(){
	    	return "%username%";
	    }
	    
	    public String getPassWord(){
	    	return "%password%";
	    }



   
}
