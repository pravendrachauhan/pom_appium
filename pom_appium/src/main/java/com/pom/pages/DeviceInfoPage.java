package com.pom.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DeviceInfoPage {
	
	@AndroidFindBy(id="com.testdroid.sample.android:id/device_property_tv_value")
	private List<WebElement> propertyValues;
	
	public DeviceInfoPage(AppiumDriver<WebElement> driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public String getTextFromManufacturer(){
		return propertyValues.get(0).getText();
	}
	
	public String getTextFromDeviceName(){
		return propertyValues.get(1).getText();
	}
	

}
