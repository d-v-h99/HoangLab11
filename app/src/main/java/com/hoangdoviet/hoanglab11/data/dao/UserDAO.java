package com.hoangdoviet.hoanglab11.data.dao;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hoangdoviet.hoanglab11.data.model.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = IGNORE)
    long insertUser(User user);
    @Delete
    void deleteUser(User user);
    @Query("select * from user_table")
    List<User> getAllUser();
    @Update
    int updateUser(User user);
    @Query("SELECT * FROM user_table WHERE id=:id")
    User findUser(long id);



}
