Feature: Getting the list of countries

  Background:
    Given there is a Cities server

  Scenario: get the countries
    When I GET the /countries endpoint
    Then I receive a 200 status code
    And I receive a list of countries