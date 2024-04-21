package cn.nanchengyu.errand.enums;

public enum OrderStatusEnum {
    CANCEL("已取消"),
    NO_ACCEPT("待接单"),
    NO_ARRIVE("待送达"),
    NO_RECEIVE("待收货"),
    NO_COMMENT("待评价"),
    DONE("已完成");

    private String value;

    OrderStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}