package com.example.trabalhot2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Contato> contatos;

    public Adapter(List<Contato> contatos){
        this.contatos = contatos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position){
        viewHolder.setData(contatos.get(position));
    }

    @Override
    public int getItemCount(){
        return contatos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNome;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            txtNome = itemView.findViewById(R.id.txtNome);
        }

        private void setData (Contato contato){
            txtNome.setText(new Integer(contato.getNome()).toString());
        }

        public void onClick(View view){

        }
    }
}

