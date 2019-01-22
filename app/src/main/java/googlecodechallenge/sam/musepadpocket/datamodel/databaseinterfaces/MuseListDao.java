package googlecodechallenge.sam.musepadpocket.datamodel.databaseinterfaces;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import googlecodechallenge.sam.musepadpocket.model.MuseModel;

@Dao
public interface MuseListDao {
    @Query("SELECT * FROM muselist")
    LiveData<List<MuseModel>> fetchAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMuse(List<MuseModel> museEntry);
}
