package com.journaldev.spring.model;


import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;


public class DBConnection {
	

	
	public String updateRecordsByNewDate(Date startDate){
		List<ScheduleData> responseList = getQuerySelectData();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
	//	String jsonStr = null;
		try{
			
			for(int i=0; i<responseList.size(); i++){
				if(i==0){
					responseList.get(i).setSheduleDate(startDate);
				}else {
					Calendar c = Calendar.getInstance();
					c.setTime(startDate); 
					c.add(Calendar.DATE, 7*i);		
					responseList.get(i).setSheduleDate(c.getTime());
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public List<ScheduleData> cancelCourseByDateOrTopicUnFinished(Date cancelDate, String info){
		List<ScheduleData> responseList = getQuerySelectData();
		List<ScheduleData> updatedList = new ArrayList<ScheduleData>();
		ScheduleData obj1 = new ScheduleData();
		obj1.setSheduleDate(cancelDate);
		obj1.setTopics("Class Canceled");
		ScheduleData obj2 = new ScheduleData();
		ScheduleData obj3 = new ScheduleData();
		updatedList = responseList;
		String updatedCancelDateJSON = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//cancelDate = dateFormat.parse(cancelDate);
		try{
			for (int i=0; i<responseList.size(); i++){
				if(responseList.get(i).getSheduleDate().compareTo(cancelDate) == 0 ){
					if(!responseList.get(i).getTopics().equals("Class Canceled")){
						obj2 = responseList.get(i);
						obj3 = responseList.get(i+1);
						for(int j=i; j<updatedList.size(); j++){
							if(j != updatedList.size()-1){
								
								updatedList.get(j).setSheduleDate(responseList.get(j+1).getSheduleDate());
							}
						}
						updatedList.remove(updatedList.size()-1);
						if(info.equals("cancelDate")){
							System.out.println("In If stmt");
							updatedList.add(i, obj1);
						}else if(info.equals("topicUnfinished")){
							System.out.println("In else stmt");
							updatedList.add(i, obj2);
							updatedList.add(obj3);
						}
						
						break;
					}else {
						//return "Class is already cancelled .. no need to update in DB again";
					}
					
				}
			}			
			for(int k=0; k<updatedList.size(); k++){
				updateRecordsInDBAfterTask(updatedList.get(k));
			}		
		
				
			
		
	} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Updated Cancel date JSON"+updatedCancelDateJSON);
		
		return updatedList;
	}
	
	public void updateRecordsInDBAfterTask(ScheduleData obj) throws SQLException{
		Connection connection = getConnectionToDB();
		String sql = "UPDATE scheduler SET cshedule = ? WHERE dateDM = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, obj.getTopics());
		
		java.sql.Date sqlDate = new java.sql.Date(obj.getSheduleDate().getTime());
		ps.setDate(2, sqlDate);
		ps.executeUpdate();
		ps.close();
		
	}
	public List<ScheduleData> topicUnFinished(Date cancelDate, String info){
		List<ScheduleData> responseList = getQuerySelectData();
		List<ScheduleData> updatedList = new ArrayList<ScheduleData>();
		ScheduleData obj1 = new ScheduleData();
		obj1.setSheduleDate(cancelDate);
		obj1.setTopics("Class Canceled");
		ScheduleData obj2 = new ScheduleData();
		ScheduleData obj3 = new ScheduleData();
		updatedList = responseList;
		String updatedCancelDateJSON = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//cancelDate = dateFormat.parse(cancelDate);
		try{
			for (int i=0; i<responseList.size(); i++){
				if(responseList.get(i).getSheduleDate().compareTo(cancelDate) == 0 ){
					if(!responseList.get(i).getTopics().equals("Class Canceled")){
						obj2 = responseList.get(i);
						obj3 = responseList.get(i+1);
						for(int j=i; j<updatedList.size(); j++){
							if(j != updatedList.size()-1){
								
								updatedList.get(j).setSheduleDate(responseList.get(j+1).getSheduleDate());
							}
						}
						updatedList.remove(updatedList.size()-1);
						if(info.equals("cancelDate")){
							System.out.println("In If stmt");
							updatedList.add(i, obj1);
						}else if(info.equals("topicUnfinished")){
							System.out.println("In else stmt");
							updatedList.add(i, obj2);
							updatedList.add(obj3);
						}
						
						break;
					}else {
						//return "Class is already cancelled .. no need to update in DB again";
					}
					
				}
			}			
			updateRecordsInDBUnfinished(obj2,obj3);
			
	} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Updated Cancel date JSON"+updatedCancelDateJSON);
		
		return updatedList;
	}
	
	

	public void updateRecordsInDBUnfinished(ScheduleData obj,ScheduleData obj1) throws SQLException{
		Connection connection = getConnectionToDB();
		String sql = "UPDATE scheduler SET cshedule = ? WHERE dateDM = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, obj.getTopics()+obj1.getTopics());
		
		java.sql.Date sqlDate = new java.sql.Date(obj.getSheduleDate().getTime());
		ps.setDate(2, sqlDate);
		ps.executeUpdate();
		ps.close();
		
	}
	
	public List<ScheduleData> getQuerySelectData(){
		Connection connection = getConnectionToDB();
		List<ScheduleData> list = null;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rus = stmt.executeQuery("Select * from scheduler");
			
			list = new ArrayList<ScheduleData>();
			while (rus.next()) {
				ScheduleData scheduleDataObj = new ScheduleData();
				java.util.Date dbSqlDateConvertedToDate = new java.util.Date(rus.getDate(1).getTime());
				scheduleDataObj.setSheduleDate(dbSqlDateConvertedToDate);
				scheduleDataObj.setTopics(rus.getString(2));
				
				list.add(scheduleDataObj);
	        }
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	

	public Connection getConnectionToDB(){
		//System.out.println("I am trying to connect2");
		try{
			Class.forName("com.mysql.jdbc.Driver");			
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/ASE","amulya","password");			
			//System.out.println("Connection Success ");
			
			return conn;			
		}catch (Exception e) {
			// TODO: handle exception
			//System.out.println("Error while connecting");
			e.printStackTrace();
			return null;
		}		
		
	}

	public String getData() {
		// TODO Auto-generated method stub
		return null;
	}
}
