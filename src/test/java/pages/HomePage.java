package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomePage {


    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "search_query_top")
    public WebElement searchBox;



    public void clickOnProductLink(String product){
        String xpath = "//ul[@id='homefeatured']//a[@title='"+product+"'][@class='product-name']";
        Driver.getDriver().findElement(By.xpath(xpath)).click();

    }




}
