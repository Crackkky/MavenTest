public class Vehicle {
     public int number;
     public String color;

    /**
     * Create a vehicle
     *
     * @param number : the number reference for that vehicle
     * @param color : the color of the vehicle
     */
    public Vehicle(int number, String color) {
        this.number = number;
        this.color = color;
    }


    @Override
    public String toString() {
        return "Vehicle : " + number + ", is " + color ;
    }
}
