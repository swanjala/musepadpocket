package googlecodechallenge.sam.musepadpocket.datamodel.api;

import java.util.ArrayList;

import googlecodechallenge.sam.musepadpocket.model.DeleteMuse;
import googlecodechallenge.sam.musepadpocket.model.ItemModel;
import googlecodechallenge.sam.musepadpocket.model.LoginModel;
import googlecodechallenge.sam.musepadpocket.model.MuseModel;
import googlecodechallenge.sam.musepadpocket.model.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("auth/login")
    Call<UserModel> login(@Body LoginModel login);

    @POST("muselists")
    Call<MuseModel> addMuselist(@Body MuseModel name);

    @GET("muselists?limit=1000")
    Call<ArrayList<MuseModel>> getMuselist();

    @POST("auth/register")
    Call<UserModel> registerUser(@Body UserModel userDetails);

    @POST("muselists/{id}/items?limit=1000")
    Call<ItemModel> addItems(@Path("id")String id, @Body ItemModel name);

    @DELETE("muselists/{id}")
    Call<DeleteMuse> deleteMuseList(@Path("id") String id);
}
