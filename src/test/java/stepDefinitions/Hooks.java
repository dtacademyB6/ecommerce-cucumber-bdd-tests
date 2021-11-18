package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.Driver;

import java.time.Duration;

public class Hooks {

    // Hook methods run before and after each scenario
    @Before
    public void setupScenario(){
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5)) ;
        Driver.getDriver().manage().window().maximize();
    }

    @After
    public void tearDownScenario(){
         Driver.quitDriver();
    }
}
