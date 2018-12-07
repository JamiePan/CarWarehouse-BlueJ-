import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Write a description of class App here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class App {

    public static int currentYear = 0;

    public static void main(String[] args) 
    {

        // Get data from file
        CarManager.init();

        System.out.println("Enter the current year: ");
        currentYear = new Scanner(System.in).nextInt();

        boolean isActive = true;
        String appDescription = "\nSelect one of the listed option:" +
                "\n============================" +
                "\n1. Search cars" +
                "\n2. Add car" +
                "\n3. Delete car" +
                "\n4. Edit car" +
                "\n5. Exit system" +
                "\n============================" +
                "\nEnter your option: ";

        Scanner scanner = new Scanner(System.in);
        do 
        {
            try 
            {

                System.out.println(appDescription);
                int option = scanner.nextInt();
                switch (option) 
                {
                    case 1:
                        System.out.println("You selected car searching: " +
                                "\n------------------------------");
                        CarManager.searchCar();
                        break;

                    case 2:
                        System.out.println("You selected car adding: " +
                                "\n------------------------------");
                        CarManager.addCar();
                        for (Car car : Car.getCars()) {
                            System.out.println(car);
                        }
                        break;

                    case 3:
                        System.out.println("You selected car deleting: " +
                                "\n------------------------------");
                        CarManager.deleteCar();
                        break;

                    case 4:
                        System.out.println("You selected car editing: " +
                                "\n------------------------------");
                        CarManager.editCar();
                        break;

                    case 5:
                        System.out.println("Good luck!");
                        boolean success = CarManager.commit();
                        if (!success) {
                            System.out.println("Error while saving the data!");
                            continue;
                        }
                        isActive = false;
                        break;

                    default:
                        System.out.println("Your selection was wrong. Try one more time!");
                        break;
                }
            } 
            
            catch (InputMismatchException ex) 
            {
                System.out.println("Wrong input. Try one more time!");
            }
            scanner.nextLine();
        } while (isActive);

    }

}
