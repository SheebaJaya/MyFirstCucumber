@loginProcess

  Feature: Login
    Scenario Outline: Failed login with wrong credentials
      Given We are on the login page
      When I fill Username with <username> and I fill Password with <password>
      And I click on Sign In Button
      Then I should see <warning> message
      Examples:
      | username            | password    | warning               |
      | jayagokes@gmail.com | password    | Password not  Correct |
      |                     | password123 | Please enter username |
