package stepdefinitions;

import dataClass.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReader;

import java.util.*;

import static io.restassured.RestAssured.given;

public class AllApiSteps {

    protected static RequestSpecification specification;
    protected static Response response;
    protected JsonPath jsonPath;
    public static String projectName;
    public static String tasksName;
    public static String firstProjectGid;
    public static String secondProjectGid;
    public static String thirdProjectGid;
    public static String firstProjectFirstTaskGid;
    public static String firstProjectSecondTaskGid;
    public static String firstProjectThirdTaskGid;
    public static String secondProjectFirstTaskGid;
    public static String secondProjectSecondTaskGid;
    public static String secondProjectThirdTaskGid;

    public static String thirdProjectFirstTaskGid;
    public static String thirdProjectSecondTaskGid;
    public static String thirdProjectThirdTaskGid;
    String projectGid;
    String firstProjectTaskGid;
    String secondProjectTaskGid;
    String thirdProjectTaskGid;
    ApiProjectData apiData;
    FirstProjectTaskData firstProjectTaskData;
    SecondProjectTaskData secondProjectTaskData;
    ThirdProjectTaskData thirdProjectTaskData;
    List<String> listProjects;
    List<String> firstProjectListTasks;
    List<String> secondProjectListTasks;
    List<String> thirdProjectListTasks;


    @Given("Set Base URLs for creating projects")
    public void setBaseURLsForCreatingProjects() {

        specification = new RequestSpecBuilder().
                setBaseUri(ConfigReader.getProperty("baseURL")).
                build();
        specification.pathParam("projectPath", "projects");

    }

    @When("Set expected data with  {string}data and send request")
    public void setExpectedDataWithDataAndSendRequest(String projectNames) {

        projectName = projectNames;

        apiData = new ApiProjectData();

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

        jsonPath = response.jsonPath();

        projectGid = jsonPath.get("data.gid");

        ProjectGidStorage gidStorage = ProjectGidStorage.getInstance();

        gidStorage.addProjectGid(projectGid);

        listProjects = gidStorage.getProjectsGid();


        if (listProjects.size() >= 3) {
            firstProjectGid = listProjects.get(0);
            secondProjectGid = listProjects.get(1);
            thirdProjectGid = listProjects.get(2);
        } else {
            System.out.println("There are not enough projects in the list to access.");
        }

    }

    @Then("Verify all of created projects")
    public void verifyAllOfCreatedProjects() {

        response.then().assertThat().statusCode(201);

    }

    @Given("Set Base URLs for creating all tasks for First Project")
    public void setBaseURLsForCreatingAllTasksForFirstProject() {

        specification = new RequestSpecBuilder().
                setBaseUri(ConfigReader.getProperty("baseURL")).
                build();

        specification.pathParam("taskPath", "tasks");
    }

    @When("Set expected data with  {string}data and send request for  First Project")
    public void setExpectedDataWithDataAndSendRequestForFirstProject(String taskNames) {

        tasksName = taskNames;

        firstProjectTaskData = new FirstProjectTaskData();

        HashMap<String, Object> expectedDataAndReqBody = firstProjectTaskData.reqTestDataBody();

        response = given().
                spec(specification).
                contentType(ContentType.JSON).
                header("Authorization", ConfigReader.getProperty("token")).
                body(expectedDataAndReqBody).
                when().
                post("{taskPath}");

        System.out.println("Response: ");

        response.prettyPrint();

        jsonPath = response.jsonPath();

        firstProjectTaskGid = jsonPath.get("data.gid");

        FirstProjectTaskGidStorage gidStorage = FirstProjectTaskGidStorage.getInstance();

        gidStorage.addTaskGid(firstProjectTaskGid);

        firstProjectListTasks = gidStorage.getTaskGid();

        if (firstProjectListTasks.size() >= 3) {
            firstProjectFirstTaskGid = firstProjectListTasks.get(0);
            firstProjectSecondTaskGid = firstProjectListTasks.get(1);
            firstProjectThirdTaskGid = firstProjectListTasks.get(2);
        } else {
            System.out.println("There are not enough projects in the list to access.");
        }

    }

