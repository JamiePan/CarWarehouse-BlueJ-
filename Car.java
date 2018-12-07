import java.util.*;
/**
 * Write a description of class Car here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Car 
{

    private String regNumber;
    private int year;
    private String make;
    private String model;
    private int price;
    private List<String> colours;
    private static List<Car> cars = new ArrayList<>();

    public Car() 
    {
        // for preventing the NullPointerException
        this.colours = new ArrayList<>();
    }

    // print the car info
    @Override
    public String toString() 
    {

        String colour = "";
        for (String c : this.colours) 
        {
            colour += c + ",";
        }

        colour = colour.substring(0, colour.length()-1);

        return String.format("\nCar info: " +
                "\nRegistration Number: %s" +
                "\nMade Year: %d" +
                "\nColour(s): %s" +
                "\nCar Make: %s" +
                "\nCar Model: %s" +
                "\nPrice: $%d\n", this.regNumber, this.year, colour, this.make, this.model, this.price);
    }

    public String getRegNumber() 
    {
        return regNumber;
    }

    public void setRegNumber(String regNumber) 
    {
        this.regNumber = regNumber;
    }

    public int getYear() 
    {
        return year;
    }

    public void setYear(int year) 
    
    {
        this.year = year;
    }

    public List<String> getColours() {
        return colours;
    }

    public void setColours(List<String> colours) 
    {
        this.colours = colours;
    }

    public String getMake() 
    {
        return make;
    }

    public void setMake(String make) 
    {
        this.make = make;
    }

    public String getModel() 
    {
        return model;
    }

    public void setModel(String model) 
    {
        this.model = model;
    }

    public int getPrice() 
    {
        return price;
    }

    public void setPrice(int price) 
    {
        this.price = price;
    }

    public static List<Car> getCars() 
    {
        return cars;
    }

    public static void setCars(List<Car> cars) 
    {
        Car.cars = cars;
    }
}
