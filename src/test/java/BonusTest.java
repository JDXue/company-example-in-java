import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class BonusTest {

    Salary salary;
    Bonus bonus;

    @BeforeEach
    void bonusCalculatorTestSetup(){
        salary = new Salary(20000, "GBP");
        bonus = new Bonus();


//        alice = new Employee("Alice", "18/01/1999", newSalary, 0.0);
//        bob = new Manager("Bob", "20/12/1984", newSalary, 0.3);
//        jeff = new Programmer("Jeff", "20/11/1984", newSalary, 0.2);


//        List<Employee> myEmployees = new ArrayList();
//        myEmployees.add(jeff);
//        myEmployees.add(bob);
//
//        Company myCompany = new Company(myEmployees);


    }

    @Test
    void whenManagerPassed_shouldGetReturnedDouble(){
        //Given
        Manager bob = new Manager("Bob", "20/12/1984", salary, 0.3);
        System.out.println(bob);

        //When
        double salaryWithBonus = bonus.bonusCalculator(bob);
        System.out.println(salaryWithBonus);

        //Then
        assertNotNull( salaryWithBonus );

    }

    @Test
    void whenProgrammerPassed_shouldGetReturnedDouble(){
        //Given
        Programmer jeff = new Programmer("Jeff", "20/11/1984", salary, 0.2);

        //When
        double salaryWithBonus = bonus.bonusCalculator(jeff);
        System.out.println(salaryWithBonus);

        //Then
        assertNotNull( salaryWithBonus );

    }

    @Test
    void whenManagerPassed_shouldGetReturnedExpectedDouble(){
        //Given
        Manager bob = new Manager("Bob", "20/12/1984", salary, 0.3);
        System.out.println(bob);

        //When
        double expectedSalary = 26000.0;
        double salaryWithBonus = bonus.bonusCalculator(bob);
        System.out.println(salaryWithBonus);

        //Then
        assertEquals( expectedSalary, salaryWithBonus, 0.01);

    }

    @Test
    void whenProgrammerPassed_shouldGetReturnedExpectedDouble(){
        //Given
        Programmer jeff = new Programmer("Jeff", "20/11/1984", salary, 0.2);

        //When
        double expectedSalary = 24000.0;
        double salaryWithBonus = bonus.bonusCalculator(jeff);
        System.out.println(salaryWithBonus);

        //Then
        assertEquals( expectedSalary, salaryWithBonus, 0.01);

    }







}


