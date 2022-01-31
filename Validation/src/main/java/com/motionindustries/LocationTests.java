package com.motionindustries;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
public class LocationTests extends APITestBase {
    private static final Logger LOGGER = Logger.getLogger(LocationTests.class);

    @Test()
    public void validateUserCanMakeAGetRequestUsingZipCode() throws ParseException {
        RestAssured.baseURI = baseURI;
        RestAssured.get(baseURI);
        Response response = RestAssured.given()
                .when()
                .header("User-Agent", userAgentHeader)
                .param("postalCode", "11374")
                .get("/misvc/mi/services/json/locations.locations");
        LOGGER.info("Body from request is : " + response.getBody());
        LOGGER.info("Validate Response body is not empty");
        JSONObject jsonObject = getJsonObjectFromResponse(response);
        Assert.assertTrue(jsonObject.size() > 0);
        // from the jsonObject we can validate the other fields against the database to make sure they are populating as expected
        LOGGER.info("Validate Status Code is 200");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test()
    public void validateUserCanMakeAGetRequestUsingCountry() throws ParseException {
        RestAssured.baseURI = baseURI;
        RestAssured.get(baseURI);
        Response response = RestAssured.given()
                .when()
                .header("User-Agent", userAgentHeader)
                .param("country", "CN")
                .get("/misvc/mi/services/json/locations.states");
        LOGGER.info("Body from request is :" + response.getBody());
        LOGGER.info("Validate response body is not empty");
        JSONObject jsonObject = getJsonObjectFromResponse(response);
        Assert.assertTrue(jsonObject.size() > 0);
        LOGGER.info("Validate Status Code is 200");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test()
    public void validateUserCanMakeAGetRequestUsingState() throws ParseException {
        RestAssured.baseURI = baseURI;
        RestAssured.get(baseURI);
        Response response = RestAssured.given()
                .when()
                .header("User-Agent", userAgentHeader)
                .param("state", "New York")
                .get("/misvc/mi/services/json/locations.locations");
        LOGGER.info("Body from request is : " + response.getBody());
        LOGGER.info("Validate Response body is not empty");
        JSONObject jsonObject = getJsonObjectFromResponse(response);
        Assert.assertTrue(jsonObject.size() > 0);
        LOGGER.info("Validate Status Code is 200");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test()
    public void validateUserCanMakeAGetRequestUsingCountryAndState() throws ParseException {
        RestAssured.baseURI = baseURI;
        RestAssured.get(baseURI);
        Response response = RestAssured.given()
                .when()
                .header("User-Agent", userAgentHeader)
                .param("country", "CN")
                .param("State", "Alberta")
                .get("/misvc/mi/services/json/locations.states");
        LOGGER.info("Body from request is :" + response.getBody());
        LOGGER.info("Validate response body is not empty");
        JSONObject jsonObject = getJsonObjectFromResponse(response);
        Assert.assertTrue(jsonObject.size() > 0);
        // from the jsonObject we can validate the other fields against the database to make sure they are populating as expected
        LOGGER.info("Validate Status Code is 200");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test()
    public void validateLocationNotShownIfInvalidZipCodeIsProvided() throws ParseException {
        RestAssured.baseURI = baseURI;
        RestAssured.get(baseURI);
        Response response = RestAssured.given()
                .when()
                .header("User-Agent", userAgentHeader)
                .param("postalCode", "ABCDE")
                .get("/misvc/mi/services/json/locations.locations");
        LOGGER.info("Body from request is : " + response.getBody());
        JSONObject jsonObject = getJsonObjectFromResponse(response);
        LOGGER.info("Validate no location is shown");
        Assert.assertTrue(jsonObject.containsValue("errorMsg:Error retrieving locations}"));
    }
}
