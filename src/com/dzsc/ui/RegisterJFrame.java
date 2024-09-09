package com.dzsc.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    //注册界面

    public RegisterJFrame() {
        //设置界面的宽高
        this.setSize(488, 500);

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置居中
        this.setLocationRelativeTo(null);

        //设置默认关闭方式
        this.setDefaultCloseOperation(3);
        //让界面显示出来
        this.setVisible(true);
    }
}
