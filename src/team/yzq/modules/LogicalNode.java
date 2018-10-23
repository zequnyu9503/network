package team.yzq.modules;

import team.yzq.Interface.LogicalNodeTask;
import team.yzq.net.Starter;
import team.yzq.tables.ShortTable;
import team.yzq.tables.ShortTableMap;

/**
 * @Author: YZQ
 * @Date: 2018/10/16
 */
public class LogicalNode implements Runnable, LogicalNodeTask {

    public LogicalNode(AddressPrefix aPrefix){
        this(aPrefix,null,null);
    }

    public LogicalNode(AddressPrefix aPrefix, String alias){
        this(aPrefix);
        this.alias = alias;
    }

    public LogicalNode(AddressPrefix aPrefix, ShortTable sTable, ShortTableMap sTableMap) {
        this.aPrefix = aPrefix;
        this.sTable = sTable;
        this.sTableMap = sTableMap;
        this.data = null;
    }

    private String alias;                                        //逻辑节点别名
    private AddressPrefix aPrefix;                     //逻辑节点地址前缀
    private ShortTable sTable;                           //短接表
    private ShortTableMap sTableMap;          //短接表映射
    private Data data;                                          //数据包

    public AddressPrefix getaPrefix() {
        return aPrefix;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public ShortTable getsTable() {
        return sTable;
    }

    public ShortTableMap getsTableMap() {
        return sTableMap;
    }

    public void setsTable(ShortTable sTable) {
        this.sTable = sTable;
    }

    public void setsTableMap(ShortTableMap sTableMap) {
        this.sTableMap = sTableMap;
    }

    @Override
    public void run() {
        receive();
        if(data != null){
            checkAddressPrefix();
            send();
        }
    }

    @Override
    public synchronized void receive() {
        //数据包传输过程中无法接收
        if(TransmitSimulation.isTransmiting())
            return;
        if(aPrefix.isPrefixEqual(TransmitSimulation.getNextAddressPrefix())){
            data = TransmitSimulation.getData();
            TransmitSimulation.setCurrentAddressPrefix(aPrefix);
            TransmitSimulation.setData(null);
            TransmitSimulation.setTransmiting(false);
            System.out.println("当前节点: " + (this.alias != null ? this.alias : "")  +" [" + aPrefix.toString(".")+"] 获取数据包 " + data.getCurrentAddressPrefix().toString("."));
            if(this.aPrefix.isPrefixEqual(data.getCurrentAddressPrefix())){
                if(data.isOriginData()){
                    //确认原始数据包
                    confirm();
                }else{
                    //拆解数据包
                    dataDecode();
                }
            }
        }//获取到数据包
    }

    @Override
    public synchronized void confirm() {
        if(Starter.isDEBUG) System.out.println("数据包到达逻辑节点终点");
        TransmitSimulation.clear();
        this.data = null;
    }

    @Override
    public synchronized void drop() {
        // TO DO
    }

    @Override
    public synchronized void send() {
        TransmitSimulation.setData(this.data);
        TransmitSimulation.setTransmiting(true);
        this.data = null;
    }

    @Override
    public AddressPrefix checkShortTable() {
        return sTable != null ? sTable.isShortMatch(data.getCurrentAddressPrefix(), null) : null;
    }

    @Override
    public synchronized AddressPrefix checkShortTableMap() {
        return sTableMap != null ? sTableMap.isShortMatch(data.getCurrentAddressPrefix()) : null;
    }

    @Override
    public synchronized void checkAddressPrefix() {
        AddressPrefix nAddressPrefix = checkShortTable();
        if(nAddressPrefix != null){
            //短接信道
            TransmitSimulation.setNextAddressPrefix(nAddressPrefix);
        }else{
            //树信道
            AddressPrefix dAddressPrefix = checkShortTableMap();
            if(dAddressPrefix != null){
                //短接映射表查到匹配记录,扩展包头
                dataCode(dAddressPrefix);
            }
            if(data.getCurrentAddressPrefix().getPrefixLength() > aPrefix.getPrefixLength() &&
            data.getCurrentAddressPrefix().isPrefixMatch(aPrefix)){
                //如果当前地址前缀长度小于目标地址前缀长度,同时前缀匹配成功才能下行转发
                deliverDown();
            }else{
                deliverUp();
            }
        }
    }

    @Override
    public synchronized void deliverUp() {
        TransmitSimulation.setNextAddressPrefix(aPrefix.getAddressPrefix(aPrefix.getaPrefix().size() - 1));
    }

    @Override
    public synchronized void deliverDown() {
        AddressPrefix cPrefix = data.getCurrentAddressPrefix();
        TransmitSimulation.setNextAddressPrefix(cPrefix.getAddressPrefix(aPrefix.getaPrefix().size() + 1));
    }

    @Override
    public synchronized void dataCode(AddressPrefix addressPrefix) {
        if(data == null) return;
        this.data = data.DataCode(addressPrefix);
    }

    @Override
    public synchronized void dataDecode() {
        if(data == null) return ;
        this.data = data.DataDecode();
    }
}
