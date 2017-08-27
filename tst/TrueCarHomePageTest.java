import com.oracle.tools.packager.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.Before;
import org.junit.After;

import java.io.File;
import java.net.URL;

public class TrueCarHomePageTest {

    AppiumDriver driver;
    @Before
    public void SetUp() throws Exception {

        //Specify capabilities required by Appium server
        File appDir = new File("/Users/thakkarn/Downloads");
        File app = new File(appDir, "TrueCar The Car Buying App Find New Used Cars_v9.12.1_apkpure.com.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device","Android");

        //mandatory capabilities
        capabilities.setCapability("deviceName","Android");
        capabilities.setCapability("platformName","Android");

        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());

        driver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    //Disabling the driver after test is run
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void selectCarModelTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 60);

        //Closing the initial page of True Car to go to the home page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.truecar.mobile.android.consumer:id/imageviewClose")));
        WebElement closeButton = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/imageviewClose"));
        closeButton.click();
        Log.info("Closing the initial page");

        //Clicking on the car icon on top nav bar to go to the car selection page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.HorizontalScrollView[1]/android.widget.LinearLayout[1]/android.support.v7.app.ActionBar.Tab[2]")));
        WebElement carIcon = driver.findElement(By.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.HorizontalScrollView[1]/android.widget.LinearLayout[1]/android.support.v7.app.ActionBar.Tab[2]"));
        carIcon.click();
        WebElement usedButton = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/segmented_control_right"));
        Assert.assertTrue(usedButton.isDisplayed());
        Log.info("Goes to the car selection page");

        //Finding Used cars in 90210 zipcode
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.truecar.mobile.android.consumer:id/segmented_control_right")));
        usedButton.click();
        Log.info("click on Used Button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.truecar.mobile.android.consumer:id/button_find_vehicles_bottom")));
        WebElement findUsedCarsButton = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/button_find_vehicles_bottom"));
        findUsedCarsButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.truecar.mobile.android.consumer:id/edittext_postalcode")));
        WebElement zipCodeText = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/edittext_postalcode"));
        zipCodeText.sendKeys("90210");
        Log.info("select the text box and enter the zipcode");
        WebElement goButton = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/button_go"));
        goButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.truecar.mobile.android.consumer:id/button_find_vehicles_bottom")));
        findUsedCarsButton.click();
        Log.info("Clicking on Find Used Cars button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.truecar.mobile.android.consumer:id/used_find_search")));
        WebElement searchTextBox = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/used_find_search"));
        Assert.assertTrue(searchTextBox.isDisplayed());

        //Scrolling for BMW and selecting '2 series' model
        TouchAction action = new TouchAction(driver);
        action.press(0, 400);
        action.waitAction(200);
        action.moveTo(0, 200);
        action.release();
        action.perform();
        Log.info("Scrolling down the list");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='BMW']")));
        WebElement bmw = driver.findElement(By.xpath("//android.widget.TextView[@text='BMW']"));
        bmw.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='2 Series']")));
        WebElement selectModel = driver.findElement(By.xpath("//android.widget.TextView[@text='2 Series']"));
        selectModel.click();
        Log.info("selected 2 series model");

        //Filter to find a BMW 2 series model in Black
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.truecar.mobile.android.consumer:id/used_search_results_filter_textview")));
        WebElement clickOnFilter = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/used_search_results_filter_textview"));
        clickOnFilter.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Color']")));
        WebElement selectColor = driver.findElement(By.xpath("//android.widget.TextView[@text='Color']"));
        selectColor.click();
        Log.info("Clicking on 'color' field");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Black']")));
        WebElement clickBlack = driver.findElement(By.xpath("//android.widget.TextView[@text='Black']"));
        clickBlack.click();
        Log.info("Selecting black color");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.truecar.mobile.android.consumer:id/used_refine_search_color_submit")));
        WebElement setColorsButton = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/used_refine_search_color_submit"));
        setColorsButton.click();
        Log.info("Set Black color");

        //Opening the first car from the list
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.truecar.mobile.android.consumer:id/used_refine_search_max_mileage")));
        WebElement doneButton = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/used_refine_search_max_mileage"));
        doneButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.RelativeLayout[@index='0']")));
        WebElement selectFirstCar = driver.findElement(By.xpath("//android.widget.RelativeLayout[@index='0']"));
        selectFirstCar.click();
        Log.info("Selected the first car from the list");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.truecar.mobile.android.consumer:id/used_header_image_row___image")));
        Log.info("wait for the page to load");
        WebElement carImage = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/used_header_image_row___image"));
        Assert.assertTrue(carImage.isDisplayed());
        Log.info("Test passed!!");
    }
}
