package googlecodechallenge.sam.musepadpocket.networkutils;

import java.util.ArrayList;

import googlecodechallenge.sam.musepadpocket.models.DeleteMuse;
import googlecodechallenge.sam.musepadpocket.models.ItemModel;
import googlecodechallenge.sam.musepadpocket.models.MuseModel;
import googlecodechallenge.sam.musepadpocket.models.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface INetwork {

    @POST("auth/login")
    Call<UserModel> login(@Body UserModel login);

    @POST("muselists")
    Call<MuseModel> addMuselist(@Body MuseModel name);

    @GET("muselists?limit=1000")
    Call<ArrayList<MuseModel>> getMuselist();

    @POST("auth/register")
    Call<UserModel> registarUser(@Body UserModel userDetails);

    @POST("muselists/{id}/items?limit=1000")
    Call<ItemModel> addIterms(@Path("id")String id, @Body ItemModel name);

    @DELETE("muselists/{id}")
    Call<DeleteMuse> deleteMuseList(@Path("id") String id);
}
