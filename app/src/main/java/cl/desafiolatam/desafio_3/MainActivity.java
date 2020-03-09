package cl.desafiolatam.desafio_3;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cl.desafiolatam.desafio_3.api.Api;
import cl.desafiolatam.desafio_3.api.RetrofitClient;
import cl.desafiolatam.desafio_3.beans.Pregunta;
import cl.desafiolatam.desafio_3.beans.PreguntasLista;
import cl.desafiolatam.desafio_3.fragments.PreguntaFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LifeCycleLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: Generando...");
        Api service = RetrofitClient.getRetrofitInstance().create(Api.class);
        Call<PreguntasLista> call = service.getAllQuestions();

        call.enqueue(new Callback<PreguntasLista>() {
            @Override
            public void onResponse(Call<PreguntasLista> call, Response<PreguntasLista> response) {
                Log.d(TAG, response.toString());
                PreguntasLista preguntas = response.body();
                if (preguntas != null) {

                    Pregunta pregunta;
                    pregunta = preguntas.getResults().get(0);
                    PreguntaFragment preguntaFragment = PreguntaFragment
                            .newInstance(
                                    pregunta.getQuestion(),
                                    pregunta.getCategory(),
                                    pregunta.getDifficulty(),
                                    pregunta.getCorrect_answer(),
                                    pregunta.getIncorrect_answers()
                            );
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.contFragmento, preguntaFragment, "FRAGMENTO").commit();
                }
            }

            @Override
            public void onFailure(Call<PreguntasLista> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: no se pudo descargar información de OpenTDB", Toast.LENGTH_SHORT).show();
            }
        });
    }
}