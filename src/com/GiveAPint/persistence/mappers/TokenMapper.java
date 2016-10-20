package com.GiveAPint.persistence.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
}
