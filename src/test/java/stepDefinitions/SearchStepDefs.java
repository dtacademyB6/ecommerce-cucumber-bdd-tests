package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.Keys;
import pages.HomePage;
import pages.SearchPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.sql.SQLOutput;

public class SearchStepDefs {



    String expectedSearchTerm;


    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }
    @When("I search for a Blouse")
    public void i_search_for_a_blouse() {
        HomePage homePage = new HomePage();
        expectedSearchTerm = "Blouse".toLowerCase();
        homePage.searchBox.sendKeys(expectedSearchTerm + Keys.ENTER);
    }
    @Then("I should land on the search page")
    public void i_should_land_on_the_search_page_and_the_search_term_should_be_correct() {
        assertEquals("Search - My Store", Driver.getDriver().getTitle());

    }


    @Then("the search term should be correct")
    public void the_search_term_should_be_correct() {
        String actualSearchTerm =  new SearchPage().searchTerm.getText().toLowerCase();
        System.out.println(actualSearchTerm);
        assertTrue(actualSearchTerm.contains(expectedSearchTerm));


    }

//


}
