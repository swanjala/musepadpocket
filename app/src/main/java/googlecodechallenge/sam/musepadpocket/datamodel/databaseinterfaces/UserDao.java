package googlecodechallenge.sam.musepadpocket.datamodel.databaseinterfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import googlecodechallenge.sam.musepadpocket.model.UserModel;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertuser(UserModel userModel);

    @Query("SELECT * FROM users where userName = :userName")
    List<UserModel> getUsers(String userName);
}
