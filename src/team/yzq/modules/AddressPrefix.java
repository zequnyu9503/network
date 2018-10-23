package team.yzq.modules;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YZQ
 * @Date: 2018/10/16
 */
public class AddressPrefix {

    private List<Integer> aPrefix;

    @Deprecated
    public AddressPrefix (String aPrefixStr, String regex){
        final String [] aPrefixStrs = aPrefixStr.split(regex);
        aPrefix = new ArrayList<Integer>(){{
            //No check
            for(String aPrefix : aPrefixStrs){
                add(Integer.parseInt(aPrefix));
            }
        }};
    }

    public AddressPrefix(List<Integer> aPrefix){
        this.aPrefix = aPrefix;
    }

    public List<Integer> getaPrefix() {
        return aPrefix;
    }

    public String toString(String regex){
        StringBuilder stringBuilder = new StringBuilder();
        aPrefix.forEach( item -> stringBuilder.append(regex).append(item));
        return stringBuilder.substring(1).toString();
    }

    public void setaPrefix(List<Integer> aPrefix) {
        this.aPrefix = aPrefix;
    }

    public void setaPrefix(AddressPrefix aPrefix){
        this.aPrefix = aPrefix.getaPrefix();
    }

    /*
        *@Method 地址前缀比较(部分匹配)
     */
    public boolean isPrefixMatch(AddressPrefix addressPrefix){
        if(addressPrefix == null) return false;
        for(int index = 0, len = Math.min(aPrefix.size(),addressPrefix.getaPrefix().size()); index < len; ++index){
            if(!aPrefix.get(index).equals(addressPrefix.getaPrefix().get(index)))
                return false;
        }
        return true;
    }

    /*
         *@Method 地址前缀比较(完全比较)
     */
    public boolean isPrefixEqual(AddressPrefix addressPrefix){
        if(addressPrefix == null) return false;
        if(aPrefix.size() != addressPrefix.getaPrefix().size())
            return false;
        for(int index =0, len = aPrefix.size(); index < len ; ++index) {
            if(!aPrefix.get(index).equals(addressPrefix.getaPrefix().get(index)))
                return false;
        }
        return true;
    }

    public AddressPrefix getAddressPrefix(int len){
        final List<Integer> newPrefix = aPrefix;
        return new AddressPrefix(new ArrayList<Integer>(){{addAll(newPrefix);}}.subList(0,len));
    }

    public int getPrefixLength(){
        return aPrefix!=null ? aPrefix.size() : 0;
    }
}
