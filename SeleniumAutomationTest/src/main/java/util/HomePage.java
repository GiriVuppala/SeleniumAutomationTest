package util;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_item_price")
    List<WebElement> itemsPrice;

    @FindBy(className = "shopping_cart_link")
    WebElement cartBtn;

    @FindBy(className = "inventory_item_price")
    WebElement cartItemPrice;

    @FindBy(className = "//span[@id='layer_cart_product_price']")
    WebElement totalCartPrice;

    Float highestPrice;

    //step 3 selecting highest price item
    public void addHighestPriceItemToCart()
    {
        List<Float> mylist = new ArrayList<>();
        for(WebElement itemPrice:itemsPrice)
        {
            mylist.add(Float.parseFloat(itemPrice.getText().replace("$","")));
        }
        highestPrice = Collections.max(mylist);
        System.out.println(Collections.max(mylist));
        WebElement element = driver.findElement(By.xpath("//div[contains(text()[2],'"+Collections.max(mylist)+"')]/following::button[1]"));
        element.click();
    }

    //step 4 adding selected highest item to the cart and verifying the highest price
    public void verifyCartItemPrice()
    {
        cartBtn.click();
        Float getCartTotalPrice = Float.parseFloat(cartItemPrice.getText().replace("$",""));
        Assert.assertEquals(highestPrice,getCartTotalPrice);
    }
}
