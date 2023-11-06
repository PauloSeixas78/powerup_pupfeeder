package com.example.pupfeeder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TelaListaRacoes extends AppCompatActivity {

    DatabaseHelper helper;

    ArrayList<Racoes> racoescadastradas;

    ListView listaracoes;

    Button novaracao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_racoes);

        helper = new DatabaseHelper(this);

        racoescadastradas = new ArrayList<Racoes>();

        listaracoes = (ListView) findViewById(R.id.ListViewListaRacoes);

        novaracao = (Button) findViewById(R.id.buttonListaNovaRacao);

        novaracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaListaRacoes.this,TelaCadastroRacoes.class));
            }
        });

        ArrayAdapter <String> listaracoesadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lerRacoes());

        listaracoes.setAdapter(listaracoesadapter);

        listaracoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TelaListaRacoes.this, TelaCadastroRacoes.class);
                intent.putExtra("racao_id",racoescadastradas.get(i).getId().toString());
                startActivity(intent);
            }
        });


    }

    private String [] lerRacoes(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id,marca,quantidade,tipo,porte from racoes",null);
        cursor.moveToFirst();
        String [] racoeslidas = new String[cursor.getCount()];
        for(int item = 0; item <cursor.getCount();item++){
            racoescadastradas.add(new Racoes(cursor.getLong(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4)));
            racoeslidas[item] = cursor.getString(1);
            cursor.moveToNext();
        }
        cursor.close();

        return racoeslidas;
    }
}