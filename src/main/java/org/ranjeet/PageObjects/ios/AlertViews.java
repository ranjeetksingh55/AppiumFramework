

// correct code

package org.ranjeet.PageObjects.ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.IosActions;

import java.time.Duration;

public class AlertViews extends IosActions {
    IOSDriver driver;

    public AlertViews(IOSDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
    private WebElement textBox;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Text Entry']")
    private WebElement textEntryMenu;

    @iOSXCUITFindBy(iOSNsPredicate = "name == 'OK'")
    private WebElement acceptPopup;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Confirm / Cancel'`]")
    private WebElement confirmMenuItem;

    @iOSXCUITFindBy(iOSNsPredicate = "name == 'Confirm / Cancel'")
    private WebElement confirmPopup;

    @iOSXCUITFindBy(iOSNsPredicate = "name == 'A message should be a short, complete sentence.'")
    private WebElement confirmMessage;

    public void fillTextLabel(String name) {
        textEntryMenu.click();
        textBox.sendKeys(name);
        confirmPopup.click();
        acceptPopup.click();
        confirmMenuItem.click();
    }

    public String getConfirmMessage() {
        // Implementing WebDriverWait to ensure the confirm message element is visible before retrieving the text
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(confirmMessage));
        return confirmMessage.getText();
    }
}