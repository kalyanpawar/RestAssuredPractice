package generator;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Employee {
    private Faker faker;

    public Employee(){
        this.faker =  new Faker();
    }

    // Generate a random first name
    public String getFirstName() {
        return faker.name().firstName();
    }

    // Generate a random last name
    public String getLastName() {
        return faker.name().lastName();
    }

    // Generate a random date of birth
    public String getDOB() {
        Date dob = faker.date().birthday(18, 65); // Ages 18 to 65
        return new java.sql.Date(dob.getTime()).toLocalDate().toString();
    }

    // Calculate age based on date of birth
    public int getAge() {
        Date dob = faker.date().birthday(18, 65);
        LocalDate dob1 = new java.sql.Date(dob.getTime()).toLocalDate();
        return Period.between(dob1, LocalDate.now()).getYears();
    }

    // Generate a random salary between 30,000 and 120,000
    public double getSalary() {
        return faker.number().numberBetween(30000, 120000);
    }

    // Get the current date
    public LocalDate getDate() {
        return LocalDate.now();
    }

    // Generate a random email
    public String getEmail() {
        return faker.internet().emailAddress();
    }

//    public static void main(String[] args) {
//        Employee employee = new Employee();
//        System.out.println("First Name: " + employee.getFirstName());
//        System.out.println("Last Name: " + employee.getLastName());
//        System.out.println("Date of Birth: " + employee.getDOB());
//        System.out.println("Age: " + employee.getAge());
//        System.out.println("Salary: " + employee.getSalary());
//        System.out.println("Date: " + employee.getDate());
//        System.out.println("Email: " + employee.getEmail());
//    }
}
