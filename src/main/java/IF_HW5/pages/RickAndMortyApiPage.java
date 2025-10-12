package IF_HW5.pages;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RickAndMortyApiPage {

    private String baseUrl;

    public RickAndMortyApiPage(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Response getCharacterByName(String name) {
        return given()
                .baseUri(baseUrl)
                .when()
                .get("/character/?name=" + name);
    }

    public Response getEpisode(String episodeUrl) {
        return given()
                .baseUri(baseUrl)
                .when()
                .get(episodeUrl.replace(baseUrl, ""));
    }

    public Response getCharacter(String characterUrl) {
        return given()
                .baseUri(baseUrl)
                .when()
                .get(characterUrl.replace(baseUrl, ""));
    }

    public String getSpecies(JsonPath jsonPath, String path) {
        return jsonPath.getString(path);
    }

    public String getLocation(JsonPath jsonPath, String path) {
        return jsonPath.getString(path);
    }

    public String getLastEpisodeUrl(JsonPath characterJson) {
        return characterJson.getString("results[0].episode[-1]");
    }

    public String getLastCharacterUrl(JsonPath episodeJson) {
        return episodeJson.getString("characters[-1]");
    }
}