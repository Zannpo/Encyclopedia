package pl.zan;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

import java.util.List;

public interface ProfessionServices {
    @GET("/getProfessions")
    Call<List<Profession>> getProfessions();

    @POST("/addProfessions")
    Call<Void> addProfession(@Body Profession profession);
}
