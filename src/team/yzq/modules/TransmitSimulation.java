package team.yzq.modules;

/**
 * @Author: YZQ
 * @Date: 2018/10/16
 */
public class TransmitSimulation {

    private static volatile AddressPrefix currentAddressPrefix;
    private static volatile AddressPrefix nextAddressPrefix;
    private static volatile Data data;
    private static volatile boolean transmiting;

    public static AddressPrefix getCurrentAddressPrefix() {
        return currentAddressPrefix;
    }

    public static void setCurrentAddressPrefix(AddressPrefix currentAddressPrefix) {
        TransmitSimulation.currentAddressPrefix = currentAddressPrefix;
    }

    public static AddressPrefix getNextAddressPrefix() {
        return nextAddressPrefix;
    }

    public static void setNextAddressPrefix(AddressPrefix nextAddressPrefix) {
        TransmitSimulation.nextAddressPrefix = nextAddressPrefix;
    }

    public static boolean isTransmiting() {
        return transmiting;
    }

    public static void setTransmiting(boolean transmiting) {
        TransmitSimulation.transmiting = transmiting;
    }


    public static Data getData() {
        return data;
    }

    public static void setData(Data data) {
        TransmitSimulation.data = data;
    }

    public static void clear(){
        setCurrentAddressPrefix(null);
        setNextAddressPrefix(null);
        setData(null);
    }

    static {
        currentAddressPrefix = null;
        nextAddressPrefix = null;
        data = null;
    }
}
