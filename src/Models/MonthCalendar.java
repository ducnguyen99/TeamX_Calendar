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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class MonthCalendar {
    private ArrayList<VboxNode> allCalendarDays = new ArrayList<>(35);
    private VBox layout;
    public MonthCalendar(){
        //Initialize and set alignment for the calendar
        GridPane calendar = new GridPane();
        calendar.setGridLinesVisible(true);

        //Create and arrange all the day cells of the displaying month
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                VboxNode vBox = new VboxNode();
                vBox.setPrefSize(200,120);
                vBox.setAlignment(Pos.TOP_CENTER);
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setContent(vBox);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                calendar.add(scrollPane,j,i);// add the cell into the calendar
                allCalendarDays.add(vBox);// add the cell into an array list to change it value later
            }
        }

        //Create dayOfWeek bar
        Text[] dayNames = new Text[]{ new Text("Sunday"), new Text("Monday"), new Text("Tuesday"),
                new Text("Wednesday"), new Text("Thursday"), new Text("Friday"),
                new Text("Saturday") };
        GridPane dayOfWeek = new GridPane();
        dayOfWeek.setPadding(new Insets(25,0,25,0));
        dayOfWeek.setAlignment(Pos.CENTER);
        Integer col = 0;
        for (Text txt : dayNames) {
            BorderPane borderPane = new BorderPane();
            borderPane.setPrefSize(200, 10);
            borderPane.setCenter(txt);
            dayOfWeek.add(borderPane, col++, 0);
        }

        //Add all components to layout and arrange them
        layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(0,0,25,0));
        layout.getChildren().addAll(dayOfWeek,calendar);
    }

    public VBox getLayout() {
        return layout;
    }

    public ArrayList<VboxNode> getAllCalendarDays() {
        return allCalendarDays;
    }
}
