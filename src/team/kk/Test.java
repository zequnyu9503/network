package team.kk;

import team.kk.controller.NetController;

/**
 * 测试函数
 */
public class Test {
    /**
     * 程序入口
     * @param args
     */
    public static void main(String[] args) {
        NetController netController = new NetController(1200, 600);
        netController.start();
    }
}
