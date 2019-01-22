package googlecodechallenge.sam.musepadpocket.datamodel.apiinterfaces;

import java.util.ArrayList;

import googlecodechallenge.sam.musepadpocket.model.ItemModel;
import googlecodechallenge.sam.musepadpocket.model.MuseModel;
import googlecodechallenge.sam.musepadpocket.model.UserModel;
import googlecodechallenge.sam.musepadpocket.model.UserRequestModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("auth/login")
    Call<UserModel> login(@Body UserRequestModel login);

    @POST("muselists")
    Call<MuseModel> addMuselist(@Body MuseModel name);

    @GET("muselists?limit=1000")
    Call<ArrayList<MuseModel>> getMuselist();

    @POST("auth/register")
    Call<UserModel> registerUser(@Body UserRequestModel userDetails);

    @POST("muselists/{id}/items?limit=1000")
    Call<ItemModel> addItems(@Path("id")String id, @Body ItemModel name);

}
