package mx.itesm.kuali.kuali;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.BitmapRequestListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrea on 10/19/18.
 */

public class RecyclerAdapterCat extends RecyclerView.Adapter<RecyclerAdapterCat.HolderCat>{

    List<Categoria> list;
    Context context;


    public RecyclerAdapterCat(List<Categoria> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerAdapterCat.HolderCat onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card,parent,false);
        final RecyclerAdapterCat.HolderCat myHolder = new RecyclerAdapterCat.HolderCat(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Categoria.categoria_busqueda = list.get(myHolder.position).id;
                Intent intent = new Intent(parent.getContext(), CategoriasActivity.class);
                parent.getContext().startActivity(intent);

            }
        });
        view.findViewById(R.id.thumbnail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Categoria.categoria_busqueda = list.get(myHolder.position).id;
                Intent intent = new Intent(parent.getContext(), CategoriasActivity.class);
                parent.getContext().startActivity(intent);

            }
        });


        return myHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull HolderCat holder, int position) {
        Categoria currentCat = list.get(position);
        holder.nombre.setText(currentCat.getNombre());
        holder.descargarThumbnail(currentCat.getUrl_thumbnail());
        holder.position = position;
    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){

                arr = 0;

            }
            else{

                arr=list.size();
            }



        }catch (Exception e){



        }

        return arr;

    }

    class HolderCat extends RecyclerView.ViewHolder{
        TextView nombre;
        ImageView thumbnail;
        int position;


        public HolderCat(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.title);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);

        }

        private void descargarThumbnail(String imagen) {
            AndroidNetworking.get(imagen.toString())
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsBitmap(new BitmapRequestListener() {
                        @Override
                        public void onResponse(Bitmap response) {
                            thumbnail.setImageBitmap(response);
                            Log.i("Imagen", "Bien");
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.i("Lectura Imagen", anError.toString());
                        }
                    });
        }
    }

    public void prepareIntent(Intent intent, RecyclerAdapterCat.HolderCat myHolder){


    }

}

