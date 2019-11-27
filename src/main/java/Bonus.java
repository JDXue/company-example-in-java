class Bonus {

    double bonusCalculator(Employee employee) {
        double bonusCalculated = 0;

        if (employee instanceof Manager) {
            //do something
            bonusCalculated = employee.getSalary().getAmount() + (employee.getSalary().getAmount());
            System.out.println(bonusCalculated);
            return bonusCalculated;

        } else if (employee instanceof Programmer) {
            bonusCalculated = employee.getSalary().getAmount() + (employee.getSalary().getAmount());
            System.out.println(bonusCalculated);
            return bonusCalculated;

        }

        return bonusCalculated;
    }




}


//calculateManagerBonus(Manager parameter) {
//    getManagerBonus(new Manager { getBonus() {parameter.getBonus();});
//}


