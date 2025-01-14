package com.example.agendaunsadatpi.services;

import com.example.agendaunsadatpi.interfaces.CareerPlanAPI;
import com.example.agendaunsadatpi.models.CareerPlan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CareerPlanService {

    private static final String BASE_URL = "https://ebca-190-19-136-129.ngrok-free.app/api/";
    private Retrofit retrofit;
    private CareerPlanAPI careerPlanAPI; // Ensure this is declared

    public CareerPlanService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        careerPlanAPI = retrofit.create(CareerPlanAPI.class); // Initialize here
    }

    public Call<List<CareerPlan>> getCareerPlans() {
        return careerPlanAPI.getCareerPlans();
    }

    // Updated method for PATCH request with hardcoded student ID
    public Call<Void> updateCareerPlan(String requestBody) {
        int studentId = 3; // Hardcoded student ID
        return careerPlanAPI.updateCareerPlan(studentId, requestBody);
    }
}
