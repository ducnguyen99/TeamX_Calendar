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
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EventWindow {

    private VBox eventWindow;
    private TextField title = new TextField();
    private DatePicker startDate = new DatePicker();
    private DatePicker endDate = new DatePicker();
    private TextField startTime = new TextField();
    private TextField endTime = new TextField();
    private TextField location = new TextField();
    private TextField description = new TextField();
    private TextField owner = new TextField();
    private TextField guestList = new TextField();
    private TextField notiTime = new TextField();
    private ComboBox<String> notiType = new ComboBox<String>();

    private ArrayList<TextField> info = new ArrayList<>(8);
    private ArrayList<DatePicker> infoDate = new ArrayList<>(2);
    private Text error;
    private Button close;
    private Button save;

    //Window to help user create event
    public EventWindow() {
        eventWindow = new VBox();
        eventWindow.setPrefSize(550,450);

        startDate.setConverter(converter);
        endDate.setConverter(converter);

        //Create and arrange information areas
        HBox titleBox = createBox(new Text("Title: "), title, "Type in the title...", 67);
        info.add(title);

        HBox startDateBox = createDatePickerBox(new Text("Start Date: "), startDate,"dd-mm-yyyy",10);
        infoDate.add(startDate);

        HBox endDateBox = createDatePickerBox(new Text("End Date: "), endDate,"dd-mm-yyyy",19);
        infoDate.add(endDate);

        HBox startTimeBox = createBox(new Text("Start Time: "), startTime,"HH:mm", 5);
        info.add(startTime);

        HBox endTimeBox = createBox(new Text("End Time: "), endTime, "HH:mm", 15);
        info.add(endTime);

        HBox locationBox = createBox(new Text("Location: "), location,"Type in the location...", 27);
        info.add(location);

        HBox ownerBox = createBox(new Text("Owner: "), owner,"Type in owner name...", 48);
        info.add(owner);

        HBox descriptionBox = createBox(new Text("Description: "), description,"Type in description...", 0);
        info.add(description);

        HBox guestListBox = createBox(new Text("Guest List: "), guestList,"guest1@gmail.com guest2@gmail.com", 14);
        info.add(guestList);

        HBox notiBox = createBox(new Text("Notification: "), notiTime,"Type in number of minutes/hours...", 0);
        info.add(notiTime);

        notiType.getItems().addAll("Minute(s)", "Hour(s)");
        notiBox.getChildren().add(notiType);

        eventWindow.getChildren().addAll(titleBox, startDateBox, endDateBox, startTimeBox, endTimeBox, locationBox, descriptionBox, ownerBox, guestListBox, notiBox);

        //Create and arrange errors message
        HBox errorBox = new HBox();
        errorBox.setAlignment(Pos.CENTER);
        errorBox.setPadding(new Insets(10,0,0,0));
        errorBox.setPrefWidth(500);
        error = new Text("Invalid Dates, Times or Notification Time");
        error.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
        error.setFill(Color.RED);
        error.setVisible(false);
        errorBox.getChildren().add(error);
        eventWindow.getChildren().add(errorBox);

        //Create and arrange buttons
        HBox button = new HBox();
        save = new Button("Save");
        close = new Button("Close");
        button.setAlignment(Pos.BASELINE_CENTER);
        button.getChildren().addAll(save,close);

        eventWindow.getChildren().add(button);
    }

    //Function to design box containing text field
    public HBox createBox (Text list, TextField text, String promptText, int i){

        HBox singleBox = new HBox(i);
        //line.setStyle("-fx-border-color: black;"+"-fx-background-color: #cccccc");
        //line.setAlignment(Pos.CENTER);
        singleBox.setPadding(new Insets(10,10,10,10));
        singleBox.setPrefWidth(1000);
        //Text name = new Text("Title: ");
        list.setFont(Font.font("Times New Roman",FontWeight.BOLD,24));
        text.setPrefColumnCount(20);
        text.setFocusTraversable(false);
        text.setPromptText(promptText);
        singleBox.getChildren().addAll(list,text);
        return singleBox;
    }

    //Function to design box containing date picker
    public HBox createDatePickerBox (Text list, DatePicker datePicker, String promptText, int i) {
        HBox singleBox = new HBox(i);
        singleBox.setPadding(new Insets(10,10,10,10));
        singleBox.setPrefWidth(1000);
        list.setFont(Font.font("Times New Roman",FontWeight.BOLD,24));
        datePicker.setPrefWidth(300);
        datePicker.setFocusTraversable(false);
        datePicker.setPromptText(promptText);
        singleBox.getChildren().addAll(list,datePicker);
        return singleBox;
    }

    //Create string converter
    public StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");

        @Override
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormatter.format(date);
            } else {
                return "";
            }
        }
        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }
    };

    public VBox getView() {
        return eventWindow;
    }

    public Text getError() {
        return error;
    }

    public Button getClose() {
        return close;
    }

    public Button getSave() {
        return save;
    }

    public TextField getTitle() {
        return title;
    }

    public DatePicker getStartDate() {
        return startDate;
    }

    public DatePicker getEndDate() {
        return endDate;
    }

    public TextField getStartTime() {
        return startTime;
    }

    public TextField getEndTime() {
        return endTime;
    }

    public TextField getLocation() {
        return location;
    }

    public TextField getDescription() {
        return description;
    }

    public TextField getGuestList() {
        return guestList;
    }

    public TextField getOwner() {
        return owner;
    }

    public ComboBox<String> getNotiType() {
        return notiType;
    }

    public TextField getNotiTime() {
        return notiTime;
    }

    public StringConverter<LocalDate> getConverter() {
        return converter;
    }

    public ArrayList<DatePicker> getInfoDate() {
        return infoDate;
    }

    public ArrayList<TextField> getInfo() {
        return info;
    }
}