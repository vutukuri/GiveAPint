package com.GiveAPint.persistence.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface NotificationMapper {
	
	/**
	 * Updates the registration id for the client, this is usually done when the token for
	 * the client is changed.
	 * 
	 * @param userName
	 * @param regId
	 * @return
	 */
	@Update("UPDATE \"notifications\" SET \"regid\" = #{regId} where \"username\"= #{userName}")
	public int updateNotificationToken(@Param("userName") String userName, @Param("regId") String regId);
	
	/**
	 * Useful to see if there is some tuple corresponding to a user.
	 * @param userName
	 * @return
	 */
	@Select("SELECT \"regid\" from notifications where \"username\" = #{userName}")
	public String getNotificationToken(@Param("userName") String userName);
	
	/**
	 * Inserts the tuple for a user. This is only called once for every client.
	 * @param userName
	 * @param regId
	 * @return
	 */
	@Insert("INSERT INTO \"notifications\"(\"username\", \"regid\") VALUES(#{userName}, #{regId})")
	public int insertNotificationToken(@Param("userName") String userName, @Param("regId") String regId);

}
