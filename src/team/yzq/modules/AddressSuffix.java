package team.yzq.modules;

import java.util.List;

/**
 * @Author: YZQ
 * @Date: 2018/10/16
 */
public class AddressSuffix {

    private List<Integer> sPrefix;

    public AddressSuffix(List<Integer> sPrefix) {
        this.sPrefix = sPrefix;
    }

    public List<Integer> getsPrefix() {
        return sPrefix;
    }

    public void setsPrefix(List<Integer> sPrefix) {
        this.sPrefix = sPrefix;
    }
}
