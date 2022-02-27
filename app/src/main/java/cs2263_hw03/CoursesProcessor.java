package cs2263_hw03;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

/**
 * This class controls all of the stuff that goes on with JavaFX. I underestimated this project and was unable to fully
 * complete it. There are many things in here that don't work and for reasons that I don't understand with the amount of
 * time that I gave myself for this.
 * This class works, or attempts to, by using fxml files to generate what the UI should look like while this controller
 * implements the logic behind it. This controller follows and interacts with the model which is the other two classes.
 * The idea I was hoping to implement was an MVC design pattern, but the learning curve on JavaFX was a little too high
 * for this to be completed effectively.
 *
 * @author Grant Madson
 *
 *
 */
public class CoursesProcessor {


    public Button yesExit;
    public Button noExit;
    Catalog catalog = new Catalog();

    //-------------------------------course creation UI------------------
    @FXML
    private TextField credits;
    @FXML
    private TextField courseNum;
    @FXML
    private ComboBox dept;
    @FXML
    private TextField courseName;

    private final Stage courseCreation = new Stage();

    private final Stage fieldsAreEmpty = new Stage();

    private final Stage exitConf = new Stage();

    /**
     * This method is meant to be triggered when created a course and the enter key is pressed. It should trigger the
     * creation of a course or throw an exception (more of a popup really) if all the fields arent filled in.
     * It accomplishes this by using the private makeCourse method.
     *
     * @param keyEvent
     * @throws IOException
     */
    public void keyPressed(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode().getName().equals("Enter")) {
            makeCourse(Integer.parseInt(courseNum.getText()), courseName.getText(), Integer.parseInt(credits.getText()), (int)dept.getValue());
        }
    }

    /**
     * This method is meant to select which department is going to be used for showing all the courses. This is one
     * of the ones I was unable to figure out in time. I'm uncertain how to populate a vbox inside a scrollpane
     * using fxml.
     *
     * @param mouseEvent
     */
    public void deptClick(MouseEvent mouseEvent) {
        catalog.departmentCourses((String) dept.getValue());
    }

    /**
     * This is the method that handles when you click on the create course button when already in the course creation
     * menu. Functionality is offloaded to a private class.
     *
     * @param mouseEvent
     * @throws IOException
     */
    public void createCourse(MouseEvent mouseEvent) throws IOException {
        makeCourse(Integer.parseInt(courseNum.getText()), courseName.getText(), Integer.parseInt(credits.getText()), (int)dept.getValue());
    }

    /**
     *
     * A private method for taking care of course creation and the error that pops up if there isn't enough info.
     *
     * @param courseNumber
     * @param name
     * @param credits
     * @param department
     * @throws IOException
     */
    private void makeCourse(int courseNumber, String name, int credits, int department) throws IOException {
        if(courseNumber >= 0 && name != null && 0 <= credits && 0 <= department && department <= 6) {
            catalog.addCourse(new Course(courseNumber, name, credits, department));
            courseCreation.close();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("entriesAreEmpty.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            fieldsAreEmpty.setTitle("Course creation");
            fieldsAreEmpty.setScene(scene);
            fieldsAreEmpty.show();


        }
    }

    /**
     * This is meant to close the exception popup from the course creation menu, but for some reason it doesn't work.
     *
     * @param actionEvent
     */
    @FXML
    private void exceptionClick(ActionEvent actionEvent) {
        fieldsAreEmpty.close();
    }

    //--------------------------------------------------main UI-----------------------------

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button saveButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button displayButton;
    @FXML
    private ComboBox comboBox;
    @FXML
    private Button courseButton;
    @FXML
    private VBox Vbox;

    /**
     * This launches the menu for creating a new course.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void newCourse(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("courseInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        courseCreation.setTitle("Course creation");
        courseCreation.setScene(scene);
        courseCreation.show();
    }

    /**
     * This is meant to populate the vbox  with radio buttons for each course in a given department. It doesn't work though.
     *
     * @param actionEvent
     */
    public void displayButton(ActionEvent actionEvent) {
        int i = 0;
        for(Course course : catalog.departmentCourses(comboBox.getValue().toString())){
            Vbox.getChildren().add(new RadioButton(course.toString()));
        }
    }

    /**
     * This is the exit button. This one almost works, but if you decide to not shut down after pressing this, the exit
     * menu can't close.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void exit(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("exitConf.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        exitConf.setTitle("Exit confirmation");
        exitConf.setScene(scene);
        exitConf.show();
    }

    /**
     * This is meant to load in another catalog that has been saved with Gson. Unfortunately, I was unable to test this
     * due to other problems with the UI. The UI on this looks good though
     *
     * @param actionEvent
     * @throws IOException
     */
    public void loadButton(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        catalog = catalog.loadCatalog(file);
    }

    /**
     * This is meant to save the current catalog as a Json string using Gson so it can be loaded back in later. I
     * was unable to test this, unfortunately.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void saveButton(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        catalog.saveCatalog(file, catalog);
    }


    /**
     * This is the button that closes the program if you pres yes after the initial exit button
     *
     * @param actionEvent
     */
    public void yesExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    /**
     * This method is meant to close the exit menu in the case that you decide to stay, but it doesnt work.
     *
     * @param actionEvent
     */
    public void noExit(ActionEvent actionEvent) {
        exitConf.close();
        //This doesn't work and I can't find out why.
    }
}
