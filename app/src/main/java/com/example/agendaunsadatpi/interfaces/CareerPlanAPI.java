package com.example.agendaunsadatpi.interfaces;

import com.example.agendaunsadatpi.models.CareerPlan;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CareerPlanAPI {

    @GET("carrerPlans")
    Call<List<CareerPlan>> getCareerPlans();

}
