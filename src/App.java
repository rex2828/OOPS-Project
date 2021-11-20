class App {
    public static void Help() {
        System.out.println("\tWelcome to our OOP Mini Project");
        System.out.println("\tThis is the help menu");
        System.out.println("To load data from csv files to database ==> java App -load");
        System.out.println("To search some records ==> javac App.java -search -{record name} -{key to search}");
    }

    public static void main(String args[]) {
        if (args.length < 1 || args[0].equals("-h")) {
            Help();
            return;
        }
        switch (args[0]) {
        case "-load":
            csv_to_dbms.main(args);
            break;
        case "-search":
            search.main(args);
            break;
        case "-delete":
            deletion.main(args);
            break;
        case "-prepenv":
            prepareEnv.main(args);
            break;
        case "-help":
            Help();
            break;
        default:
            System.out.println("Give correct arguments. Use -help for info.");
            break;
        }
    }
}
