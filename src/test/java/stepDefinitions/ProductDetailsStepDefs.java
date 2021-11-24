package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HomePage;
import pages.ProductDetailsPage;
import utilities.Driver;

public class ProductDetailsStepDefs {

    String productNameAnother;
    @When("I click on {string}")
    public void i_click_on(String productName) throws InterruptedException {
        productNameAnother = productName;
        new HomePage().clickOnProductLink(productName);

    }
    @Then("I should land on the product details page")
    public void i_should_land_on_the_product_details_page() {
        Assert.assertEquals(productNameAnother + " - My Store", Driver.getDriver().getTitle());
    }
    @Then("The default quantity should be {int}")
    public void the_default_quantity_should_be(Integer quantity) {
          Assert.assertEquals(String.valueOf(quantity), new ProductDetailsPage().defaultQuantity.getAttribute("value"));
    }

    @When("I click on {word} button {int} times")
    public void i_click_on_plus_button_times(String plusOrMinus, Integer times) {
        ProductDetailsPage productDetailsPage = new ProductDetailsPage();
        for (int i = 0; i < times; i++) {
            if(plusOrMinus.equals("plus"))
            productDetailsPage.plusButton.click();
            else
                productDetailsPage.minusButton.click();
        }

    }


    @Then("The price should be {double}")
    public void the_price_should_be(Double expectedPrice) {
       Double actualPrice = Double.valueOf(new ProductDetailsPage().price.getText().substring(1));

       Assert.assertEquals(expectedPrice, actualPrice);
    }

}
