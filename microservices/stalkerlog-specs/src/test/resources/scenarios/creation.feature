Feature: Creation of stars

  Background:
    Given there is a Stars server

  Scenario: create a fruit
    Given I have a star payload
    When I POST it to the /stars endpoint
    Then I receive a 201 status code