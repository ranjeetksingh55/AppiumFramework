//package org.ranjeet.PageObjects.Android;
//
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.pagefactory.AndroidFindBy;
//import io.appium.java_client.pagefactory.AppiumFieldDecorator;
//import org.checkerframework.checker.units.qual.A;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.PageFactory;
//import org.w3c.dom.Text;
//import utils.AndroidActions;
//
//import java.util.List;
//
//public class CartPage extends AndroidActions {
//    AndroidDriver driver;
//
//    public CartPage(AndroidDriver driver) {
//        super(driver);
//        this.driver = driver;
//        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
//    }
//
//    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
//    public List<WebElement> productList;
//
//    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
//    public List<WebElement> totalAmount;
//    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
//    public List<WebElement> terms;
//
//    @AndroidFindBy(id = "android:id/button1")
//    public List<WebElement> acceptButton;
//
//    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
//    public List<WebElement> proceed;
//
//    @AndroidFindBy(id = "android.widget.CheckBox")
//    public List<WebElement> checkbox;
//
//    public List<WebElement> getProductList() {
//        return productList;
//    }
//
//    public Double getProductsSum() {
//        int count = productList.size();
//        double totalSum = 0;
//        for (int i = 0; i < count; i++) {
//            String amountString = productList.get(i).getText();
//            Double price = getFormattedAmount(amountString);
//            totalSum = totalSum + price;
//            System.out.println(totalSum);
//        }
//        return totalSum;
//
//    }
//
//    public Double getTotalAmountDisplayed() {
//        return getFormattedAmount(totalAmount.get());
//    }
//
//    public void acceptTermsConditions()
//    {
//        LongPressAction(terms);
//        acceptButton.click();
//    }
//    public Double getFormattedAmount(String amount)
//    {
//        Double price = Double.parseDouble(amount.substring(1));
//        return price;
//    }
//    public void submitOrder()
//    {
//        checkbox.click;
//        checkbox.click();
//    }
//}
//

package org.ranjeet.PageObjects.Android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

import java.util.List;

public class CartPage extends AndroidActions {
    AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    public List<WebElement> productList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    public WebElement totalAmount; // Single WebElement instead of List

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    public WebElement terms; // Single WebElement instead of List

    @AndroidFindBy(id = "android:id/button1")
    public WebElement acceptButton; // Single WebElement instead of List

    @AndroidFindBy(className = "android.widget.CheckBox")
    public WebElement checkbox; // Single WebElement instead of List
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    public WebElement proceed; // Single WebElement instead of List



    // Getter for productList
    public List<WebElement> getProductList() {
        return productList;
    }

    // Calculate the sum of the products
    public Double getProductsSum() {
        int count = productList.size();
        double totalSum = 0;
        for (int i = 0; i < count; i++) {
            String amountString = productList.get(i).getText();
            Double price = getFormattedAmount(amountString);
            totalSum += price;
            System.out.println(totalSum);
        }
        return totalSum;
    }

    // Get the total amount displayed
    public Double getTotalAmountDisplayed() {
        return getFormattedAmount(totalAmount.getText()); // Use getText() to retrieve the amount
    }

    // Accept terms and conditions
    public void acceptTermsConditions() {
        LongPressAction(terms); // Interact with the single WebElement terms
        acceptButton.click(); // Click the single WebElement acceptButton
    }

    // Format the amount string to a Double
    public Double getFormattedAmount(String amount) {
        return Double.parseDouble(amount.substring(1)); // Remove the currency symbol and convert to Double
    }

    // Submit the order by interacting with the checkbox
    public void submitOrder() {
        checkbox.click(); // Perform the first click on the checkbox
        proceed.click(); // Click the proceed button to submit the order
    }

}
