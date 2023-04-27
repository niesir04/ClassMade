public abstract class  Company{//抽象父类
    private String brand;//品牌
    private int id;//车辆编号
    private float price;//租赁价格
    private String license;//车牌号
    private int days;//租赁天数
    private double payMoney;//应付租金金额

    public Company(){
    }//空构造
    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }


    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public abstract float RecentFee(int days);//抽象支付计算方法

}

