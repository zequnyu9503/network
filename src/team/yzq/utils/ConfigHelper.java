package team.yzq.utils;

import team.yzq.modules.AddressPrefix;
import team.yzq.modules.Data;
import team.yzq.modules.LogicalNode;
import team.yzq.modules.TransmitSimulation;
import team.yzq.tables.ShortTable;
import team.yzq.tables.ShortTableMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: YZQ
 * @Date: 2018/10/20
 */
public class ConfigHelper {

    /*
        *@Method 只有一个短接信道的逻辑节点装配短接表
        * @Parameter dNode 本侧直接短接节点
        * @Parameter dNode 对侧直接短接节点
        * @Parameter nodes 对方扩展短接节点
     */
    public void setShort(LogicalNode dNode,LogicalNode rNode, List<LogicalNode> nodes){
        ShortTable shortTable = new ShortTable();
        List<AddressPrefix> addressPrefixes = new ArrayList<AddressPrefix>();
        for(LogicalNode n : nodes){
            addressPrefixes.add(n.getaPrefix());
        }
        shortTable.addShort(rNode.getaPrefix(),addressPrefixes);
        dNode.setsTable(shortTable);
    }

    /*
     *@Method 只有一个短接信道的逻辑节点装配短接表
     * @Parameter dNode 本侧直接短接节点
     * @Parameter nodes 对方扩展短接节点,至少包含对侧直接短接节点
     */
    public void setShort(LogicalNode dNode, List<LogicalNode> nodes){
        if(nodes.size() < 1) return ;
        ShortTable shortTable = new ShortTable();
        List<AddressPrefix> addressPrefixes = new ArrayList<AddressPrefix>();
        for(LogicalNode n : nodes){
            addressPrefixes.add(n.getaPrefix());
        }
        shortTable.addShort(addressPrefixes.get(0),addressPrefixes);
        dNode.setsTable(shortTable);
    }

    /*
     *@Method 只有一个短接信道的逻辑节点装配短接映射表
     * @Parameter cNode 当前带配置映射表逻辑节点
     * @Parameter dNode 本侧直接短接节点
     * @Parameter nodes 对侧节点
     */
    public void setShortMap(LogicalNode cNode,LogicalNode dNode, List<LogicalNode> nodes){
        ShortTableMap shortTableMap = new ShortTableMap();
        List<AddressPrefix> addressPrefixes = new ArrayList<AddressPrefix>();
        for(LogicalNode n : nodes){
            addressPrefixes.add(n.getaPrefix());
        }
        shortTableMap.setShortTableMap(new ArrayList<AddressPrefix>(){{add(dNode.getaPrefix());}},addressPrefixes);
        cNode.setsTableMap(shortTableMap);
    }

    /*
        *@Method 设置数据包
        * @Parameter node 目的节点
        * @Parameter  dataStr 数据载荷
        * @Return data数据包
     */
    public Data setData(LogicalNode node, String dataStr){
        Stack<AddressPrefix> stack = new Stack<>();
        stack.push(node.getaPrefix());
        Data data = new Data(stack, null, dataStr);
        TransmitSimulation.setData(data);
        return data;
    }
}
