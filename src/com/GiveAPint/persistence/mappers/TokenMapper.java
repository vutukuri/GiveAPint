package com.GiveAPint.persistence.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.GiveAPint.dto.NotificationTokenDTO;
import com.GiveAPint.persistence.dbdo.TokenDBDO;

/**
 * Mapper functions to update, insert, get tuples in tokens database table.
 * 
 * @author mamanoha
 *
 */
public interface TokenMapper {

	@Select("SELECT \"token\" from tokens where \"username\" = #{userName}")
	public String getToken(String userName);

	@Update("UPDATE \"tokens\" SET \"token\" = #{token} where \"username\"= #{userName}")
	public int updateToken(@Param("token") String token, @Param("userName") String userName);

	@Insert("INSERT INTO \"tokens\"(\"username\", \"token\") VALUES(#{userName}, #{token})")
	public int insertToken(TokenDBDO token);
	
	@Insert("INSERT INTO \"notifications\"(\"username\", \"regid\") VALUES(#{userName}, #{regId})")
	public int insertNotificationToken(NotificationTokenDTO notification);
	
	/**
	 * Returns us the list of username and regid key-value pairs.
	 * This list can be used to send the notifications to specific devices using their regId.
	 * @param users list of usernames for which we need to retrive the regId.
	 * @return Key-value paris of userName - regId.
	 */
	public List<NotificationTokenDTO> getSelectedRegIds(List<String> users);
}
