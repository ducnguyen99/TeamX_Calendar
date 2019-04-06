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

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class WeekCalendar {
    private ScrollPane layout;
    private ArrayList<VboxNode> dayOfCurrentWeek = new ArrayList<>(7);
    private ArrayList<PaneNode> shortActivities = new ArrayList<>(7);

    public WeekCalendar(){
        //Initialize and set alignment of the calendar view
        GridPane calendar = new GridPane();
        calendar.setAlignment(Pos.TOP_LEFT);
        calendar.setGridLinesVisible(true);

        //Create and arrange the dayLabels bar
        Text[] dayNames = new Text[]{new Text("Monday"), new Text("Tuesday"),
                new Text("Wednesday"), new Text("Thursday"), new Text("Friday"),
                new Text("Saturday"), new Text("Sunday") };

        HBox dayLabels = new HBox();
        for (int i = 0; i < 7; i++){
            Text txt = dayNames[i];
            BorderPane borderPane = new BorderPane();
            borderPane.setPrefSize(150, 10);
            borderPane.setCenter(txt);
            VboxNode vBox = new VboxNode();
            vBox.setPrefSize(150,10);
            vBox.setAlignment(Pos.TOP_CENTER);
            dayOfCurrentWeek.add(vBox);//Add the cell to an array list to change its value later

            VBox combine = new VBox();
            combine.getChildren().addAll(borderPane,vBox);
            dayLabels.getChildren().add(combine);
        }
        //Add the dayLabels bar into the calendar
        calendar.add(dayLabels,1,0);

        //Create and arrange time labels vertical bar
        VBox timeLabels = new VBox();
        for (int i = 0; i < 24; i++ ) {
            StackPane timeZone = new StackPane();
            timeZone.setAlignment(Pos.TOP_CENTER);
            timeZone.setStyle("-fx-border-color: black;");
            timeZone.setPrefSize(200,200);
            timeZone.getChildren().add(new Text(Integer.toString(i) + ":00" ));
            timeLabels.getChildren().add(timeZone);
        }
        calendar.add(timeLabels,0,2);

        //Create and arrange all the activity cells of 7 days of the displaying week
        HBox shortActivityTable = new HBox();
        for (int i = 0; i <7; i++){
            PaneNode activity = new PaneNode();
            activity.setPrefHeight(4800);
            activity.setMinWidth(150);
            ScrollPane scrol = new ScrollPane();
            scrol.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrol.setStyle("-fx-border-color: black;");
            scrol.setPrefWidth(150);
            scrol.setContent(activity);
            shortActivityTable.getChildren().add(scrol);
            shortActivities.add(activity);
        }
        calendar.add(shortActivityTable,1,2);

        //Put the calendar into a scroll pane for convenient view
        layout = new ScrollPane();
        layout.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        layout.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        layout.setContent(calendar);
    }

    public ArrayList<VboxNode> getDayOfCurrentWeek() {
        return dayOfCurrentWeek;
    }

    public ScrollPane getLayout() {
        return layout;
    }

    public ArrayList<PaneNode> getShortActivities() {
        return shortActivities;
    }
}

