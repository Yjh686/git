package ui;

import javabean.enemy;
import javabean.hero;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class fighting {
    public void fightstart(String username){
        System.out.println("-----------------------------");
        System.out.println("♟战斗开始，"+username+"！");
        System.out.println("-----------------------------");

        hero h=cheat_hero(username);

        ArrayList<enemy> enemies=new ArrayList<>();
        enemies.add(new enemy("初级战士",100,15,10,"猛击"));
        enemies.add(new enemy("敏捷刺客",80,20,5,"偷袭"));
        enemies.add(new enemy("重装坦克",130,10,15,"护盾防御"));
        enemies.add(new enemy("神秘法师",60,25,3,"冰冻法术"));


        System.out.println("角色创建成功");
        System.out.println("你的初始属性为:"+h.show());
        System.out.println("你的技能为:"+h.skill.toString());

        int count=1;
        int wins=0;
        while(h.isalive()){
            if(wins>0){
                System.out.println("✧胜利后，敌人属性提升");
                for (int i = 0; i < enemies.size(); i++) {
                    enemy e=enemies.get(i);
                    e.maxHP+=10;
                    e.HP=e.maxHP;
                    e.atk+=3;
                    e.defend+=2;
                }
            }
            Random r=new Random();
            int index=r.nextInt(enemies.size());
            enemy enemycharacter=enemies.get(index);



            System.out.println("-----------------------------");
            System.out.println("✧战斗开始");
            System.out.println("第"+count+"关 对手是"+enemycharacter.name);
            int round=1;
            while(h.isalive()){
                System.out.println("✧第"+round+"回合");
                System.out.println(HPbar(h.name,h.HP,h.maxHP));
                System.out.println(HPbar(enemycharacter.name,enemycharacter.HP,enemycharacter.maxHP));

                playturn(h,enemycharacter);

                if(!enemycharacter.isalive()){
                    System.out.println("✧你击败了"+enemycharacter.name);
                    wins++;
                    count++;
                    System.out.println("当前胜场:"+wins);
                    break;
                }
                enemyturn(enemycharacter,h);

                if(!h.isalive()){
                    System.out.println("✧你被击败了");
                    break;
                }
                round++;


            }


        }

    }

    public String HPbar(String name,int HP,int maxHP){
        int barlen=20;
        StringBuilder sb=new StringBuilder();
        int a=(int)((HP*1.0/maxHP)*barlen);
        sb.append(name).append(":[");
        for (int i = 0; i < barlen; i++) {
            if(i<a){
                sb.append("▋");
            }
            else{
                sb.append(" ");
            }
        }
        sb.append("]").append(HP).append("/").append(maxHP).append("HP");
        return sb.toString();

    }

    public hero cheat_hero(String username){
        System.out.println("创建你的角色");
        System.out.println("你的角色为:"+username);

        Scanner sc=new Scanner(System.in);
        System.out.println("请分配你的属性点（共20点）");
        System.out.println("HP(每点+10HP)");
        System.out.println("ATK(每点+2ATK)");
        System.out.println("DEF(每点+1DEF)");
        int points=20;

        String[] attributes={"HP","ATK","DEF"};
        //分配好的属性点
        int[] attributePoints=new int[3];

        for (int i = 0; i < attributes.length; i++) {
            System.out.println("请输入"+attributes[i]+"的点数："+"(当前剩余"+points+"点)");
            int point=sc.nextInt();
            if (point>points){
                System.out.println("属性点不足,全部属性点将分配给"+attributes[i]);
                attributePoints[i]+=20;
                break;
            }
            points-=point;
            attributePoints[i]=point;
            if(i==2){
                if(points>0){
                    System.out.println("剩余的属性点"+points+"将自动分配给HP");
                    attributePoints[0]+=points;
                }
            }

        }
        hero h=new hero(username,100+attributePoints[0]*10,10+attributePoints[1]*2,attributePoints[2]);
        h.skill.add("普通攻击");
        h.skill.add("强力击打");
        h.skill.add("生命汲取");
        return h;
    }

    public void playturn(hero h,enemy e){
        Scanner sc=new Scanner(System.in);
        System.out.println("✧你的回合");
        System.out.println("请选择你的技能：");
        System.out.println("1.普通攻击 2.强力击打 3.生命汲取");
        System.out.print("输入技能(1-3):");
        String choose=sc.next();
        switch (choose){
            case "1":
                int damage=calculateDamage(h.atk,e.defend);
                System.out.println("✪你使用了普通攻击对"+e.name+"造成了"+damage+"点伤害");
                e.take_damage(damage);
                break;
            case "2":
                if (h.HP>10) {
                    h.take_damage(10);
                    int damage2=calculateDamage(h.atk*2,e.defend);

                    e.take_damage(damage2);
                    System.out.println("✪你失去了10HP但是成功使用了强力击打对"+e.name+"造成了"+damage2+"点伤害");
                }
                else{
                    System.out.println("你的HP不足，无法使用强力击打");
                }
                break;
            case "3":
                if(h.HP>10){
                    h.take_damage(10);
                    Random r=new Random();
                    int amount=r.nextInt(20,40);
                    h.heal(amount);
                    System.out.println("♡你失去了10HP但是成功使用了生命汲取恢复了"+amount+"点HP");
                }
                else{
                    System.out.println("你的HP不足，无法使用生命汲取");
                }
                break;
        }

    }

    public int calculateDamage(int atk,int def){
        int damage=atk-def;
        if(damage<0){
            damage=0;
        }
        return damage;
    }

    public void enemyturn(enemy e,hero h){
        System.out.println("✧敌人回合");
        String action="";
        Random r=new Random();
        int num=r.nextInt(2);
        if(num==0){
            action="普通攻击";
        }
        else{
            action=e.skill;
        }
        System.out.println(e.name+"使用了"+action);
        switch(action){
            case "普通攻击":
                int damage=calculateDamage(e.atk,h.defend);
                System.out.println("♢敌人使用了普通攻击对"+h.name+"造成了"+damage+"点伤害");
                h.take_damage(damage);
                break;
            case "偷袭" :
                int damage2=calculateDamage(e.atk*2,h.defend);
                System.out.println("♢敌人使用了偷袭对"+h.name+"造成了"+damage2+"点伤害");
                h.take_damage(damage2);
                break;
            case "猛击"  :
                int damage3=calculateDamage((int)(e.atk*1.5),h.defend);
                System.out.println("♢敌人使用了猛击对"+h.name+"造成了"+damage3+"点伤害");
                h.take_damage(damage3);
                break;
            case "护盾防御" :
                e.definding=true;
                System.out.println(e.name+"使用了护盾防御,下回合减伤50%");
                break;
            case "冰冻法术":
                int damage4=calculateDamage((int)(e.atk*1.7),h.defend);
                System.out.println("♢敌人使用了冰冻法术对"+h.name+"造成了"+damage4+"点伤害");
                h.take_damage(damage4);
                break;
        }


    }
}
