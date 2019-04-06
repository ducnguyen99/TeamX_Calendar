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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

//A window to help user see full details of an events
public class EventDetails {
    private VBox eventDetailsWindow;
    private Button update;
    private Button delete;
    private Button ok;
    private ArrayList<Text> info = new ArrayList<>(11);

    public EventDetails(){
        eventDetailsWindow = new VBox();
        eventDetailsWindow.setAlignment(Pos.CENTER);
        eventDetailsWindow.setPrefSize(500,450);

        HBox headerBox = new HBox();
        Text header = new Text("Event");
        header.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD,50));
        header.setFill(Color.WHITE);
        headerBox.setPadding(new Insets(10,10,10,10));
        headerBox.setAlignment(Pos.TOP_CENTER);
        headerBox.setStyle("-fx-background-color: GREEN;"+"-fx-border-color: black;");
        headerBox.getChildren().add(header);

        //Create and arrange title area
        HBox titleBox = new HBox(67);
        titleBox.setPadding(new Insets(10,10,10,10));
        titleBox.setPrefWidth(1000);
        Text titleLabel = new Text("Title: ");
        titleLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
        Text title = new Text();
        title.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        titleBox.getChildren().addAll(titleLabel,title);
        info.add(title);

        //Create and arrange start date area
        HBox startDateBox = new HBox(10);
        startDateBox.setPadding(new Insets(10,10,10,10));
        startDateBox.setPrefWidth(1000);
        Text startDateLabel = new Text("Start Date: ");
        startDateLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
        Text startDate = new Text();
        startDate.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        startDateBox.getChildren().addAll(startDateLabel,startDate);
        info.add(startDate);

        //Create and arrange end date area
        HBox endDateBox = new HBox(19);
        endDateBox.setPadding(new Insets(10,10,10,10));
        endDateBox.setPrefWidth(1000);
        Text endDateLabel = new Text("End Date: ");
        endDateLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
        Text endDate = new Text();
        endDate.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        endDateBox.getChildren().addAll(endDateLabel,endDate);
        info.add(endDate);

        //Create and arrange start time area
        HBox startTimeBox = new HBox(5);
        startTimeBox.setPadding(new Insets(10,10,10,10));
        startTimeBox.setPrefWidth(1000);
        Text startTimeLabel = new Text("Start Time: ");
        startTimeLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
        Text startTime = new Text();
        startTime.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        startTimeBox.getChildren().addAll(startTimeLabel,startTime);
        info.add(startTime);

        //Create and arrange end time area
        HBox endTimeBox = new HBox(15);
        endTimeBox.setPadding(new Insets(10,10,10,10));
        endTimeBox.setPrefWidth(1000);
        Text endTimeLabel = new Text("End Time: ");
        endTimeLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
        Text endTime = new Text();
        endTime.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        endTimeBox.getChildren().addAll(endTimeLabel,endTime);
        info.add(endTime);

        //Create and arrange location area
        HBox locationBox = new HBox(27);
        locationBox.setPadding(new Insets(10,10,10,10));
        locationBox.setPrefWidth(1000);
        Text locationLabel = new Text("Location: ");
        locationLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
        Text location = new Text();
        location.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        locationBox.getChildren().addAll(locationLabel,location);
        info.add(location);

        //Create and arrange description area
        HBox descriptionBox = new HBox(0);
        descriptionBox.setPadding(new Insets(10,10,10,10));
        descriptionBox.setPrefWidth(1000);
        Text descriptionLabel = new Text("Description: ");
        descriptionLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
        Text description = new Text();
        description.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        descriptionBox.getChildren().addAll(descriptionLabel,description);
        info.add(description);

        //Create and arrange owner area
        HBox ownerBox = new HBox(50);
        ownerBox.setPadding(new Insets(10,10,10,10));
        ownerBox.setPrefWidth(1000);
        Text ownerLabel = new Text("Owner: ");
        ownerLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
        Text owner = new Text();
        owner.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        ownerBox.getChildren().addAll(ownerLabel,owner);
        info.add(owner);

        //Create and arrange guest list area
        HBox guestListBox = new HBox(16);
        guestListBox.setPadding(new Insets(10,10,10,10));
        guestListBox.setPrefWidth(1000);
        Text guestListLabel = new Text("Guest List: ");
        guestListLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
        Text guestList = new Text();
        guestList.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        guestListBox.getChildren().addAll(guestListLabel,guestList);
        info.add(guestList);

        //Create and arrange notification area
        HBox notiBox = new HBox(8);
        notiBox.setPadding(new Insets(10,10,10,10));
        notiBox.setPrefWidth(1000);
        Text notiLabel = new Text("Notification: ");
        notiLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
        Text notiTime = new Text();
        notiTime.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        Text notiType = new Text();
        notiType.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        notiBox.getChildren().addAll(notiLabel,notiTime,notiType);
        info.add(notiTime);
        info.add(notiType);

        eventDetailsWindow.getChildren().addAll(headerBox, titleBox, startDateBox, endDateBox, startTimeBox, endTimeBox, locationBox, descriptionBox, ownerBox, guestListBox,notiBox);

        //Create and arrange buttons update and delete
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.TOP_LEFT);
        update = new Button("UPDATE");
        delete = new Button("DELETE");
        buttonBox.setAlignment(Pos.BASELINE_CENTER);
        buttonBox.getChildren().addAll(update,delete);

        ok = new Button("OK");

        eventDetailsWindow.getChildren().addAll(buttonBox,ok);
    }

    public ArrayList<Text> getInfo() {
        return info;
    }

    public Button getDelete() {
        return delete;
    }

    public Button getOk() {
        return ok;
    }

    public Button getUpdate() {
        return update;
    }

    public VBox getEventDetailsWindow() {
        return eventDetailsWindow;
    }
}
