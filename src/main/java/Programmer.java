public class Programmer extends Employee {
    private double bonus = 0.2;

    public Programmer(String name, String dateOfBirth, Salary salary, double bonus){
        super(name, dateOfBirth, salary);

        this.bonus = bonus;
    }

    public void programApplication(){
        System.out.println("I am programming a cool application");
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

//    @Override
//    public String toString() {
//
//        return  getName() + ": " + " Born: " + getDateOfBirth() + " Salary: " + getSalary() + " Salary with added bonus: " + bonusCalculator() ;
//    }
}
