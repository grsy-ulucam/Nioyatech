package dataClass;

import java.util.ArrayList;
import java.util.List;

public class FirstProjectTaskGidStorage {

    private static FirstProjectTaskGidStorage instance;
    private List<String> list = new ArrayList<>();

    public FirstProjectTaskGidStorage() {
    }

    public static FirstProjectTaskGidStorage getInstance() {
        if (instance == null) {
            instance = new FirstProjectTaskGidStorage();
        }
        return instance;
    }

    public void addTaskGid(String gid) {

        list.add(gid);
    }

    public List<String> getTaskGid() {

        return list ;
    }

}
