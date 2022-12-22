@API
Feature: API tests for Swapi films API

  Scenario: Check in full monitor resolution
    Given Find film with a title "A New Hope"
    When Find person with name "Biggs Darklighter" among the characters of the film
    And Find which starship the character was flying on
    Then Check that starship class is "Starfighter"
    Then Check that character "Luke Skywalker" is among pilots that were also flying this kind of starship