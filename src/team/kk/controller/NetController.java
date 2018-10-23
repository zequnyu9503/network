package team.kk.controller;


import team.kk.frame.NetFrame;
import team.kk.frame.NetNodeFrame;
import team.kk.frame.ReceivedFrame;
import team.kk.mode.DataNode;
import team.kk.mode.Line;
import team.kk.mode.NetNode;
import team.kk.utils.NetNodeHelp;
import team.yzq.modules.AddressPrefix;
import team.yzq.modules.LogicalNode;
import team.yzq.modules.TransmitSimulation;
import team.yzq.net.Starter;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class NetController {
    private NetFrame netFrame;
    private int width;
    private int height;
    private List<NetNode> netNodes;
    private List<Line> lines;
    private List<DataNode> dataNodes;
    private Starter starter;
    private NetNode sendTo;
    private boolean isRun = true;
    private boolean isShowReceived = false;

    /**
     *
     * @param width 窗口宽度
     * @param height 窗口高度
     */
    public NetController(int width, int height) {
//        this.count = count;
        this.width = width;
        this.height = height;
    }

    /**
     * 启动
     */
    public void start(){
        EventQueue.invokeLater(()->{
            initData();
            netFrame = new NetFrame("层次网络模拟" ,width,height);
            netFrame.addKeyListener(new AlgoKey());
            netFrame.addMouseListener(new AlgoMouse());
            new Thread(()->{
                run();
            }).start();
            netFrame.setVisible(true);
        });
    }

    /**
     * 初始化数据  包括 开始坐标 速度 半径
     */
    private void initData(){
        // starter 为网络运行模拟器
        starter = new Starter();
        starter.init();
        starter.start();


        netNodes = new ArrayList<>();
        lines = new ArrayList<>();
        dataNodes = new ArrayList<>();
        String[] nodeName= {"A'", "B'", "C'", "D'", "E'", "F'", "G'", "A", "B", "C", "D", "E", "F", "G",
                "H", "I", "J", "K", "L", "M"};
        int[] nodex = {275, 175, 375, 125, 225, 325, 425,
                875, 775, 875, 975, 675, 775, 875, 975, 1075, 625, 725, 925, 1025};
        int[] nodey = {150, 350, 350, 550, 550, 550, 550,
                50, 200, 200, 200, 350, 350, 350, 350, 350, 550, 550, 550, 550};
        int[] lineFrom = {0, 0, 1, 1, 2, 2,
                7, 7, 7, 8, 8, 8, 10, 10, 11, 11, 14, 14};
        int[] lineTo =   {1, 2, 3, 4, 5, 6,
                8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        // 创建节点视图
        // 图形网络节点关联 模拟逻辑节点
        for(LogicalNode ln: starter.getLogicalNodes()){
            netNodes.add(new NetNode(ln));
        }
        int i = 0;
        for (NetNode nn: netNodes){
            nn.setName(nodeName[i]);
            nn.setX(nodex[i]);
            nn.setY(nodey[i]);
            nn.setR(30);
            i ++;
        }
        // 连线树信道
        for(int j=0; j < lineFrom.length; j ++){
            lines.add(new Line(netNodes.get(lineFrom[j]), netNodes.get(lineTo[j])));
        }
        //短接信道
        Line ltemp = new Line(netNodes.get(2), netNodes.get(11));
        ltemp.setBrokenLine(true);
        lines.add(ltemp);

        Line ltemp2 = new Line(netNodes.get(7), netNodes.get(14));
        ltemp2.setBrokenLine(true);
        lines.add(ltemp2);




        //设置数据包出发位置
//        TransmitSimulation.setNextAddressPrefix(NetNodeHelp.getNetNodeByName(netNodes, "D'").
//                getLogicalNode().getaPrefix());
//
//        //设置数据包目标逻辑节点
//        Stack<AddressPrefix> stack = new Stack<>();
//        stack.push(NetNodeHelp.getNetNodeByName(netNodes, "I").
//                getLogicalNode().getaPrefix());
//        Data data = new Data(stack, null, "123");
//        TransmitSimulation.setData(data);


        // 数据点
//        System.out.println(TransmitSimulation.getCurrentAddressPrefix());
//        NetNode nnTemp = NetNodeHelp.getNetNodeByAddressPrefix(netNodes,
//                TransmitSimulation.getCurrentAddressPrefix());
//        System.out.println(nnTemp);
        // 创建数据节点
        dataNodes.add(new DataNode(0));
//        dataNodes.get(0).setColor(Color.blue);

    }

    /**
     * 控制数据小球运动
     */
    private void run(){
        try {
            while (true) {
                if(isRun){
                    dataMove();
                }
                this.netFrame.rePaint(netNodes, lines, dataNodes);
                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void dataMove() {
        for(DataNode dn : dataNodes){
            if(!dn.isMoving()){
                if(TransmitSimulation.getCurrentAddressPrefix() != null &&
                        TransmitSimulation.getNextAddressPrefix() != null) {
                    NetNode nnfrom = NetNodeHelp.getNetNodeByAddressPrefix(netNodes,
                            TransmitSimulation.getCurrentAddressPrefix());
                    AddressPrefix add = TransmitSimulation.getNextAddressPrefix();
                    NetNode nnto = NetNodeHelp.getNetNodeByAddressPrefix(netNodes,
                            TransmitSimulation.getNextAddressPrefix());
                    dn.setMove(nnfrom, nnto);

                    TransmitSimulation.setTransmiting(false);
                } else {
                    TransmitSimulation.setTransmiting(false);
                }

                if(sendTo != null) {
                    if(isShowReceived) {
                        if (position1(dn, sendTo.getX(), sendTo.getY())) {
                            ReceivedFrame receivedFrame = new ReceivedFrame(sendTo, dataNodes.get(0).getData().getDataStr());
                            isShowReceived = false;
                        }
                    }
                }

            }else {
                if (dn.getForm() != null && dn.getTo() != null) {
                    dn.move();
                    isShowReceived = true;
                    //TransmitSimulation.setTransmiting(false);
                }
            }
        }
    }

    /**
     * 键盘监听事件
     */
    class AlgoKey extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            if(e.getKeyChar() == ' '){

                isRun = ! isRun;
            }
        }
    }
    /**
     * 鼠标监听事件
     */
    class AlgoMouse extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println();
            super.mousePressed(e);
            for (NetNode i : netNodes){
                if (position(i, (int)e.getPoint().getX(), (e.getY() - netFrame.getBounds().height + height))) {

                    // 使用节点的信息初始化窗口
                    NetNodeFrame netNodeFrame = new NetNodeFrame(i) {
                        @Override
                        // 点击发送按钮后需要执行的动作
                        public void sendAction() {

                            NetNode nnto = NetNodeHelp.getNetNodeByName(netNodes, this.getSendTo());
                            sendTo = nnto;
                            NetNodeHelp.sendDataByNode(i, nnto, dataNodes.get(0), this.getSendData());

                        }
                    };
                }
            }
        }

        /**
         * 计算点击点是否在圈内
         * @param circle
         * @param x 点击坐标
         * @param y 点击坐标
         * @return 是 为true
         */
        private boolean position(NetNode circle, int x, int y){
            return (circle.getX()-x) * (circle.getX()-x) + (circle.getY()-y) * (circle.getY()-y) < circle.getR() * circle.getR();
        }
    }

    private boolean position1(DataNode circle, int x, int y){
        return (circle.getX()-x) * (circle.getX()-x) + (circle.getY()-y) * (circle.getY()-y) < circle.getR() * circle.getR();
    }
}

