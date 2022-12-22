package com.aperov.teststeps;

import com.aperov.conf.ConfigProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Copyright (c) 2022.
 * Test steps
 *
 * @author Aleksei Perov
 * @version 1.0
 * @since 1.0
 */
public class SwapiTestSteps {
    private static final ConfigProperties CONFIG_PROPERTIES = ConfigProperties.getInstance();

    Map<String, Object>  film;
    String characterUrl;
    String starshipUrl;

    @Given("Find film with a title {string}")
    public void findFilmWithATitle(String title) {
        film = RestAssured.get(CONFIG_PROPERTIES.getApiEnvAddress() + "api/films")
                .then()
                .extract()
                .jsonPath()
                .get("results.find { it.title == '" + title + "' }");
    }

    @When("Find person with name {string} among the characters of the film")
    public void findPersonWithNameAmongTheCharactersOfTheFilm(String characterName) {
        List<String> characters = (List<String>) film.get("characters");
        characterUrl = characters.stream()
                .filter(url -> {
                    String name = given().when().get(url).then().extract().jsonPath().get("name");
                    return name.equals(characterName);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Provided character not found"));
    }

    @And("Find which starship the character was flying on")
    public void findWhichStarshipTheCharacterWasFlyingOn() {
        starshipUrl = RestAssured.get(characterUrl)
                .then()
                .extract()
                .jsonPath()
                .getList("starships").get(0).toString();
    }

    @Then("Check that starship class is {string}")
    public void checkThatStarshipClassIs(String starshipClassName) {
        String actualClass = RestAssured.get(starshipUrl)
                .then()
                .extract()
                .jsonPath()
                .getString("starship_class");
        assertEquals(starshipClassName, actualClass);
    }

    @Then("Check that character {string} is among pilots that were also flying this kind of starship")
    public void checkThatCharacterIsAmongPilotsOfStarship(String characterName) {
        List<String> pilotsList = RestAssured.get(starshipUrl)
                .then()
                .extract()
                .jsonPath()
                .getList("pilots");
        pilotsList.stream()
                .filter(url -> {
                    String name = given().when().get(url).then().extract().jsonPath().get("name");
                    return name.equals(characterName);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Provided character not found"));
    }
}
