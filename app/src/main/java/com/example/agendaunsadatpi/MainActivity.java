package com.example.agendaunsadatpi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaunsadatpi.models.CareerPlan;
import com.example.agendaunsadatpi.services.CareerPlanService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCareerPlans;
    private CareerPlanService careerPlanService;
    private int selectedCareerPlanId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_career_plan);

        // Initialize the Spinner and Button
        spinnerCareerPlans = findViewById(R.id.spinnerCareerPlans);
        Button buttonUpdate = findViewById(R.id.buttonUpdate);

        // Create an instance of the service
        careerPlanService = new CareerPlanService();

        // Make the API call to fetch career plans
        careerPlanService.getCareerPlans().enqueue(new Callback<List<CareerPlan>>() {
            @Override
            public void onResponse(Call<List<CareerPlan>> call, Response<List<CareerPlan>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CareerPlan> careerPlans = response.body();
                    ArrayAdapter<CareerPlan> adapter = new ArrayAdapter<>(
                            MainActivity.this,
                            android.R.layout.simple_spinner_item,
                            careerPlans
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCareerPlans.setAdapter(adapter);

                    spinnerCareerPlans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            CareerPlan selectedCareerPlan = (CareerPlan) parent.getItemAtPosition(position);
                            selectedCareerPlanId = selectedCareerPlan.getId();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle case when nothing is selected if needed
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<CareerPlan>> call, Throwable t) {
                Log.e("MainActivity", "Error fetching career plans: " + t.getMessage());
            }
        });

        // Set up the button to trigger the update
        buttonUpdate.setOnClickListener(v -> {
            try {
                updateCareerPlan();
            } catch (Exception e) {
                Log.e("MainActivity", "Error updating career plan: " + e.getMessage());
            }
        });
    }

    private void updateCareerPlan() {
        if (selectedCareerPlanId == 0) {
            Log.e("UpdateCareerPlan", "No career plan selected.");
            return; // Exit if no selection
        }

        String requestBody = "{\"id_carrer_plan\": " + selectedCareerPlanId + "}";

        Log.d("UpdateCareerPlan", "Request Body: " + requestBody);

        careerPlanService.updateCareerPlan(requestBody).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("UpdateCareerPlan", "Success: " + response.code());
                    // Optionally log the response body if applicable
                } else {
                    Log.d("UpdateCareerPlan", "Error: " + response.code() + " - " + response.message());
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("UpdateCareerPlan", "Failure: " + t.getMessage());
            }
        });
    }
}
