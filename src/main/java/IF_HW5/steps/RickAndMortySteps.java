package IF_HW5.steps;

import IF_HW5.config.TestConfig;
import IF_HW5.pages.RickAndMortyApiPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.qameta.allure.Allure;

import static org.junit.Assert.*;

public class RickAndMortySteps {

    private Response mortyResponse;
    private Response episodeResponse;
    private Response characterResponse;
    private JsonPath mortyJson;
    private JsonPath episodeJson;
    private JsonPath characterJson;
    private String mortySpecies;
    private String mortyLocation;
    private String lastCharacterSpecies;
    private String lastCharacterLocation;
    private final RickAndMortyApiPage rickAndMortyPage;

    public RickAndMortySteps() {
        this.rickAndMortyPage = TestConfig.getInstance().getRickAndMortyPage();
    }

    @Given("получить информацию о персонаже {string}")
    public void getCharacterInfo(String characterName) {
        mortyResponse = rickAndMortyPage.getCharacterByName(characterName);
        mortyJson = mortyResponse.jsonPath();
        Allure.step("Получена информация о персонаже: " + characterName);
    }

    @When("извлечь вид и локацию Морти")
    public void extractMortySpeciesAndLocation() {
        mortySpecies = rickAndMortyPage.getSpecies(mortyJson, "results[0].species");
        mortyLocation = rickAndMortyPage.getLocation(mortyJson, "results[0].location.name");
        Allure.step("Извлечены вид: " + mortySpecies + " и локация: " + mortyLocation);
    }

    @When("получить последний эпизод Морти")
    public void getLastEpisode() {
        String lastEpisodeUrl = rickAndMortyPage.getLastEpisodeUrl(mortyJson);
        episodeResponse = rickAndMortyPage.getEpisode(lastEpisodeUrl);
        episodeJson = episodeResponse.jsonPath();
        Allure.step("Получен последний эпизод Морти");
    }

    @When("получить последнего персонажа из эпизода")
    public void getLastCharacterFromEpisode() {
        String lastCharacterUrl = rickAndMortyPage.getLastCharacterUrl(episodeJson);
        characterResponse = rickAndMortyPage.getCharacter(lastCharacterUrl);
        characterJson = characterResponse.jsonPath();
        Allure.step("Получен последний персонаж из эпизода");
    }

    @When("извлечь вид и локацию последнего персонажа")
    public void extractLastCharacterSpeciesAndLocation() {
        lastCharacterSpecies = rickAndMortyPage.getSpecies(characterJson, "species");
        lastCharacterLocation = rickAndMortyPage.getLocation(characterJson, "location.name");
        Allure.step("Извлечены вид: " + lastCharacterSpecies + " и локация: " + lastCharacterLocation);
    }

    @Then("все данные должны быть не null")
    public void allDataShouldNotBeNull() {
        assertNotNull("Вид Морти не должен быть null", mortySpecies);
        assertNotNull("Локация Морти не должна быть null", mortyLocation);
        assertNotNull("Вид последнего персонажа не должен быть null", lastCharacterSpecies);
        assertNotNull("Локация последнего персонажа не должна быть null", lastCharacterLocation);
        Allure.step("Все данные проверены - ни одно значение не равно null");
    }

    @Then("сравнить данные Морти и последнего персонажа")
    public void compareMortyAndLastCharacterData() {
        String comparisonMessage = String.format(
                "Сравнение данных:%nМорти - Вид: %s, Локация: %s%nПоследний персонаж - Вид: %s, Локация: %s",
                mortySpecies, mortyLocation, lastCharacterSpecies, lastCharacterLocation
        );
        Allure.step(comparisonMessage);
    }
}