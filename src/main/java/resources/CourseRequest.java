package resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseRequest {

    private int employeeId;
    private int courseId;

    public CourseRequest(
      @JsonProperty("employeeId") int employeeId,
      @JsonProperty("courseId") int courseId ){

        this.employeeId = employeeId;
        this.courseId = courseId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return "CourseRequest{" +
                "employeeId=" + employeeId +
                ", courseId=" + courseId +
                '}';
    }
}
