package User;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String id;
    private String pw;

    public User(String name, String id, String pw) {
        this.name = name;
        this.id = id;
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }
}
