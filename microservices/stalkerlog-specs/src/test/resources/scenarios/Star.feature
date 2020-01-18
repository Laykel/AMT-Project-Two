Feature: Operation on stars

  Background:
    Given there is a Stars server

  Scenario: create a star
    Given I have a star payload
    When I POST it to the /stars endpoint
    Then I receive a 201 status code

  Scenario: get a star by id
    Given I have the star's id
    When I GET the /stars/id endpoint
    Then I receive a 200 status code
    And I receive a Star