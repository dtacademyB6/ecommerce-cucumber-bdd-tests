Feature: Sign up feature involving DB layer


  Scenario: New User Sign Up from UI to DB flow
    Given I am on the sign up page and I am connected to the DB
    When I sign up with the following info
         |username| first | last | email | password|
         |bob.evans| Bob | Evans | bobevans@gmail.com| bobevans23|
    Then I should land on the homepage
    And The database should also have the correct info



  Scenario: New User Creation from DB to UI flow
    Given I am connected to the DB
    When I add a new user to the database with the following info
      |username| first | last | email | password|
      |daisyduck| Daisy | Duck | daisyduck@gmail.com| daisyduck99|
    Then I should be able to log in with the "daisyduck" as uasername and "daisyduck99" as password on the UI

  @duotify
  Scenario: Verify Songs Table Column Names
    Given I am connected to the DB
    When I retrieve the column names for the Songs table
    Then It should be the following
      | id         |
      | title      |
      | artist     |
      | album      |
      | genre      |
      | duration   |
      | path       |
      | albumOrder |
      | plays      |
