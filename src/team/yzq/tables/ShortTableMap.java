package team.yzq.tables;

import team.yzq.constant.ChannelStatus;
import team.yzq.modules.AddressPrefix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: YZQ
 * @Date: 2018/10/16
 */
public class ShortTableMap {

    public ShortTableMap(){
        directShortPrefix = new HashMap<>();
        shortStatus = new HashMap<>();
    }

    private HashMap<String, List<AddressPrefix>>directShortPrefix;
    private HashMap<String, Integer> shortStatus;

    /*
        *@Method 向逻辑节点添加短接映射, 短接映射表至少包括对方的直接短接节点
     */
    public void setShortTableMap(List<AddressPrefix> directShortPrefix, List<AddressPrefix> mappedPrefixs){
        directShortPrefix.forEach(shortChannel -> {
            this.directShortPrefix.put(shortChannel.toString("."),mappedPrefixs);
            this.shortStatus.put(shortChannel.toString("."),ChannelStatus.STATUS_ON);
        });
    }

    /*
        *@Method 短接映射表匹配地址前缀
        * @Return 本侧直接短接节点地址前缀
     */
    public AddressPrefix isShortMatch(AddressPrefix addressPrefix){
        if(directShortPrefix == null || addressPrefix == null) return null;
        for(Map.Entry<String,List<AddressPrefix>> kv : directShortPrefix.entrySet()){
            if(Integer.parseInt(shortStatus.get(kv.getKey()).toString()) == ChannelStatus.STATUS_ON){
                for(AddressPrefix aPrefix : kv.getValue()){
                    if(aPrefix.isPrefixMatch(addressPrefix))
                        return new AddressPrefix(kv.getKey(),"\\.");
                }
            }
        }
        return null;
    }
}
