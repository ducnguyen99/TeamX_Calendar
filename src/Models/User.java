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

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class User {
    private ArrayList<Event> eventList = new ArrayList<>();
    private ArrayList<Reminder> remindList = new ArrayList<>();

    //Constructor
    public User(){ ; }

    //get reminders form database
    public void getRemindersFromFile(){
        File file = new File("reminder.txt");
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(file);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                Reminder reminder = new Reminder();
                reminder.setTitle(line);
                line = bufferedReader.readLine();
                reminder.setDate(line);
                line = bufferedReader.readLine();
                reminder.setTime(line);
                line = bufferedReader.readLine();
                reminder.setRepeatType(line);
                remindList.add(reminder);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + file + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + file + "'");
        }
    }

    //Compare 2 reminders
    public boolean compareReminder(Reminder reminder1, Reminder reminder2){
        if(reminder1.getTitle().equals(reminder2.getTitle())) return true;
        else return false;
    }

    //Find the index of a given reminder, if it does not exist, return size + 1
    public int findReminder(Reminder reminder){
        int i = 0;
        while(i < remindList.size()){
            if(compareReminder(reminder,remindList.get(i))){
                return i;
            }
            i++;
        }
        return i;
    }

    //Write all the user containing reminders to file
    public void writeRemindersToFile(){
        File file = new File("reminder.txt");
        try {
            PrintWriter delete = new PrintWriter(file);
            delete.print("");
            delete.close();

            FileWriter output = new FileWriter(file, true);
            for(Reminder reminder : remindList){
                output.append(reminder.getTitle()).append("\n");
                output.append(reminder.getDate()).append("\n");
                output.append(reminder.getTime()).append("\n");
                output.append(reminder.getRepeatType()).append("\n").append("\n");
            }
            output.close();
        } catch (IOException e) {
            System.out.println("Error reading file '" + file + "'");
        }
    }

    //Get events from file
    public void getEventsFromFile(){
        File file = new File("event.txt");
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(file);

            // Wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                Event event = new Event();
                //read title
                event.setTitle(line);
                line = bufferedReader.readLine();
                //read start datee
                event.setStartDate(line);
                line = bufferedReader.readLine();
                //read end date
                event.setEndDate(line);
                line = bufferedReader.readLine();
                //read start time
                event.setStartTime(line);
                line = bufferedReader.readLine();
                //read end time
                event.setEndTime(line);
                line = bufferedReader.readLine();
                //read location
                event.setLocation(line);
                line = bufferedReader.readLine();
                //read description
                event.setDescription(line);
                line = bufferedReader.readLine();
                //read owner
                event.setOwner(line);
                line = bufferedReader.readLine();
                //read guest list
                event.setGuestList(line);
                line = bufferedReader.readLine();
                //read hours/minutes to pop-up notification
                event.setNotiTime(line);
                line = bufferedReader.readLine();
                //read the time type
                event.setNotiType(line);
                eventList.add(event);

                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + file + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + file + "'");
        }
    }

    //Compare two events
    public boolean compareEvent(Event event1, Event event2){
        if(event1.getTitle().equals(event2.getTitle())) return true;
        else return false;
    }

    //Find a given event in the array list, if it does not exist, return size + 1
    public int findEvent(Event event){
        int i = 0;
        while(i < eventList.size()){
            if(compareEvent(event,eventList.get(i)) == true){
                return i;
            }
            i++;
        }
        return i;
    }

    //Write all the user containing events to file
    public void writeEventsToFile(){
        File file = new File("event.txt");
        try {
            PrintWriter delete = new PrintWriter(file);
            delete.print("");
            delete.close();

            FileWriter output = new FileWriter(file, true);
            for(Event event : eventList){
                output.append(event.getTitle()).append("\n");
                output.append(event.getStartDate()).append("\n");
                output.append(event.getEndDate()).append("\n");
                output.append(event.getStartTime()).append("\n");
                output.append(event.getEndTime()).append("\n");
                output.append(event.getLocation()).append("\n");
                output.append(event.getDescription()).append("\n");
                output.append(event.getOwner()).append("\n");
                output.append(event.getGuestList()).append("\n");
                output.append(event.getNotiTime()).append("\n");
                output.append(event.getNotiType()).append("\n").append("\n");
            }
            output.close();
        } catch (IOException e) {
            System.out.println("Error reading file '" + file + "'");
        }
    }

    public void sortEvent(){
        Collections.sort(eventList, new Event());
    }

    public void sortReminder(){ Collections.sort(remindList, new Reminder()); }

    public ArrayList<Reminder> getRemindList() { return remindList; }

    public ArrayList<Event> getEventList() {
        return eventList;
    }
}
