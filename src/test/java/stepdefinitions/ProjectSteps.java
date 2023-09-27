package stepdefinitions;

import dataClass.ProjectData;
import dataClass.TaskData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pages.ProjectPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ProjectSteps {

    protected static RequestSpecification specification;
    protected static Response response;
    protected JsonPath jsonPath;
    public static String gid;
    public static String taskGid;
    public static String name;

    protected ProjectData data;
    TaskData taskData;
    ProjectPage projectPage;
    public static String newTaskName;
    public static String firstTaskName;

    public static String projectNames;

    @Given("Set Base URLs")
    public void setBaseURLs() {
        specification = new RequestSpecBuilder().
                setBaseUri(ConfigReader.getProperty("baseURL")).
                build();

        specification.pathParam("projectPath", "projects");
    }

    @When("Set expected {string} data and send request")
    public void setExpectedDataAndSendRequest(String projectName) {

        ProjectSteps.projectNames=projectName;

        data = new ProjectData();
        HashMap<String, Object> expectedDataAndReqBody = data.reqTestDataBody();
        response = given().
                spec(specification).
                contentType(ContentType.JSON).
                header("Authorization", "Bearer 1/1205543491884173:59c403d909d21bf0455d3cffe8db0327").
                body(expectedDataAndReqBody).
                when().
                post("{projectPath}");

        System.out.println("Response: ");
        response.prettyPrint();

    }


    @Then("Verify project created")
    public void verifyProjectCreated() {
        response.then().assertThat().statusCode(201);
        jsonPath = response.jsonPath();
        gid = jsonPath.get("data.gid");
        name = jsonPath.getString("data.name");
        System.out.println("Project gid = " + gid);
        System.out.println("Project name = " + name);

    }

    @Given("Go to Asana website")
    public void goToAsanaWebsite() {
        Driver.getDriver().get(ConfigReader.getProperty("URL")
        );
    }

    @When("Enter valid email address and password")
    public void enterValidEmailAddressAndPassword() {
        projectPage.loginPage();
    }

    @Then("Verify new project created")
    public void verifyNewProjectCreated() {
        projectPage.checkFirstProject();
    }

    @Given("set base url tasks")
    public void setBaseUrlTasks() {
        specification = new RequestSpecBuilder().
                setBaseUri(ConfigReader.getProperty("baseURL")).
                build();

        specification.pathParam("taskPath", "tasks");
    }

    @When("set expected {string} and request")
    public void setExpectedAndRequest(String taskName) {
        ProjectSteps.newTaskName = taskName;

        taskData = new TaskData();
        HashMap<String, Object> expectedDataAndReqBody = taskData.reqTestDataBody();

        System.out.println(expectedDataAndReqBody);

        response = given().

                spec(specification).
                contentType(ContentType.JSON).
                header("Authorization", ConfigReader.getProperty("token")).
                body(expectedDataAndReqBody).
                when().
                post("{taskPath}");

        jsonPath = response.jsonPath();
        taskGid = jsonPath.get("data.gid");
        System.out.println("Project gid = " + gid);
        taskName = jsonPath.get("data.name");

    }

    @Then("verify new tasks with api")
    public void verifyNewTasksWithApi() {

        response.then().assertThat().statusCode(201);

    }

    @Given("Set Base URLs for deleting")
    public void setBaseURLsForDeleting() {

        specification = new RequestSpecBuilder().
                setBaseUri(ConfigReader.getProperty("baseURL")).
                build();
        specification.pathParams("taskPath", "tasks", "idPath", ProjectSteps.taskGid);

    }

    @When("Set send request for deleting")
    public void setSendRequestForDeleting() {

        response = given().

                spec(specification).

                contentType(ContentType.JSON).

                header("Authorization", ConfigReader.getProperty("token")).

                when().

                delete("{taskPath}/{idPath}");
    }

    @Then("Verify task deleted")
    public void verifyTaskDeleted() {
        response.then().assertThat().statusCode(200);

    }

    @Then("Verify new task created with {string}")
    public void verifyNewTaskCreatedWith(String firstTaskName) {

        projectPage.checkFirstProjectTasks();

    }


    @Given("Set Base URLs for deleting project")
    public void setBaseURLsForDeletingProject() {

        specification = new RequestSpecBuilder().
                setBaseUri(ConfigReader.getProperty("baseURL")).
                build();

        specification.pathParams("projectPath", "projects", "idPath", ProjectSteps.gid);

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

    @Then("Verify project deleted")
    public void verifyProjectDeleted() {

        response.then().assertThat().statusCode(200);

    }


}
