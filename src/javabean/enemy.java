package javabean;

public class enemy extends character{
    public boolean definding;
    public String skill;

    public enemy(){
        super();
    }
    public enemy(String name, int atk, int defend, int HP, String skill) {
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
