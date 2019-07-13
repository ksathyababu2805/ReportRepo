package com.aap.stepDefinition;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.RequestBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aap.utils.TestUtil;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

public class UITester {
	
	public static WebDriver driver;
	
	
	
	@Given("^Open chrome and start application$")
	public void open_chrome_and_start_application() throws Throwable {
		System.setProperty("webdriver.chrome.driver","Drivers/chromedriver-3");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
		
	}

	@When("^I search for \"([^\"]*)\"$")
	public void i_search_for(String arg1) throws Throwable {
	    driver.findElement(By.name("q")).sendKeys(arg1);
	    driver.findElement(By.name("btnK")).submit();
	    File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    try {
	    FileUtils.copyFile(src, new File("screenshots/page1.png"));
	    saveScreenshot (TestUtil.toByteArray(new File("screenshots/page1.png")));
	    }
	    catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
	    
	}

	@Then("^User should be able to click the search result$")
	public void user_should_be_able_to_click_the_search_result() throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText("infosys.com")).click();
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    try {
	    FileUtils.copyFile(src, new File("screenshots/page2.png"));
	    saveScreenshot (TestUtil.toByteArray(new File("screenshots/page2.png")));
	    }
	    catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
		driver.quit();
	}
	
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshot(byte[] screenShot) {
	    return screenShot;
	}
		
		
				
	
}
