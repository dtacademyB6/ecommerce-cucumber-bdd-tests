Feature: Homepage related features


  Scenario: Verify popular product names
    Given  I am on the homepage
    Then The popular products should be the following
      | Faded Short Sleeve T-shirts |
      | Blouse                      |
      | Printed Dress               |
      | Printed Dress               |
      | Printed Summer Dress        |
      | Printed Summer Dress        |
      | Printed Chiffon Dress       |




  Scenario: Verify popular product names and their prices
    Given  I am on the homepage
    Then The popular products and their prices should be the following
      | Faded Short Sleeve T-shirts | 16.51  |
      | Blouse                      | 27.00  |
      | Printed Dress               | 26.00  |
      | Printed Summer Dress        | 28.98  |
      | Printed Chiffon Dress       | 16.40  |


    Scenario: Example scenario for sample DataTable that is converted to List of lists
      Given I am on the homepage
      Then I should see the following customers
        | Annie M. G. | Schmidt  | 1911-03-20 |
        | Roald       | Dahl     | 1916-09-13 |
        | Astrid      | Lindgren | 1907-11-14 |



  Scenario: Example scenario for sample DataTable that is converted to List of
    Given I am on the homepage
    Then I should see the following customers using
      | firstName   | lastName | birthDate  |
      | Annie M. G. | Schmidt  | 1911-03-20 |
      | Roald       | Dahl     | 1916-09-13 |
      | Astrid      | Lindgren | 1907-11-14 |



  Scenario: Verify popular product names and their prices
    Given  I am on the homepage
    Then The popular products and their other details should be the following
      | Faded Short Sleeve T-shirts | 16.51  | demo_1 | New |
      | Blouse                      | 27.00  |demo_2 | New |
      | Printed Dress               | 26.00  |demo_3 | New |
      | Printed Summer Dress        | 28.98  |demo_5 | New |
      | Printed Chiffon Dress       | 16.40  |demo_7 | New |


    @temp
    Scenario: Verify a single products details
      Given I am on the homepage
      When I click on "Blouse"
      Then The details of the product should be the following
        | name   | price | model  | condition | default_quantity | default_size |
        | Blouse | 27.00 | demo_2 | New       | 1                | S            |
      Then The title of the page should contain "Blouse"




