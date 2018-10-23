package team.yzq.Interface;

import team.yzq.modules.AddressPrefix;

/**
 * @Author: YZQ
 * @Date: 2018/10/16
 */
public interface ShortChannelTask {

    AddressPrefix checkShortTable();

    AddressPrefix checkShortTableMap();
}
