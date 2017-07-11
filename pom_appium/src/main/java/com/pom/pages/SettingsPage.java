package com.pom.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SettingsPage {
	
	@AndroidFindBy(xpath="//android.widget.EditText")
	private List<WebElement> testBoxesList;
	
	public SettingsPage(AppiumDriver<WebElement> driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	public void inputTextInHybridURL(){
		testBoxesList.get(0).sendKeys("http://www.google.com");
	}
	
	public String getTextFromHybridURL(){
		return testBoxesList.get(0).getText();
	}
	
	
}
