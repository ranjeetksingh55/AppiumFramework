package org.ranjeet;

import org.ranjeet.PageObjects.Android.ProductCatalogue;
import org.ranjeet.PageObjects.Android.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerceTc4_HybridApp extends AndroidBaseTest {

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







