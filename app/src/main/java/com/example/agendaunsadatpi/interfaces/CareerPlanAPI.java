package com.example.agendaunsadatpi.interfaces;

import com.example.agendaunsadatpi.models.CareerPlan;
import java.util.List;
// API THINGS
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
public interface CareerPlanAPI {

    @GET("carrerPlans")
    Call<List<CareerPlan>> getCareerPlans();

    @PATCH("students/{id}") // Use the student ID in the URL
    Call<Void> updateCareerPlan(@Path("id") int studentId, @Body String requestBody);
}
