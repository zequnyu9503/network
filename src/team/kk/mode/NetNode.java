package team.kk.mode;

import team.yzq.modules.LogicalNode;

import java.awt.*;

public class NetNode {
    private String name;
    private int x,y;    //坐标
    private int r;      // 半径
    private boolean isFIll; //是否为实心
    private Color color;

    private LogicalNode logicalNode;    // 逻辑节点

    public NetNode() {
    }

    public NetNode(LogicalNode logicalNode) {
        this.logicalNode = logicalNode;
    }

    public boolean isFIll() {
        return isFIll;
    }

    public void setFIll(boolean FIll) {
        isFIll = FIll;
    }

    public NetNode(String name, int x, int y, int r) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public NetNode(String name, int x, int y, int r, boolean isFIll, Color color) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.r = r;
        this.isFIll = isFIll;
        this.color = color;
    }

    public LogicalNode getLogicalNode() {
        return logicalNode;
    }

    public void setLogicalNode(LogicalNode logicalNode) {
        this.logicalNode = logicalNode;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setR(int r) {
        this.r = r;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
