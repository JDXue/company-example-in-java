public class Employee {
    private String name;
    private String dateOfBirth;
    private Salary salary;

    public Employee(String name, String dateOfBirth, Salary salary){
        this.name =  name;
        this.dateOfBirth= dateOfBirth;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }


//    public double getBonus() {
//        return bonus;
//    }
//
//    public void setBonus(double bonus) {
//        this.bonus = bonus;
//    }

    @Override
    public String toString(){
        return  name + ": " + " Born: " + dateOfBirth + " Salary: " + salary;
    }
}
