import java.util.*;

class Ride{
    String name;
    String pickup;
    String drop;
    List<String> stop;
    double fare;
    String status;
    
    Ride(String name,String pickup,String drop){
        this.name=name;
        this.pickup=pickup;
        this.drop=drop;
        this.stop=new ArrayList<>();
        this.fare=calculation();
        this.status="Booked";
    }
    Ride(String name,String pickup,String drop,List<String> stop){
        this.name=name;
        this.pickup=pickup;
        this.drop=drop;
        this.stop=stop;
        this.fare=calculation();
        this.status="Booked";
    }
    double calculation(){
        return 50+(stop.size()*50);
    }
    void showdetails(){
        System.out.println("\n Customer "+this.name+" \n route :");
        System.out.print(this.pickup);
        for(String s:stop){
            System.out.println("-->"+s);
        }System.out.println("-->"+this.drop);
        System.out.println(this.fare);
        System.out.println(this.status);
        System.out.println("--------------------");
    }
    void cancelride(){
        if(this.status.equals("cancelled")){
            System.out.println("Already cancelled");
        }else if(this.status.equals("completed")){
            System.out.println("completed ride cannot be cancelled");
        }else{
            this.status="cancelled";
            this.fare=0;
            System.out.println("successfully cancelled");
        }
    }
    void completeride(){
         if(this.status.equals("cancelled")){
            System.out.println("cancelled ride cannot be completed");
        }else if(this.status.equals("completed")){
            System.out.println("already completed");
        }else{
            this.status="completed";
            this.fare=0;
            System.out.println("successfully completed");
        }
    }

}

public class Day10 {
    public static void main(String[] args) {
        Ride r1=new Ride("siva", "st.josephs", "tnagar");
        r1.showdetails();
        List<String> stop=new ArrayList<>();
        stop.add("airport");
        stop.add("alandur");
        Ride r2=new Ride("subanesh","tambaram","guindy",stop);
        r2.showdetails();
        r1.cancelride();
        r1.showdetails();
        r2.completeride();
        r2.showdetails();
    }
}
