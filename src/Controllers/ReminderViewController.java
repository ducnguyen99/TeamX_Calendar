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

import Models.Reminder;
import Views.ReminderWindow;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Control "Create Reminder" Window
public class ReminderViewController {
    private ReminderWindow reminderWindow = new ReminderWindow();
    private Stage popup = new Stage();
    private Scene reminderScene = new Scene(reminderWindow.getView());
    private EventViewController eventViewController = new EventViewController();

    //Constructors
    public ReminderViewController() {
        popup.setScene(reminderScene);
    }

    //Check valid input
    public Boolean isInputValueValid() {
        if(!(reminderWindow.getDate().getEditor().getText().matches("")) && eventViewController.checkTimeFormat(reminderWindow.getTime().getText())) {
            return true;
        } else return false;
    }
    //get input values
    public Reminder getInputReminder() {
        Reminder reminder = new Reminder();
        reminder.setTitle(reminderWindow.getTitle().getText());
        reminder.setDate(reminderWindow.getDate().getEditor().getText());
        reminder.setTime(reminderWindow.getTime().getText());
        reminder.setRepeatType(reminderWindow.getRepeatType().getValue());
        clearReminderInfo();
        return reminder;
    }

    // clear information in reminder window
    public void clearReminderInfo() {
        reminderWindow.getTitle().clear();
        reminderWindow.getDate().getEditor().clear();
        reminderWindow.getTime().clear();
    }

    public ReminderWindow getReminderWindow() {
        return reminderWindow;
    }

    public Stage getPopup() {
        return popup;
    }
}
