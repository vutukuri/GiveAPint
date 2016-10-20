package com.GiveAPint.persistence.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.GiveAPint.persistence.dbdo.UserDBDO;
import com.GiveAPint.persistence.dbdo.UserStatusDBDO;

/**
 * This interface will be implemented by MyBatis, we need to map every method to
 * a database query in which table attributes match with the "UserDAO".
 * 
 * @author mamanoha               
 *
 */
public interface UserMapper {
	
	@Update("UPDATE \"userstatus\" SET \"healthstatus\" = #{healthStatus} WHERE \"userid\" = #{userid}")
	public Integer updateHealthStatus( @Param("userid")int userid, @Param("healthStatus")String healthStatus );
	
	@Insert("INSERT INTO \"donationhistory\" (\"userid\", \"donateddate\") VALUES (#{userid}, #{lastDonatedDate})")
	public Integer insertLastDonatedDate( @Param("userid")int userid, @Param("lastDonatedDate") Date lastDonatedDate);
	
	@Select("SELECT \"username\" FROM \"loginusers\" WHERE \"userid\" = #{userid}")
	public String getUser( @Param("userid") int userid);

	@Select("SELECT \"firstname\", \"lastname\", \"userid\", \"passcode\", \"username\" FROM \"loginusers\"")
	public List<UserDBDO> getAllUsers();
	
	/**
	 * Registers a new user into the database and generates a serial number which acts as an userId for the newly inserted user.
	 * @param newUser with information.
	 * @return number of rows affected.
	 */
	@Insert("INSERT INTO \"loginusers\" (\"firstname\", \"lastname\", \"passcode\", \"username\", \"phone\", \"dob\",\"gender\") VALUES (#{firstName}, #{lastName}, #{passcode},#{userName},#{phone},#{dob},#{gender})")      
	public int registerUser(UserDBDO newUser);
	
	/**
	 * Inserts the user status including the location and health status while creating a new user.
	 * @param newUserStatus with all additional parameters.
	 * @return number of rows affected.
	 */
	@Insert("INSERT INTO \"userstatus\" (\"userid\", \"healthstatus\", \"nextavailabledate\", \"lastlocation\", \"bloodgroup\") VALUES (#{userId}, #{healthStatus}, #{nextAvailableDate}, ST_SetSRID(ST_MakePoint(-118.285385,34.032384), 4326), #{bloodGroup})")
	public int registerUserStatus(UserStatusDBDO newUserStatus);  
	
	@Select("SELECT max(\"userid\") from \"loginusers\"")
	public int getMaxUserId();
	
	@Select("SELECT \"passcode\" FROM \"loginusers\" WHERE \"username\" = #{userName}")
	public String getPasscode(String userName);
	
	/**
	 * If insertion in userstatus table gives an error. This deletes the corresponding entry(with prevId) from the loginusers table.
	 * @return
	 */
	@Delete("DELETE FROM \"loginusers\" WHERE \"userid\" = #{prevId}")
	public Integer deleteLoginUsers(int prevId);
}