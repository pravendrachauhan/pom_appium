package com.pom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LandingPage {
	
	AppiumDriver<WebElement> driver;
	
	@AndroidFindBy(id="com.testdroid.sample.android:id/mm_b_native")
	private WebElement nativeActivityButton;
	
	@AndroidFindBy(id="com.testdroid.sample.android:id/mm_b_hybrid")
	private WebElement hybridActivityButton;
	
	@AndroidFindBy(id="com.testdroid.sample.android:id/mm_b_functions")
	private WebElement functionsButton;
	
	@AndroidFindBy(id="com.testdroid.sample.android:id/mm_b_deviceInfo")
	private WebElement deviceInfoButton;
	
	@AndroidFindBy(id="com.testdroid.sample.android:id/mm_b_settings")
	private WebElement settingsButton;
	
	public LandingPage(AppiumDriver<WebElement> driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	public void clickOnNativeActivityButton(){
		nativeActivityButton.click();
	}
	
	public void clickOnHybridActivityButton(){
		hybridActivityButton.click();
	}
	
	public void clickOnFunctionsButton(){
		functionsButton.click();
	}
	
	public DeviceInfoPage clickOnDeviceInfoButton(){
		deviceInfoButton.click();
		return new DeviceInfoPage(driver);
	}
	
	public SettingsPage clickOnSettingsButton(){
		settingsButton.click();
		return new SettingsPage(driver);
	}
	
	

}
