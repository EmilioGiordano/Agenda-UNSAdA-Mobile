package com.example.agendaunsadatpi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaunsadatpi.models.CareerPlan;
import com.example.agendaunsadatpi.services.CareerPlanService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textViewCareerPlans;
    private CareerPlanService careerPlanService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_career_plan);

        // Inicializamos el TextView
        textViewCareerPlans = findViewById(R.id.textViewCareerPlans);

        // Creamos la instancia del servicio
        careerPlanService = new CareerPlanService();

        // Realizamos la llamada a la API
        careerPlanService.getCareerPlans().enqueue(new Callback<List<CareerPlan>>() {
            @Override
            public void onResponse(Call<List<CareerPlan>> call, Response<List<CareerPlan>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Obtenemos los datos y los mostramos
                    List<CareerPlan> careerPlans = response.body();
                    StringBuilder sb = new StringBuilder();

                    // Recorremos la lista de planes de carrera y los agregamos al StringBuilder
                    for (CareerPlan careerPlan : careerPlans) {
                        sb.append("ID: ").append(careerPlan.getId()).append("\n");
                        sb.append("Nombre: ").append(careerPlan.getName()).append("\n");
                        sb.append("Código de Propuesta: ").append(careerPlan.getProposal_code()).append("\n\n");
                    }

                    // Actualizamos el TextView con los datos
                    textViewCareerPlans.setText(sb.toString());
                }
            }

            @Override
            public void onFailure(Call<List<CareerPlan>> call, Throwable t) {
                // En caso de error, mostramos un mensaje
                textViewCareerPlans.setText("Error al cargar los datos.");
            }
        });
    }
}
