Feature: Operation on stars

  Background:
    Given there is a Stars server

  Scenario: create a star
    Given I am authenticated
    Given I have a star payload
    When I POST it to the /stars endpoint
    Then I receive a 201 status code

  Scenario: get a page of star
    Given I am authenticated
    When I GET page 1 with 10 items of the /stars endpoint
    Then I receive a 200 status code
    And I receive a list of <= 10 stars
