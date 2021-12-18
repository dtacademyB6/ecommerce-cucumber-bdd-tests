package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import pages.duotify.RegisterPage;
import utilities.DBUtility;
import utilities.Driver;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DuotifyStepDefs {

    Map<String, String>  expectedMap;

    @Given("I am on the sign up page and I am connected to the DB")
    public void iAmOnTheSignUpPageAndIAmConnectedToTheDB() {

        Driver.getDriver().get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");
        DBUtility.createConnection();

    }
    @When("I sign up with the following info")
    public void iSignUpWithTheFollowingInfo(List<Map<String,String>> dataTable) {


        expectedMap = dataTable.get(0);
        RegisterPage registerPage = new RegisterPage();
         registerPage.hideLogin.click();
        registerPage.username.sendKeys(expectedMap.get("username"));
        registerPage.firstName.sendKeys(expectedMap.get("first"));
        registerPage.lastName.sendKeys(expectedMap.get("last"));
        registerPage.email.sendKeys(expectedMap.get("email"));
        registerPage.email2.sendKeys(expectedMap.get("email"));
        registerPage.password.sendKeys(expectedMap.get("password"));
        registerPage.password2.sendKeys(expectedMap.get("password"));
        registerPage.registerButton.click();

    }
    @Then("I should land on the homepage")
    public void iShouldLandOnTheHomepage() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?"));
    }
    @Then("The database should also have the correct info")
    public void theDatabaseShouldAlsoHaveTheCorrectInfo() throws SQLException {

        String expectedUsername = expectedMap.get("username");
        String expectedFirst = expectedMap.get("first");
        String expectedLast = expectedMap.get("last");
        String expectedEmail = expectedMap.get("email");
        String expectedPass = expectedMap.get("password");

        String query  = "select * from users where username='"+expectedUsername+"'";
        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps(query);
        Map<String, Object> actualMap = queryResultListOfMaps.get(0);

        String actualUsername = (String)(actualMap.get("username"));
        String actualFirst= (String)(actualMap.get("firstName"));
        String actualLast = (String)(actualMap.get("lastName"));
        String actualEmail = (String)(actualMap.get("email"));
        String actualPassword = (String)(actualMap.get("password"));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(expectedUsername).isEqualTo( actualUsername);
        softAssertions.assertThat(expectedFirst).isEqualTo(actualFirst);
        softAssertions.assertThat(expectedLast).isEqualTo( actualLast);
        softAssertions.assertThat(expectedEmail).isEqualTo( actualEmail);
        System.out.println(expectedEmail);
        System.out.println(actualEmail);
        String expectedPassMD5 = DigestUtils.md5Hex(expectedPass);
        softAssertions.assertThat(expectedPassMD5).isEqualTo( actualPassword);

        softAssertions.assertAll();

        DBUtility.updateQuery("delete from users where username='"+expectedUsername+"'");
        DBUtility.close();




    }


    @Given("I am connected to the DB")
    public void iAmConnectedToTheDB() {
        DBUtility.createConnection();
    }
    @When("I add a new user to the database with the following info")
    public void iAddANewUserToTheDatabaseWithTheFollowingInfo(List<Map<String,String>> dataTable) throws SQLException {
        Map<String, String> expectedMap = dataTable.get(0);

        String passwordAsMd5 = DigestUtils.md5Hex(expectedMap.get("password"));
        String query = "INSERT INTO users (username, firstName, lastName, email, password ) values ('"+expectedMap.get("username")+"', '"+expectedMap.get("first")+"', '"+expectedMap.get("last")+"', '"+expectedMap.get("email")+"', '"+passwordAsMd5+"')";

        DBUtility.updateQuery(query);


    }
    @Then("I should be able to log in with the {string} as uasername and {string} as password on the UI")
    public void iShouldBeAbleToLogInWithTheAsUasernameAndAsPasswordOnTheUI(String user, String pass) throws SQLException {

        Driver.getDriver().get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");
        RegisterPage registerPage = new RegisterPage();

        registerPage.usernameLog.sendKeys(user);
        registerPage.passwordLog.sendKeys(pass);
        registerPage.loginButton.click();

        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?"));
        DBUtility.updateQuery("delete from users where username='"+user+"'");
        DBUtility.close();

    }

    List<String> actualColumnNames;
    @When("I retrieve the column names for the Songs table")
    public void iRetrieveTheColumnNamesForTheSongsTable() {
        actualColumnNames = DBUtility.getColumnNames("select * from songs limit 1");
    }
    @Then("It should be the following")
    public void itShouldBeTheFollowing(List<String> expected) {
       SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualColumnNames).isEqualTo(expected);

        DBUtility.close();
        softAssertions.assertAll();
    }

}
