package javabean;

import java.util.ArrayList;

public class hero extends character{
    public ArrayList<String> skill;



    public hero(){
        super();
        skill=new ArrayList<String>();
    }

    public hero(String name, int HP, int atk, int def) {
        super(name,HP,atk,def);
        skill=new ArrayList<String>();
    }
}
