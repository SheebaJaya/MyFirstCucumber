@login
Feature: HomePage


  @tag1 @reg
  Scenario: Verify Home Page is loaded
    Given I open my home page
    Then Check the home page title is correct

  @tag2
  Scenario: Verify Home Page is loaded2
    Given I open my home page
    Then Check the home page title is correct2

  @tag3 @reg @smoke
  Scenario: Verify login page is loaded
    Given I entered username and password
    Then Check the Successful Url

  Scenario: Verify login page is loaded
    Given I clicked password forgot
    Then Enter wrong email id and verify the message


  Scenario: Verify login page is loaded
    Given Read username and password from excel file

  Scenario: Verify Home Page is loaded
    Given I open my home page
    Then Check that home page title is Welcome to iBusiness

   Scenario Outline: Verify Home Page is loaded
      Given I open my home page
      Then Check that home page title is <pageTitle>

      Examples:
     | pageTitle             |
     | Welcome to iBusiness  |
     | Welcome to iBusiness2 |
# to run cuccumber with specific tests from cmd line  mvn test -Dcucumber.options="- -tags @tag1,@tag2" -Dbrowser=chrome
