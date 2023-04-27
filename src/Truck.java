public class Truck extends Company {
    private String model;//子类独有属性：车辆型号
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public Truck(String brand, String model, float price, int id, String license){//有参构造
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setId(id);
        setLicense(license);
    }


    @Override
    public float RecentFee(int days) {//重写支付计算方法
        // TODO Auto-generated method stub
        float money = this.getPrice()*days;//得到折扣前应付租金
        if(days > 7 && days <=30) {
            money *= 0.9f;
        }else if(days > 30 && days <= 150) {
            money *=0.8f;
        }else if(days > 150) {
            money *= 0.7f;
        }
        return money;//返回打折后应付金额
    }
}
