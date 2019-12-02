import com.starlingbank.company.entities.*;
import com.starlingbank.company.services.HRApplication;
import com.starlingbank.externalservices.CourseService;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args){
        Salary newSalary = new Salary(20000, "GBP");
//        com.starlingbank.company.entities.Employee e1 = new com.starlingbank.company.entities.Employee("bob", "12/12/1988", newSalary);
//
////        System.out.println("Hi I am " + e1.getName() + ", I was born " + e1.getDateOfBirth() + " my salary is " + e1.getSalary());


        Manager bob = new Manager("Bob", "20/12/1984", newSalary);
//        System.out.println("Hi I am " + m1.getName() + " I was born " + m1.getDateOfBirth());

        System.out.println(bob);



//
//
        Programmer jeff = new Programmer("Jeff", "20/11/1984", newSalary);
        System.out.println(jeff);

        jeff.programApplication();

            List<Employee> myEmployees = new ArrayList();
            myEmployees.add(jeff);
            myEmployees.add(bob);

            Company myCompany = new Company(myEmployees);

            System.out.println(myCompany);

//        CourseService courseService = new CourseService();
//
//            HRApplication bonus = new HRApplication(courseService);
//
//            bonus.calculateBonus(bob);
//            bonus.calculateBonus(jeff);

    }
}
