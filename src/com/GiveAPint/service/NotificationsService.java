package com.GiveAPint.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

/**
 * Contains the logic to trigger the Google firebase to send notifications to device with the proper
 * message and data.
 * @author Manu
 *
 */
public class NotificationsService {
	
	public void sendAndroidNotification() throws IOException {
		System.out.println("Inside the notification service");
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("https://fcm.googleapis.com/fcm/send");
		post.setHeader("Content-type", "application/json");
		post.setHeader("Authorization", "key=AIzaSyCnmKZ2pq6YBnX5bZtAaX3fyX5vQaQmFf8");
		
		
		                                     
		System.out.println("Initial parameters are set");
		JSONObject message = new JSONObject();
		try {
			message.put("to", "cBOnm_9y6YY:APA91bEpwh8C5eqqF8L3MwIx91S7sTNaF_DTeuAJ2bPWy-o0v1fdKx7g7x5GXh0_OENI-XlN-AvcWEfzLU1vFRybXzp28zeAlhGVkHRD5_9vlX7P0xU11gOnANgbNLDo8zOT6f-wIJZg");
			message.put("priority", "high");
			JSONObject notification = new JSONObject();
			//Argument
			notification.put("title", "Blood Connection");
			//Argument
			notification.put("body", "Notification from the service");
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
