class Animal{
    void eat(){
        System.out.println("animal is eating");
    }
}
class cat extends Animal{
    void sound(){
        System.out.println("cat meows");
    }
}
class dog extends Animal{
    void sound(){
        System.out.println("dog barks");
    }
}


public class Animalextend {
    public static void main(String[] args) {
        cat c=new cat();
        c.eat();
        c.sound();
        dog d=new dog();
        d.eat();
        d.sound();
    }
}
