package com.example.agendaunsadatpi.services;
import com.example.agendaunsadatpi.interfaces.CareerPlanAPI;
import com.example.agendaunsadatpi.models.CareerPlan;
import com.example.agendaunsadatpi.interfaces.CareerPlanAPI;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class CareerPlanService {

    private static final String BASE_URL = "https://30b3-190-19-136-129.ngrok-free.app/api/";
    private Retrofit retrofit;
    public CareerPlanService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<List<CareerPlan>> getCareerPlans(){
        CareerPlanAPI service = retrofit.create(CareerPlanAPI.class);
        return service.getCareerPlans();
    }
}
