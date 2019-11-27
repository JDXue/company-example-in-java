import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args){
        Salary newSalary = new Salary(20000, "GBP");
//        Employee e1 = new Employee("bob", "12/12/1988", newSalary);
//
////        System.out.println("Hi I am " + e1.getName() + ", I was born " + e1.getDateOfBirth() + " my salary is " + e1.getSalary());


        Manager bob = new Manager("Bob", "20/12/1984", newSalary);
//        System.out.println("Hi I am " + m1.getName() + " I was born " + m1.getDateOfBirth());



//
//
        Programmer jeff = new Programmer("Jeff", "20/11/1984", newSalary, 0.2);
        System.out.println(jeff);

        jeff.programApplication();

            List<Employee> myEmployees = new ArrayList();
            myEmployees.add(jeff);
            myEmployees.add(bob);

            Company myCompany = new Company(myEmployees);

            System.out.println(myCompany);

            Bonus bonus = new Bonus();

            bonus.bonusCalculator(bob);
            bonus.bonusCalculator(jeff);

    }
}
