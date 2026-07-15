package javabean;

public class enemy extends character{
    public boolean definding;
    public String skill;

    public enemy(){
        super();
    }
    public enemy(String name, int HP, int atk, int defend, String skill) {
        super(name, atk, defend, HP);
        this.skill = skill;
    }
    @Override
    public void take_damage(int amount){
        if(definding){
            amount=amount/2>1?amount/2:1;
            definding=false;
        }
        super.take_damage(amount);
    }



}
