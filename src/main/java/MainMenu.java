import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainMenu implements Menu {


    static String url = "";

    public MainMenu(String url) {
        this.url = url;
    }


    public void system() {

        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu(url);

        boolean menu = true;

        while (menu) {

            System.out.println("\n1. Log in as a manager");
            System.out.println("2. Log in as a customer");
            System.out.println("3. Create a customer");
            System.out.println("0. Exit\n");

            switch (scanner.nextInt()){

                case 1:
                    secondMenu();
                    break;

                case 2:

                    Map<Integer,String> list = mainMenu.getCustomerList();
                    if (list.isEmpty()) {
                        System.out.println("\nThe customer list is empty!\n");
                        break;
                    } else {
                        CustomerMenu(list);
                    }
                    break;

                case 3:
                    System.out.println("\nEnter the customer name:");
                    mainMenu.addCustomer();
                    System.out.println("The customer was added!\n");
                    break;

                case 0:
                    menu = false;
                    break;
            }

        }
    }
    static void secondMenu() {

        Scanner scanner = new Scanner(System.in);

        boolean menu = true;
        MainMenu mainMenu = new MainMenu(url);

        while (menu) {
            System.out.println("\n1. Company list");
            System.out.println("2. Create a company");
            System.out.println("0. Back");

            switch(scanner.nextInt()) {

                case 1:

                    Map<Integer, String> list = mainMenu.getCompanyList();
                    if (list.isEmpty()) {
                        System.out.println("The company list is empty!\n");
                        break;
                    } else {
                        CompanyMenu(list);
                    }
                    break;

                case 2:
                    System.out.println("\nEnter the company name:");
                    mainMenu.addCompany();
                    System.out.println("The company was created!\n");
                    break;

                case 0:
                    menu = false;
            }
        }

    }

    static void CompanyMenu( Map<Integer, String> list) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = true;

        while(exit) {
            System.out.println("\nChoose the company:");

            for (int i = 1 ; i < list.size() + 1 ; i++) {
                System.out.println(i + ". " + list.get(i));
            }
            System.out.println("0. Back\n");
            int key = scanner.nextInt();

            switch(key) {
                case 0:
                    exit = false;
                    break;

                default:
                    if (list.containsKey(key)) {
                        CarMenu(key, list.get(key));
                        exit = false;
                    }
            }
        }


    }


    static void CarMenu (int id, String name) {

        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu(url);

        boolean exit = true;
        while (exit) {

            System.out.println("\n'" + name + "' company:");
            System.out.println("1. Car list");
            System.out.println("2. Create a car");
            System.out.println("0. Back\n");

            switch (scanner.nextInt()) {

                case 0:
                    exit = false;
                    break;

                case 1:
                    List<String> list = mainMenu.getCarList(id);
                    if (list.isEmpty()) {
                        System.out.println("The car list is empty!\n");
                        break;
                    }

                    System.out.println("Car list:");
                    for (int i = 0 ; i < list.size(); i++) {
                        System.out.println((i+1) + ". " + list.get(i));
                    }
                    break;

                case 2:
                    System.out.println("\nEnter the car name:");
                    mainMenu.addCar(id);
                    System.out.println("The car was added!");
                    break;
            }

        }

    }

    static void CustomerMenu(Map<Integer, String> list) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = true;

        while (exit) {

            System.out.println("\nCustomer list:");

            for (int i = 1; i< list.size()+1 ; i++) {
                System.out.println(i + ". " + list.get(i));
            }

            System.out.println("0. Back\n");
            int key = scanner.nextInt();

            switch(key) {
                case 0:
                    exit = false;
                    break;

                default:
                    if (list.containsKey(key)) {
                        RentMenu(list.get(key));
                        exit = false;
                    }
            }

        }



    }

    static void RentMenu(String nameCust) {

        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu(url);
        boolean exit = true;

        while (exit) {

            System.out.println("\n1. Rent a car");
            System.out.println("2. Return a rented car");
            System.out.println("3. My rented car");
            System.out.println("0. Back\n");

            switch(scanner.nextInt()) {

                case 1:
                    if (mainMenu.getCompanyList().isEmpty()) {
                        System.out.println("The company list is empty!\n");
                        break;
                    }
                    if (mainMenu.isRented(nameCust)) {
                        System.out.println("You've already rented a car!\n");
                        break;
                    }
                    CustomerCompanyList(mainMenu.getCompanyList(),nameCust);
                    break;

                case 2:
                    if(!mainMenu.isRented(nameCust)) {
                        System.out.println("You didn't rent a car!\n");
                        break;
                    }
                    mainMenu.returnCar(nameCust);
                    System.out.println("You've returned a rented car!\n");
                    break;

                case 3:
                    if(!mainMenu.isRented(nameCust)) {
                        System.out.println("You didn't rent a car!\n");
                        break;
                    }
                    List<String> list = mainMenu.myCar(nameCust);
                    System.out.println("\nYour rented car:");
                    System.out.println(list.get(0));
                    System.out.println("Company:");
                    System.out.println(list.get(1) +"\n");

                    break;

                case 0:
                    exit = false;
                    break;
            }

        }

    }

