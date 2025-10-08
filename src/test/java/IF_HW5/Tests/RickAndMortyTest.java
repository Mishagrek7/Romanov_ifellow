package IF_HW5.Tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class RickAndMortyTest extends BaseTest {

    @Test
    public void testCharacterComparison() {
        String baseUrl = getProperty("rickandmorty.base.url");

        Response mortyResponse = given()
                .baseUri(baseUrl)
                .when()
                .get("/character/?name=Morty Smith");

        JsonPath mortyJson = mortyResponse.jsonPath();
        String mortySpecies = mortyJson.getString("results[0].species");
        String mortyLocation = mortyJson.getString("results[0].location.name");

        String lastEpisodeUrl = mortyJson.getString("results[0].episode[-1]");

        Response episodeResponse = given()
                .baseUri(baseUrl)
                .when()
                .get(lastEpisodeUrl.replace(baseUrl, ""));

        JsonPath episodeJson = episodeResponse.jsonPath();
        String lastCharacterUrl = episodeJson.getString("characters[-1]");

        Response characterResponse = given()
                .baseUri(baseUrl)
                .when()
                .get(lastCharacterUrl.replace(baseUrl, ""));

        JsonPath characterJson = characterResponse.jsonPath();
        String lastCharacterSpecies = characterJson.getString("species");
        String lastCharacterLocation = characterJson.getString("location.name");

        // 4. Проверить расу и местоположение
        assertNotNull("Раса Морти не должна быть null", mortySpecies);
        assertNotNull("Локация Морти не должна быть null", mortyLocation);
        assertNotNull("Раса последнего персонажа не должна быть null", lastCharacterSpecies);
        assertNotNull("Локация последнего персонажа не должна быть null", lastCharacterLocation);
    }
}