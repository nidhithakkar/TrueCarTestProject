import com.oracle.tools.packager.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
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
    public void clickOnCloseButton() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.truecar.mobile.android.consumer:id/imageviewClose")));
        WebElement closeButton = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/imageviewClose"));
        closeButton.click();

        //Clicking on the car icon on top nav bar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.HorizontalScrollView[1]/android.widget.LinearLayout[1]/android.support.v7.app.ActionBar.Tab[2]")));
        WebElement carIcon = driver.findElement(By.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.HorizontalScrollView[1]/android.widget.LinearLayout[1]/android.support.v7.app.ActionBar.Tab[2]"));
        carIcon.click();
        WebElement usedButton = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/segmented_control_right"));
        Assert.assertTrue(usedButton.isDisplayed());

        //Finding Used cars in 90210 zipcode
        usedButton.click();
        Log.info("click on Used Button");
        WebElement findUsedCarsButton = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/button_find_vehicles_bottom"));
        findUsedCarsButton.click();
        WebElement zipCodeText = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/edittext_postalcode"));
        zipCodeText.sendKeys("90210");
        Log.info("select the text box and enter the zipcode");
        WebElement goButton = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/button_go"));
        goButton.click();
        findUsedCarsButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.truecar.mobile.android.consumer:id/used_find_search")));
        WebElement searchTextBox = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/used_find_search"));
        Assert.assertTrue(searchTextBox.isDisplayed());

        //Selecting BMW and 2 series model
        searchTextBox.sendKeys("2 series");
        WebElement bmwElement = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/tc_used_make_view"));
        bmwElement.click();
        Log.info("click on BMW 2 series brand");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.truecar.mobile.android.consumer:id/tc_toolbar")));
        WebElement resultsPage = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/tc_toolbar"));
        Assert.assertTrue(resultsPage.isDisplayed());

        //Filter to find a 2015 2 series in Black
        WebElement clickOnFilter = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/used_search_results_filter_textview"));
        clickOnFilter.click();
        WebElement selectColor = driver.findElement(By.xpath(" //android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[5]/android.widget.RelativeLayout[1]\n"));
        selectColor.click();
        Log.info("Clicking on 'color' field");
        WebElement clickBlack = driver.findElement(By.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.support.v7.widget.RecyclerView[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]"));
        clickBlack.click();
        Log.info("Selecting black color");
        WebElement setColorsButton = driver.findElement(By.id("com.truecar.mobile.android.consumer:id/used_refine_search_color_submit"));
        setColorsButton.click();
    }
}
