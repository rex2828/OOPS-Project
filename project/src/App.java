
class App {
    public static void main(String args[]) {
        switch (args[0]) {
        case "-i":
            csv_to_dbms.main(args);
            break;
        case "-h":
            help.main(args);
        }
    }
}