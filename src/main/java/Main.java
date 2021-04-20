
public class Main {

    private static final String DBPath = "./src/carsharing/db/";

    public static void main(String[] args) {

        String url = "jdbc:h2:" + DBPath + "carsharing";

        if (args.length > 1 && "-databaseFileName".equals(args[0])) {
            url = "jdbc:h2:" + DBPath +args[1] ;

        }
        DBClass db = new DBClass(url);

        db.createTable();

        MainMenu menu = new MainMenu(url);
        menu.system();


    }
}

