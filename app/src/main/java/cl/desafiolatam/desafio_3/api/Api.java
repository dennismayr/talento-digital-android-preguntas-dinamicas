package cl.desafiolatam.desafio_3.api;

import cl.desafiolatam.desafio_3.beans.PreguntasLista;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("api.php?amount=1&category=18&difficulty=medium&type=boolean")
    Call<PreguntasLista> getAllQuestions();
}
