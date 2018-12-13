package team.yzq.Interface;

import team.yzq.modules.AddressPrefix;

/**
 * @Author: YZQs
 * @Date: 2018/10/16
 */
public interface TreeChannelTask {

    void checkAddressPrefix();              //数据包地址前缀和当前逻辑节点地址前缀相匹配

    void deliverUp();               //上行转发数据包

    void deliverDown();             //下行转发数据包

    void dataCode(AddressPrefix addressPrefix);         //扩展包头

        void dataDecode();              //拆解包头
}
