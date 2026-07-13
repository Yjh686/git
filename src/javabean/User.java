package javabean;

import java.util.Random;

public class User {
    private String id;
    private String username;
    private String password;
    private boolean status;
    public User(){
        id=creatid();
        status=true;
    }
    public User(String username, String password) {
        id=creatid();
        this.username = username;
        this.password = password;
        status=true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    //随机生成id
    public String creatid(){
        StringBuilder sb=new StringBuilder("Laoyingbi");
        Random r=new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }


}