    @Then("Verify all of created tasks for  First Project")
    public void verifyAllOfCreatedTasksForFirstProject() {

        response.then().assertThat().statusCode(201);

    }

    @Given("Set Base URLs with for deleting all task on First Project")
    public void setBaseURLsWithForDeletingAllTaskOnFirstProject() {


        String[] taskGids = {firstProjectFirstTaskGid, firstProjectSecondTaskGid, firstProjectThirdTaskGid};

        for (String gid : taskGids) {
            specification = new RequestSpecBuilder()
                    .setBaseUri(ConfigReader.getProperty("baseURL"))
                    .build();
            specification.pathParams("taskPath", "tasks", "idPath", gid);
        }

    }

    @When("Set send request for deleting all task on First Project")
    public void setSendRequestForDeletingAllTaskOnFirstProject() {
        response = given().

                spec(specification).

                contentType(ContentType.JSON).

                header("Authorization", ConfigReader.getProperty("token")).

                when().

                delete("{taskPath}/{idPath}");

    }

    @Then("Verify deleted all of  on First Project")
    public void verifyDeletedAllOfOnFirstProject() {

        response.then().statusCode(200);
    }

    @Given("Set Base URLs for creating all tasks for Second Project")
    public void setBaseURLsForCreatingAllTasksForSecondProject() {


        specification = new RequestSpecBuilder().
                setBaseUri(ConfigReader.getProperty("baseURL")).
                build();

        specification.pathParam("taskPath", "tasks");


    }

    @When("Set expected data with  {string}data and send request for  Second Project")
    public void setExpectedDataWithDataAndSendRequestForSecondProject(String taskNames) {

        tasksName = taskNames;

        secondProjectTaskData = new SecondProjectTaskData();

        HashMap<String, Object> expectedDataAndReqBody = secondProjectTaskData.reqTestDataBody();

        response = given().
                spec(specification).
                contentType(ContentType.JSON).
                header("Authorization", ConfigReader.getProperty("token")).
                body(expectedDataAndReqBody).
                when().
                post("{taskPath}");

        System.out.println("Response: ");

        response.prettyPrint();

        jsonPath = response.jsonPath();

        secondProjectTaskGid = jsonPath.get("data.gid");

        SecondProjectTaskGidStorage secondProjectTaskGidStorage = SecondProjectTaskGidStorage.getInstance();

        secondProjectTaskGidStorage.addTaskGid(secondProjectTaskGid);

        secondProjectListTasks = secondProjectTaskGidStorage.getTaskGid();

        if (secondProjectListTasks.size() >= 3) {
            secondProjectFirstTaskGid = secondProjectListTasks.get(0);
            secondProjectSecondTaskGid = secondProjectListTasks.get(1);
            secondProjectThirdTaskGid = secondProjectListTasks.get(2);
        } else {
            System.out.println("There are not enough projects in the list to access.");
        }

    }

    @Then("Verify all of created tasks for  Second Project")
    public void verifyAllOfCreatedTasksForSecondProject() {

        response.then().assertThat().statusCode(201);
    }

    @Given("Set Base URLs with for deleting all task on Second Project")
    public void setBaseURLsWithForDeletingAllTaskOnSecondProject() {

        String[] taskGids = {secondProjectFirstTaskGid, secondProjectSecondTaskGid, secondProjectThirdTaskGid};

        for (String gid : taskGids) {
            specification = new RequestSpecBuilder()
                    .setBaseUri(ConfigReader.getProperty("baseURL"))
                    .build();
            specification.pathParams("taskPath", "tasks", "idPath", gid);
        }

    }

    @When("Set send request for deleting all task on Second Project")
    public void setSendRequestForDeletingAllTaskOnSecondProject() {

        response = given().

                spec(specification).

                contentType(ContentType.JSON).

                header("Authorization", ConfigReader.getProperty("token")).

                when().

                delete("{taskPath}/{idPath}");

    }


