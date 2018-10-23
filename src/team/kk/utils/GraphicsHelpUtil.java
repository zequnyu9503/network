package team.kk.utils;

import team.kk.mode.DataNode;
import team.kk.mode.Line;
import team.kk.mode.NetNode;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * 画笔帮助类
 * @Author: wugui
 * @Date 2018-6-2 16:48
 */
public class GraphicsHelpUtil {
    private GraphicsHelpUtil(){}

    /**
     * 设置线条宽度与拐角属性
     * @param g2d
     * @param width 线条宽度
     */
    public static void setStroke(Graphics2D g2d, int width){
        g2d.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    /**
     * 绘制空心圆
     * @param g2d
     * @param x 圆心x
     * @param y 圆心y
     * @param r 圆心半径
     */
    public static void strokeCircle(Graphics2D g2d, int x, int y, int r){
        Ellipse2D ellipse2D =new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
        g2d.draw(ellipse2D);
    }

    /**
     * 绘制实心圆
     * @param g2d
     * @param x 圆心x
     * @param y 圆心y
     * @param r 半径
     */
    public static void fillCircle(Graphics2D g2d, int x, int y, int r){
        Ellipse2D ellipse2D = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
        g2d.fill(ellipse2D);
    }

    public static void drawNetNode(Graphics2D g2d, NetNode netNode){
        Color temp = g2d.getColor();
        // 画点
        g2d.setColor(netNode.getColor());
        Ellipse2D ellipse2D = new Ellipse2D.Double(netNode.getX()-netNode.getR(),
                netNode.getY()-netNode.getR(), netNode.getR() * 2, netNode.getR() * 2);
        g2d.fill(ellipse2D);

        // 的名字
        g2d.setColor(Color.white);
        String text = netNode.getName();
        Font font=new Font("宋体",Font.PLAIN,22);
        g2d.setFont(font);

        FontMetrics fm = g2d.getFontMetrics(font);
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        g2d.drawString(text, netNode.getX() - textWidth / 2, netNode.getY() + textHeight / 3);
        g2d.setColor(temp);
    }
    public static void drawLine(Graphics2D g2d, Line line){
        Color temp = g2d.getColor();
        Stroke   st   =   g2d.getStroke();
        Stroke   bs;

        //LINE_TYPE_DASHED
        if(line.isBrokenLine()) {
            bs = new BasicStroke(line.getWidth(), BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_BEVEL, 0,
                    new float[]{8, 4}, 0);
        } else {
            bs = new BasicStroke(line.getWidth());
        }


        // 画线
        g2d.setColor(line.getColor());
        g2d.setStroke(bs);
        g2d.drawLine(line.getForm().getX(),
                line.getForm().getY(),
                line.getTo().getX(),
                line.getTo().getY());
        g2d.setStroke(st);
        g2d.setColor(temp);
    }

    public static void drawDataNode(Graphics2D g2d, DataNode dataNode){
        Color temp = g2d.getColor();
        g2d.setColor(dataNode.getColor());

        Ellipse2D ellipse2D = new Ellipse2D.Double(dataNode.getX()-dataNode.getR(),
                dataNode.getY()-dataNode.getR(), dataNode.getR() * 2, dataNode.getR() * 2);
        g2d.fill(ellipse2D);

        g2d.setColor(temp);
    }



//    public static void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2){
//        Line2D line2D = new Line2D.Double(x1, y1, x2, y2);
//        g2d.drawOval();
//    }
}
