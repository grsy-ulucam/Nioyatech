package dataClass;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import stepdefinitions.AllApiSteps;
import utilities.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SecondProjectTaskData {

    public HashMap<String, Object> reqTestDataBody(){

        String jsonStr = "{\"data\":{\"projects\":[],\"workspace\":\"1205543498728328\",\"assignee\":\"1205543498728329\",\"notes\":\"crax\",\"name\":\"karya\"}}";

        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(jsonStr, JsonObject.class);

        JsonArray projectsArray = jsonObject.getAsJsonObject("data").getAsJsonArray("projects");

        String firstProjectGid= null;

        firstProjectGid =AllApiSteps.secondProjectGid;

        String newProject =firstProjectGid;

        projectsArray.add(newProject);

        jsonObject.getAsJsonObject("data").addProperty("name", AllApiSteps.tasksName);

        String modifiedJson = gson.toJson(jsonObject);

        HashMap<String,Object> expectedDataAndReqBody= JsonUtils.fromJson(modifiedJson, HashMap.class);

        return expectedDataAndReqBody;

    }

}
