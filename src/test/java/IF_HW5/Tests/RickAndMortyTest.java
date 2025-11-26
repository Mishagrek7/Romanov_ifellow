package IF_HW5.Tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class RickAndMortyTest extends BaseTest {

    @Test
    public void testCharacterComparison() {
        Response mortyResponse = rickAndMortyPage.getCharacterByName("Morty Smith");
        JsonPath mortyJson = mortyResponse.jsonPath();

        String mortySpecies = rickAndMortyPage.getSpecies(mortyJson, "results[0].species");
        String mortyLocation = rickAndMortyPage.getLocation(mortyJson, "results[0].location.name");

        String lastEpisodeUrl = rickAndMortyPage.getLastEpisodeUrl(mortyJson);

        Response episodeResponse = rickAndMortyPage.getEpisode(lastEpisodeUrl);
        JsonPath episodeJson = episodeResponse.jsonPath();
        String lastCharacterUrl = rickAndMortyPage.getLastCharacterUrl(episodeJson);

        Response characterResponse = rickAndMortyPage.getCharacter(lastCharacterUrl);
        JsonPath characterJson = characterResponse.jsonPath();

        String lastCharacterSpecies = rickAndMortyPage.getSpecies(characterJson, "species");
        String lastCharacterLocation = rickAndMortyPage.getLocation(characterJson, "location.name");

        boolean speciesDiffer = !mortySpecies.equals(lastCharacterSpecies);
        boolean locationDiffer = !mortyLocation.equals(lastCharacterLocation);

        assertTrue(speciesDiffer || locationDiffer);

    }
}