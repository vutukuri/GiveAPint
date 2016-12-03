package com.GiveAPint.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GiveAPint.dto.NotificationDetailsDTO;

import com.GiveAPint.persistence.dbdo.QueryResultDBDO;
import com.GiveAPint.persistence.mappers.NotificationMapper;
import com.GiveAPint.persistence.mappers.RequestMapper;
import com.GiveAPint.persistence.mappers.UserMapper;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

/**
 * Contains the logic to trigger the Google firebase to send notifications to device with the proper
 * message and data.
 * @author Manu
 *
 */
@Service("NotificationsService")
public class NotificationsService {
	
	@Autowired
	private RequestMapper requestMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private NotificationMapper notificationsMapper;
	
	public List<NotificationDetailsDTO> notificationWrapperForRequest(List<QueryResultDBDO> listOfUsers, String bloodType,
			int requesterId)
	{
		System.out.println("Came inside this notification wrapper.");
		List<NotificationDetailsDTO> usernameList = new ArrayList<>();
		//List<QueryResultDBDO> listOfUsers = result.getQueryResult();
		//The result as argument contains the resultant users who are returned as output to the request queries.
		//this function call is triggered only when there is no error set in result DTO and also if the number of 
		//resultant users are more than zero.
		try
		{
		System.out.println("List size retieved is: " +listOfUsers.size());
		usernameList = requestMapper.getUserNamesForUserIds(listOfUsers);
		System.out.println("Output from the request Mapper is: " +usernameList.toString());
		usernameList = requestMapper.getRegIdsForUsers(usernameList);
		System.out.println("Result returned from the second mapper is : "+usernameList.toString());
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		try
		{
			//Need to get the username for the userid and the request blood type.
			String username = userMapper.getFirstName(requesterId);
			String message = username + " requested for blood of type " +bloodType;
			
		for(NotificationDetailsDTO notification : usernameList )
		{
			//check if the regid is not null nor empty.
			if( !(notification.getRegId() == null || notification.getRegId().equals("")) )
			{
				System.out.println("About to send notification for the user: " +notification.getUserName());
				sendAndroidNotification(message, notification.getRegId());
			}
		}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println(e.getCause().getMessage());
		}
		//Send message and regid to whom we are sending.
		return usernameList;
	}
	
	/**
	 * Sends the notifications to the requester after some one accepted the request.
	 */
	public void sendNotificationAfterDonorsFound(int requestId, int responderId)
	{
		System.out.println("Came inside the corresponding notification method.");
		try
		{	
		int requesterId = requestMapper.getRequesterId(requestId);
		System.out.println("RequesterId fetched using the requestiD is:" + requesterId);
		String userName = userMapper.getUserName(requesterId);
		System.out.println("User name fetched using the requesterId is:" +userName);
		String regId =  notificationsMapper.getNotificationToken(userName);
		System.out.println("RegId fetched using the username is:" +regId);
		String responderName = userMapper.getFirstName(responderId);
		System.out.println("responderName fetched using the responderId is:"+responderName);
		String message = responderName + " accepted your request, you can now connect to him.";
		System.out.println("About to trigger the notifications main method");
		sendAndroidNotification(message, regId);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception occurred while sending the notiifcation after  some user responding.");
		}
	}
	
	public void sendAndroidNotification(String messageToUser, String regId) throws IOException {
		System.out.println("Inside the notification service");
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("https://fcm.googleapis.com/fcm/send");
		post.setHeader("Content-type", "application/json");
		post.setHeader("Authorization", "key=AIzaSyCnmKZ2pq6YBnX5bZtAaX3fyX5vQaQmFf8");		                                     
		System.out.println("Initial parameters are set");
		JSONObject message = new JSONObject();
		try {
			message.put("to", regId);
			message.put("priority", "high");
			JSONObject notification = new JSONObject();
			//Argument
			notification.put("title", "Blood Connection");
			//Argument
			notification.put("body", messageToUser);
			message.put("notification", notification);
			System.out.println("Json data is ready");
		}
			catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			post.setEntity(new StringEntity(message.toString(), "UTF-8"));
			System.out.println("About to make a call");
			HttpResponse response = client.execute(post);
			System.out.println(response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(message);
		System.out.println("Successful execution");
    }

}
