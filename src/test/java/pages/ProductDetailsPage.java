package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.html.HTMLInputElement;
import utilities.Driver;

public class ProductDetailsPage {



    public ProductDetailsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "quantity_wanted")
    public WebElement defaultQuantity;

    @FindBy(xpath = "//i[@class='icon-plus']")
    public WebElement plusButton;

    @FindBy(xpath = "//i[@class='icon-minus']")
    public WebElement minusButton;

    @FindBy(id = "our_price_display")
    public WebElement price;




}
