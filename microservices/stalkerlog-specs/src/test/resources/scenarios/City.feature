Feature: Getting the list of cities

  Background:
    Given there is a Cities server

  Scenario: get a page of cities
    When I GET page 1 with 10 items of the /cities endpoint
    Then I receive a 200 status code
    And I receive a list of cities
    And I receive 2 pagination headers