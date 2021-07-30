package pl.zan;
import retrofit.Call;
import retrofit.http.*;

import java.util.List;
public interface CharacterServices {

        @GET("/getCharacters")
        Call<List<Character>> getCharacter();

        @POST("/addCharacter")
        Call<Void> addCharacter(@Body Character character);

}
