package team.yzq.modules;

import java.util.Stack;

/**
 * @Author: YZQ
 * @Date: 2018/10/16
 */
public class Data {

    public Data(Stack<AddressPrefix> aPrefixStack, AddressSuffix aSuffix, String dataStr) {
        this.aPrefixStack = aPrefixStack;
        this.aSuffix = aSuffix;
        this.dataStr = dataStr;
    }

    private Stack<AddressPrefix> aPrefixStack;
    private final AddressSuffix aSuffix;
    private final String dataStr;

    public Stack<AddressPrefix> getaPrefixStack() {
        return aPrefixStack;
    }

    public AddressSuffix getaSuffix() {
        return aSuffix;
    }

    public String getDataStr() {
        return dataStr;
    }

    private void setCurrentAddressPrefix(AddressPrefix addressPrefix){
        if(aPrefixStack.size() > 1){
            aPrefixStack.peek().setaPrefix(addressPrefix);
        }
    }

    public AddressPrefix getCurrentAddressPrefix(){
        if(aPrefixStack == null) return null;
        return aPrefixStack.peek();
    }

    public boolean isOriginData(){
        return aPrefixStack.size() > 1 ? false : true;
    }

    public Data DataCode(AddressPrefix addressPrefix){
        aPrefixStack.push(addressPrefix);
        return this;
    }

    public Data DataDecode(){
        if(!isOriginData())
            aPrefixStack.pop();
        return this;
    }
}
