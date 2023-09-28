package dataClass;

import java.util.ArrayList;
import java.util.List;

public class ThirdProjectTaskGidStorage {

    private static ThirdProjectTaskGidStorage instance;
    private List<String> list = new ArrayList<>();

    public ThirdProjectTaskGidStorage() {
    }

    public static ThirdProjectTaskGidStorage getInstance() {
        if (instance == null) {
            instance = new ThirdProjectTaskGidStorage();
        }
        return instance;
    }

    public void addTaskGid(String gid) {

        list.add(gid);
    }

    public List<String> getTaskGid() {

        return list;
    }

}
