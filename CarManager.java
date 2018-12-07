import java.io.*;
import java.util.*;
/**
 * Write a description of class CarManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CarManager 
{

    // First method for reading the file content
    public static void init() 
    {
        try 
        {
            // read file's content from the project root path
            FileInputStream fis = new FileInputStream("usedcars.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String strLine;

            while ((strLine = br.readLine()) != null)   
            {
                Car newCar = new Car();

                String[] carValues = strLine.split(",");
                newCar.setRegNumber(carValues[0]);
                newCar.setYear(Integer.parseInt(carValues[1]));
                // check the colors nullable
                if (carValues[2] != null && !carValues[2].isEmpty()) 
                {
                    newCar.getColours().add(carValues[2]);
                }
                if (carValues[3] != null && !carValues[3].isEmpty()) 
                {
                    newCar.getColours().add(carValues[3]);
                }
                if (carValues[4] != null && !carValues[4].isEmpty()) 
                {
                    newCar.getColours().add(carValues[4]);
                }
                newCar.setMake(carValues[5]);
                newCar.setModel(carValues[6]);
                newCar.setPrice(Integer.parseInt(carValues[7]));
                Car.getCars().add(newCar);
            }

            //automatically show all cars from file - optional
            for (Car car : Car.getCars()) {
                System.out.println(car);
            }

            //Close the input stream
            br.close();
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println(ex.getMessage());
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }

    // save data to file
    public static boolean commit() 
    {

        try
        {
            PrintWriter writer = new PrintWriter("usedcars.txt", "UTF-8");
            for (Car car : Car.getCars()) 
            {

              //  String coloursString = String.join(",", car.getColours()); // use it if u may use Java 8.
                String coloursString = "";
                for (String colour: car.getColours()) 
                {
                    coloursString += colour + ",";
                }
                coloursString = coloursString.substring(0, coloursString.length()-1); // delete the last comma
                if (car.getColours().size() == 1) 
                {
                    coloursString += ",,";
                } 
                else if (car.getColours().size() == 2) 
                {
                    coloursString += ",";
                }

                String line = String.format("%s,%d,%s,%s,%s,%d",
                        car.getRegNumber(),
                        car.getYear(),
                        coloursString,
                        car.getMake(),
                        car.getModel(),
                        car.getPrice());
                writer.println(line);

            }
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public static void searchCar() {

        String searchOptions = "\nCar searching options: " +
                "\n============================" +
                "\n1. By Registration Number: " +
                "\n2. By Car Make and Car Model: " +
                "\n3. By Age: " +
                "\n4. By Price (range): " +
                "\n5. By colour: " +
                "\n6. Back to Main Menu: " +
                "\n============================" +
                "\nEnter your option: ";

        boolean isActive = true;
        Scanner scanner = new Scanner(System.in);
        do 
        {
            try 
            {
                List<Car> result = new ArrayList<>();
                System.out.println(searchOptions);

                int option = scanner.nextInt();
                switch (option) 
                {

                    case 1:
                        System.out.println("Search By Registration number: " +
                                "\n------------------------------");
                        System.out.println("Print registration number: ");
                        String regNumber = new Scanner(System.in).nextLine().trim().toLowerCase();

                        if (regNumber.length() < 1 || regNumber.length() > 6) 
                        {
                            System.out.println("Registration Number must contain from 1 to 6 alphanumerical symbols!");
                            continue;
                        }

                        if (!regNumber.matches("^[a-zA-Z0-9]*$")) 
                        {
                            System.out.println("Registration Number must contain only alphanumerical symbols!");
                            continue;
                        }

                        for (Car car : Car.getCars()) {
                            if (regNumber.equals(car.getRegNumber().toLowerCase())) 
                            {
                                result.add(car);
                            }
                        }

                        if (result.isEmpty()) 
                        {
                            System.out.println("No such car with this Registration Number");
                        } else 
                        {
                            System.out.println(result.get(0)); // print the car's info
                        }

                        break;

                    case 2:
                        List<Car> result2 = new ArrayList<>();
                        System.out.println("Search By Car Make and Car Model: " +
                                "\n------------------------------");

                        System.out.println("Car Make: ");
                        String carMake = new Scanner(System.in).nextLine().trim().toLowerCase();

                        if (carMake.isEmpty()) 
                        {
                            System.out.println("Car Make can't be empty!");
                            continue;
                        }

                        if (!carMake.matches("^[a-zA-Z0-9]*$")) 
                        {
                            System.out.println("Car Make must contain only alphanumerical symbols!");
                            continue;
                        }

                        // show all cars
                        if ("any".equals(carMake)) {
                            System.out.printf("\nFound %d car(s)...\n" +
                                    "--------------------\n", Car.getCars().size());
                            for (Car car : Car.getCars()) 
                            {
                                System.out.println(car);
                            }
                            break;
                        }

                        System.out.println("Car Model: ");
                        String carModel = new Scanner(System.in).nextLine().trim().toLowerCase();

                        if (carModel.isEmpty()) 
                        {
                            System.out.println("Car Model can't be empty!");
                            continue;
                        }

                        if (!carModel.matches("^[a-zA-Z0-9]*$")) 
                        {
                            System.out.println("Car Model must contain only alphanumerical symbols!");
                            continue;
                        }

                        for (Car car : Car.getCars()) {
                            if (carModel.equals(car.getMake().toLowerCase())) {
                                result.add(car);
                            }
                        }

                        if (result.isEmpty()) {
                            System.out.println("No such car with this Car Make and Car Model");
                        } 
                        else 
                        {
                            if (carModel.equals("any")) 
                            {
                                System.out.printf("\nFound %d car(s)...\n" +
                                        "--------------------\n", result.size());
                                for (Car car : result) 
                                {
                                    System.out.println(car);
                                }
                            } 
                            else 
                            {
                                for (Car car : result) 
                                {
                                    if (carModel.equals(car.getModel().toLowerCase())) 
                                    {
                                        result2.add(car);
                                    }
                                }
                            }
                            if (result2.isEmpty()) 
                            {
                                System.out.println("No such car with this Car Make and Car Model");
                            } 
                            else 
                            {
                                System.out.printf("\nFound %d car(s)...\n" +
                                        "--------------------\n", result2.size());
                                for (Car car : result2) 
                                {
                                    System.out.println(car);
                                }
                            }
                        }

                        break;

                    case 3:

                        System.out.println("Search By Age: " +
                                "\n------------------------------");
                        System.out.println("Print car age: ");

                        int age = new Scanner(System.in).nextInt();
                        if (age < 0) 
                        {
                            System.out.println("Age must be a positive number!");
                            continue;
                        }

                        for (Car car : Car.getCars()) 
                        {
                            if (car.getYear() == (App.currentYear - age)) 
                            {
                                result.add(car);
                            }
                        }

                        if (result.isEmpty()) 
                        {
                            System.out.println("No such car with this maximum age");
                        } 
                        else 
                        {
                            System.out.printf("\nFound %d car(s)...\n" +
                                    "--------------------\n", result.size());
                            for (Car car : result) 
                            {
                                System.out.println(car);
                            }
                        }

                        break;

                    case 4:
                        System.out.println("Search By Price: " +
                                "\n------------------------------");
                        System.out.println("Print the minimum price: ");
                        int min = new Scanner(System.in).nextInt();
                        if (min < 0) 
                        {
                            System.out.println("Minimum price must be a positive number!");
                            continue;
                        }

                        System.out.println("Print the maximum price: ");
                        int max = new Scanner(System.in).nextInt();
                        if (max < min) 
                        {
                            System.out.println("Maximum price can't be less then the minimum price!");
                            continue;
                        }

                        for (Car car : Car.getCars()) 
                        {
                            if (car.getPrice() >= min && car.getPrice() <= max) 
                            {
                                result.add(car);
                            }
                        }

                        if (result.isEmpty()) 
                        {
                            System.out.println("No such car with this range");
                        } 
                        else 
                        {
                            System.out.printf("\nFound %d car(s)...\n" +
                                    "--------------------\n", result.size());
                            for (Car car : result) 
                            {
                                System.out.println(car);
                            }
                        }

                        break;

                    case 5:

                        System.out.println("Search by colour: " +
                                "\n------------------------------");
                        System.out.println("Enter the colour: ");
                        String colour = new Scanner(System.in).nextLine().trim().toLowerCase();

                        if (colour.isEmpty()) 
                        {
                            System.out.println("Colour can't be empty!");
                            continue;
                        }

                        if (!colour.matches("^[a-zA-Z0-9]*$")) 
                        {
                            System.out.println("Colour must contain only alphanumerical symbols!");
                            continue;
                        }

                        for (Car car : Car.getCars()) {
                            for (String c : car.getColours()) {
                                if (colour.equals(c.toLowerCase())) {
                                    result.add(car);
                                }
                            }
                        }

                        if (result.isEmpty()) {
                            System.out.println("No such car with this range");
                        } else {
                            System.out.printf("\nFound %d car(s)...\n" +
                                    "--------------------\n", result.size());
                            for (Car car : result) {
                                System.out.println(car);
                            }
                        }

                        break;

                    case 6:
                        System.out.println("Exit the search option: ");
                        isActive = false;
                        break;

                    default:
                        System.out.println("Your selection was wrong. Try one more time!");
                        break;

                }
            } catch (InputMismatchException ex) {
                System.out.println("Your selection was wrong. Try one more time!");
            }
            scanner.nextLine();
        } while (isActive);
    }

    public static void addCar() {

        Car newCar = new Car();

        System.out.println("Please, fill the values for a new car: " +
                "\n------------------------------");
        boolean isActive = true;
        do {
            try {
                if (newCar.getRegNumber() == null || newCar.getRegNumber().isEmpty()) {
                    System.out.println("Registration Number: ");
                    String regNumber = new Scanner(System.in).nextLine().trim().toUpperCase();

                    if (regNumber.length() < 1 || regNumber.length() > 6) {
                        System.out.println("Registration Number must contain from 1 to 6 alphanumerical symbols!");
                        continue;
                    }

                    if (!regNumber.matches("^[a-zA-Z0-9]*$")) {
                        System.out.println("Registration Number must contain only alphanumerical symbols!");
                        continue;
                    }

                    boolean isCarExists = false;
                    for (Car car: Car.getCars()) {
                        if (regNumber.equals(car.getRegNumber().toUpperCase())) {
                            System.out.println("Car with this registration number already exists!");
                            isCarExists = true;
                            break;
                        }
                    }
                    if (isCarExists) {
                        continue;
                    }
                    newCar.setRegNumber(regNumber);
                }

                if (newCar.getYear() < 1900) {
                    System.out.println("Made Year: ");
                    int year = new Scanner(System.in).nextInt();
                    if (year < 1900 || year > App.currentYear) {
                        System.out.println("Made Year must be from 1900 to 2017!");
                        continue;
                    }
                    newCar.setYear(year);
                }

                if (newCar.getColours().isEmpty()) {
                    System.out.println("Write max 3 colours separated with comma: ");
                    String coloursString = new Scanner(System.in).nextLine().trim();

                    if (coloursString.isEmpty()) {
                        System.out.println("You must write at least one colour!");
                        continue;
                    }
                    String[] c = coloursString.split(",");
                    if (c.length > 3) {
                        System.out.println("You can't select more than 3 colours!");
                        continue;
                    }
                    List<String> colours = Arrays.asList(c);
                    List<String> colours2 = new ArrayList<>();
                    for (String col : colours) {
                        if (col != null && !col.trim().isEmpty()) {
                            colours2.add(col.trim());
                        }
                    }
                    if (colours2.isEmpty()) {
                        System.out.println("You must write at least one colour!");
                        continue;
                    } else {
                        for (String col : colours2) {
                            newCar.getColours().add(col);
                        }
                    }
                }

                if (newCar.getMake() == null || newCar.getMake().isEmpty()) {
                    System.out.println("Car Make: ");
                    String carMake = new Scanner(System.in).nextLine().trim();

                    if (carMake.isEmpty()) {
                        System.out.println("Car Make can't be empty!");
                        continue;
                    }

                    if (!carMake.matches("^[a-zA-Z0-9]*$")) {
                        System.out.println("Car Make must contain only alphanumerical symbols!");
                        continue;
                    }
                    newCar.setMake(carMake);
                }

                if (newCar.getModel() == null || newCar.getModel().isEmpty()) {
                    System.out.println("Car Model: ");
                    String carModel = new Scanner(System.in).nextLine().trim();

                    if (carModel.isEmpty()) {
                        System.out.println("Car Model can't be empty!");
                        continue;
                    }

                    if (!carModel.matches("^[a-zA-Z0-9]*$")) {
                        System.out.println("Car Model must contain only alphanumerical symbols!");
                        continue;
                    }
                    newCar.setModel(carModel);
                }

                if (newCar.getPrice() <= 0) {
                    System.out.println("Car Price: ");
                    int price = new Scanner(System.in).nextInt();
                    if (price <= 0) {
                        System.out.println("Price must be a positive number!");
                        continue;
                    }
                    newCar.setPrice(price);
                    isActive = false;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Your selection was wrong. Try one more time!");
            }
        } while (isActive);
        Car.getCars().add(newCar);

    }

    public static void editCar() {

        boolean isActive = true;
        do {
            try {
                Car foundCar = null;
                System.out.println("Write the registration number:");
                String regNumber = new Scanner(System.in).nextLine().trim().toUpperCase();

                if (regNumber.length() < 1 || regNumber.length() > 6) {
                    System.out.println("Registration Number must contain from 1 to 6 alphanumerical symbols!");
                    continue;
                }

                if (!regNumber.matches("^[a-zA-Z0-9]*$")) {
                    System.out.println("Registration Number must contain only alphanumerical symbols!");
                    continue;
                }

                for (Car car: Car.getCars()) {
                    if (regNumber.equals(car.getRegNumber())) {
                        foundCar = car;
                        break;
                    }
                }

                if (foundCar == null) {
                    System.out.println("Car not found with this registration number!");
                    continue;
                }

                System.out.println("Enter the new colour(s) separated with comma : ");
                String coloursString = new Scanner(System.in).nextLine().trim();

                if (coloursString.isEmpty()) {
                    System.out.println("You must write at least one colour!");
                    continue;
                }
                String[] c = coloursString.split(",");
                if (c.length > 3) {
                    System.out.println("You can't select more than 3 colours!");
                    continue;
                }

                System.out.println("Enter the new Car Price: ");
                int price = new Scanner(System.in).nextInt();
                if (price <= 0) {
                    System.out.println("Price must be a positive number!");
                    continue;
                }

                // car object in Car list automaticly will update, because in Java objects pasts as reference
                foundCar.setColours(Arrays.asList(c));
                foundCar.setPrice(price);
                System.out.println("Car successfully updated!");

                isActive = false;
            } catch (InputMismatchException ex) {
                System.out.println("Price must be a number!");
            }
        } while (isActive);

    }

    public static void deleteCar() {

        boolean isActive = true;
        while (isActive) {
            System.out.println("Write the registration number:");
            String regNumber = new Scanner(System.in).nextLine().trim().toUpperCase();

            if (regNumber.length() < 1 || regNumber.length() > 6) {
                System.out.println("Registration Number must contain from 1 to 6 alphanumerical symbols!");
                continue;
            }

            if (!regNumber.matches("^[a-zA-Z0-9]*$")) {
                System.out.println("Registration Number must contain only alphanumerical symbols!");
                continue;
            }

            Car deletedCar = new Car();
            for (Car car: Car.getCars()) {
                if (regNumber.equals(car.getRegNumber())) {
                    System.out.println("Car successfully deleted!");
                    deletedCar = car;
                    break;
                }
            }
            Car.getCars().remove(deletedCar);
            isActive = false;
        }

    }

}
