public class GoodsVehicle extends Company{
    private String tonnage;

    public String getTonnage() {
        return tonnage;
    }

    public void setTonnage(String tonnage) {
        this.tonnage = tonnage;
    }

    public GoodsVehicle(String brand, String tonnage, float price, int id, String license){//有参构造
        setBrand(brand);
        setTonnage(tonnage);
        setPrice(price);
        setId(id);
        setLicense(license);
    }


    @Override
    public float RecentFee(int days) {//重写支付计算方法
        // TODO Auto-generated method stub
        float money = this.getPrice()*days;//得到折扣前应付租金
        if(days > 3 && days <=7) {
            money *= 0.8f;
        }else if(days > 7 && days <= 15) {
            money *=0.7f;
        }else if(days > 15 && days <= 30) {
            money *= 0.6f;
        }else if(days > 30 && days <= 150) {
            money *= 0.5f;
        }else if(days > 150) {
            money *= 0.4f;
        }
        return money;//返回打折后应付金额
    }
}