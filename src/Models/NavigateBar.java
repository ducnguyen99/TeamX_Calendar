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
package Models;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//A bar containing all time navigating methods for user to use
public class NavigateBar {
    private HBox navigateBar;
    private Text calendarTitle;
    private Button next;
    private Button prev;
    private Button today;
    private Button week;
    private Button month;

    public NavigateBar(){
        calendarTitle = new Text();
        calendarTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));

        //Show current displaying month name
        HBox calendarTitleBox = new HBox();
        calendarTitleBox.setPrefSize(1000,30);
        calendarTitleBox.setAlignment(Pos.CENTER_LEFT);
        calendarTitleBox.getChildren().add(calendarTitle);

        //Previous and Next button
        HBox prevNext = new HBox();
        prevNext.setAlignment(Pos.CENTER);
        prevNext.setSpacing(10);
        prev = new Button("< PREV");
        next = new Button("NEXT >");
        prevNext.getChildren().addAll(prev,next);

        //Today button to help user comeback to current time (month/week)
        today = new Button("TODAY");

        //To choose week view or month view
        VBox viewChoice = new VBox();
        week = new Button("WEEK");
        month = new Button("MONTH");
        viewChoice.getChildren().addAll(week,month);
        HBox containingBox = new HBox();
        containingBox.setAlignment(Pos.CENTER_RIGHT);
        containingBox.getChildren().add(viewChoice);

        navigateBar = new HBox(today, prevNext, calendarTitleBox,containingBox);
        navigateBar.setStyle("-fx-border-color: black;"+"-fx-background-color: #cccccc");
        navigateBar.setSpacing(25);
        navigateBar.setPadding(new Insets(25,35,25,35));
        navigateBar.setAlignment(Pos.CENTER);
    }

    public Button getNext() {
        return next;
    }

    public Button getPrev() {
        return prev;
    }

    public Button getToday() {
        return today;
    }

    public Button getMonthButton() {
        return month;
    }

    public Button getWeekButton() {
        return week;
    }

    public HBox getNavigateBar() {
        return navigateBar;
    }

    public Text getCalendarTitle() {
        return calendarTitle;
    }
}
