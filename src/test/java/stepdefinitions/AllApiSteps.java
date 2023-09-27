package stepdefinitions;

import dataClass.ApiData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReader;


import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AllApiSteps {

    protected static RequestSpecification specification;
    protected static Response response;
    protected JsonPath jsonPath;
    public static String projectName;

    List<String>projectGids;;
    ApiData apiData;


    @Given("Set Base URLs for creating projects")
    public void setBaseURLsForCreatingProjects() {

        specification = new RequestSpecBuilder().
                setBaseUri(ConfigReader.getProperty("baseURL")).
                build();
        specification.pathParam("projectPath", "projects");

    }
    @When("Set expected data with  {string}data and send request")
    public void setExpectedDataWithDataAndSendRequest(String projectNames) {

        AllApiSteps.projectName=projectNames;

        apiData=new ApiData();

        HashMap<String, Object> expectedDataAndReqBody = apiData.reqTestDataBodyForProjects();

        response = given().
                spec(specification).
                contentType(ContentType.JSON).
                header("Authorization", "Bearer 1/1205543491884173:59c403d909d21bf0455d3cffe8db0327").
                body(expectedDataAndReqBody).
                when().
                post("{projectPath}");

        System.out.println("Response: ");

        response.prettyPrint();
/*
        String jsonStr1 = String.valueOf(response.jsonPath()); // İlk JSON verisini buraya ekleyin
        String jsonStr2 = String.valueOf(response.jsonPath()); // İkinci JSON verisini buraya ekleyin
        String jsonStr3 = String.valueOf(response.jsonPath()); // Üçüncü JSON verisini buraya ekleyin

        List<String> gidList = new ArrayList<>();

        // İlk JSON verisinden gid'i alıp listeye ekleme
        JSONObject jsonObject1 = new JSONObject(jsonStr1);
        String gid1 = jsonObject1.getJSONObject("data").getString("gid");
        gidList.add(gid1);

        // İkinci JSON verisinden gid'i alıp listeye ekleme
        JSONObject jsonObject2 = new JSONObject(jsonStr2);
        String gid2 = jsonObject2.getJSONObject("data").getString("gid");
        gidList.add(gid2);

        // Üçüncü JSON verisinden gid'i alıp listeye ekleme
        JSONObject jsonObject3 = new JSONObject(jsonStr3);
        String gid3 = jsonObject3.getJSONObject("data").getString("gid");
        gidList.add(gid3);

        System.out.println("Project GidList"+gidList);
        */
    }

    @Then("Verify all of created projects")
    public void verifyAllOfCreatedProjects() {

        response.then().assertThat().statusCode(201);

    }
}
