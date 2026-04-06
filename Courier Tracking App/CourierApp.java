import java.util.*;

enum Status{//enum for type safety
    BOOKED,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED
}

abstract class User{//Abstract to hide the user info.
    String name;
    String mobile_no;
    String address;

    public User(String name,String mobile_no,String address){
        this.name=name;                       //this keyword to point the variables in the function with the same variables in the class
        this.mobile_no=mobile_no;
        this.address=address;
    }
    public String getName(){
        return name;
    }
}
//Generally there are two users includes Sender and Receiver hence we inherit them from user class using extend keyword
class Sender extends User{ 
    public Sender(String name,String mobile_no,String address){
        super(name ,mobile_no,address);//super is used to refer to the variables in the immediate parent class i.e adjacent parent
    }
}
class Receiver extends User{ 
    public Receiver(String name,String mobile_no,String address){
        super(name ,mobile_no,address);
    }
}

class Tracker{
    String location;
    String time;
    Status status;
    String remarks;

    public Tracker(String location,Status status,String remarks){
        this.location=location;
        this.time=new Date().toString();//Date() gives the current day date time
        this.status=status;
        this.remarks=remarks;
    }
    public void display(){
        System.out.println(time+" || " +location+" || "+status);
    }
}


class Courier{
    int courierId;
    Sender sender;
    Receiver receiver;
    String Source;
    String Destination;
    double weight;
    Status status;
    String currLocation;
    String dispatchDate;
    String deliveryDate;

    List<Tracker> list=new ArrayList<>();

    public Courier(int courierId,Sender sender,Receiver receiver,String Source,String Destination, double weight){
        this.courierId=courierId;
        this.sender=sender;
        this.receiver=receiver;
        this.Source=Source;
        this.Destination=Destination;
        this.weight=weight;

        this.status=Status.BOOKED;
        this.currLocation=Source;
        this.dispatchDate=new Date().toString();

        list.add(new Tracker(Source, status,"Courier BOOKED"));
    }
    public int getCourierId(){
        return courierId;
    }
    public void Status_update(String location,Status status, String remarks){
        this.currLocation=location;
        this.status=status;

        if(status==Status.DELIVERED){
            this.deliveryDate=new Date().toString();
        }

        list.add(new Tracker(location, status, remarks));
    }

    public void track_courier(){
        System.out.println("\n courier tracking ID"+ courierId);
        for(Tracker t: list){
            t.display();
        }
    }

    public void displayDetails(){
        System.out.println("----------DETAILS!----------");
        System.out.println();
        System.out.println("\nCouried ID :"+ courierId);
        System.out.println("Sender :" + sender.getName());
        System.out.println("Receiver :" + receiver.getName());
        System.out.println("From :"+ Source);
        System.out.println("To :"+Destination);
        System.out.println("Weight :"+weight);
        System.out.println("Status :"+ status);
        System.out.println("Current Location :"+currLocation);
        System.out.println("Dispatch Date :"+dispatchDate);
        System.out.println("Delivery Date :" + (deliveryDate == null ? "Not DELIVERED" : deliveryDate));
    }
}

class Admin{
    int adminId;
    String name;

    public Admin(int adminId,String name){
        this.adminId=adminId;
        this.name=name;
    }
    public void updateCourier(CourierService service,int id, String loc, Status status, String remarks){
        service.updateCourierStatus(id,loc,status,remarks);
    }
    public void viewAll(CourierService service){
        service.displayAllCouriers();
    }
}

class CourierService{
    private List<Courier> couriers=new ArrayList<>();

    public void addCourier(Courier c){
        couriers.add(c);
        System.out.println("Courier booked Successfully!");
    }
    public Courier findCourier(int id){
        for(Courier c: couriers){
            if(c.getCourierId()==id) return c;
        }
        return null;
    }

    public void updateCourierStatus(int id ,String loc,Status status,String remarks){
        Courier c=findCourier(id);
        if(c!=null){
            c.Status_update(loc, status, remarks);
            System.out.println("Status updated");
        }
        else{
            System.out.println("Courier not found!");
        }
    }

    public void track_courier(int id){
        Courier c=findCourier(id);
        if(c!=null){
            c.track_courier();
        }
        else{
            System.out.println("Courier not found!");
        }
    }
    public void displayAllCouriers(){
        if(couriers.isEmpty()){
            System.out.println("No couriers Available!");
            return;
        }
        for(Courier c:couriers){
            c.displayDetails();
        }
    }
}



public class CourierApp {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        CourierService service=new CourierService();
        Admin admin=new Admin(1,"Admin");
        
        while(true){
            System.out.println("\n-------Courier MENU-------");
            System.out.println("1. Book Courier");
            System.out.println("2. Track Courier");
            System.out.println("3. Update Status");
            System.out.println("4. View All Couriers");
            System.out.println("5. Exit");

            int choice=sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.println("Courier Id: ");
                    int id=sc.nextInt();
                    sc.nextLine();

                    System.out.print("Sender Name :");
                    String sName=sc.nextLine();
                    System.out.print("Sender Phone :");
                    String sPhone=sc.nextLine();
                    System.out.print("Sender Address :");
                    String sAddress=sc.nextLine();
                    Sender sender=new Sender(sName ,sPhone, sAddress);

                    System.out.print("Receiver Name :");
                    String rName=sc.nextLine();
                    System.out.print("Receiver Phone :");
                    String rPhone=sc.nextLine();
                    System.out.print("Receiver Address");
                    String rAddress=sc.nextLine();
                    Receiver receiver=new Receiver(rName ,rPhone, rAddress);

                    System.out.println("Source: ");
                    String source=sc.nextLine();
                    System.out.println("Destination: ");
                    String destination=sc.nextLine();
                    System.out.println("Weight:");
                    double weight=sc.nextDouble();

                    Courier courier =new Courier(id, sender, receiver, source, destination, weight);
                    service.addCourier(courier);
                    break;

                case 2:
                    System.out.println("Enter Courier Id :");
                    int tid=sc.nextInt();
                    service.track_courier(tid);
                    break;
                
                case 3:
                    System.out.print("Courier ID :");
                    int uid=sc.nextInt();
                    sc.nextLine();

                    System.out.print("Location :");
                    String loc=sc.nextLine();

                    System.out.println("1. IN_TRANSIT 2.OUT_FOR_DELIVERY 3.DELIVERED");
                    int s=sc.nextInt();
                    sc.nextLine();

                    Status status=Status.IN_TRANSIT;
                    if(s==2) status =Status.OUT_FOR_DELIVERY;
                    if(s==3) status=Status.DELIVERED;

                    System.out.print("Remarks :");
                    String remarks=sc.nextLine();

                    admin.updateCourier(service, uid, loc, status, remarks);
                    break;

                case 4:
                    admin.viewAll(service);
                    break;

                case 5:
                    System.out.println("Exiting...!");
                    return;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
