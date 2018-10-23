package team.kk.frame;

import team.kk.mode.NetNode;

import javax.swing.*;
import java.awt.*;

public class ReceivedFrame extends JFrame {

    private JLabel jlLogicalAddress1;
    private JTextField  jlLogicalAddress;      // 逻辑地址前缀
    private JLabel jlto;                // 接受到
    private JTextField jtfto;



    public ReceivedFrame(NetNode netNode, String data){
        JFrame jf = new JFrame("节点：" + netNode.getName());          // 创建窗口
        jf.setSize(250, 100);                       // 设置窗口大小
        jf.setResizable(false);

        jf.setLocationRelativeTo(getOwner());

        JPanel panel = new JPanel(new FlowLayout());
        jlLogicalAddress1 = new JLabel("节点地址前缀: ");
        jlLogicalAddress1.setPreferredSize(new Dimension(90,20));
        jlLogicalAddress = new JTextField(netNode.getLogicalNode().getaPrefix().toString("."));
        jlLogicalAddress.setPreferredSize(new Dimension(110,20));
        jlLogicalAddress.setEnabled(false);

        jlto = new JLabel("数据内容: ");
        jlto.setPreferredSize(new Dimension(90,20));
        jtfto = new JTextField();
        jtfto.setPreferredSize(new Dimension(110,20));
        jtfto.setText(data);
        jtfto.setEnabled(false);

        panel.add(jlLogicalAddress1);
        panel.add(jlLogicalAddress);
        panel.add(jlto);
        panel.add(jtfto);

        jf.add(panel);
        jf.setVisible(true);
    }

//    public static void main(String[] args) {
//        ReceivedFrame receivedFrame = new ReceivedFrame();
//    }
}
