package org.ranjeet;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.ranjeet.PageObjects.Android.FormPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	public AndroidDriver driver;
	public FormPage formPage;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException
	{
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();

		// Set desired capabilities

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Ranjeet Phone");
//		options.setApp("//Users//91sqft//eclipse-workspace//Appium//Appium//src//resources//ApiDemos-debug.apk");
		options.setApp("//Users//91sqft//IdeaProjects//Appium1//src//resources//General-Store.apk");
		options.setChromedriverExecutable("/Users/91sqft/Desktop/Driver/chromedriver");
//		options.setCapability("browserName", "Chrome");
		// Initialize the Android driver

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 formPage = new FormPage(driver);

	}

	public void LongPressAction(WebElement ele) {
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
						"duration", 2000));
	}
	public void scrollToEndAction(){
		boolean canScrollMore;
		do
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
					"left", 100, "top", 100, "width", 200, "height", 200,
					"direction", "down",
					"percent", 3.0
			)); while (canScrollMore);

	}
	public Double getFormattedAmount(String amount){
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}
	public void swipeAction(WebElement ele,String direction){
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