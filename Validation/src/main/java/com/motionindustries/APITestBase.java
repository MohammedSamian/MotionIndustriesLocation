package com.motionindustries;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APITestBase {
   protected String baseURI = "https://www.motionindustries.com/about-mi/locations";
   protected String userAgentHeader = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Geko) Version/13.0.3 Mobile/15E148 Safari/604.1";


   public JSONObject getJsonObjectFromResponse(Response response) throws ParseException {
       JSONParser jsp = new JSONParser();
       JSONObject jsonObject = (JSONObject) jsp.parse(response.getBody().asString());
return jsonObject;
   }


}
