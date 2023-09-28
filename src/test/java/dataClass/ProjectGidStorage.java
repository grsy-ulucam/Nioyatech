package dataClass;

import java.util.ArrayList;
import java.util.List;

public class ProjectGidStorage {

    private static ProjectGidStorage instance;
    private List<String> list = new ArrayList<>();

    public ProjectGidStorage() {
    }

    public static ProjectGidStorage getInstance() {
        if (instance == null) {
            instance = new ProjectGidStorage();
        }
        return instance;
    }

    public void addProjectGid(String gid) {

        list.add(gid);
    }

    public List<String> getProjectsGid() {

        return list;
    }

}
