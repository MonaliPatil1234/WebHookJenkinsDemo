@tag
Feature: Error Validation Test
  I want to use this template for my feature file

  @ErrorValidation
  Scenario: Error Validation
    Given I landed on Ecommerce page
   
    When Logged in with username <email> and password <password>
    Then "Incorrect email or password." message should be displayed.
     Examples:
    |email               | password   | 
    |monali.patil@gmail.com| Asdf@1234 | 