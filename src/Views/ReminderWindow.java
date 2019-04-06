/*

RMIT University Vietnam
Course: INTE2512 Object-Oriented Programming
Semester: 2018C
Assessment: Assignment 3 - Class Diagram
Authors: Nguyen Huu Ngoc Duc, Duong Minh Nhat, Nguyen Minh Tri
ID: S3680598, S3715125, S3726096
Created date:  17/01/2019
Acknowledgement:
Java Naming Convention, Java 8 API  (https://docs.oracle.com/javase/8/docs/api/)
https://gist.github.com/jewelsea/2658491?fbclid=IwAR3Vkg6UMcBJ0DjuDnw56wcAcej2A7G7jmWOqX7tNWG0MK39J-QLNUeJ-QU
https://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/?fbclid=IwAR14MgPJu91xJh61Lkru6g6OA-kPTzvPQi3mxu1GPr5ojXuzUQ57Cns7kXY
https://gist.github.com/jewelsea/2658491?fbclid=IwAR1kF3zNiIJjIbgg0MLx4J0RtLIcOO9hpucSGIE3IBWMsMQQdJOo3n9afYg
https://www.tutorialspoint.com/java/java_date_time.htm?fbclid=IwAR0eSC7M2i79JGrFDHmG-zQtM8113mIytetaaAdsgqkCJ7gkTiNOEnHZy3w
https://gist.github.com/jewelsea/2658491?fbclid=IwAR1WW_9I8imgCJT28mGUg11UIDcSxVcMzukGZV_rdvnYAM-crwC3nRP2HwI
https://github.com/SirGoose3432/javafx-calendar
https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method?fbclid=IwAR0y14VzptPFpZAn00b18Gwmq3mM09boMMG9vqPv-HusEu6SRnV4HQeWQMQ
https://stackoverflow.com/questions/20231539/java-check-the-date-format-of-current-string-is-according-to-required-format-or
https://o7planning.org/en/11085/javafx-datepicker-tutorial
*/

package Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ReminderWindow {

    private VBox reminderWindow;
    public EventWindow event = new EventWindow();
    private Button save;
    private Button close;
    private TextField title = new TextField();
    private Text error;
    private DatePicker date = new DatePicker();
    private TextField time = new TextField();
    private ComboBox<String> repeatType;
    //Window to help user create reminder
    public ReminderWindow() {

        reminderWindow = new VBox();
        reminderWindow.setPrefSize(500,250);

        date.setConverter(event.getConverter()); //Import date converter from EventWindow class

        //Create information boxes
        HBox titleBox = event.createBox(new Text("Title: "), title,"Type in the title...", 10);
        HBox dateBox = event.createDatePickerBox(new Text("Date: "), date,"dd-mm-yyyy", 10);
        HBox timeBox = event.createBox(new Text("Time: "), time,"HH:mm", 4);
        HBox repeatTypeBox = createRepeatBox(new Text("Repeat Type"));

        reminderWindow.getChildren().addAll(titleBox, dateBox, timeBox, repeatTypeBox);

        //Create and arrange errors messages
        HBox errorBox = new HBox();
        errorBox.setAlignment(Pos.CENTER);
        errorBox.setPadding(new Insets(10,0,0,0));
        errorBox.setPrefWidth(500);
        error = new Text("Invalid Dates, Times or Emails");
        error.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
        error.setFill(Color.RED);
        error.setVisible(false);
        errorBox.getChildren().add(error);
        reminderWindow.getChildren().add(errorBox);

        //Create buttons
        HBox button = new HBox();
        save = new Button("Save");
        close = new Button("Close");
        button.setAlignment(Pos.BASELINE_CENTER);
        button.getChildren().addAll(save,close);

        reminderWindow.getChildren().add(button);
    }

    public HBox createRepeatBox (Text list) {
        HBox singleLine = new HBox(10);
        singleLine.setPadding(new Insets(10,10,10,10));
        singleLine.setPrefWidth(1000);

        list.setFont(Font.font("Times New Roman",FontWeight.BOLD,24));

        repeatType = new ComboBox<String>();

        repeatType.getItems().addAll("No Repeat","Daily", "Weekly", "Monthly", "Yearly", "Custom");
        //repeatType.setValue("WEEK");
        singleLine.setAlignment(Pos.BASELINE_LEFT);
        singleLine.getChildren().addAll(list, repeatType);

        return singleLine;
    }

    public VBox getView() {
        return reminderWindow;
    }

    public Button getSave() { return save; }

    public Button getClose() { return close;}

    public TextField getTitle() {
        return title;
    }

    public DatePicker getDate() {
        return date;
    }

    public TextField getTime() {
        return time;
    }

    public Text getError() {
        return error;
    }

    public ComboBox<String> getRepeatType() {
        return repeatType;
    }

    public EventWindow getEvent() {
        return event;
    }
}
