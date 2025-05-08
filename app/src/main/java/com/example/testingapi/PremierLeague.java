package com.example.testingapi;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PremierLeague extends AppCompatActivity {

    RecyclerView rvpremierleague;
    TeamAdapter teamAdapter;
    List<Team> teamList = new ArrayList<>();
    ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premier_league);

        rvpremierleague = findViewById(R.id.rvpremierleague);
        rvpremierleague.setLayoutManager(new LinearLayoutManager(this));

        teamAdapter = new TeamAdapter(teamList);
        rvpremierleague.setAdapter(teamAdapter);

        pbLoading = findViewById(R.id.pbLoading);
        getTeamData();
    }

    private void getTeamData() {
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
        Call<TeamResponse> call = apiService.getTeams("English Premier League");

        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    teamList.clear();
                    teamList.addAll(response.body().teams);
                    teamAdapter.notifyDataSetChanged();
                    rvpremierleague.setVisibility(View.VISIBLE);
                }
                pbLoading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Toast.makeText(PremierLeague.this, "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}