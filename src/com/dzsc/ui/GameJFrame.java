package com.dzsc.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener , ActionListener {
    // GameJFrame 表示游戏主界面

    //创建一个二维数组
    //目的：用来管理数据
    //加载图片的时候会根据二维数组中的数据进行加载
    int[][] data = new int[4][4];
    //记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;

    //定义变量名记录当前图片路径
    String path = "puzzlegame\\image\\animal\\animal3\\";

    //定义二维数组，存储中缺德数据
    int[][] win = {
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,0}
    };

    //定义变量用来统计步数
    int step = 0;

    //创建选项下面的条目
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("公众号");

    public GameJFrame() {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱）
        initData();

        //初始化图片（根据打乱之后的结果去加载图片）
        initImage();

        //让界面显示出来
        this.setVisible(true);
    }

    //初始化数据（打乱顺序）
    private void initData() {
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
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
                data[i / 4][i % 4] = tempArr[i];
        }

    }

    // 初始化图片
    //添加图片时按照二维数组添加
    private void initImage() {
        //清空已有图片
        this.getContentPane().removeAll();

        if(victory()){
            JLabel winJLabel = new JLabel(new ImageIcon("puzzlegame\\image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        // 外循环 -----把内循环重复执行四次。
        for (int i = 0; i < 4; i++) {
            //内循环 在一行添加四个图片
            for (int j = 0; j < 4; j++) {
                //获取当前要加载的图片序号
                int number = data[i][j];
                //创建一个图片ImageIcon的对象
                ImageIcon icon = new ImageIcon(path + number + ".jpg");
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(icon);
                //指定图片位置
                jLabel.setBounds(105 * j + 84, 105 * i + 134, 105, 105);
                //给图片添加边框  0 凸起 1凹下去
                jLabel.setBorder(new BevelBorder(1));
                //把管理容器添加到界面中

                this.getContentPane().add(jLabel);

                //刷新界面
                this.getContentPane().repaint();

            }
        }

        //添加背景图片
        ImageIcon bg = new ImageIcon("puzzlegame\\image\\background.png");
        JLabel background = new JLabel(bg);
        //设置背景图片位置
        background.setBounds(40, 40, 508, 560);
        //把背景图片添加进界面
        this.getContentPane().add(background);


    }

    //初始化菜单
    private void initJMenuBar() {
        //创建整个菜单
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单上的两个选项
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");


        //把条目添加进选项中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        //把选项添加进菜单
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置对象
        this.setJMenuBar(jMenuBar);
    }

    //初始化界面
    private void initJFrame() {
        //设置界面的宽高
        this.setSize(603, 688);
        //设置界面的标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置居中
        this.setLocationRelativeTo(null);
        //设置默认关闭方式
        this.setDefaultCloseOperation(3);
        //取消默认的居中放置
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下不松时调用
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){
            this.getContentPane().removeAll();
            //加载完整的图片
            JLabel all = new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);

            //添加背景图片
            ImageIcon bg = new ImageIcon("puzzlegame\\image\\background.png");
            JLabel background = new JLabel(bg);
            //设置背景图片位置
            background.setBounds(40, 40, 508, 560);
            //把背景图片添加进界面
            this.getContentPane().add(background);

            //刷新界面
            this.getContentPane().repaint();
        }
    }
    //松开按键会调用方法
    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利，胜利则直接结束无法继续移动
        if(victory()){
            return;
        }

        //对上下左右进行判断
        //左上右下对应的分别是37 38 39 40
        int code = e.getKeyCode();
        if (code == 37) {
            System.out.println("向左移动");
            if(y == 3){
                //表示空白方块已经在最右方了，他的右面没有图片可移动
                return;
            }
            //逻辑：把空白方块向右移动
            //x,y表示空白方块
            //x,y表示空白方块
            data[x][y]=data[x][y+1];
            data[x][y+1]=0;
            y++;
            //每移动一次，步数加一
            step++;
            //按照最新的图片加载图片
            initImage();

        } else if (code == 38) {
            System.out.println("向上移动");
            if(x == 3){
                //表示空白方块已经在最下方了，他的下面没有图片可移动
                return;
            }
            //逻辑：把空白方块向下移动
            //x,y表示空白方块
            //x+1,y表示空白方块
            data[x][y]=data[x+1][y];
            data[x+1][y]=0;
            x++;
            //每移动一次，步数加一
            step++;
            //按照最新的图片加载图片
            initImage();

        } else if (code == 39) {
            System.out.println("向右移动");
            if(y == 0){
                //表示空白方块已经在最左方了，他的左面没有图片可移动
                return;
            }
            //逻辑：把空白方块向左移动
            //x,y表示空白方块
            //x,y-1表示空白方块
            data[x][y]=data[x][y-1];
            data[x][y-1]=0;
            y--;
            //每移动一次，步数加一
            step++;
            //按照最新的图片加载图片
            initImage();

        } else if (code == 40) {
            System.out.println("向下移动");
            if(x == 0){
                //表示空白方块已经在最上方了，他的上面没有图片可移动
                return;
            }
            //逻辑：把空白方块向上移动
            //x,y表示空白方块
            //x-1,y表示空白方块
            data[x][y]=data[x-1][y];
            data[x-1][y]=0;
            x--;
            //每移动一次，步数加一
            step++;
            //按照最新的图片加载图片
            initImage();

        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    //判断data数组中的数据跟win中是否相同
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]){
                    //如果有一个不一样，返回false
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的方法
        Object obj = e.getSource();
        if(obj == replayItem){
            System.out.println("重新游戏");
            //打乱数据
            initData();
            //计数器清零
            step = 0;
            //重新加载图片
            initImage();

        } else if (obj == reLoginItem) {
            System.out.println("重新登录");
            //关闭当前界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            //关闭虚拟机
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("公众号");
            //创建弹窗对象
            JDialog jDialog = new JDialog();
            //构建一个管理图片的容器对象JLabel
            JLabel jLabel = new JLabel(new ImageIcon("puzzlegame\\image\\about.png"));
            //设置位置和宽高
            jLabel.setBounds(0,0,258,258);
            //把图片添加进图片
            jDialog.getContentPane().add(jLabel);
            //设置弹窗大小
            jDialog.setSize(344,344);
            //弹窗置顶
            jDialog.setAlwaysOnTop(true);
            //弹窗居中
            jDialog.setLocationRelativeTo(null);

            //弹窗不关闭无法操作下面的界面
            jDialog.setModal(true);

            //让弹窗显示出来
            jDialog.setVisible(true);
        }
    }
}
