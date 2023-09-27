package dataClass;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import stepdefinitions.ProjectSteps;
import utilities.JsonUtils;
import java.util.HashMap;

public class ProjectData {
    public HashMap<String, Object> reqTestDataBody(){

        String json = "{\n" +
                "  \"data\": {\n" +
                "    \"current_status\": {\n" +
                "      \"author\": {\n" +
                "        \"name\": \"Gürsoy\"\n" +
                "      },\n" +
                "      \"created_by\": {\n" +
                "        \"name\": \"Gürsoy\"\n" +
                "      },\n" +
                "      \"title\": \"Status Update - Jun 15\",\n" +
                "      \"text\": \"The project is moving forward according to plan...\",\n" +
                "      \"html_text\": \"<body>The project <strong>is</strong> moving forward according to plan...</body>\",\n" +
                "      \"color\": \"green\"\n" +
                "    },\n" +
                "\n" +
                "    \"name\": \"AAAAA Project\",\n" +
                "    \"archived\": false,\n" +
                "    \"color\": \"light-blue\",\n" +
                "    \"default_view\": \"list\",\n" +
                "    \"due_date\": \"2023-09-21\",\n" +
                "    \"due_on\": \"2023-09-21\",\n" +
                "    \"html_notes\": \"<body>These are things we need to purchase.</body>\",\n" +
                "    \"notes\": \"crax\",\n" +
                "    \"public\": true,\n" +
                "    \"default_access_level\": \"admin\",\n" +
                "    \"minimum_access_level_for_customization\": \"admin\",\n" +
                "    \"minimum_access_level_for_sharing\": \"admin\",\n" +
                "    \"owner\": \"1205543498728329\",\n" +
                "    \"team\": \"1205543498728330\",\n" +
                "    \"workspace\": \"1205543498728328\"\n" +
                "  }\n" +
                "}";

        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        jsonObject.getAsJsonObject("data").addProperty("name", ProjectSteps.projectNames);

        String modifiedJson = gson.toJson(jsonObject);


        HashMap<String,Object> expectedDataAndReqBody= JsonUtils.fromJson(modifiedJson, HashMap.class);

        return expectedDataAndReqBody;
    }
}
