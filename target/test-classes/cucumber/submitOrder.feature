@tag
Feature: Purchase the order from an Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page  

  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with username <email> and password <password>   
    When  I add product <productname> from cart
    And Checkout <productname> and submit order
    
    Then "THANKYOU FOR THE ORDER." should be displayed on ConfirmationPage
   
    Examples:
    |email               | password   | productname |
    |monali.patil@gmail.com| Asdf@1234* | ZARA COAT 3 |

  