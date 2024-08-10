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
import org.ranjeet.PageObjects.Android.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class eCommerceTc4_HybridApp extends BaseTest {

    @Test
    public void FillForm() throws InterruptedException {
        formPage.setNameField("Ranjeet Kumar Singh");
        formPage.setGender("Female");
        formPage.setCountrySelection("Austria");
        ProductCatalogue productCatalogue = formPage.submitForm();
        productCatalogue.addItemToCartBYindex(0);
        productCatalogue.addItemToCartBYindex(0);
        CartPage cartPage = productCatalogue.goToCart();

        // submit button
        Thread.sleep(2000);
        double totalSum = cartPage.getProductsSum();
        double displayFormattedSum = cartPage.getTotalAmountDisplayed();

        Assert.assertEquals(totalSum, displayFormattedSum);
        Thread.sleep(3000);
        cartPage.acceptTermsConditions();
        Thread.sleep(3000);
        cartPage.submitOrder();
        Thread.sleep(3000);

    }
}

        // Hybrid - Google page
//        Set<String> contexts = driver.getContextHandles();
//        for (String contextName : contexts){
//            System.out.println(contextName);
//        }
//
//        driver.context("WEBVIEW_com.androidsample.generalstore");
//        driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
//        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//        driver.pressKey(new KeyEvent(AndroidKey.BACK));
//        driver.context("NATIVE_APP");
//    }







