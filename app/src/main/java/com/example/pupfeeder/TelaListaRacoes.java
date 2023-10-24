package com.example.pupfeeder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TelaListaRacoes extends AppCompatActivity {

    ListView listaracoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_racoes);

        listaracoes = (ListView) findViewById(R.id.ListViewListaRacoes);

        String [] racoes = new String[]{"Premium","Resto de ontem","Pedigree","Filhotes"};

        ArrayAdapter <String> listaracoesadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,racoes);

        listaracoes.setAdapter(listaracoesadapter);

    }
}