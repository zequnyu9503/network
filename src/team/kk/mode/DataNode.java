package team.kk.mode;

import team.yzq.modules.Data;

import java.awt.*;

/**
 * 表示数据流动的结点
 */
public class DataNode {
    private double x;
    private double y;

    private int r = 10;
    private NetNode form;
    private NetNode to;

    private Color color = new Color(0xFF881A);
    private boolean isMoving;
    private Data data;
    // 默认为1
    private float speed = 1.0f;
    public NetNode getForm() {
        return form;
    }

    public void setForm(NetNode form) {
        this.form = form;
    }

    public NetNode getTo() {
        return to;
    }

    public void setTo(NetNode to) {
        this.to = to;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public DataNode(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public DataNode(int r) {
        this.r = r;
    }

    public DataNode(NetNode form) {
        this.form = form;
        this.x = form.getX();
        this.y = form.getY();
    }

    public DataNode(int x, int y, int r, NetNode form, NetNode to, Color color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.form = form;
        this.to = to;
        this.color = color;
    }

    public void setMove(NetNode form, NetNode to){
        this.x = form.getX();
        this.y = form.getY();

        this.form = form;
        this.to = to;
        this.isMoving = true;
    }
    public void move(){

        double vx =(this.to.getX() - this.form.getX()) / 100.0 * speed;
        double vy =(this.to.getY() - this.form.getY()) / 100.0 * speed;

        if((this.to.getX() - this.x) * (this.to.getX() - this.x)
                + (this.to.getY() - this.y) * (this.to.getY() - this.y)
                < (r * r) / 4){
            isMoving = false;
        } else {
            isMoving = true;
        }
        if(isMoving) {
            x += vx;
            y += vy;
        }
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
