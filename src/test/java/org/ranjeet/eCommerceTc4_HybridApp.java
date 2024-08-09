package org.ranjeet;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.ranjeet.PageObjects.Android.FormPage;
import org.ranjeet.PageObjects.Android.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class eCommerceTc4_HybridApp extends BaseTest {

    @Test
    public void FillForm() throws InterruptedException {
        FormPage formPage = new FormPage(driver);
        formPage.setNameField("Ranjeet Kumar Singh");
        formPage.setGender("Female");
        formPage.setCountrySelection("Austria");
        formPage.submitForm();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        productCatalogue.addItemToCartBYindex(0);
        productCatalogue.addItemToCartBYindex(0);

        // submit button
        productCatalogue.goToCart();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),
                "text", "Cart"));
        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        int count = productPrices.size();
        double totalSum = 0;
        for (int i = 0; i < count; i++) {
            String amountString = productPrices.get(i).getText();
            Double price = getFormattedAmount(amountString);
            totalSum = totalSum + price;
            System.out.println(totalSum);
        }
        String displaySum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Double displayFormattedSum = getFormattedAmount(displaySum);
        Assert.assertEquals(totalSum, displayFormattedSum);
        WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        LongPressAction(ele);
        Thread.sleep(3000);
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(6000);
        // Hybrid - Google page
        Set<String> contexts = driver.getContextHandles();
        for (String contextName : contexts){
            System.out.println(contextName);
        }

        driver.context("WEBVIEW_com.androidsample.generalstore");
        driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
    }
}





