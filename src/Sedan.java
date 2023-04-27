public class Sedan extends Company{
    private String seat;//子类独有属性：车辆座位
    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
    public Sedan(String brand, String seat, float price, int id,String license){//有参构造
        setBrand(brand);
        setSeat(seat);
        setPrice(price);
        setId(id);
        setLicense(license);
    }


    @Override
    public float RecentFee(int days) {//重写支付计算方法
        float money = this.getPrice()*days;//得到折扣前应付金额
        if(days >= 3 && days <7) {
            money *= 0.9f;
        }else if(days >= 7 && days < 30) {
            money *=0.8f;
        }else if(days >= 30 && days < 150) {
            money *= 0.7f;
        }else if(days >= 150) {
            money *=0.6f;
        }
        return money;//返回折扣后应付金额
    }
}