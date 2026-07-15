package ui;

import javabean.enemy;
import javabean.hero;

import java.util.ArrayList;
import java.util.Scanner;

public class fighting {
    public void fightstart(String username){
        System.out.println("-----------------------------");
        System.out.println("战斗开始，"+username+"！");
        System.out.println("-----------------------------");

        cheat_hero(username);

        ArrayList<enemy> enemies=new ArrayList<>();
        enemies.add(new enemy("初级战士",100,15,10,"猛击"));
        enemies.add(new enemy("敏捷刺客",80,20,5,"偷袭"));
        enemies.add(new enemy("重装坦克",130,10,15,"护盾防御"));
        enemies.add(new enemy("神秘法师",60,25,3,"冰冻法术"));
    }

    public void cheat_hero(String username){
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
                return;
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
        System.out.println("角色创建成功");
        System.out.println("你的初始属性为:"+h.show());

        h.skill.add("普通攻击");
        h.skill.add("强力击打");
        h.skill.add("生命汲取");

        System.out.println("你的技能为:"+h.skill.toString());
    }
}
