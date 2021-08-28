package com.test.demo02;

import static java.lang.Thread.sleep;

//代理角色
public class UserServiceProxy implements UserService{

    private UserServiceImpl userService;

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }


    @Override
    public void insert() {
        userService.insert();
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ing("insert");
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("insert");
    }

    @Override
    public void delete() {
        userService.delete();
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ing("delete");
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("delete");
    }

    @Override
    public void update() {
        userService.update();
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ing("update");
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("update");
    }

    @Override
    public void select() {
        userService.select();
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ing("select");
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("select");
    }

    //日志方法
    public void log(String msg){
        System.out.println("已"+msg+"该用户");
    }
    public void ing(String msg){
        System.out.println("正在"+msg+"…………");
    }
}
