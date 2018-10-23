package team.kk.mode;

import java.awt.*;

public class Line {
    private NetNode form;
    private NetNode to;
    private Color color;
    private float width = 4.0f;
    private boolean isBrokenLine;

    public Line(NetNode form, NetNode to, Color color, float width, boolean isBrokenLine) {
        this.form = form;
        this.to = to;
        this.color = color;
        this.width = width;
        this.isBrokenLine = isBrokenLine;
    }

    public Color getColor() {
        return color;
    }

    public Line(NetNode form, NetNode to, Color color, float width) {
        this.form = form;
        this.to = to;
        this.color = color;
        this.width = width;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Line(NetNode form, NetNode to) {
        this.form = form;
        this.to = to;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public boolean isBrokenLine() {
        return isBrokenLine;
    }

    public void setBrokenLine(boolean brokenLine) {
        isBrokenLine = brokenLine;
    }

    public NetNode getForm() {
        return form;
    }

    public NetNode getTo() {
        return to;
    }

    public void setForm(NetNode form) {
        this.form = form;
    }

    public void setTo(NetNode to) {
        this.to = to;
    }
}
