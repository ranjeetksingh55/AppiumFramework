package org.ranjeet;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.ranjeet.PageObjects.ios.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Ios_BaseTest {
	public IOSDriver driver;
	public AppiumDriverLocalService service;
	public HomePage homePage;

	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.withIPAddress("127.0.0.1")
				.usingPort(4723)
				.build();

		// Start the Appium server
		service.start();

		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 15 Pro");
		options.setApp("/Users/91sqft/Desktop/Testapp3/UIKitCatalog.app");
		options.setPlatformVersion("17.5");
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));

		// Initialize the iOS driver
		driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		homePage=new HomePage(driver);
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			// Quit the driver
			driver.quit();
		}

		if (service != null) {
			// Stop the Appium server
			service.stop();

		}
	}
}