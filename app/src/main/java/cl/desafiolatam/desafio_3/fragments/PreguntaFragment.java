package cl.desafiolatam.desafio_3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;

import cl.desafiolatam.desafio_3.R;

public class PreguntaFragment extends Fragment {
  private int radioButtonValue = 0;
  private TextView preguntaView, categoriaView, dificultadView;
  private RadioGroup grupoRespuestasView;
  private RadioButton respuestaUno, respuestaDos;

  public static PreguntaFragment newInstance(String pregunta,
                                             String categoria,
                                             String dificultad,
                                             String respuestaCorrecta,
                                             ArrayList<String> respuestasIncorrectas) {
    PreguntaFragment fragment = new PreguntaFragment();

    Bundle arguments = new Bundle();
    arguments.putString("PREGUNTA", pregunta);
    arguments.putString("CATEGORIA", categoria);
    arguments.putString("DIFICULTAD", dificultad);
    arguments.putString("RESPUESTA_CORRECTA", respuestaCorrecta);
    arguments.putStringArrayList("RESPUESTAS_INCORRECTAS", respuestasIncorrectas);
    fragment.setArguments(arguments);
    return fragment;
  }

  private void initializeViews(View view) {
    preguntaView = view.findViewById(R.id.pregunta);
    categoriaView = view.findViewById(R.id.categoria);
    dificultadView = view.findViewById(R.id.dificultad);
    grupoRespuestasView = view.findViewById(R.id.radioGrupoRespuestas);
    respuestaUno = view.findViewById(R.id.respuestaTrue);
    respuestaDos = view.findViewById(R.id.respuestaFalse);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_pregunta, container, false);
    final String pregunta = Objects.requireNonNull(getArguments()).getString("PREGUNTA");
    final String categoria = Objects.requireNonNull(getArguments().getString("CATEGORIA"));
    final String dificultad = Objects.requireNonNull(getArguments().getString("DIFICULTAD"));
    final String respuestaCorrecta =
        Objects.requireNonNull(getArguments().getString("RESPUESTA_CORRECTA"));
    final ArrayList<String> respuestasIncorrectas = Objects.requireNonNull(getArguments().getStringArrayList("RESPUESTAS_INCORRECTAS"));

    initializeViews(view);

    preguntaView.setText(pregunta);
    categoriaView.setText(categoria);
    dificultadView.setText(dificultad);

    respuestaUno.setText(respuestasIncorrectas.get(0));
    respuestaDos = view.findViewById(R.id.respuestaFalse);
    respuestaDos.setText(respuestaCorrecta);

    grupoRespuestasView.setOnCheckedChangeListener((group, checkedId) -> {
      if (respuestaUno.isChecked()) {
        radioButtonValue = 1;
      } else if (respuestaDos.isChecked()) {
        radioButtonValue = 2;
      }
    });

    return view;
  }
}