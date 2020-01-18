Feature: Getting the list of cities

  Background:
    Given there is a Cities server

  Scenario: get the cities
    When I GET the /cities endpoint
    Then I receive a 200 status code
    And I receive a list of cities