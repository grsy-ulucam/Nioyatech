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

    @Given("Set Base URLs with for deleting first task on First Project")
    public void setBaseURLsWithForDeletingFirstTaskOnFirstProject() {

        specification = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseURL"))
                .build();
        specification.pathParams("taskPath", "tasks", "idPath", firstProjectFirstTaskGid);

    }

    @When("Set send request for deleting  task on First Project")
    public void setSendRequestForDeletingFirstTaskOnFirstProject() {
        response = given().
                spec(specification).
                contentType(ContentType.JSON).
                header("Authorization", ConfigReader.getProperty("token")).
                when().
                delete("{taskPath}/{idPath}");
    }

    @Then("Verify deleted task on First Project")
    public void verifyDeletedFirstOfOnFirstProject() {
        response.then().statusCode(200);
    }

    @Given("Set Base URLs with for  deleting second task on First Project")
    public void setBaseURLsWithForSecondDeletingSecondTaskOnFirstProject() {

        specification = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseURL"))
                .build();
        specification.pathParams("taskPath", "tasks", "idPath", firstProjectSecondTaskGid);

    }

    @Given("Set Base URLs with for  deleting third task on First Project")
    public void setBaseURLsWithForDeletingThirdTaskOnFirstProject() {

        specification = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseURL"))
                .build();
        specification.pathParams("taskPath", "tasks", "idPath", firstProjectThirdTaskGid);

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

    @Given("Set Base URLs with for deleting first task on Second Project")
    public void setBaseURLsWithForDeletingFirstTaskOnSecondProject() {

        specification = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseURL"))
                .build();
        specification.pathParams("taskPath", "tasks", "idPath", secondProjectFirstTaskGid);
    }

    @When("Set send request for deleting  task on Second Project")
    public void setSendRequestForDeletingTaskOnSecondProject() {

        response = given().
                spec(specification).
                contentType(ContentType.JSON).
                header("Authorization", ConfigReader.getProperty("token")).
                when().
                delete("{taskPath}/{idPath}");

    }

    @Then("Verify deleted task on Second Project")
    public void verifyDeletedTaskOnSecondProject() {

        response.then().statusCode(200);

    }

    @Given("Set Base URLs with for  deleting second task on Second Project")
    public void setBaseURLsWithForDeletingSecondTaskOnSecondProject() {

        specification = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseURL"))
                .build();
        specification.pathParams("taskPath", "tasks", "idPath", secondProjectSecondTaskGid);

    }

    @Given("Set Base URLs with for  deleting third task on Second Project")
    public void setBaseURLsWithForDeletingThirdTaskOnSecondProject() {

        specification = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseURL"))
                .build();
        specification.pathParams("taskPath", "tasks", "idPath", secondProjectThirdTaskGid);

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

        HashMap<String, Object> expectedDataAndReqBody = thirdProjectTaskData.reqTestDataBody();

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

        ThirdProjectTaskGidStorage thirdProjectTaskGidStorage = ThirdProjectTaskGidStorage.getInstance();

        thirdProjectTaskGidStorage.addTaskGid(thirdProjectTaskGid);

        thirdProjectListTasks = thirdProjectTaskGidStorage.getTaskGid();

        if (thirdProjectListTasks.size() >= 3) {
            thirdProjectFirstTaskGid = thirdProjectListTasks.get(0);
            thirdProjectSecondTaskGid = thirdProjectListTasks.get(1);
            thirdProjectThirdTaskGid = thirdProjectListTasks.get(2);
        } else {
            System.out.println("There are not enough projects in the list to access.");
        }

    }

    @Then("Verify all of created tasks for  Third Project")
    public void verifyAllOfCreatedTasksForThirdProject() {

        response.then().assertThat().statusCode(201);

    }

    @Given("Set Base URLs with for deleting first task on Third Project")
    public void setBaseURLsWithForDeletingFirstTaskOnThirdProject() {
        specification = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseURL"))
                .build();
        specification.pathParams("taskPath", "tasks", "idPath", thirdProjectFirstTaskGid);
    }

    @When("Set send request for deleting  task on Third Project")
    public void setSendRequestForDeletingTaskOnThirdProject() {
        response = given().
                spec(specification).
                contentType(ContentType.JSON).
                header("Authorization", ConfigReader.getProperty("token")).
                when().
                delete("{taskPath}/{idPath}");
    }

    @Then("Verify deleted task on Third Project")
    public void verifyDeletedTaskOnThirdProject() {

        response.then().statusCode(200);

    }

    @Given("Set Base URLs with for  deleting second task on Third Project")
    public void setBaseURLsWithForDeletingSecondTaskOnThirdProject() {
        specification = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseURL"))
                .build();
        specification.pathParams("taskPath", "tasks", "idPath", thirdProjectSecondTaskGid);
    }

    @Given("Set Base URLs with for  deleting third task on Third Project")
    public void setBaseURLsWithForDeletingThirdTaskOnThirdProject() {

        specification = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseURL"))
                .build();
        specification.pathParams("taskPath", "tasks", "idPath", thirdProjectThirdTaskGid);

    }

    @Given("Set Base URLs  for deleting first project")
    public void setBaseURLsForDeletingFirstProject() {

        specification = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseURL"))
                .build();
        specification.pathParams("projectPath", "projects", "idPath", firstProjectGid);

    }

    @When("Set send request for deleting project")
    public void setSendRequestForDeletingProject() {

        response = given().

                spec(specification).
                contentType(ContentType.JSON).
                header("Authorization", ConfigReader.getProperty("token")).
                when().
                delete("{projectPath}/{idPath}");

    }

    @Then("Verify deleted the  project")
    public void verifyDeletedTheProject() {

        response.then().assertThat().statusCode(200);

    }

    @Given("Set Base URLs  for deleting second project")
    public void setBaseURLsForDeletingSecondProject() {

        specification = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseURL"))
                .build();
        specification.pathParams("projectPath", "projects", "idPath", secondProjectGid);

    }

    @Given("Set Base URLs  for deleting third project")
    public void setBaseURLsForDeletingThirdProject() {

        specification = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseURL"))
                .build();
        specification.pathParams("projectPath", "projects", "idPath", thirdProjectGid);

    }
}

