package cs2263_hw03;

/**
 * THis class is the base course class. This holds all the information about the individual courses, as well as the
 * only copy of the departments and depCodes. They are mapped together by having the same index on the arrays.
 *
 * @author Grant Madson
 */
public class Course {

    private final static String[] departments = {"Computer Science", "Mathematics", "Chemistry", "Physics", "Biology", "Electrical Engineering"};
    private final static String[] deptCodes = {"CS", "MATH", "CHEM", "PHYS", "BIOL", "EE"};
    private final int courseNumber;
    private final String name;
    private final int credits;
    private final int department;

    /**
     * The constructor for Course. It takes in the coursenumber, name, the credits, and the index of the department it is in.
     *
     * @param courseNumber
     * @param name
     * @param credits
     * @param department
     */
    public Course(int courseNumber, String name, int credits, int department) {
        this.courseNumber = courseNumber;
        this.name = name;
        this.credits = credits;
        this.department = department;
    }


    /**
     * The getter for the course number
     * @return the course number
     */
    public int getCourseNumber() {
        return courseNumber;
    }

    /**
     * Getter for the course name
     * @return string name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the number of credits in the course
     * @return number of credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Gets what department the course is a part of
     * @return String department
     */
    public String getDepartment() {
        return departments[department];
    }

    /**
     * Returns a copy of the list of departments
     * @return String[] of departments
     */
    public String[] getDepartments(){
        return departments.clone();
    }

    /**
     * Returns the department code that the course is a part of.
     * @return String dept code
     */
    private String getDepartmentCode() {
        return deptCodes[department];
    }

    /**
     * Getter for the list of department codes. Returns a copy of them.
     * @return String[] of dept codes
     */
    public String[] getDeptCodes(){
        return deptCodes.clone();
    }

    /**
     * The tostring method for course. Meant to make it look pretty when it made it onto the list.
     * @return string representation of the course.
     */
    public String toString(){
        return getDepartmentCode() + " " + getCourseNumber() + "    " + getName() + "   Credits: " + getCredits();
    }


}
