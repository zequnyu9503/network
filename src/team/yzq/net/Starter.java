package team.yzq.net;

import team.yzq.constant.ChannelStatus;
import team.yzq.constant.Regex;
import team.yzq.modules.*;
import team.yzq.utils.ConfigHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Starter {

    public static boolean isDEBUG;

    public Starter() {
        this.logicalNodes = new ArrayList<>();
    }

    private List<LogicalNode> logicalNodes;

    public List<LogicalNode> getLogicalNodes() {
        return logicalNodes;
    }

    public void start() {
        try {
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            for ( LogicalNode node : logicalNodes) {
                executor.scheduleAtFixedRate(node,0,50,TimeUnit.MILLISECONDS);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void init(){
        LogicalNode node_A1 = new LogicalNode(new AddressPrefix("1",Regex.DEFAULT),"A1");
        logicalNodes.add(node_A1);
        LogicalNode node_B1 = new LogicalNode(new AddressPrefix("1.1",Regex.DEFAULT),"B1");
        logicalNodes.add(node_B1);
        LogicalNode node_C1 = new LogicalNode(new AddressPrefix("1.2",Regex.DEFAULT),"C1");
        logicalNodes.add(node_C1);
        LogicalNode node_D1 = new LogicalNode(new AddressPrefix("1.1.1",Regex.DEFAULT),"D1");
        logicalNodes.add(node_D1);
        LogicalNode node_E1 = new LogicalNode(new AddressPrefix("1.1.2",Regex.DEFAULT),"E1");
        logicalNodes.add(node_E1);
        LogicalNode node_G1 = new LogicalNode(new AddressPrefix("1.2.1",Regex.DEFAULT),"G1");
        logicalNodes.add(node_G1);
        LogicalNode node_F1 = new LogicalNode(new AddressPrefix("1.2.2",Regex.DEFAULT),"F1");
        logicalNodes.add(node_F1);
        LogicalNode node_A2 = new LogicalNode(new AddressPrefix("2",Regex.DEFAULT),"A2");
        logicalNodes.add(node_A2);
        LogicalNode node_B2 = new LogicalNode(new AddressPrefix("2.1",Regex.DEFAULT),"B2");
        logicalNodes.add(node_B2);
        LogicalNode node_C2 = new LogicalNode(new AddressPrefix("2.2",Regex.DEFAULT),"C2");
        logicalNodes.add(node_C2);
        LogicalNode node_D2 = new LogicalNode(new AddressPrefix("2.3",Regex.DEFAULT),"D2");
        logicalNodes.add(node_D2);
        LogicalNode node_E2 = new LogicalNode(new AddressPrefix("2.1.1",Regex.DEFAULT),"E2");
        logicalNodes.add(node_E2);
        LogicalNode node_F2 = new LogicalNode(new AddressPrefix("2.1.2",Regex.DEFAULT),"F2");
        logicalNodes.add(node_F2);
        LogicalNode node_G2 = new LogicalNode(new AddressPrefix("2.1.3",Regex.DEFAULT),"G2");
        logicalNodes.add(node_G2);
        LogicalNode node_H2 = new LogicalNode(new AddressPrefix("2.3.1",Regex.DEFAULT),"H2");
        logicalNodes.add(node_H2);
        LogicalNode node_I2 = new LogicalNode(new AddressPrefix("2.3.2",Regex.DEFAULT),"I2");
        logicalNodes.add(node_I2);
        LogicalNode node_J2 = new LogicalNode(new AddressPrefix("2.1.1.1",Regex.DEFAULT),"J2");
        logicalNodes.add(node_J2);
        LogicalNode node_K2 = new LogicalNode(new AddressPrefix("2.1.1.2",Regex.DEFAULT),"K2");
        logicalNodes.add(node_K2);
        LogicalNode node_L2 = new LogicalNode(new AddressPrefix("2.3.1.1",Regex.DEFAULT),"L2");
        logicalNodes.add(node_L2);
        LogicalNode node_M2 = new LogicalNode(new AddressPrefix("2.3.1.2",Regex.DEFAULT),"M2");
        logicalNodes.add(node_M2);

        ConfigHelper helper = new ConfigHelper();

        //在C1节点配置短接表
        helper.setShort(node_C1,node_E2,new ArrayList<LogicalNode>(){{ add(node_E2);add(node_A2); }});

//        ShortTable shortTable_C1_E2 = new ShortTable();
//        shortTable_C1_E2.addShort(node_E2.getaPrefix(),new ArrayList<AddressPrefix>(){{
//            add(node_E2.getaPrefix());  //对方子树的直接短接节点地址前缀
//            add(node_A2.getaPrefix());  //对方子树的根节点地址前缀
//        }});
//        node_C1.setsTable(shortTable_C1_E2);

        //在E2节点配置短接表
        helper.setShort(node_E2,node_C1,new ArrayList<LogicalNode>(){{ add(node_C1);add(node_A1); }});

//        ShortTable shortTable_E2_C1 = new ShortTable();
//        shortTable_E2_C1.addShort(node_C1.getaPrefix(),new ArrayList<AddressPrefix>(){{
//            add(node_C1.getaPrefix());  //对方子树的直接短接节点地址前缀
//            add(node_A1.getaPrefix());  //对方子树的根节点地址前缀
//        }});
//        node_E2.setsTable(shortTable_E2_C1);

        //在A2节点配置短接表
        helper.setShort(node_A2,node_H2,new ArrayList<LogicalNode>(){{ add(node_H2);}});

//        ShortTable shortTable_A2_H2 = new ShortTable();
//        shortTable_A2_H2.addShort(node_H2.getaPrefix(),new ArrayList<AddressPrefix>(){{
//            add(node_H2.getaPrefix());
//        }});
//        node_A2.setsTable(shortTable_A2_H2);

        //在H2节点配置短接表
        helper.setShort(node_H2,node_A2,new ArrayList<LogicalNode>(){{ add(node_A2);}});
        node_H2.getsTable().setShortStatus(node_A2.getaPrefix(),ChannelStatus.STATUS_OFF);
//        ShortTable shortTable_H2_A2 = new ShortTable();
//        shortTable_H2_A2.addShort(node_A2.getaPrefix(),new ArrayList<AddressPrefix>(){{
//            add(node_A2.getaPrefix());
//        }});
//        shortTable_H2_A2.setShortStatus(node_A2.getaPrefix(),ChannelStatus.STATUS_OFF);
//        node_H2.setsTable(shortTable_H2_A2);

        //在A1节点配置短接映射表
        helper.setShortMap(node_A1,node_C1,new ArrayList<LogicalNode>(){{ add(node_A2); add(node_E2); }});

//        ShortTableMap shortTableMap_C1 = new ShortTableMap();
//        shortTableMap_C1.setShortTableMap(new ArrayList<AddressPrefix>(){{
//            add(node_C1.getaPrefix());
//        }},new ArrayList<AddressPrefix>(){{
//            add(node_E2.getaPrefix());
//            add(node_A2.getaPrefix());
//        }});
//        node_A1.setsTableMap(shortTableMap_C1);


        //在B2节点配置短接映射表
        helper.setShortMap(node_B2,node_E2,new ArrayList<LogicalNode>(){{ add(node_A1); add(node_C1); }});
        //在A2节点配置短接映射表
        helper.setShortMap(node_A2,node_E2,new ArrayList<LogicalNode>(){{ add(node_A1); add(node_C1); }});

//        ShortTableMap shortTableMap_E2 = new ShortTableMap();
//        shortTableMap_E2.setShortTableMap(new ArrayList<AddressPrefix>(){{
//            add(node_E2.getaPrefix());
//        }},new ArrayList<AddressPrefix>(){{
//            add(node_C1.getaPrefix());
//            add(node_A1.getaPrefix());
//        }});
//        node_B2.setsTableMap(shortTableMap_E2);
//        node_A2.setsTableMap(shortTableMap_E2);

//        //设置数据包出发位置
//        TransmitSimulation.setNextAddressPrefix(node_D1.getaPrefix());
//
//        //设置数据包目标逻辑节点
//        Data data = helper.setData(node_M2,"This is a test message");
////        Stack<AddressPrefix> stack = new Stack<>();
////        stack.push(node_D2.getaPrefix());
////        Data data = new Data(stack, null, "123");
//        TransmitSimulation.setData(data);
    }

    static {
        isDEBUG = true;
    }
}
