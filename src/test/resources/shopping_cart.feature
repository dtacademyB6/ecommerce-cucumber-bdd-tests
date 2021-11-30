Feature:  As a user I should be able to add and checkout using shopping cart page
  @temp
  Scenario: Verify shopping cart deatils
    Given I am on the homepage
    When I click on "Blouse"
    Then I should land on the product details page
    When I increase the quantity to 3 and choose size "M" and color "White" and click on add to cart button
    And I click on Proceed to Checkout
    Then The quantity, size and color should be correct
    Then The other shopping cart details should be the following
      | Name   | SKU    | Unit Price | Shipping fee | Tax   |
      | Blouse | demo_2 | $27.00     | $2.00        | $0.00 |




