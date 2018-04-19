# ClassSchedulerUsingSpring
University project:create an application that will change a
course schedule  When an application user select the first day of class,
the application needs to change the dates in course schedule automatically 
If a class is canceled due to inclement weather, entire dates should be updated
If the class didn’t finish the topics as scheduled, contents of course, 
quiz and assignment schedule should be updated 
You may create a separate UI for testing purposes or utilize a Tool like SoapUI
or PostMan.  You will need to use the latest of:      
Java 8          
Spring Framework          
MySQL or Maria DB



I have developed the project in MVC ,
model,view controller

my controller class is HomeController
I have used the request mapping for displaying the view in jsp pages

added dependencies in my pom.xml

created web.xml
spring-servlet.xml added base package for my project 
<context:component-scan base-package="com.journaldev.spring" />

My model classes:
DBConnection:


public List<ScheduleData> getQuerySelectData(){
this method selects all the records in the database


public Connection getConnectionToDB(){
this connects the database
with the given url and username,password



cancelCourseByDate(Date cancelDate,String info){
which cancels the course if the string info is "cancelDate"
then it sets the topics to Class Canceled
next it will iterate the responseList
removes the last line in updated list

the method updateRecordsInDBAfterTask(ScheduleData obj) {

will take the object from cancelCourseByDate and updates in the data base

}

the method List<ScheduleData> topicUnFinished(Date cancelDate, String info){
It will take the date and string info and it will get the response list by querying the database data
now it saves the data into updated list 

now it iterates the data in such a way that the object 2 has the iterated list 
object 3 has the i+1 th record 
now it adds two contents for the same record . the unfinshed as well as the record of next class in the next record

updateRecordsInDBUnfinished(ScheduleData obj,ScheduleData obj1)

this method will update the database




}




