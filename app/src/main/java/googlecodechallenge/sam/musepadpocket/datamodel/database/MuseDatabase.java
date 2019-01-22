package googlecodechallenge.sam.musepadpocket.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import googlecodechallenge.sam.musepadpocket.models.MuseModel;


@Database(entities = {MuseModel.class}, version = 3)

public abstract class MuseDatabase extends RoomDatabase {


    private static final String LOG_TAG = MuseDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "musepadpocketdb";
    private static MuseDatabase database;

    public static MuseDatabase getDatabase(Context context){
        if (database == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "Creating new database instance");
                database = Room.databaseBuilder(context.getApplicationContext(),
                        MuseDatabase.class, MuseDatabase.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        Log.d(LOG_TAG,"Get db instance");
        return database;
    }

    public abstract MuseListDao museListDao();
}
