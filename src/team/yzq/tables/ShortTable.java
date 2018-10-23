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
public class ShortTable {

    public ShortTable() {
        directShortPrefix = new HashMap<>();
        shortStatus = new HashMap<>();
    }

    private HashMap<String,List<AddressPrefix>> directShortPrefix;
    private HashMap<String, Integer> shortStatus;

    /*
        *@Method 向扩展短接表添加短接信道, 扩展表中至少具有直接短接节点地址前缀
     */
    public void addShort(AddressPrefix aPrefix, List<AddressPrefix> extendedShortPrefixs){
        directShortPrefix.put(aPrefix.toString("."),extendedShortPrefixs);
        shortStatus.put(aPrefix.toString("."),ChannelStatus.STATUS_ON);
    }

    /*
        *@Method 修改短接记录的状态位
     */
    public void setShortStatus(AddressPrefix aPrefix, int status){
        if(shortStatus.containsKey(aPrefix.toString(".")))
            shortStatus.put(aPrefix.toString("."),status);
    }

    /*
        *@Method 在扩展短接表中删除某条信道(逻辑或物理删除)
     */
    public void removeShort(AddressPrefix aPrefix, Boolean isLogicalDelete){
        if(isLogicalDelete)
            shortStatus.put(aPrefix.toString("."),ChannelStatus.STATUS_ON);
        else{
            directShortPrefix.remove(aPrefix.toString("."));
            shortStatus.remove(aPrefix.toString("."));
        }
    }

    /*
        *@Method 扩展短接表中匹配地址前缀
        * @Param addressPrefix 数据包目的地址前缀
        * @Param iPrefix 匹配后忽略的地址前缀
        * @Return 返回匹配项的直接短接地址前缀
     */
    public AddressPrefix isShortMatch(final AddressPrefix addressPrefix, final AddressPrefix iPrefix){
        if(directShortPrefix == null || addressPrefix == null) return null;
        for(Map.Entry<String,List<AddressPrefix>> kv : directShortPrefix.entrySet()){
            if(iPrefix != null && iPrefix.toString(".").equals(kv.getKey()))
                continue;
            if(Integer.parseInt(shortStatus.get(kv.getKey()).toString()) == ChannelStatus.STATUS_ON){
                for(AddressPrefix aPrefix : kv.getValue()){
                    if(aPrefix.isPrefixMatch(addressPrefix)){
                        return new AddressPrefix(kv.getKey(),"\\.");
                    }
                }
            }
        }
        return null;
    }

    /*
     *@Method 扩展短接表中匹配地址前缀
     * @Param addressPrefix 数据包目的地址前缀
     * @Return 返回匹配项的直接短接地址前缀
     */
    public AddressPrefix isShortMatch(final AddressPrefix addressPrefix){
        return isShortMatch(addressPrefix,null);
    }
}
