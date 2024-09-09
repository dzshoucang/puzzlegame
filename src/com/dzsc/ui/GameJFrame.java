package com.dzsc.ui;

import javax.swing.*;
import java.util.Random;

public class GameJFrame extends JFrame {
    // GameJFrame 表示游戏主界面

    //创建一个二维数组
    //目的：用来管理数据
    //加载图片的时候会根据二维数组中的数据进行加载
    int[][] data = new int[4][4];

    public GameJFrame() {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱）
        intData();

        //初始化图片（根据打乱之后的结果去加载图片）
        initImage();

        //让界面显示出来
        this.setVisible(true);
    }

    //初始化数据（打乱顺序）
    private void intData() {
        // 把一维数组中数据0-15打乱顺序，按四个一组放进二维数组

        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        //按四个一组放进二维数组
        for (int i = 0; i < tempArr.length; i++) {
            data[i / 4][i % 4] = tempArr[i];
        }

    }

    // 初始化图片
    //添加图片时按照二维数组添加
    private void initImage() {
        // 外循环 -----把内循环重复执行四次。
        for (int i = 0; i < 4; i++) {
            //内循环 在一行添加四个图片
            for (int j = 0; j < 4; j++) {
                //获取当前要加载的图片序号
                int number = data[i][j];
                //创建一个图片ImageIcon的对象
                ImageIcon icon = new ImageIcon("D:\\JavaProject\\projec\\puzzlegame\\image\\animal\\animal3\\" + number + ".jpg");
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(icon);
                //指定图片位置
                jLabel.setBounds(105 * j, 105 * i, 105, 105);
                //把管理容器添加到界面中

                this.getContentPane().add(jLabel);

            }
        }


    }

    private void initJMenuBar() {
        //创建整个菜单
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单上的两个选项
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        //创建选项下面的条目
        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登录");
        JMenuItem closeItem = new JMenuItem("关闭游戏");

        JMenuItem accountItem = new JMenuItem("公众号");

        //把条目添加进选项中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //把选项添加进菜单
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置对象
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面的宽高
        this.setSize(603, 608);
        //设置界面的标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置居中
        this.setLocationRelativeTo(null);
        //设置默认关闭方式
        this.setDefaultCloseOperation(3);

        //取消默认的居中放置
        setLayout(null);
    }
}
