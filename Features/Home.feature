#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Interaction with Buttons on Reqres Website

  Background: 
    Given the user is on the home page of the application

 @automated
  Scenario: User clicks the "List Users" Button
    When the user selects "LIST USERS"
    Then user should see a successful response with detailed validation
    
 @automated
  Scenario: User clicks "Single User" Button
    When the user selects "SINGLE USER"
    Then User should see a successful response

  @automated
  Scenario: User clicks "Single User Not Found" Button
    When the user selects "SINGLE USER NOT FOUND"
    And User should not see a successful response

  @automated
  Scenario: Verify Support Page Options
  	Given Homepage should display options for one-time and monthly support
    When the user clicks on the Support link after selecting one time or monthly payment
    Then the detail payment for upgrade details should be displayed
    
  @automated
  Scenario: Send a POST request to create a user
    When the user sends a POST request to "https://reqres.in/api/users/2" with body:
      """
      {
        "name": "morpheus",
        "job": "leader"
      }
      """
    Then the response status code should be 201
    And the response body should contain:
      """
      {
        "name": "morpheus",
        "job": "leader"
      }
      """  