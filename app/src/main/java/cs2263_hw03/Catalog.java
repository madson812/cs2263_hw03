package cs2263_hw03;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This is the catalog of the program. This contains all the instances of Course and organizes them.
 *
 * @author Grant Madson
 */
public class Catalog {
    private ArrayList<Course> courses = new ArrayList<Course>();

    /**
     * This is the constructor. This one is used for an empty catalog.
     */
    public Catalog(){
        new Catalog(null);
    }

    /**
     * This is the overloaded constructor which is used for loading in a catalog from memory.
     *
     * @param courses
     */
    public Catalog(ArrayList<Course> courses){
        this.courses = courses;
    }

    /**
     * This is the method for adding a new course to the catalog.
     *
     * @param course
     */
    public void addCourse(Course course){
        courses.add(course);
    }

    /**
     * This removes a course from the catalog by finding the first matching instance of the course you provide
     * and then bonking it out of the system.
     *
     * @param course
     */
    public void removeCourse(Course course){
        courses.remove(course);
    }

    /**
     * This is the save catalog method. It requires a file location and a catalog object in order to save.
     *
     * @param file
     * @param catalog
     * @throws IOException
     */
    public void saveCatalog(File file, Catalog catalog) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(file);
        writer.write(gson.toJson(catalog));
    }

    /**
     * This is the loa catalog method. This returns a catalog and requires the file in order to run.
     *
     * @param file
     * @return returns a catalog object that replaces the current one
     * @throws IOException
     */
    public Catalog loadCatalog(File file) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileReader reader = new FileReader(file);
        return gson.fromJson(reader, Catalog.class);
    }

    /**
     * This is the method for finding every course that belongs to a certain department. Requires to know which
     * department is being looked through.
     *
     * @param dept
     * @return returns a list of the courses in the department
     */
    public ArrayList<Course> departmentCourses(String dept){
        ArrayList<Course> inDept = new ArrayList<Course>();
        for(Course course : courses){
            if(course.getDepartment().equals(dept)){
                inDept.add(course);
            }
        }
        return inDept;
    }

}
