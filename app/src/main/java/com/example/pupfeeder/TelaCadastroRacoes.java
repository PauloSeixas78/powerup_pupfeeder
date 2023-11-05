package com.example.pupfeeder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TelaCadastroRacoes extends AppCompatActivity {

    DatabaseHelper helper;

    EditText marca;
    EditText quantidade;
    Spinner tipo;
    Spinner porte;
    Button salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_racoes);

        marca = findViewById(R.id.editTextTelaCadstroRacoesMarca);
        quantidade = findViewById(R.id.editTextTelaCadastroRacoesQuantidade);
        tipo = findViewById(R.id.spinnerTelcCadastroRacoesTipo);
        porte = findViewById(R.id.spinnerTelaCadastroRacoesPorte);
        salvar = findViewById(R.id.buttonTelaCadastroRacoesSalvar);
        helper = new DatabaseHelper(this);

        marca.setText("");
        quantidade.setText("");

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarRacao(view);
            }
        });

    }

    public void salvarRacao(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        if (marca.getText().length() > 0 && quantidade.getText().length() > 0) {
            valores.put("marca", marca.getText().toString());
            valores.put("quantidade", quantidade.getText().toString());
            valores.put("tipo", tipo.getSelectedItem().toString());
            valores.put("porte", porte.getSelectedItem().toString());


            long resultado = db.insert("racoes", null, valores);

            if (resultado != -1) {
                Toast.makeText(this, getString(R.string.activity_tela_cadastro_racoes_salvo_com_sucesso), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.activity_tela_cadastro_racoes_erro_ao_salvar), Toast.LENGTH_LONG).show();
            }

        } else
        {
            Toast.makeText(this, getString(R.string.activity_tela_cadastro_racoes_campos_vazios),Toast.LENGTH_SHORT).show();
        }

        startActivity(new Intent(TelaCadastroRacoes.this, TelaListaRacoes.class));



    }

}