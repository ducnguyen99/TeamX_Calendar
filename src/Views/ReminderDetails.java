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

//A window help user to see all details of reminder
public class ReminderDetails {
    private VBox reminderDetailsWindow;
    private Button update;
    private Button delete;
    private Button ok;
    ArrayList<Text> info = new ArrayList<>(4);

    public ReminderDetails(){
        reminderDetailsWindow = new VBox();
        reminderDetailsWindow.setAlignment(Pos.CENTER);
        reminderDetailsWindow.setPrefSize(500,300);

        HBox headerBox = new HBox();
        Text header = new Text("Reminder");
        header.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD,50));
        header.setFill(Color.WHITE);
        headerBox.setPadding(new Insets(10,10,10,10));
        headerBox.setAlignment(Pos.TOP_CENTER);
        headerBox.setStyle("-fx-background-color: RED;"+"-fx-border-color: black;");
        headerBox.getChildren().add(header);



        //Create and arrange title area
        HBox titleBox = new HBox(39);
        titleBox.setPadding(new Insets(10,10,10,10));
        titleBox.setPrefWidth(1000);
        Text titleLabel = new Text("Title: ");
        titleLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
        Text title = new Text();
        title.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        titleBox.getChildren().addAll(titleLabel,title);
        info.add(title);

        //Create and arrange date area
        HBox dateBox = new HBox(40);
        dateBox.setPadding(new Insets(10,10,10,10));
        dateBox.setPrefWidth(1000);
        Text dateLabel = new Text("Date: ");
        dateLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
        Text date = new Text();
        date.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        dateBox.getChildren().addAll(dateLabel,date);
        info.add(date);

        //Create and arrange timebox area
        HBox timeBox = new HBox(34);
        timeBox.setPadding(new Insets(10,10,10,10));
        timeBox.setPrefWidth(1000);
        Text timeLabel = new Text("Time: ");
        timeLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
        Text time = new Text();
        time.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        timeBox.getChildren().addAll(timeLabel,time);
        info.add(time);

        //Create and arrange repeat type area
        HBox repeatBox = new HBox(17);
        repeatBox.setPadding(new Insets(10,10,10,10));
        repeatBox.setPrefWidth(1000);
        Text repeatLabel = new Text("Repeat: ");
        repeatLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
        Text repeat = new Text();
        repeat.setFont(Font.font("Times New Roman", FontWeight.NORMAL,20));
        repeatBox.getChildren().addAll(repeatLabel,repeat);
        info.add(repeat);

        reminderDetailsWindow.getChildren().addAll(headerBox, titleBox, dateBox, timeBox, repeatBox);

        //Create and arrange buttons
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.TOP_LEFT);
        update = new Button("UPDATE");
        delete = new Button("DELETE");
        buttonBox.setAlignment(Pos.BASELINE_CENTER);
        buttonBox.getChildren().addAll(update,delete);

        ok = new Button("OK");

        reminderDetailsWindow.getChildren().addAll(buttonBox,ok);
    }

    public Button getUpdate() {
        return update;
    }

    public Button getOk() {
        return ok;
    }

    public Button getDelete() {
        return delete;
    }

    public ArrayList<Text> getInfo() {
        return info;
    }

    public VBox getReminderDetailsWindow() {
        return reminderDetailsWindow;
    }
}
