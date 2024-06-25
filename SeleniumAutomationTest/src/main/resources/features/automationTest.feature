@Test
Feature: Selenium Automation Test

  Scenario Outline: verify the prices of dresses and add the highest price to cart
    Given open browser and navigate to url
    When login with "<username>" and "<password>"
    Then select item with highest price and verify the item in cart
    Examples:
      | username      | password     |
      | standard_user | secret_sauce |