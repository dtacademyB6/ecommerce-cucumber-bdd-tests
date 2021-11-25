package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import pages.HomePage;
import pages.ProductDetailsPage;
import utilities.Driver;

import java.util.List;
import java.util.Map;

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





    @Then("The details of the product should be the following")
    public void theDetailsOfTheProductShouldBeTheFollowing(List<Map<String,String>> dataTable) {

        Map<String, String> expectedData = dataTable.get(0);


        ProductDetailsPage productDetailsPage = new ProductDetailsPage();

        //JUnit does NOT have any built in soft assertions
        // Instead we can AssertJ library that offers SoftAssertions class

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(expectedData.get("name")).isEqualTo(productDetailsPage.productName.getText() + "nds");

        softAssertions.assertThat(expectedData.get("price")).isEqualTo(productDetailsPage.price.getText().substring(1));
        softAssertions.assertThat(expectedData.get("model")).isEqualTo( productDetailsPage.model.getText() );
        softAssertions.assertThat(expectedData.get("condition")).isEqualTo( productDetailsPage.condition.getText() +"svsc" );
        softAssertions.assertThat(expectedData.get("default_quantity")).isEqualTo( productDetailsPage.defaultQuantity.getAttribute("value"));
        softAssertions.assertThat(expectedData.get("default_size")).isEqualTo( new Select(new ProductDetailsPage().defaultSize).getFirstSelectedOption().getText() );

         softAssertions.assertAll();


    }


    @Then("The title of the page should contain {string}")
    public void theTitleOfThePageShouldContain(String string) {
      Assert.assertTrue(Driver.getDriver().getTitle().contains(string));
    }

}
