package resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import java.time.LocalDate;

public class EmployeeRequest {
    private String name;
    private String dateOfBirth;
    private int salaryId;
    private String employeeType;

    public EmployeeRequest(@JsonProperty("name") String name,
                           @JsonProperty("dateOfBirth") String dateOfBirth,
                           @JsonProperty("salaryId") int salaryId,
                           @JsonProperty("employeeType") String employeeType) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salaryId = salaryId;
        this.employeeType = employeeType;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getSalaryId(){
        return salaryId;
    }

    public String getEmployeeType() { return employeeType; }

    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", salaryId=" + salaryId +
                ", employeeType='" + employeeType + '\'' +
                '}';
    }
}
