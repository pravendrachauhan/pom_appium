package com.pom.testScripts;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.pom.pages.DeviceInfoPage;
import com.pom.pages.LandingPage;

public class TestdroidTest {
	static AppiumDriver<WebElement> driver;
	LandingPage landingPage;
	DeviceInfoPage deviceInfoPage;
	private static AppiumDriverLocalService service;
	
	@BeforeSuite
	public void createAppiumSession(){
		

		String osName = System.getProperty("os.name").toLowerCase();

		String nodePath = null;
		String appiumPath = null;

		if (osName.contains("mac")) {
			//            mac path
			nodePath = "/usr/local/bin/node";
			appiumPath = "/usr/local/lib/node_modules/appium/build/lib/main.js";
		} else if (osName.contains("linux")) {
			//      linux path
			nodePath = System.getenv("HOME") + "/.linuxbrew/bin/node";
			appiumPath = System.getenv("HOME") + "/.linuxbrew/lib/node_modules/appium/build/lib/main.js";
		}
		else if(osName.contains("windows")){
			//          windows path
			nodePath = "C:\\Program Files (x86)\\Appium\\node.exe";
			appiumPath = "C:\\Program Files (x86)\\Appium\\node_modules\\appium\\lib\\server\\main.js";
		}
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
		.usingDriverExecutable(new File(nodePath))
		.usingPort(4723)
		.withAppiumJS(new File(appiumPath)));

		service.start();	
	
	}
	
	@AfterSuite
	public void stopAppiumSession(){
		service.stop();
	}
	
	@BeforeClass
	public void setUp() throws MalformedURLException{
		DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
		String packageName="com.testdroid.sample.android";
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "192.168.101.101:5555");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.0");
		desiredCapabilities.setCapability("deviceName", "Android Emulator");

		String apkName="TestDroid.apk";
		File classpathRoot=new File(System.getProperty("user.dir"));
		File appDir=new File(classpathRoot,"//testdata//apk");
		File app= new File(appDir, apkName);
		desiredCapabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		desiredCapabilities.setCapability("appPackage", packageName);
		desiredCapabilities.setCapability("appActivity", packageName+".MM_MainMenu");
		desiredCapabilities.setCapability("locale", "US");
		desiredCapabilities.setCapability("deviceReadyTimeout", "450");
		desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60000");
		
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
	
	}
	
	@AfterClass
	public void quitDriver(){
		driver.quit();
	}
	
	@Test
	public void testdroidAppTest(){
		String expectedManufacturer= "Genymotion";
		String expectedDeviceName= "Google Nexus 5";
		
		landingPage = new LandingPage(driver);
		deviceInfoPage = landingPage.clickOnDeviceInfoButton();
		
		String manufacturer = deviceInfoPage.getTextFromManufacturer();
		String deviceName =  deviceInfoPage.getTextFromDeviceName();
		System.out.println("Manufacturer: "+manufacturer);
		System.out.println("My device name: "+deviceName);
		
		Assert.assertEquals(manufacturer, expectedManufacturer);
		boolean chk=false;
		if(deviceName.contains(expectedDeviceName)){
			chk= true;
		}
		Assert.assertTrue(chk);
		
	}
	
	
	
}