static void CustomerCompanyList(Map<Integer,String> list, String nameCust) {

    Scanner scanner = new Scanner(System.in);
    boolean exit = true;

    while(exit) {
        System.out.println("\nChoose the company:");

        for (int i = 1 ; i < list.size() + 1 ; i++) {
            System.out.println(i + ". " + list.get(i));
        }
        System.out.println("0. Back\n");
        int key = scanner.nextInt();

        switch(key) {
            case 0:
                exit = false;
                break;

            default:
                if (list.containsKey(key)) {

                    CustomerCarMenu(key, list.get(key),nameCust);
                    exit = false;
                }
        }
    }
}

static void CustomerCarMenu(int id , String name , String nameCust) {

    MainMenu mainMenu = new MainMenu(url);
    Scanner scanner = new Scanner(System.in);
    boolean exit = true;
    List<String>  list = mainMenu.getAvailableCarList(id);
    if (list.isEmpty()) {
        System.out.println("No available cars in the '" + name + "' company");
        exit = false;
    }

    while (exit) {


        System.out.println("\nChoose a car:");

        for (int i = 0 ; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i));
        }
        System.out.println("0. Back\n");

        int key = scanner.nextInt();

        switch(key) {

            case 0:
                exit = false;
                break;

            default:
                if ((list.size()+1) > key && key > 0) {
                    mainMenu.rent(list.get(key-1),nameCust);
                    System.out.println("You rented '" + list.get(key-1) + "'\n");
                    exit = false;
                    break;

            }

        }

    }

}



    @Override
    public Map<Integer, String> getCompanyList() {
        DBClass db = new DBClass(url);
        return db.getCompanyList();

    }
    @Override
    public void addCompany() {
        Scanner scanner = new Scanner(System.in);
        DBClass db = new DBClass(url);
        db.addCompany(scanner.nextLine());
    }
    @Override
    public void addCar(int id){
        Scanner scanner = new Scanner(System.in);
        DBClass db = new DBClass(url);
        db.addCar(scanner.nextLine(), id);
    }
    @Override
    public List<String> getCarList(int id) {
        DBClass db = new DBClass(url);
        return db.getCarList(id);
    }
    @Override
    public void addCustomer(){
        Scanner scanner = new Scanner(System.in);
        DBClass db = new DBClass(url);
        db.addCustomer(scanner.nextLine());

    }
    @Override
    public Map<Integer, String> getCustomerList() {
        DBClass db = new DBClass(url);
        return db.getCustomerList();
    }
    @Override
    public List<String> getAvailableCarList(int id) {
        DBClass db = new DBClass(url);
        return db.getAvailableCarList(id);
    }
    @Override
    public boolean isRented(String name) {
        DBClass db = new DBClass(url);
        return  db.isRented(name);
    }
    @Override
    public void rent(String name,String nameCust) {
        DBClass db = new DBClass(url);
        db.rent(name, nameCust);
    }
    @Override
    public void returnCar(String name) {
        DBClass db = new DBClass(url);
        db.returnCar(name);
    }
    @Override
    public List<String> myCar(String name) {
        DBClass db = new DBClass(url);
        return db.myCar(name);
    }
}
