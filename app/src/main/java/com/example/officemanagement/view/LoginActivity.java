package com.example.officemanagement.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.officemanagement.R;
import com.example.officemanagement.model.UserToken;
import com.example.officemanagement.network.ApiInterface;
import com.example.officemanagement.network.RetrofitApiClient;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button signInBT;
    private TextInputEditText userNameET, userPasswordET;
    private TextView errorTV;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();

        signInBT = findViewById(R.id.sign_in);
        userNameET = findViewById(R.id.userID_inputEditText);
        userPasswordET = findViewById(R.id.book_title);
        errorTV = findViewById(R.id.errorID);
        progressBar = findViewById(R.id.progressBar);

        signInBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = userNameET.getText().toString();
                String password = userPasswordET.getText().toString();
                if (id.isEmpty()) {
                    userNameET.setError("Required");
                }
                if (password.isEmpty()) {
                    userPasswordET.setError("Required");
                }
                if (!id.isEmpty() && !password.isEmpty()) {

                    progressBar.setVisibility(View.VISIBLE);

                    ApiInterface apiInterface = RetrofitApiClient.getRetrofit().create(ApiInterface.class);
                    apiInterface.signIn(id, password).enqueue(new Callback<UserToken>() {
                        @Override
                        public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                            if (response.body() != null) {
                                storeData(response.body());
                            } else {
                                storeData(null);
                            }
                        }

                        @Override
                        public void onFailure(Call<UserToken> call, Throwable t) {
                            storeError(t.getMessage());
                        }
                    });
                }
            }
        });
    }

    private void storeData(UserToken userToken) {

        progressBar.setVisibility(View.GONE);

        if (userToken == null) {
            errorTV.setVisibility(View.VISIBLE);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("userName", userToken.getId());
            startActivity(intent);
            finish();
        }
    }

    private void storeError(String error) {
        progressBar.setVisibility(View.GONE);
        errorTV.setVisibility(View.VISIBLE);
        errorTV.setText("Something Wrong Please Try Again");
    }
}
