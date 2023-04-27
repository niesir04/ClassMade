import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerSys {//管理系统类
    private static List<Company> listCar=new ArrayList<>();//管理系统轿车集合
    private static List<Company> bus=new ArrayList<>();//管理系统客车集合
    private static List<Company> GoodsCar=new ArrayList<>();//管理系统货车集合
    public static void main(String []args){
        //默认添加管理系统4种轿车
        listCar.add(new Truck("宝马","X5",800,1001,"湘FQ9456"));
        listCar.add(new Truck("奥迪","A6",600,1002,"湘FQ8888"));
        listCar.add(new Truck("大众","CC",300,1003,"湘FQ6666"));
        listCar.add(new Truck("别克","GL8",600,1004,"湘FQ6688"));
        //默认添加管理系统4种客车
        bus.add(new Sedan("金杯","16",800,1001,"湘FQ12345"));
        bus.add(new Sedan("金杯","16",800,1002,"湘FQ23456"));
        bus.add(new Sedan("亚星","32",1500,1003,"湘FQ34567"));
        bus.add(new Sedan("亚星","32",1500,1004,"湘FQ45678"));
        //默认添加管理系统2种货车
        GoodsCar.add(new GoodsVehicle("福田","10",500,2001,"湘AQ20211"));
        GoodsCar.add(new GoodsVehicle("东风","24",1000,2002,"湘AQ20212"));
        show();//系统选择交互界面
    }
    public static void show() {//系统选择交互界面方法
        while (true) {
            System.out.println("--------欢迎进入汽车之家--------");
            System.out.println("1-进入管理员系统");
            System.out.println("2-进入普通租赁用户系统");
            System.out.println("3-退出");
            System.out.println("请输入您想要进行的操作：");
            Scanner user = new Scanner(System.in);
            int temp = user.nextInt();
            //输入命令判断
            switch (temp) {
                case 1:
                    CheckPwd(user);//管理员登录验证
                    break;
                case 2:
                    UserSys.User(listCar,bus,GoodsCar, user);//用户系统
                    break;
                case 3:
                    System.out.println("感谢您的信任与支持，再见！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("请做个遵守规则的用户哦\n");
            }
        }
    }

    private static void CheckPwd( Scanner user) {//登录验证方法
        while (true) {
            System.out.println("请输入管理员账号:");
            int id = user.nextInt();
            System.out.println("请输入管理员密码:");
            String pwd = user.next();
            if ((pwd.equalsIgnoreCase("abc123")) && (id == 1024)) {
                Manager(user);//管理员交互界面
                break;
            } else {
                System.out.println("账号或密码错误,请重新输入!");
            }
        }
    }

    private static void Manager( Scanner user) {//管理员交互界面方法
        while (true) {
            System.out.println("*****欢迎进入汽车管理系统*****");
            System.out.println("1-查询当前系统车辆");
            System.out.println("2-查询用户租赁订单");
            System.out.println("3-增加车辆");
            System.out.println("4-删除车辆");
            System.out.println("5-修改车辆租金");
            System.out.println("6-返回主页面");
            System.out.println("请输入您想要进行的操作：");
            int manage = user.nextInt();
            //输入命令判断
            switch (manage) {
                case 1:
                    queryCar();//查询当前系统车辆
                    break;
                case 2:
                    queryOrder(UserSys.customerCar, UserSys.customerBus,UserSys.customerGoods);//查询用户租赁订单
                    break;
                case 3:
                    addCar( user);//增加车辆
                    break;
                case 4:
                    deleteCar(user);//删除车辆
                    break;
                case 5:
                    updatePrice(user);//修改车辆租金
                    break;
                case 6:
                    show();//主页面
                    break;
                default:
                    System.out.println("请做个遵守规则的用户哦\n");
            }
        }
    }

    private static void updatePrice( Scanner user) {//修改车辆租金
        int userIn=checkCar(user);//提示用户输入租赁车辆类型并检测合法性
        System.out.println("请输入您需要修改租金车辆的编号");
        int userId=user.nextInt();
        System.out.println("请输入您需要修改的租金金额：");
        float carPrice=user.nextFloat();
        if (userIn==1){//修改轿车租金
            for (int i=0;i<listCar.size();i++){
                if (listCar.get(i).getId()==userId){//遍历寻找唯一ID
                    System.out.println("车辆："+listCar.get(i).getBrand()
                            +listCar.get(i).getLicense()+"的租金已成功修改为"+carPrice);
                    listCar.get(i).setPrice(carPrice);
                    return;
                }else if(i==listCar.size()-1){
                    System.out.println("对不起，没有找到您想要修改租金的车辆！");
                }
            }
        }else if (userIn==2){//修改客车租金
            for (int i=0;i<bus.size();i++){//遍历寻找唯一ID
                if (bus.get(i).getId()==userId){
                    System.out.println("车辆："+bus.get(i).getBrand()
                            +bus.get(i).getLicense()+"的租金已成功修改为"+carPrice);
                    bus.get(i).setPrice(carPrice);
                    return;
                }else if(i==bus.size()-1){
                    System.out.println("对不起，没有找到您想要修改租金的车辆！");
                }
            }
        }else if (userIn==3){//修改货车租金
            for (int i=0;i<GoodsCar.size();i++){//遍历寻找唯一ID
                if (GoodsCar.get(i).getId()==userId){
                    System.out.println("车辆："+GoodsCar.get(i).getBrand()
                            +GoodsCar.get(i).getLicense()+"的租金已成功修改为"+carPrice);
                    GoodsCar.get(i).setPrice(carPrice);
                    return;
                }else if(i==GoodsCar.size()-1){
                    System.out.println("对不起，没有找到您想要修改租金的车辆！");
                }
            }
        }
    }

    private static void deleteCar(Scanner user) {//删除车辆方法
        System.out.println("当前您的车辆清单如下：");
        queryCar();//打印当前系统车辆
        int userIn=checkCar(user);//提示用户输入租赁车辆类型并检测合法性
        System.out.println("请输入您需要删除车辆的车牌编号");
        int userId=user.nextInt();
        if (userIn==1){//删除轿车
            for (int i=0;i<listCar.size();i++) {
                if (listCar.get(i).getId() == userId) {
                    System.out.println("车辆：" + listCar.get(i).getBrand()
                            + listCar.get(i).getLicense() + "成功删除！");
                    listCar.remove(listCar.get(i));
                    return;
                } else if (i == listCar.size() - 1) {
                    System.out.println("对不起，没有找到您想要删除的车辆！");
                }
            }
        }else if (userIn==2){//删除客车
            for (int i=0;i<bus.size();i++){
                if (bus.get(i).getId()==userId){
                    System.out.println("车辆："+bus.get(i).getBrand()
                            +bus.get(i).getLicense()+"成功删除！");
                    bus.remove(bus.get(i));
                    return;
                }else if (i == bus.size() - 1) {
                    System.out.println("对不起，没有找到您想要删除的车辆！");
                }
            }
        }else if (userIn==3){//删除货车
            for (int i=0;i<GoodsCar.size();i++){
                if (GoodsCar.get(i).getId()==userId){
                    System.out.println("车辆："+GoodsCar.get(i).getBrand()
                            +GoodsCar.get(i).getLicense()+"成功删除！");
                    GoodsCar.remove(GoodsCar.get(i));
                    return;
                }else if (i == GoodsCar.size() - 1) {
                    System.out.println("对不起，没有找到您想要删除的车辆！");
                }
            }
        }
    }


    private static void addCar( Scanner user) {//添加车辆方法
        int userIn=checkCar(user);//提示用户输入租赁车辆类型并检测合法性
        System.out.println("请您输入要添加车辆的品牌");
        String brand = user.next();
        System.out.println("请您输入要添加车辆的型号/座位/吨位");
        String select = user.next();
        System.out.println("请您输入要添加车辆的租价");
        float price = user.nextFloat();
        System.out.println("请您输入要添加车辆的编号");
        int id = user.nextInt();
        System.out.println("请您输入要添加车辆的车牌号");
        String license = user.next();
        switch (userIn) {
            case 1:
                listCar.add(new Truck(brand, select, price, id,license));//添加轿车对象
                break;
            case 2:
                bus.add(new Sedan(brand, select, price, id,license));//添加客车对象
                break;
            case 3:
                GoodsCar.add(new Sedan(brand, select, price, id,license));//添加货车对象
                break;
        }
        System.out.println("车辆:"+brand+select+"添加成功!");
    }

    public static int checkCar(Scanner user) {//用户输入租赁车辆类型方法
        boolean flag=true;
        int sc=0;
        while (flag) {
            System.out.println("请您输入要操作车辆的类型,1-轿车，2-客车,3-货车");
            int userIn = user.nextInt();
            if (userIn==1||userIn==2||userIn==3){
                sc=userIn;
                flag=false;
            }else {
                System.out.println("您输入的类型有误！");
            }
        }return sc;
    }

    public static void queryCar() {//查询打印当前系统车辆
        System.out.println("------轿车类(7天以上9折，30天以上8折，150天以上7折)------");
        System.out.println("车辆品牌\t\t"+"车辆型号\t\t"+"租赁价格\t\t"+"车辆编号\t\t"+"车牌号码");
        for (Company com:listCar) {
            Truck com1=(Truck)com;
            System.out.println(com.getBrand()+"\t\t"+com1.getModel()+"\t\t\t"
                    +com.getPrice()+"\t\t"+com.getId()+"\t\t"+com.getLicense());
        }
        System.out.println("-----客车类(3天及以上9折，7天及以上8折，30天及以上7折，150天及以上6折)-----");
        System.out.println("车辆品牌\t\t"+"车辆座位\t\t"+"租赁价格\t\t"+"车辆编号\t\t"+"车牌号码");
        for (Company com:bus) {
            Sedan com1=(Sedan) com;
            System.out.println(com.getBrand()+"\t\t"+com1.getSeat()+"\t\t\t"
                    +com.getPrice()+"\t\t"+com.getId()+"\t\t"+com.getLicense());
        }
        System.out.println("-----货车类(3天及以上8折，7天及以上7折，15天及以上6折，30天及以上5折，150天及以上4折)-----");
        System.out.println("车辆品牌\t\t"+"车辆吨位\t\t"+"租赁价格\t\t"+"车辆编号\t\t"+"车牌号码");
        for (Company com:GoodsCar) {
            GoodsVehicle com1=(GoodsVehicle) com;
            System.out.println(com.getBrand()+"\t\t"+com1.getTonnage()+"\t\t\t"
                    +com.getPrice()+"\t\t"+com.getId()+"\t\t"+com.getLicense());
        }
    }
    public static void queryOrder(List<Company>customerCar,List<Company>customerBus,
                                  List<Company>customerGoods) {//查询当前用户订单
        System.out.println("*******订单信息*******");
        System.out.println("车辆品牌\t\t"+"车辆型号/座位/吨位\t\t"+"租赁天数\t\t"+"租赁金额\t\t"+"车牌号码");
        for (Company com : customerCar) {//遍历输出轿车
            Truck com1 = (Truck) com;
            System.out.println(com.getBrand() + "\t\t\t" + com1.getModel() + "型\t\t\t\t\t"
                    + com.getDays() + "\t\t" + com.getPayMoney() + "\t\t" + com.getLicense());
        }
        for (Company com : customerBus) {//遍历输出客车
            Sedan com1 = (Sedan) com;
            System.out.println(com.getBrand() + "\t\t\t" + com1.getSeat() + "座\t\t\t\t\t"
                    + com.getDays() + "\t\t" + com.getPayMoney() + "\t\t" + com.getLicense());
        }
        for (Company com : customerGoods) {//遍历输出货车
            GoodsVehicle com1 = (GoodsVehicle) com;
            System.out.println(com.getBrand() + "\t\t\t" + com1.getTonnage() + "吨\t\t\t\t\t"
                    + com.getDays() + "\t\t" + com.getPayMoney() + "\t\t" + com.getLicense());
        }
    }

    public static void removeCar(Company trunk) {//在管理系统中移除用户创建的轿车对象，代表车已经租出
        for (int i=0;i<listCar.size();i++){
            if(trunk.getId()==listCar.get(i).getId()){
                listCar.remove(i);
                return;
            }
        }
    }
    public static void removeBus(Company sedan) {//在管理系统中移除用户创建的客车对象，代表车已经租出
        for (int i=0;i<bus.size();i++){
            if(sedan.getId()==bus.get(i).getId()){
                bus.remove(i);
                return;
            }
        }
    }
    public static void removeGoodsCar(Company goodVehicle) {//在管理系统中移除用户创建的货车对象，代表车已经租出
        for (int i=0;i<GoodsCar.size();i++){
            if(goodVehicle.getId()==GoodsCar.get(i).getId()){
                GoodsCar.remove(i);
                return;
            }
        }
    }
}
