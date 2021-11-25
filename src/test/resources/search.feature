
Feature: As a user, I should be able to perform a product search on the website


  Background: common steps for all scenarios
    Given I am on the homepage


  #@test or @temp and @second
  @second
  Scenario: Search a product using valid product name1

    When I search for a Blouse
    Then I should land on the search page
    And the search term should be correct



  Scenario: Search a product using valid product name2

    When I search for a Blouse
    Then I should land on the search page
    And the search term should be correct


  @test
  Scenario: Search a product using valid product name3

    When I search for a Blouse
    Then I should land on the search page
    And the search term should be correct


  Scenario: Search a product using empty search term

   When I do not enter any search term
    Then  I should land on the search page
    And the error message should be there


  Scenario: Search a product using valid product name Dress

    When I search for a "Dress"
    Then I should land on the search page
    And the search term should be correct


  Scenario: Search a product using valid product name Dress

    When I search for a "Summer"
    Then I should land on the search page
    And the search term should be correct



  Scenario: Search a product using valid product name Dress

    When I search for a "Shirt"
    Then I should land on the search page
    And the search term should be correct







