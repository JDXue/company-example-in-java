package resources;

public class SalaryRequest {
    private int amount;
    private String currency;

    public SalaryRequest(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "SalaryRequest{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
