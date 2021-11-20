import java.sql.*;
import java.util.ArrayList;

class Officer {
    private int id;
    private String name;
    private String designation;
    private String marital_status;
    private String rank;
    private int experience;
    private int salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Officer(int id, String name, String designation, String marital_status, String rank, int experience,
            int salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.marital_status = marital_status;
        this.rank = rank;
        this.experience = experience;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID:" + this.id + "|Name:" + this.name + "|Designation:" + this.designation + "|MaritalStatus:"
                + this.marital_status + "|Rank:" + this.rank + "|Exp:" + this.experience + "|Sal:" + this.salary;
    }
}

class NavyOfficer extends Officer {
    int sailingHours;

    public NavyOfficer(int id, String name, String designation, String marital_status, String rank, int experience,
            int salary, int sailingHours) {
        super(id, name, designation, marital_status, rank, experience, salary);
        this.sailingHours = sailingHours;
    }

    @Override
    public String toString() {
        return super.toString() + "|Sailing Hours:" + this.sailingHours;
    }

}

class AirforceOfficer extends Officer {
    int flyingHours;

    public AirforceOfficer(int id, String name, String designation, String marital_status, String rank, int experience,
            int salary, int flyingHours) {
        super(id, name, designation, marital_status, rank, experience, salary);
        this.flyingHours = flyingHours;
    }

    @Override
    public String toString() {
        return super.toString() + "|Flying Hours:" + this.flyingHours;
    }
}

class ArmyOfficer extends Officer {
    int ground_excerciseHours;

    public ArmyOfficer(int id, String name, String designation, String marital_status, String rank, int experience,
            int salary, int ground_excerciseHours) {
        super(id, name, designation, marital_status, rank, experience, salary);
        this.ground_excerciseHours = ground_excerciseHours;
    }

    @Override
    public String toString() {
        return super.toString() + "|GroundExcerciseHours:" + this.ground_excerciseHours;
    }
}

class search_by {

    ArrayList<Officer> fetch_data() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/oop";
        String username = "root";
        // String password = "admin";
        String password = "root";
        Connection connection = null;
        try {
            ArrayList<Officer> arr = new ArrayList<Officer>();
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "select officer_id,officer_name,officer_designation,officer_ranks,officer_experience,officer_marital_status,officer_Salary,Training_type,No_of_hours from officer natural join officer_Salary natural join officer_Training where (officer.officer_experience=officer_Salary.officer_experience and officer.officer_designation=officer_Training.officer_designation);";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2).toUpperCase();
                String desig = rs.getString(3).toUpperCase();
                String rank = rs.getString(4).toUpperCase();
                int exp = rs.getInt(5);
                String marital_status = rs.getString(6).toUpperCase();
                int salary = rs.getInt(7);
                int hours = rs.getInt(9);
                Officer off;
                if (desig == "Navy") {
                    off = new NavyOfficer(id, name, desig, marital_status, rank, exp, salary, hours);
                } else if (desig == "Airforce") {
                    off = new AirforceOfficer(id, name, desig, marital_status, rank, exp, salary, hours);
                } else {
                    off = new ArmyOfficer(id, name, desig, marital_status, rank, exp, salary, hours);
                }
                arr.add(off);
            }
            return arr;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    void id(int id) {
        ArrayList<Officer> a = new ArrayList<Officer>();
        a = fetch_data();
        for (Officer obj : a) {
            if (obj.getId() == id) {
                System.out.println("Id found: ");
                System.out.println(obj);
                return;
            }
        }
        System.out.println("Sorry! Given Id not found");
    }

    void experience(int experience) {
        ArrayList<Officer> a = new ArrayList<Officer>();
        a = fetch_data();
        int flag = -1;

        for (Officer obj : a) {
            if (obj.getExperience() == experience) {
                if (flag == -1) {
                    System.out.println("Officers with " + experience + " years of experience are: \n");
                    flag = 0;
                }
                System.out.println(obj + "\n");
            }
        }
        if (flag == -1)
            System.out.println("Sorry! No officer with given experience found.");
    }

    void salary(int salary) {
        ArrayList<Officer> a = new ArrayList<Officer>();
        a = fetch_data();
        int flag = -1;
        for (Officer obj : a) {
            if (obj.getSalary() == salary) {
                if (flag == -1) {
                    System.out.println("Officers with " + salary + " amount of salary are: \n");
                    flag = 0;
                }
                System.out.println(obj + "\n");
            }
        }
        if (flag == -1)
            System.out.println("Sorry! No officer with given salary found.");
    }

    void field(String field, String value) {
        ArrayList<Officer> off = new ArrayList<>();
        off = fetch_data();
        int flag = 0;
        for (Officer obj : off) {
            switch (field) {
            case "name":
                if (obj.getName().indexOf(value.toUpperCase()) != -1) {
                    System.out.println(obj);
                    flag = 1;
                }
                break;

            case "designation":
                if (obj.getDesignation().indexOf(value.toUpperCase()) != -1) {
                    System.out.println(obj);
                    flag = 1;
                }
                break;

            case "rank":
                if (obj.getRank().indexOf(value.toUpperCase()) != -1) {
                    System.out.println(obj);
                    flag = 1;
                }
                break;

            case "maritalstatus":
                if (obj.getMarital_status().indexOf(value.toUpperCase()) != -1) {
                    System.out.println(obj);
                    flag = 1;
                }
                break;

            }
        }
        if (flag == 0)
            System.out.println("Given Name doesn't exist");
    }
}

public class search {
    public static void main(String[] args) {
        search_by obj = new search_by();
        if (args.length < 3) {
            System.out.println("Please enter 3 arguments");
            return;
        }
        args[1] = args[1].toLowerCase();
        if (args[1].equals("name") || args[1].equals("designation") || args[1].equals("rank")
                || args[1].equals("traininghours") || args[1].equals("maritalstatus") || args[1].equals("salary")
                || args[1].equals("experience") || args[1].equals("id")) {
            switch (args[1]) {
            case "id":
                int id = Integer.parseInt(args[2]);
                obj.id(id);
                break;
            case "experience":
                int exp = Integer.parseInt(args[2]);
                obj.experience(exp);
                break;
            case "salary":
                int sal = Integer.parseInt(args[2]);
                obj.salary(sal);
                break;
            default:
                obj.field(args[1], args[2]);
                break;
            }
        } else {
            System.out.println("Please enter correct field name");
            return;
        }
    }
}
