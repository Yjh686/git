package ui;

import javabean.User;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;
import java.util.Scanner;

public class login {
    ArrayList<User> list=new ArrayList<>();
    public void start(){
        while (true) {
            System.out.println("-----------------------------");
            System.out.println("---------欢迎来到我的游戏--------");
            System.out.println("-----------------------------");
            System.out.println("1.登录   2.注册    3.退出");
            Scanner sc =new Scanner(System.in);
            int choose=sc.nextInt();
            switch(choose){
                case 1-> login();
                case 2-> register();
                case 3->{
                    System.out.println("用户选择了退出");
                    System.exit(0);                          //退出程序
                }
                default -> System.out.println("输入错误，请重新输入");
            }
        }


    }
    //登录操作
    public void login(){
        System.out.println("用户选择了登录");
    }
    //注册操作
    public void register(){
        System.out.println("用户选择了注册");
        User u=new User();
        Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("请输入用户名：");
            String username=sc.next();
            StringBuilder sb=new StringBuilder(username);
            //判断长度
            if(!checklen(3,10,username)){
                System.out.println("用户名长度不符合要求,请输入3-10位");
                continue;
            }
            //判断字符结构
            if(!checkusername(username)){
                System.out.println("用户名字符结构不符合要求,请输入包含数字和字母的用户名");
                continue;
            }
            //判断唯一
            if(contains(list,username)){
                System.out.println("用户名已存在,请输入其他用户名");
                continue;
            }
            u.setUsername(username);
            break;
        }

        //判断密码
        while (true) {
            System.out.println("请输入密码：");
            String password1=sc.next();
            System.out.println("请再次输入密码：");
            String password2=sc.next();

            //判断是否一致
            if(!password2.equals(password1)){
                System.out.println("两次输入的密码不一致,请重新输入");
                continue;
            }
            //判断长度
            if(!checklen(6,20,password1)){
                System.out.println("密码长度不符合要求,请输入6-20位");
                continue;
            }
            //判断结构
            if(!checkpassword(password1)){
                System.out.println("密码字符结构不符合要求,请输入包含数字和字母的密码");
                continue;
            }
            u.setPassword(password1);
            break;

        }
        list.add(u);
        System.out.println("注册成功");
    }
    public boolean checklen(int min,int max,String str){
        return str.length()>=min&&str.length()<=max;
    }

    public  boolean checkusername(String str){
        int[] count=countchar(str);

        return count[0]>=0&&count[1]>0&&count[2]==0;
    }

    public boolean contains(ArrayList<User> list,String username){
        for (int i = 0; i < list.size(); i++) {
            User u=list.get(i);
            if(u.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public boolean checkpassword(String password){
        int[] arr=countchar(password);
        return arr[0]>0&&arr[1]>0&&arr[2]==0;
    }

    public int[] countchar(String str){
        int numcount=0;
        int charcount=0;
        int othercount=0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)>='0'&&str.charAt(i)<='9'){
                numcount++;
            }else if((str.charAt(i)>='a'&&str.charAt(i)<='z')||(str.charAt(i)>='A'&&str.charAt(i)<='Z')){
                charcount++;
            }else{
                othercount++;
            }
        }
        return new int[]{numcount,charcount,othercount};
    }




}
