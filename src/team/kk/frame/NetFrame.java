package team.kk.frame;

import team.kk.mode.DataNode;
import team.kk.mode.Line;
import team.kk.mode.NetNode;
import team.kk.utils.GraphicsHelpUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 展示窗口
 */
public class NetFrame extends JFrame {
    public static int canvasWidth;
    public static int canvasHeight;
    private java.util.List<NetNode> netNodes;
    private java.util.List<Line> lines;
    private java.util.List<DataNode> dataNodes;
    /**
     * 自定义构造函数
     * @param title jFrame 标题
     * @param canvasWidth 面板长度
     * @param canvasHeight 面板高度
     */
    public NetFrame(String title, int canvasWidth, int canvasHeight){
        super(title);
        this.setLayout(null);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        NetJPanl netJPanl = new NetJPanl();
        netJPanl.setBackground(Color.white);
        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("关于我们");
        JMenuItem item = new JMenuItem("关于我们");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon imageIcon = new ImageIcon ("src/team/kk/source/net.png");
                JOptionPane.showMessageDialog(null, "小组成员：\nS201861422 于泽群" +
                        "\nS201861442 康康\nS201861420 赵国帅\nS201861416 张梦隆", "关于",
                        JOptionPane.PLAIN_MESSAGE, imageIcon);
            }
        });
        m.add(item);
        mb.add(m);
        mb.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setJMenuBar(mb);
        this.setContentPane(netJPanl);
        this.pack();
        this.setLocationRelativeTo(getOwner());
    }
    public void rePaint(java.util.List<NetNode> netNodes, java.util.List<Line> lines, java.util.List<DataNode> dataNodes){
        this.netNodes = netNodes;
        this.lines = lines;
        this.dataNodes = dataNodes;
        repaint();
    }

    /**
     * 画板 绘制结点和连线
     */
    private class NetJPanl extends JPanel{

        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.addRenderingHints(hints);

            //设置背景
            //g2d.setBackground(new Color(255,255,255));
            // 画线
            for(Line l: lines){
                GraphicsHelpUtil.drawLine(g2d, l);
            }
            // 画网络节点
            for(NetNode i : netNodes){
                GraphicsHelpUtil.drawNetNode(g2d, i);
            }
            // 画数据点
            for(DataNode dn: dataNodes) {
                GraphicsHelpUtil.drawDataNode(g2d, dn);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
