package pl.zan;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.stereotype.Component;

import retrofit.GsonConverterFactory;

@Component
public class Retrofit {

    public retrofit.Retrofit getRetrofit() {
        OkHttpClient httpClient = new OkHttpClient();
        return new retrofit.Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

    }
}