    @Then("Verify deleted all of  on Second Project")
    public void verifyDeletedAllOfOnSecondProject() {

        response.then().statusCode(200);

    }

    @Given("Set Base URLs for creating all tasks for Third Project")
    public void setBaseURLsForCreatingAllTasksForThirdProject() {

        specification = new RequestSpecBuilder().
                setBaseUri(ConfigReader.getProperty("baseURL")).
                build();

        specification.pathParam("taskPath", "tasks");

    }

    @When("Set expected data with  {string}data and send request for  Third Project")
    public void setExpectedDataWithDataAndSendRequestForThirdProject(String taskNames) {

        tasksName = taskNames;

        thirdProjectTaskData = new ThirdProjectTaskData();

        HashMap<String, Object> expectedDataAndReqBody =  thirdProjectTaskData.reqTestDataBody();

        response = given().
                spec(specification).
                contentType(ContentType.JSON).
                header("Authorization", ConfigReader.getProperty("token")).
                body(expectedDataAndReqBody).
                when().
                post("{taskPath}");

        System.out.println("Response: ");

        response.prettyPrint();

        jsonPath = response.jsonPath();

        thirdProjectTaskGid = jsonPath.get("data.gid");

        ThirdProjectTaskGidStorage thirdProjectTaskGidStorage=ThirdProjectTaskGidStorage.getInstance();

        thirdProjectTaskGidStorage.addTaskGid(thirdProjectTaskGid);

        thirdProjectListTasks = thirdProjectTaskGidStorage.getTaskGid();

        if (thirdProjectListTasks.size() >= 3) {
            thirdProjectFirstTaskGid = thirdProjectListTasks.get(0);
            thirdProjectSecondTaskGid = thirdProjectListTasks.get(1);
            thirdProjectThirdTaskGid  =thirdProjectListTasks.get(2);
        } else {
            System.out.println("There are not enough projects in the list to access.");
        }


    }

    @Then("Verify all of created tasks for  Third Project")
    public void verifyAllOfCreatedTasksForThirdProject() {

        response.then().assertThat().statusCode(201);

    }


    @Given("Set Base URLs with for deleting all task on Third Project")
    public void setBaseURLsWithForDeletingAllTaskOnThirdProject() {
        String[] taskGids = {thirdProjectFirstTaskGid, thirdProjectSecondTaskGid, thirdProjectThirdTaskGid};

        for (String gid : taskGids) {
            specification = new RequestSpecBuilder()
                    .setBaseUri(ConfigReader.getProperty("baseURL"))
                    .build();
            specification.pathParams("taskPath", "tasks", "idPath", gid);
        }
    }

    @When("Set send request for deleting all task on Third Project")
    public void setSendRequestForDeletingAllTaskOnThirdProject() {

        response = given().

                spec(specification).

                contentType(ContentType.JSON).

                header("Authorization", ConfigReader.getProperty("token")).

                when().

                delete("{taskPath}/{idPath}");

    }

    @Then("Verify deleted all of  on Third Project")
    public void verifyDeletedAllOfOnThirdProject() {

        response.then().assertThat().statusCode(200);

    }

    @Given("Set Base URLs  for deleting all of projects")
    public void setBaseURLsForDeletingAllOfProjects() {

        String[] taskGids = {firstProjectGid,secondProjectGid,thirdProjectGid};

        for (String gid : taskGids) {
            specification = new RequestSpecBuilder()
                    .setBaseUri(ConfigReader.getProperty("baseURL"))
                    .build();
            specification.pathParams("projectPath", "projects", "idPath", gid);
        }
    }

    @When("Set send request for deleting all of projects")
    public void setSendRequestForDeletingAllOfProjects() {

        response = given().

                spec(specification).
                contentType(ContentType.JSON).
                header("Authorization", ConfigReader.getProperty("token")).
                when().
                delete("{projectPath}/{idPath}");

    }

    @Then("Verify deleted all of projects")
    public void verifyDeletedAllOfProjects() {

        response.then().assertThat().statusCode(200);

    }
}