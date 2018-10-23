package team.yzq.app;
import team.yzq.net.Starter;

public class Main {

    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.init();
        starter.start();

//        //短接表测试 2018/10/17
//        LogicalNode node_A1 = new LogicalNode(new AddressPrefix("1","\\."),null,null);
//        LogicalNode node_B1 = new LogicalNode(new AddressPrefix("3.1.2","\\."),null,null);
//        LogicalNode node_B2 = new LogicalNode(new AddressPrefix("3.1","\\."),null,null);
//        ShortTable shortTableA1 = new ShortTable();
//        shortTableA1.addShort(node_B1.getaPrefix(),new ArrayList<AddressPrefix>(){{ add(node_B1.getaPrefix()); add(node_B2.getaPrefix()); }});
//        node_A1.setsTable(shortTableA1);
//
//        System.out.println(shortTableA1.isShortMatch(new AddressPrefix("3.1.2.2","\\."),
//                new AddressPrefix("3.1.2","\\.")).toString("."));



    }
}
