package cn.com;

public class Currency {
    private String name;        //货币名称
    private int originalValue;  //原始值
    public int value;          //转换为人民币后的值
    public static String[] CURRENCY_NAME = {"CNY", "HKD", "USD", "EUR"};
    public static int[] CURRENCY_RATIO = {100, 118, 15, 13};

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getOriginalValue() {
        return originalValue;
    }
    public void setOriginalValue(int originalValue) {
        this.originalValue = originalValue;
    }

    public int getValue() { return value; }
    public void setValue(int value) {
        this.value = value;
    }

    public Currency(String name, int originalValue, int value) {
        this.name = name;
        this.originalValue = originalValue;
        this.value = value;
    }

    public Currency() {
        super();
    }

}
