Feature:  Product details



  Scenario: Verify product details

    Given I am on the homepage
    When I click on "Printed Dress"
    Then I should land on the product details page
    And The default quantity should be 1


  Scenario: Verify product details default quantity

    Given I am on the homepage
    When I click on "Blouse"
    Then I should land on the product details page
    When I click on plus button 200 times
    Then The default quantity should be 201
    When I click on minus button 200 times
    Then The default quantity should be 1



  Scenario: Verify product details price

    Given I am on the homepage
    When I click on "Blouse"
    Then I should land on the product details page
    And The price should be 27.00



  Scenario: Verify product details price

    Given I am on the homepage
    When I click on "Faded Short Sleeve T-shirts"
    Then I should land on the product details page
    And The price should be 16.51