package javabean;

import java.util.ArrayList;

public class hero extends character{
    public ArrayList<String> skill;



    public hero(){
        super();
        skill=new ArrayList<String>();
    }

    public hero(String name, int atk, int defend, int HP) {
        super(name, atk, defend, HP);
        skill=new ArrayList<String>();
    }
}
