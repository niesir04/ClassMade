import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserSys {//用户系统类
    public static List<Company> customerCar=new ArrayList<>();//全局集合，存放轿车
    public static List<Company> customerBus=new ArrayList<>();//全局集合，存放客车
    public static List<Company> customerGoods=new ArrayList<>();//全局集合，存放货车
    public static void User(List<Company> listCar, List<Company> bus,
                            List<Company>GoodsCar ,Scanner user) {//用户交互方法
        while (true) {
            //打印
            System.out.println("*****欢迎光临汽车租赁公司*****");
            System.out.println("1-租赁车辆");
            System.out.println("2-查询当前系统车辆");
            System.out.println("3-查看我的租赁订单");
            System.out.println("4-返回主页面");
            System.out.println("请输入您想要进行的操作：");
            int using = user.nextInt();
            //判断用户输入命令
            switch (using) {
                case 1:
                    recentCar(listCar, bus,GoodsCar, user);
                    break;
                case 2:
                    ManagerSys.queryCar();//管理系统所有现存车辆
                    break;
                case 3:
                    ManagerSys.queryOrder(customerCar,customerBus,customerGoods);//用户租赁订单
                    break;
                case 4:
                    ManagerSys.show();//主界面
                    break;
                default:
                    System.out.println("请做个遵守规则的用户哦\n");
            }
        }
    }



    private static void recentCar(List<Company> listCar, List<Company> bus,
                                  List<Company> GoodsCar,Scanner user) {//租赁车辆方法
        ManagerSys.queryCar();//打印管理系统所有现存车辆
        int userIn= ManagerSys.checkCar(user);//提示用户输入租赁车辆类型并检测合法性
        String userModel="";//车辆型号初始化
        String userSeat="";//车辆座位初始化
        String userTonnage="";//车辆吨位初始化
        System.out.println("车辆的品牌/型号如下：");
        if (userIn==1){
            for (Company com:listCar) {//遍历打印所有轿车品牌供用户选择
                Truck com1=(Truck)com;
                System.out.println(com.getBrand()+"："+com1.getModel()+" ");
            }
            System.out.println("请输入您想要租赁的车辆品牌名称：");
            String userBrand=user.next();
            System.out.println("请输入您想要租赁的车辆型号：");
            userModel=user.next();
            Company truck=CarFactory(userBrand,userModel,userSeat,
                    userTonnage,listCar,bus,GoodsCar,userIn);//工厂创建一个新的轿车对象
            if (truck.getId()==0){//管理系统中找不到用户输入的轿车
                System.out.println("您输入的车辆品牌或型号有误！");
            }else {//管理系统中找到了用户输入的轿车
                System.out.println("请输入您想要租的天数：");
                truck.setDays(user.nextInt());//租车天数存入轿车对象
                truck.setPayMoney(truck.RecentFee(truck.getDays()));//计算租车费用并存入轿车对象
                customerCar.add(truck);//在全局轿车集合中添加创建的轿车对象
                ManagerSys.removeCar(truck);//在管理系统中移除创建的轿车对象，代表车已经租出
                System.out.println("租赁成功！您租赁的是：" + truck.getBrand()
                        + userModel + "，车牌号为：" + truck.getLicense());
                System.out.println("共租赁"+truck.getDays()+"天，需要支付的租金为：￥"+truck.getPayMoney());
            }
        }if (userIn==2){
            for (Company com:bus) {//遍历打印所有客车品牌供用户选择
                Sedan com1=(Sedan) com;
                System.out.println(com.getBrand()+"："+com1.getSeat()+"座");
            }
            System.out.println("请输入您想要租赁的车辆品牌名称：");
            String userBrand=user.next();
            System.out.println("请输入您想要租赁的车辆座位数量：");
            userSeat=user.next();
            Company sedan=CarFactory(userBrand,userModel,userSeat,
                    userTonnage,listCar,bus,GoodsCar,userIn);//工厂创建一个新的客车对象
            if (sedan.getId()==0){//管理系统中找不到用户输入的客车
                System.out.println("您输入的车辆品牌或型号有误！");
            }
            else {//管理系统中找到了用户输入的客车
                System.out.println("请输入您想要租的天数：");
                sedan.setDays(user.nextInt());//租车天数存入客车对象
                sedan.setPayMoney(sedan.RecentFee(sedan.getDays()));//计算租车费用并存入客车对象
                customerBus.add(sedan);//在全局客车集合中添加创建的客车对象
                ManagerSys.removeBus(sedan);//在管理系统中移除创建的客车对象，代表车已经租出
                System.out.println("租赁成功！您租赁的是：" + sedan.getBrand()
                        + userSeat + "座，车牌号为：" + sedan.getLicense());
                System.out.println("共租赁"+sedan.getDays()+"天，需要支付的租金为：￥"+sedan.getPayMoney());
            }
        }if (userIn==3){
            for (Company com:GoodsCar) {//遍历打印所有客车品牌供用户选择
                GoodsVehicle com1=(GoodsVehicle) com;
                System.out.println(com.getBrand()+"："+com1.getTonnage()+"吨");
            }
            System.out.println("请输入您想要租赁的车辆品牌名称：");
            String userBrand=user.next();
            System.out.println("请输入您想要租赁的车辆吨位数量：");
            userTonnage=user.next();
            Company goodsvegicle=CarFactory(userBrand,userModel,userSeat,
                    userTonnage,listCar,bus,GoodsCar,userIn);//工厂创建一个新的客车对象
            if (goodsvegicle.getId()==0){//管理系统中找不到用户输入的客车
                System.out.println("您输入的车辆品牌/型号/吨位有误！");
            }
            else {//管理系统中找到了用户输入的客车
                System.out.println("请输入您想要租的天数：");
                goodsvegicle.setDays(user.nextInt());//租车天数存入客车对象
                goodsvegicle.setPayMoney(goodsvegicle.RecentFee(goodsvegicle.getDays()));//计算租车费用并存入客车对象
                customerGoods.add(goodsvegicle);//在全局客车集合中添加创建的客车对象
                ManagerSys.removeGoodsCar(goodsvegicle);//在管理系统中移除创建的客车对象，代表车已经租出
                System.out.println("租赁成功！您租赁的是：" + goodsvegicle.getBrand()
                        + userSeat + "吨，车牌号为：" + goodsvegicle.getLicense());
                System.out.println("共租赁"+goodsvegicle.getDays()+"天，需要支付的租金为"
                        + "：￥"+goodsvegicle.getPayMoney());
            }
        }


    }

    public static Company CarFactory(String userBrand,String userModel,String userSeat,String userTonnage,
                                     List<Company> listCar, List<Company> bus,List<Company> GoodsCar,int useIn) {//车辆工厂按需求造车
        String brand = "";//初始化
        String seat = "";
        String model = "";
        String tonnage = "";
        float price = 0;
        int id = 0;
        String license = "";
        if (useIn == 1) {//创造轿车
            for (Company com : listCar) {
                Truck com1 = (Truck) com;
                if (userBrand.equalsIgnoreCase(com.getBrand())//寻找系统是否存在该轿车
                        && userModel.equalsIgnoreCase(com1.getModel())) {
                    brand = com.getBrand();
                    model = com1.getModel();
                    price = com.getPrice();
                    id = com.getId();
                    license = com.getLicense();
                    break;
                }
            }return new Truck(brand, model, price, id, license);//返回一个新的轿车对象
        } else if (useIn == 2) {
            for (Company com : bus) {
                Sedan com1 = (Sedan) com;
                if (userBrand.equalsIgnoreCase(com.getBrand()) //寻找系统是否存在该客车
                        && userSeat.equalsIgnoreCase(com1.getSeat())) {
                    brand = com.getBrand();
                    seat = com1.getSeat();
                    price = com.getPrice();
                    id = com.getId();
                    license = com.getLicense();
                    break;
                }
            }return new Sedan(brand, seat, price, id, license);//返回一个新的客车对象
        }else if (useIn == 3) {
            for (Company com : GoodsCar) {
                GoodsVehicle com1 = (GoodsVehicle) com;
                if (userBrand.equalsIgnoreCase(com.getBrand()) //寻找系统是否存在该货车
                        && userTonnage.equalsIgnoreCase(com1.getTonnage())) {
                    brand = com.getBrand();
                    tonnage = com1.getTonnage();
                    price = com.getPrice();
                    id = com.getId();
                    license = com.getLicense();
                    break;
                }
            }return new GoodsVehicle(brand, tonnage, price, id, license);//返回一个新的货车对象
        }else {
            return null;
        }
    }
}