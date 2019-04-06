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

import Controllers.EventNotificationController;
import Controllers.MonthViewController;
import Controllers.ReminderNotificationController;
import Controllers.WeekViewController;
import Views.SignIn;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.YearMonth;

public class Main extends Application {
    private Scene signInScene;
    private Scene weekViewScene;
    private Scene monthViewScene;
    @Override
    public void start(Stage primaryStage) throws Exception{
        SignIn signIn = new SignIn();
        MonthViewController monthViewController = new MonthViewController(YearMonth.now());
        WeekViewController weekViewController = new WeekViewController(LocalDateTime.now());

        signIn.getSignIn().setOnAction(e->{
            if (signIn.getInputNameArea().getText().equals("teamxdemo@gmail.com") && signIn.getInputPasswordArea().getText().equals("calendar123")){
                signIn.getInputNameArea().clear();
                signIn.getInputPasswordArea().clear();
                primaryStage.setScene(weekViewScene);
            }
            else signIn.getError().setVisible(true);
        });

        monthViewController.getMonth().getHeadBar().getSignOutBox().setOnMouseClicked(e->{
            primaryStage.setScene(signInScene);
        });

        monthViewController.getMonth().getNavigateBar().getWeekButton().setOnMouseClicked(e->{
            primaryStage.setScene(weekViewScene);
            weekViewController.createDayLabels(LocalDateTime.now());
            weekViewController.updateActivityDateTime(LocalDateTime.now());
        });

        weekViewController.getWeek().getHeadBar().getSignOutBox().setOnMouseClicked(e->{
            primaryStage.setScene(signInScene);
        });

        weekViewController.getWeek().getNavigateBar().getMonthButton().setOnMouseClicked(e->{
            primaryStage.setScene(monthViewScene);
            monthViewController.populateCalendar(YearMonth.now());
        });

        signInScene = new Scene(signIn.getView());
        weekViewScene = new Scene(weekViewController.getView());
        monthViewScene = new Scene(monthViewController.getView());

        primaryStage.setScene(signInScene);
        primaryStage.setTitle("TEAMX CALENDAR");
        primaryStage.show();

        //For notification popup
        EventNotificationController eventNoti = new EventNotificationController();
        ReminderNotificationController reminderNoti = new ReminderNotificationController();
        eventNoti.showNoti();
        reminderNoti.showNoti();
    }
}
