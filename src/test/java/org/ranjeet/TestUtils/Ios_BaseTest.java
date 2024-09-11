package org.ranjeet.TestUtils;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.ranjeet.PageObjects.ios.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.AppiumUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class Ios_BaseTest extends AppiumUtils {
	public IOSDriver driver;
	public AppiumDriverLocalService service;
	public HomePage homePage;

	@BeforeClass
	public void ConfigureAppium() throws IOException {
		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//resources//global.properties");
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		service = startAppiumServer(ipAddress,Integer.parseInt(port));

		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 15 Pro");
		options.setApp("/Users/91sqft/Desktop/Testapp3/UIKitCatalog.app");
		options.setPlatformVersion("17.5");
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));

		// Initialize the iOS driver
		driver = new IOSDriver(service.getUrl(), options);
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