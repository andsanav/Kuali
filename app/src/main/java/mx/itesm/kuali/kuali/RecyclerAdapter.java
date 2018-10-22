package mx.itesm.kuali.kuali;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Andrea on 10/11/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder>{

    List<Producto> list;
    Context context;


    public RecyclerAdapter(List<Producto> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card,parent,false);
        final Holder myHolder = new Holder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent.getContext(),PantallaProducto.class);
                prepareIntent(intent, myHolder);
                parent.getContext().startActivity(intent);

            }
        });
        view.findViewById(R.id.thumbnail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent.getContext(),PantallaProducto.class);
                prepareIntent(intent, myHolder);
                parent.getContext().startActivity(intent);
            }
        });
        view.findViewById(R.id.corazon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("SERVIO", myHolder.id+"");
                if(!Categoria.elementos_likes.contains(myHolder.id))
                    Categoria.elementos_likes.add(myHolder.id);
            }
        });

        return myHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Producto mylist = list.get(position);
        holder.nombre.setText(mylist.getNombre());
        holder.likeNum.setText(String.valueOf(mylist.getNumLikes()));
        holder.precio.setText("$"+String.valueOf(mylist.getPrecio()));
        holder.descargarThumbnail(mylist.getUrl_imagenes().get(0).toString());
        holder.position = position;
        holder.id = mylist.getId();
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

    class Holder extends RecyclerView.ViewHolder{
        TextView nombre,likeNum,precio;
        ImageView thumbnail;
        int position;
        int id;


        public Holder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.title);
            likeNum = (TextView) itemView.findViewById(R.id.count);
            precio = (TextView) itemView.findViewById(R.id.price);
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

    public void prepareIntent(Intent intent, Holder myHolder){
        Producto current = list.get(myHolder.position);
        intent.putExtra("nombre", current.getNombre());
        intent.putExtra("precio", current.getPrecio());
        intent.putExtra("numLikes", current.getNumLikes());
        intent.putExtra("url_imagenes", current.getUrl_imagenes());
        intent.putExtra("marca", current.getMarca());
        intent.putExtra("descripcion", current.getDescripcion());
        ArrayList<String> nomTiendas = new ArrayList<>();
        ArrayList<String> linkTiendas = new ArrayList<>();
        ArrayList<String> nomRedes = new ArrayList<>();
        ArrayList<String> linkRedes = new ArrayList<>();
        for(int i = 0; i < current.getUrl_tiendas().size(); i++){
            nomTiendas.add(current.getUrl_tiendas().get(i).get(0));
            linkTiendas.add(current.getUrl_tiendas().get(i).get(1));
        }
        for(int i = 0; i < current.getUrl_sociales().size(); i++){
            nomRedes.add(current.getUrl_sociales().get(i).get(0));
            linkRedes.add(current.getUrl_sociales().get(i).get(1));
        }
        intent.putExtra("nomTiendas", nomTiendas);
        intent.putExtra("linkTiendas", linkTiendas);
        intent.putExtra("nomRedes", nomRedes);
        intent.putExtra("linkRedes", linkRedes);

    }

}
