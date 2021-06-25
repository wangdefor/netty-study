package com.wy.gobang.frame;

import cn.hutool.core.io.resource.ClassPathResource;
import com.wy.gobang.client.Client;
import com.wy.gobang.msg.User;
import com.wy.gobang.msg.GoBangModelMsg;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author wangyong
 * @Classname GoBangFrame
 * @Description QiPanJPanel
 * @Date 2021/6/25 17:16
 */
public class GoBangFrame extends JFrame implements MouseListener {

    /**
     * 单例的方式实现
     *
     */
    public static GoBangFrame goBangFrame = null;

    public static GoBangFrame getInstance() {
        if (goBangFrame == null) {
            goBangFrame = new GoBangFrame("五子棋");
        }
        return goBangFrame;
    }

    /**
     * 定义坐标 并初始化为 15 * 15
     */
    public static GoBangModelMsg[][] coordinate = new GoBangModelMsg[15][15];

    /**
     * 定义棋盘画板
     */
    @Getter
    private QiPanJPanel canvas;

    /**
     * 定义背景
     */
    private BufferedImage image;

    /**
     * 定义下棋的人
     */
    @Getter
    @Setter
    private User user;

    {
        try {
            ClassPathResource resource = new ClassPathResource("back.jpg");
            image = ImageIO.read(resource.getStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GoBangFrame(String name) {
        super(name);
        setIconImage(image);
        //添加画板
        canvas = new QiPanJPanel();
        canvas.setLayout(null);
        //改变图标
        add(canvas, null);
        //窗体可关闭
        setResizable(false);
        //设置窗体大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //窗体显示
        setBounds(0, 0, 1600, 900);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        GoBangModelMsg goBangModelMsg = new GoBangModelMsg();
        goBangModelMsg.setX(x);
        goBangModelMsg.setY(y);
        goBangModelMsg.setUser(getUser());
        //发送消息
        Client instance = Client.getInstance();
        instance.getFuture()
                .channel().writeAndFlush(goBangModelMsg);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    /**
     * 主要用来画棋子
     */
    public class QiPanJPanel extends JPanel {

        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            //设置画笔颜色
            g2.drawImage(image, 0, 0, 1600, 900, null);
            g2.setColor(Color.CYAN); //填充规定区域
            g2.fill3DRect(50, 50, 700, 700, true);
            g2.setColor(Color.black);
            // 绘制下棋区域
            for (int i = 1; i < 16; i++) {
                g2.drawLine(50, 50 * i, 750, 50 * i);
            }
            for (int i = 1; i < 16; i++) {
                g2.drawLine(50 * i, 50, 50 * i, 750);
            } //画外框 //设置画笔宽度
            g2.setStroke(new BasicStroke(5));
            g2.drawLine(25, 25, 775, 25);
            g2.drawLine(25, 775, 775, 775);
            g2.drawLine(25, 25, 25, 775);
            g2.drawLine(775, 25, 775, 775); //画四个黑色圆
            for (int x = 0; x < 15; x++) {
                for (int y = 0; y < 15; y++) {
                    GoBangModelMsg[][] qipan = coordinate;
                    if (qipan[x][y] != null) {
                        GoBangModelMsg bangModel = qipan[x][y];
                        g2.setColor(bangModel.getUser().getColor());
                        g2.fillOval((x + 1) * 50 - 10, (y + 1) * 50 - 10, 20, 20);
                    }
                }
            }
            g2.setColor(new Color(176, 166, 100, 254));
            //设置画笔颜色
            g2.fillRect(26, 26, 749, 23);
            g2.fillRect(26, 49, 23, 725);
            g2.fillRect(49, 752, 725, 23);
            g2.fillRect(752, 49, 23, 703);
        }
    }

}
