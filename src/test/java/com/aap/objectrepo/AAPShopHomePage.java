package com.aap.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AAPShopHomePage {
	
	WebDriver driver;
	
	public static String AAPShopHomePageURL = "https://shop.advanceautoparts.com";
	
	public AAPShopHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='aap-dheader-garage__txt']")
	WebElement addVehicle;
		
	@FindBy(css="#aap-flyout-ymme-year")
	WebElement yearSelect;
	
	@FindBy(css=".aap-garage-flyout__make")
	WebElement makeSelect;
	
	@FindBy(id="#aap-flyout-ymme-model")
	WebElement modelSelect;
	
	@FindBy(id="#aap-flyout-ymme-engine")
	WebElement engineSelect;
	
	@FindBy(css = ".aap-js-btnaction-txt")
	WebElement addVehicleButton;
	
	@FindBy(xpath = "//*[contains(@class,'bx-row-submit-no')]")
	WebElement dismissButton;
	

	public WebElement getAddVehicleElement() {
		return addVehicle;
	}

	public WebElement getYearSelectElement() {
		return yearSelect;
	}
	
	public WebElement getMakeSelectElement() {
		return makeSelect;
	}
	
	public WebElement getModelSelectElement() {
		return modelSelect;
	}
	
	public WebElement getEngineSelectElement() {
		return engineSelect;
	}
	
	public WebElement getAddVehicleButtonElement() {
		return addVehicleButton;
	}
	
	public WebElement getDismissButtonElement() {
		return dismissButton;
	}
}
