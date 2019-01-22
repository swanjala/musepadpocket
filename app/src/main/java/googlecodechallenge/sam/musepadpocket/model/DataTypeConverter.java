package googlecodechallenge.sam.musepadpocket.models;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

public class DataTypeConverter {

    private  static Gson gson = new Gson();

    @TypeConverter
    public static List<ItemModel> stringToList(String data){
        if (data == null){
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<ItemModel>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<ItemModel> data)
    {
        return gson.toJson(data);
    }
}
