package dataClass;

import java.util.ArrayList;
import java.util.List;

public class SecondProjectTaskGidStorage {

    private static SecondProjectTaskGidStorage instance;
    private List<String> list = new ArrayList<>();

    public SecondProjectTaskGidStorage() {
    }

    public static SecondProjectTaskGidStorage getInstance() {
        if (instance == null) {
            instance = new SecondProjectTaskGidStorage();
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
