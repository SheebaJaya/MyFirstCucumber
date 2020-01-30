@search
Feature: search for an item

  Scenario: Verify if the login page is loaded
    Given I entered username and password
    Then search for an item and get search not successful

  Scenario: Verify if the login page is loaded
    Given I entered username and password
    When search for an item
    Then Add to cart and checkout the products with order successfully placed message

  Scenario: Verify if the login page is loaded
    Given I entered username and password
    Then search for an item and get search successful

