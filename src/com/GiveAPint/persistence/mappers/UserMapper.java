package com.GiveAPint.persistence.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
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
	
	/**
	 * Updates the information corresponding to an user.
	 * @param userid of the user whose information is getting updated.
	 * @param healthStatus updated health status.
	 * @return number of rows affected.
	 */
	@Update("UPDATE \"userstatus\" SET \"healthstatus\" = #{healthStatus} WHERE \"userid\" = #{userid}")
	public Integer updateHealthStatus( @Param("userid")int userid, @Param("healthStatus")String healthStatus );
	
	/**
	 * Inserts a new entry for user which helps to track the donation history of the user.
	 * @param userid of the user
	 * @param lastDonatedDate  latest Donated date
	 * @return number of rows affected
	 */
	@Insert("INSERT INTO \"donationhistory\" (\"userid\", \"donateddate\") VALUES (#{userid}, #{lastDonatedDate})")
	public Integer insertLastDonatedDate(@Param("userid") int userid, @Param("lastDonatedDate") Date lastDonatedDate);

	/**
	 * Returns all the users from the database.
	 * @return list of users.
	 */
	@Select("SELECT \"firstname\" as firstName, \"lastname\" as lastName, \"userid\" as userId, \"passcode\" as passcode, \"username\" as userName FROM \"loginusers\"")
	public List<UserDBDO> getAllUsers();

	/**
	 * Registers a new user into the database and generates a serial number
	 * which acts as an userId for the newly inserted user. sets the next userId to the DBDO.
	 * 
	 * @param newUser
	 *            with information.
	 * @return number of rows affected.
	 */
	@Insert("INSERT INTO \"loginusers\" (\"userid\", \"firstname\", \"lastname\", \"passcode\", \"username\", \"phone\", \"dob\",\"gender\") VALUES (#{userId}, #{firstName}, #{lastName}, #{passcode},#{userName},#{phone},#{dob},#{gender})")
	@SelectKey(statement="SELECT nextVal('loginusers_userid_seq')", keyProperty="userId", before = true, resultType=int.class)
	public int registerUser(UserDBDO newUser);

	/**
	 * Inserts the user status including the location and health status while
	 * creating a new user.
	 * 
	 * @param newUserStatus
	 *            with all additional parameters.
	 * @return number of rows affected.
	 */
	@Insert("INSERT INTO \"userstatus\" (\"userid\", \"healthstatus\", \"nextavailabledate\", \"lastlocation\", \"bloodgroup\") VALUES (#{userId}, #{healthStatus}, #{nextAvailableDate}, ST_SetSRID(ST_MakePoint(-118.285385,34.032384), 4326), #{bloodGroup})")
	public int registerUserStatus(UserStatusDBDO newUserStatus);

	/**
	 * Returns the max userId which indicates the last inserted user's Id.
	 * @return maximum userId available.
	 */
	@Select("SELECT max(\"userid\") from \"loginusers\"")
	public int getMaxUserId();

	/**
	 * Returns the passcode corresponding to the username.
	 * @param userName
	 * @return passcode
	 */
	@Select("SELECT \"passcode\" FROM \"loginusers\" WHERE \"username\" = #{userName}")
	public String getPasscode(String userName);

	/**
	 * If insertion in userStatus table gives an error. This deletes the
	 * corresponding entry(with prevId) from the loginUsers table.
	 * 
	 * @return the number of rows affected.
	 */
	@Delete("DELETE FROM \"loginusers\" WHERE \"userid\" = #{prevId}")
	public Integer deleteLoginUsers(int prevId);

	/**
	 * Get userName corresponding to the given userId
	 * @return the username.
	 */
	@Select("SELECT \"username\" FROM \"loginusers\" WHERE \"userid\" = #{userId}")
	public String getUserName(@Param("userId") int userId);
}