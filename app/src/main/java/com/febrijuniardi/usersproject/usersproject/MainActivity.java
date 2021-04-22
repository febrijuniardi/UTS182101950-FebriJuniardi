package com.febrijuniardi.usersproject.usersproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.febrijuniardi.usersproject.R;
import com.febrijuniardi.usersproject.model.User;
import com.febrijuniardi.usersproject.service.GetService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = findViewById(R.id.tv_data);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final GetService getService = retrofit.create(GetService.class);

        Call<List<User>> call = getService.getUsersList();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (response.isSuccessful()){

                    List<User> users = response.body();

                    for (User user : users){
                        String isidata =""+ "\n";
                        isidata += "Id : "+ user.getId()+ "\n";
                        isidata += "UserName : "+ user.getUsername()+ "\n";
                        isidata += "Name : "+ user.getName()+ "\n";
                        isidata += "Email : "+ user.getEmail()+ "\n";

                        tvData.append(isidata);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}