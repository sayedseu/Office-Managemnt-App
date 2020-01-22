package com.example.officemanagement.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.officemanagement.R;
import com.example.officemanagement.model.Information;
import com.example.officemanagement.network.ApiInterface;
import com.example.officemanagement.network.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView designationTV, dateTV, resposibilityTV, taskTV, locationTV, errorTv;
    private ProgressBar progressBar;
    private String userName;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Office Management");
        setSupportActionBar(toolbar);

        userName = getIntent().getStringExtra("userName");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        designationTV = findViewById(R.id.designationTV);
        dateTV = findViewById(R.id.dateTV);
        resposibilityTV = findViewById(R.id.resposibilityTV);
        taskTV = findViewById(R.id.taskTV);
        locationTV = findViewById(R.id.locationTV);
        progressBar = findViewById(R.id.progressBar);
        imageView = findViewById(R.id.imageView);
        errorTv = findViewById(R.id.errorTV);

        errorTv.setVisibility(View.GONE);

        if (!userName.isEmpty()) {

            progressBar.setVisibility(View.VISIBLE);

            ApiInterface apiInterface = RetrofitApiClient.getRetrofit().create(ApiInterface.class);
            apiInterface.getInfo(userName).enqueue(new Callback<Information>() {
                @Override
                public void onResponse(Call<Information> call, Response<Information> response) {
                    if (response.body() != null) {
                        storeData(response.body());
                    } else {
                        storeData(null);
                    }
                }

                @Override
                public void onFailure(Call<Information> call, Throwable t) {
                    onError();
                }
            });
        }
    }

    private void storeData(Information information) {

        progressBar.setVisibility(View.GONE);

        if (information != null) {
            imageView.setVisibility(View.VISIBLE);
            designationTV.setText("Designation : " + information.getDesignation());
            dateTV.setText("Joining Date : " + information.getJoiningDate());
            resposibilityTV.setText("Responsibility : " + information.getResponsibility());
            taskTV.setText("Assigned Task : " + information.getAssignedTask());
            locationTV.setText("Current Location : " + information.getCurrentLocation());
        } else {
            errorTv.setVisibility(View.VISIBLE);
            errorTv.setText("Nothing To Show...");
        }
    }

    private void onError() {
        errorTv.setVisibility(View.VISIBLE);
        errorTv.setText("Something Went Wrong Please Try Again...");
    }
}
