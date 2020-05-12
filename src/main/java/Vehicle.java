public class Vehicle {
     public int number;
     public String color;

    public Vehicle(int number, String color) {
        this.number = number;
        this.color = color;
    }



    @Override
    public String toString() {
        return "Vehicle : " + number + ", is " + color ;
    }
}
