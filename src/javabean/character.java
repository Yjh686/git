package javabean;

public class character {
     public String name;
     public int atk;
     public int defend;
     public int HP;
     public int maxHP;

     public character(){}

    public character(String name, int atk, int defend, int HP) {
        this.name = name;
        this.atk = atk;
        this.defend = defend;
        this.HP = HP;
        this.maxHP = HP;
    }

    public boolean isalive(){
        return HP>0;
    }

    public void heal(int amount){
        HP+=amount;
        if(HP>maxHP){
            HP=maxHP;
        }
    }

    public void take_damage(int amount){
        HP-=amount;
        if(HP<0){
            HP=0;
        }
    }

    public void show(){
        System.out.println(name+"[当前生命:"+HP+" 攻击:"+atk+" 防御:"+defend+"]");
    }


}




