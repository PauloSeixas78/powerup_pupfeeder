package com.example.pupfeeder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
    Button excluir;
    String racao_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_racoes);
        racao_id = getIntent().getStringExtra("racao_id");

        marca = findViewById(R.id.editTextTelaCadstroRacoesMarca);
        quantidade = findViewById(R.id.editTextTelaCadastroRacoesQuantidade);
        tipo = findViewById(R.id.spinnerTelcCadastroRacoesTipo);
        porte = findViewById(R.id.spinnerTelaCadastroRacoesPorte);
        salvar = findViewById(R.id.buttonTelaCadastroRacoesSalvar);
        excluir = findViewById(R.id.buttonTelaCadastroRacoesExcluir);
        helper = new DatabaseHelper(this);

        if(racao_id!=null){
            prepararEdicao();
        }else{
            marca.setText("");
            quantidade.setText("");
        }

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarRacao(view);
            }
        });

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluirRacao(view);
            }
        });

    }

    public void prepararEdicao(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT marca, quantidade, tipo, porte from racoes WHERE _id = ?",new String[]{racao_id});
        cursor.moveToFirst();
        marca.setText(cursor.getString(0));
        quantidade.setText(cursor.getString(1));
        String[] tipos = getResources().getStringArray(R.array.activity_tela_cadastro_racoes_tipo_lista);
        for(int item=0;item < tipos.length;item++){
            if(tipos[item].equals(cursor.getString(2))){
                tipo.setSelection(item);
            }
        }
        String[] portes = getResources().getStringArray(R.array.activity_tela_cadastro_racoes_porte_lista);
        for(int item=0;item < portes.length;item++){
            if(portes[item].equals(cursor.getString(3)));
        }
    }


    public void salvarRacao(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        if (marca.getText().length() > 0 && quantidade.getText().length() > 0) {
            valores.put("marca", marca.getText().toString());
            valores.put("quantidade", quantidade.getText().toString());
            valores.put("tipo", tipo.getSelectedItem().toString());
            valores.put("porte", porte.getSelectedItem().toString());
            long resultado;
            if(racao_id == null) {
                resultado = db.insert("racoes", null, valores);
            }else{
                resultado = db.update("racoes",valores,"_id = ?",new String[]{racao_id});
            }

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

    public void excluirRacao(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        long resultado = db.delete("racoes","_id = ?",new String[]{racao_id});
        if (resultado != -1) {
            Toast.makeText(this, "Ração excluída com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Falha ao excluir a ração", Toast.LENGTH_LONG).show();
        }
        startActivity(new Intent(TelaCadastroRacoes.this, TelaListaRacoes.class));
    }

}