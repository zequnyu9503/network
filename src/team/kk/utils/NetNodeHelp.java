package team.kk.utils;


import team.kk.mode.DataNode;
import team.kk.mode.NetNode;
import team.yzq.modules.AddressPrefix;
import team.yzq.modules.Data;
import team.yzq.modules.TransmitSimulation;

import java.util.List;
import java.util.Stack;

/**
 * 网络节点的一些处理方法
 */
public class NetNodeHelp {

    public static NetNode getNetNodeByAddressPrefix(List<NetNode> netNodes, AddressPrefix apf){
        for (NetNode nn: netNodes) {
            if(nn.getLogicalNode().getaPrefix().isPrefixEqual(apf)){
                return nn;
            }
        }
        return null;
    }

    public static NetNode getNetNodeByName(List<NetNode> netNodes, String name){
        for (NetNode nn: netNodes) {
            if(nn.getName().equals(name)){
                return nn;
            }
        }
        return null;
    }

    public static void sendDataByNode(NetNode fromNode, NetNode toNode, DataNode dataNode, String Data){
        TransmitSimulation.setNextAddressPrefix(fromNode.
                getLogicalNode().getaPrefix());
        //设置数据包目标逻辑节点
        Stack<AddressPrefix> stack = new Stack<>();
        stack.push(toNode.
                getLogicalNode().getaPrefix());
        Data data = new Data(stack, null, Data);
        TransmitSimulation.setData(data);
        dataNode.setData(data);
        dataNode.setR(10);
    }
}