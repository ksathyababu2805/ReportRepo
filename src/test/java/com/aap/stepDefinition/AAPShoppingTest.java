/*
 * package com.aap.stepDefinition;
 * 
 * import java.io.File; import java.util.Iterator; import java.util.Set;
 * 
 * import org.apache.commons.io.FileUtils; import org.openqa.selenium.By; import
 * org.openqa.selenium.Keys; import org.openqa.selenium.OutputType; import
 * org.openqa.selenium.TakesScreenshot; import org.openqa.selenium.WebDriver;
 * import org.openqa.selenium.chrome.ChromeDriver; import
 * org.openqa.selenium.interactions.Actions; import
 * org.openqa.selenium.support.ui.ExpectedCondition; import
 * org.openqa.selenium.support.ui.ExpectedConditions; import
 * org.openqa.selenium.support.ui.WebDriverWait;
 * 
 * import com.aap.objectrepo.AAPShopHomePage; import com.aap.utils.TestUtil;
 * 
 * import cucumber.api.java.After; import cucumber.api.java.Before; import
 * cucumber.api.java.en.Given; import cucumber.api.java.en.Then; import
 * cucumber.api.java.en.When; import
 * ru.yandex.qatools.allure.annotations.Attachment;
 * 
 * public class AAPShoppingTest {
 * 
 * static WebDriver driver; AAPShopHomePage aapshophome;
 * 
 * @Before public void setDriver() {
 * System.setProperty("webdriver.chrome.driver","Drivers/chromedriver-3");
 * driver = new ChromeDriver(); driver.manage().window().maximize();
 * 
 * }
 * 
 * @Given("^I open the aap shopping home page$") public void
 * i_open_the_aap_shopping_home_page() throws Throwable {
 * driver.get(AAPShopHomePage.AAPShopHomePageURL);
 * takeAndAttachScreenshot(driver); }
 * 
 * 
 * 
 * @When("^I select add vehicle option$") public void
 * i_select_add_vehicle_option() throws Throwable { aapshophome = new
 * AAPShopHomePage(driver); Actions a = new Actions(driver);
 * a.moveToElement(aapshophome.getAddVehicleElement()).build().perform();
 * takeAndAttachScreenshot(driver);
 * 
 * }
 * 
 * @Then("^I enter the vehicle details$") public void
 * i_enter_the_vehicle_details() throws Throwable { Thread.sleep(5000);
 * aapshophome.getDismissButtonElement().click();
 * System.out.println("Successfully Dismissed"); Thread.sleep(2000);
 * 
 * Actions a = new Actions(driver);
 * a.moveToElement(aapshophome.getAddVehicleElement()).build().perform();
 * System.out.println("Clicked on Add Vehicles");
 * 
 * a.moveToElement(aapshophome.getYearSelectElement()).click().sendKeys(Keys.
 * ARROW_DOWN,Keys.ENTER).build().perform();
 * a.moveToElement(aapshophome.getMakeSelectElement()).click().sendKeys(Keys.
 * ARROW_DOWN,Keys.ENTER).build().perform();
 * a.moveToElement(aapshophome.getModelSelectElement()).click().sendKeys(Keys.
 * ARROW_DOWN,Keys.ENTER).build().perform();
 * a.moveToElement(aapshophome.getEngineSelectElement()).click().sendKeys(Keys.
 * ARROW_DOWN,Keys.ENTER).build().perform(); takeAndAttachScreenshot(driver); }
 * 
 * @Then("^I save the information$") public void i_save_the_information() throws
 * Throwable { aapshophome.getAddVehicleButtonElement().submit();
 * Thread.sleep(2000); takeAndAttachScreenshot(driver); }
 * 
 * @After public void tearDown() { //driver.close(); }
 * 
 * @Attachment(value = "Page screenshot", type = "image/png") public byte[]
 * saveScreenshot(byte[] screenShot) { return screenShot; }
 * 
 * private void takeAndAttachScreenshot(WebDriver driver) { File src=
 * ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); try {
 * FileUtils.copyFile(src, new File("screenshots/page1.png")); saveScreenshot
 * (TestUtil.toByteArray(new File("screenshots/page1.png"))); } catch (Exception
 * e) { System.out.println(e.getMessage()); } }
 * 
 * public boolean waitForNewWindow(WebDriver driver, int timeout){ boolean flag
 * = false; int counter = 0; while(!flag){ try { Set<String> winId =
 * driver.getWindowHandles(); if(winId.size() > 1){ flag = true; return flag; }
 * Thread.sleep(1000); counter++; if(counter > timeout){ return flag; } } catch
 * (Exception e) { System.out.println(e.getMessage()); return false; } } return
 * flag; }
 * 
 * }
 */