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
package Controllers;

import Views.ReminderDetails;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Control "Reminder Details" window
public class ReminderDetailsController {
    private ReminderDetails reminderDetails;
    private Stage popup;
    private Scene reminderScene;
    public ReminderDetailsController(){
        reminderDetails = new ReminderDetails();
        popup = new Stage();
        reminderScene = new Scene(reminderDetails.getReminderDetailsWindow());
        popup.setScene(reminderScene);
    }

    public void assignInformation(String title, String date, String time, String repeatType){
        reminderDetails.getInfo().get(0).setText(title);
        reminderDetails.getInfo().get(1).setText(date);
        reminderDetails.getInfo().get(2).setText(time);
        reminderDetails.getInfo().get(3).setText(repeatType);
    }

    public Stage getPopup() {
        return popup;
    }

    public ReminderDetails getReminderDetails() {
        return reminderDetails;
    }
}
