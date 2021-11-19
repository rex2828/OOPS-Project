import java.sql.*;

public class prepareEnv {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/oop";
        String username = "root";
        String password = "root";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement s = connection.createStatement();
            String q1 = "create table officer(Officer_id int,Officer_name varchar(70),Officer_designation varchar(50),Officer_ranks varchar(50),Officer_experience int,Officer_marital_status varchar(50), primary key (Officer_id));";
            String q2 = "create table officer_Salary(Officer_experience int,Officer_salary int, primary key (Officer_experience));";
            String q3 = "create table officer_Training(Officer_designation varchar(50), Training_type varchar(30),No_of_hours int,primary key (Officer_designation));";
            String q4 = "create procedure `Insertion`(in id int,in name varchar(70),in designation varchar(50),in ranks varchar(50),in experience int,in marital_status varchar(50))"
                    + "begin " + " insert into officer values(id,name,designation,ranks,experience,marital_status); "
                    + "end";
            String q5 = "create procedure `Deletion`(in id int) " + "begin "
                    + " delete from officer where Officer_id = id; " + "end";
            String q6 = "insert into officer_salary values(1,50000);";
            String q7 = "insert into officer_salary values(2,60000);";
            String q8 = "insert into officer_salary values(3,70000);";
            String q9 = "insert into officer_salary values(4,80000);";
            String q10 = "insert into officer_salary values(5,90000);";
            String q11 = "insert into officer_salary values(6,100000);";

            String q12 = "insert into officer_training values('Airforce','flying hours',12);";
            String q13 = "insert into officer_training values('Navy','sailing hours',15);";
            String q14 = "insert into officer_training values('Army','ground exercise hours',13);";

            s.addBatch(q1);
            s.addBatch(q2);
            s.addBatch(q3);
            s.addBatch(q4);
            s.addBatch(q5);
            s.addBatch(q6);
            s.addBatch(q7);
            s.addBatch(q8);
            s.addBatch(q9);
            s.addBatch(q10);
            s.addBatch(q11);
            s.addBatch(q12);
            s.addBatch(q13);
            s.addBatch(q14);
            s.executeBatch();
            connection.close();
            System.out.println("Done!");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
