
@tag
Feature: Purchase The Order From Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page
  

  @Regression
  Scenario Outline: Positive Test For Submitting The Order
    Given Logged in with username <name> and password <Password>
    When I add product <productName> to Cart 
    And  Checkout <productName> and Submit the order
    Then "THANKYOU FOR THE ORDER." Message is displayed on ConfirmationPage

    Examples: 
      | name                  | password		| productName |
      | shetty1234@gmail.com  | Jha@1234	  | ZARA COAT 3 |
       
