
package org.ranjeet.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.ranjeet.PageObjects.Android.FormPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import utils.AppiumUtils;

public class AndroidBaseTest extends AppiumUtils {
    public AndroidDriver driver;
    public FormPage formPage;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void ConfigureAppium() throws IOException {
        Properties prop = new Properties();

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//resources//global.properties");
        prop.load(fis);
        String ipAddress = prop.getProperty("ipAddress");
        String port = prop.getProperty("port");

        service = startAppiumServer(ipAddress,Integer.parseInt(port));

        // Set desired capabilities

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Ranjeet Phone");
//		options.setApp("//Users//91sqft//eclipse-workspace//Appium//Appium//src//resources//ApiDemos-debug.apk");
        options.setApp(System.getProperty("user.dir")+"//src//resources//General-Store.apk");
        options.setChromedriverExecutable("/Users/91sqft/Desktop/Driver/chromedriver");
//		options.setCapability("browserName", "Chrome");
        // Initialize the Android driver

        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        formPage = new FormPage(driver);

    }

    public void LongPressAction(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(),
                        "duration", 2000));
    }

    public void scrollToEndAction() {
        boolean canScrollMore;
        do
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            )); while (canScrollMore);

    }

    public Double getFormattedAmount(String amount) {
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public void swipeAction(WebElement ele, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "direction", "left",
                "percent", 0.75
        ));
    }

    @AfterClass
    public void tearDown() {
        // Quit the driver
        driver.quit();

        // Stop the Appium server

        service.stop();
    }

}
