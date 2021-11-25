package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import pages.HomePage;
import pages.ProductDetailsPage;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.util.*;

public class HomePageStepDefs {


    @Then("The popular products should be the following")
    public void thePopularProductsShouldBeTheFollowing(List<String> expectedPopularProducts) {

        List<String> actualPopularProducts = SeleniumUtils.getElementsText(new HomePage().allPopularProducts);

        Assert.assertEquals(expectedPopularProducts, actualPopularProducts);

    }


    @Then("The popular products and their prices should be the following")
    public void thePopularProductsAndTheirPricesShouldBeTheFollowing(Map<String, String> expectedProductPrice) {

        System.out.println(expectedProductPrice);

//        expectedProductPrice.put("Dress", "34.56"); //java.lang.UnsupportedOperationException
       HomePage homePage =  new HomePage();

        List<String> actualProduct = new ArrayList<>(new LinkedHashSet<>(SeleniumUtils.getElementsText(homePage.allPopularProducts)));
//
        Map <String, String> actualProductPriceMap = new LinkedHashMap<>();

        for (String product: actualProduct){

            homePage.clickOnProductLink(product);
            String price = new ProductDetailsPage().price.getText().substring(1);
            actualProductPriceMap.put(product, price);
            Driver.getDriver().navigate().back();
        }

        System.out.println(actualProductPriceMap);
        Assert.assertEquals(expectedProductPrice, actualProductPriceMap);


    }




    @Then("I should see the following customers")
    public void iShouldSeeTheFollowingCustomers(List<List<String>> dataTable) {


//        for (List<String> row : dataTable) {
//            System.out.println(row);
//        }
        System.out.println(dataTable.get(2).get(2));

    }


    @Then("I should see the following customers using")
    public void iShouldSeeTheFollowingCustomersUsing( List<Map<String,String>> dataTable) {


        for (Map<String, String> row : dataTable) {
            System.out.println(row);
        }

        //System.out.println(dataTable.get(1).get("birthDate"));
    }


    @Then("The popular products and their other details should be the following")
    public void thePopularProductsAndTheirOtherDetailsShouldBeTheFollowing(Map<String, List<String> > expectedMap) {

        Map<String, List<String> > actualMap =  new LinkedHashMap<>();
        List<String> products = new ArrayList<>(new LinkedHashSet<>(SeleniumUtils.getElementsText(new HomePage().allPopularProducts)));

        for (String product : products) {

            new HomePage().clickOnProductLink(product);
            String price = new ProductDetailsPage().price.getText().substring(1);
            String model = new ProductDetailsPage().model.getText();
            String condition = new ProductDetailsPage().condition.getText();

            actualMap.put(product, Arrays.asList(price, model, condition));

            Driver.getDriver().navigate().back();


        }

        Assert.assertEquals(expectedMap, actualMap);

    }
}
