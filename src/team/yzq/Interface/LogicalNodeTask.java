package team.yzq.Interface;

/**
 * @Author: YZQ
 * @Date: 2018/10/16
 */
public interface LogicalNodeTask extends TreeChannelTask, ShortChannelTask{

    void receive();

    void confirm();

    void drop();

    void send();
}
