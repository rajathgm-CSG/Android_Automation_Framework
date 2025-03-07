Feature: As a user i want to validate Railway field service application

  @TC_001
  Scenario Outline: Verify the Login and Home page functionality with valid data
    Given I connect virtual device with Desired Capabilities "<TCID>"
    And I verify app install or not if not i Install app using testdata "<TCID>"
    And I luanch app using desired capabilities by testdata "<TCID>"
    And I enter the valid email id and valid password "<TCID>"
    And I verify that application successfully navigates to the home page "<TCID>"
    Then I logout the application
    And I close the application

    Examples:
      | TCID   |
      | RFS_01 |

   @TC_002
  Scenario Outline: Verify Home page Daily logs details functionality
    Given I connect virtual device with Desired Capabilities "<TCID>"
    And I verify app install or not if not i Install app using testdata "<TCID>"
    And I luanch app using desired capabilities by testdata "<TCID>"
    And I enter the valid email id and valid password "<TCID>"
    And I verify that application successfully navigates to the home page "<TCID>"
    And I entered daily logs details using testdata "<TCID>"
    And I verify the Activities page "<TCID>"
    Then I logout the application
     And I close the application

    Examples:
      | TCID   |
      | RFS_01 |
