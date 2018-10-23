package team.kk.frame;

import team.kk.mode.NetNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class NetNodeFrame extends JFrame {

    private JLabel jlLogicalAddress1;
    private JTextField jlLogicalAddress;      // 逻辑地址前缀
    private JLabel jlto;                // 逻辑节点终点
    private JTextField jtfto;
    private JLabel jtaData1;
    private JTextField jtaData;          // 发送的数据
    private JButton jbSend;             // 发送按钮

    public NetNodeFrame(NetNode netNode){
        JFrame jf = new JFrame("节点：" + netNode.getName());          // 创建窗口
        jf.setLayout(null);
        jf.setSize(new Dimension(250, 180));                       // 设置窗口大小
        jf.setResizable(false);
        jf.setLocationRelativeTo(getOwner());
        jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBounds(0,10,250,150);
        //显示当前逻辑节点的地址前缀
        jlLogicalAddress1 = new JLabel("节点地址前缀: ");
        jlLogicalAddress1.setPreferredSize(new Dimension(90,20));
        jlLogicalAddress = new JTextField(netNode.getLogicalNode().getaPrefix().toString("."));
        jlLogicalAddress.setPreferredSize(new Dimension(110,20));
        jlLogicalAddress.setEnabled(false);

        jlto = new JLabel("逻辑节点终点 : ");
        jlto.setPreferredSize(new Dimension(90,20));
        jtfto = new JTextField();
        jtfto.setPreferredSize(new Dimension(110,20));

        jtaData1 = new JLabel("数据内容");
        jtaData1.setPreferredSize(new Dimension(90,20));
        jtaData = new JTextField();
        jtaData.setPreferredSize(new Dimension(110,20));


        JLabel space = new JLabel(" ");
        space.setPreferredSize(new Dimension(250,15));


        jbSend = new JButton("发送");
        jbSend.setPreferredSize(new Dimension(80,20));


        // 按键监听
        jbSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = jtfto.getText().toUpperCase();
                if (str.length() == 0){
                    JOptionPane.showMessageDialog(null, "请输入逻辑节点终点", "错误", JOptionPane.ERROR_MESSAGE);
                    return ;
                }
                if(((str.length() == 2) && (str.charAt(0) <= 'G' && str.charAt(0) >= 'A') && str.charAt(1) == '\'') ||
                        ((str.length() == 1) && (str.charAt(0) <= 'M' && str.charAt(0) >= 'A'))){
                    jtfto.setText(str.toUpperCase());
                    sendAction();
                    jf.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "找不到该逻辑节点", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jtfto.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        panel.add(jlLogicalAddress1);
        panel.add(jlLogicalAddress);
        panel.add(jlto);
        panel.add(jtfto);
        panel.add(jtaData1);
        panel.add(jtaData);
        panel.add(space);
        panel.add(jbSend);

        // 4. 把 面板容器 作为窗口的内容面板 设置到 窗口
        jf.add(panel);

        // 5. 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
        jf.setVisible(true);
    }

    public String getSendTo(){
        return jtfto.getText();
    }
    public String getSendData(){
        return jtaData.getText();
    }

    // 点击发送按钮后需要执行的动作
    public abstract void sendAction();

    public void initFrame(String title){



    }

    public static void main(String[] args) {

    }


}
