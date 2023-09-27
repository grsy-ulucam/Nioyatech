package dataClass;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import stepdefinitions.ProjectSteps;
import utilities.JsonUtils;
import java.util.HashMap;

public class TaskData {

    public HashMap<String, Object> reqTestDataBody(){

        String jsonStr = "{\"data\":{\"projects\":[],\"workspace\":\"1205543498728328\",\"assignee\":\"1205543498728329\",\"notes\":\"crax\",\"name\":\"karya\"}}";

        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(jsonStr, JsonObject.class);

        JsonArray projectsArray = jsonObject.getAsJsonObject("data").getAsJsonArray("projects");

        String newProject = ProjectSteps.gid;

        projectsArray.add(newProject);

        jsonObject.getAsJsonObject("data").addProperty("name", ProjectSteps.newTaskName);

        String modifiedJson = gson.toJson(jsonObject);

        HashMap<String,Object> expectedDataAndReqBody= JsonUtils.fromJson(modifiedJson, HashMap.class);

        return expectedDataAndReqBody;

    }

}

