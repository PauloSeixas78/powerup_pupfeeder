package com.example.pupfeeder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class TelaListaRacoes extends AppCompatActivity {

    ListView listaracoes;

    Button novaracao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_racoes);

        listaracoes = (ListView) findViewById(R.id.ListViewListaRacoes);

        novaracao = (Button) findViewById(R.id.buttonListaNovaRacao);

        novaracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaListaRacoes.this,TelaCadastroRacoes.class));
            }
        });

        String [] racoes = new String[]{"Premium","Resto de ontem","Pedigree","Filhotes"};

        ArrayAdapter <String> listaracoesadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,racoes);

        listaracoes.setAdapter(listaracoesadapter);

    }
}