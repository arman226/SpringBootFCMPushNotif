package com.armando.pushnotif.armanfcm.repositories;

import org.springframework.stereotype.Repository;

import com.armando.pushnotif.armanfcm.dto.UserDto;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface IUserRepository extends JpaRepository<UserDto,Long> {
	@Query(value="select user_id, username FROM usertbl", nativeQuery=true)
	List<UserDto> getUsers();
	
	@Query(value="INSERT INTO usertbl(username) VALUES(:name) RETURNING *", nativeQuery=true)
	Integer addUser(String name);
	
	@Query(value="SELECT token from tokens WHERE \"userId\"=:id", nativeQuery=true)
	String getUserToken(Integer id);

}
