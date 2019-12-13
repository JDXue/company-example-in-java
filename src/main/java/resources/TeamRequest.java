package resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeamRequest {
    private int managerId;
    private int employeeId;

    public TeamRequest(
            @JsonProperty("managerId") int managerId,
            @JsonProperty("employeeId") int employeeId) {

        this.managerId = managerId;
        this.employeeId = employeeId;
    }

    public int getManagerId() {
        return managerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return "TeamRequest{" +
                "managerId=" + managerId +
                ", employeeId=" + employeeId +
                '}';
    }
}
