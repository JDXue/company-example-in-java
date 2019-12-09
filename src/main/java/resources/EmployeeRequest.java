package resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import java.time.LocalDate;

public class EmployeeRequest {
    private String name;
    private LocalDate dateOfBirth;
    private SalaryRequest salaryRequest;

    public EmployeeRequest(@JsonProperty("name") String name,
                           @JsonProperty("dateOfBirth") LocalDate dateOfBirth,
                           @JsonProperty("salary") SalaryRequest salaryRequest) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salaryRequest = salaryRequest;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public SalaryRequest getSalaryRequest() {
        return salaryRequest;
    }

    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", salaryRequest=" + salaryRequest +
                '}';
    }
}
