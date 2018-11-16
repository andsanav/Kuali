package mx.itesm.kuali.kuali;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder>{

    List<Producto> list;
    Context context;
    ImageView logos;

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
                //ConexionAInternet.obtenerConexion(context, true);
                Intent intent = new Intent(parent.getContext(),PantallaProducto.class);
                prepareIntent(intent, myHolder);
                parent.getContext().startActivity(intent);

            }
        });
        view.findViewById(R.id.thumbnail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ConexionAInternet.obtenerConexion(context, true);
                Intent intent = new Intent(parent.getContext(),PantallaProducto.class);
                prepareIntent(intent, myHolder);
                parent.getContext().startActivity(intent);
            }
        });
        view.findViewById(R.id.corazon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("SERVIO", myHolder.id+"");
                logos = view.findViewById(R.id.corazon);
                if(Categoria.elementos_likes.contains(myHolder.id+"")){
                    Categoria.elementos_likes.remove(myHolder.id+"");
                    logos.setImageResource(R.drawable.unfavoriteheart);
                    list.get(myHolder.position).numLikes--;
                } else {
                    Categoria.elementos_likes.add(myHolder.id+"");
                    logos.setImageResource(R.drawable.favoriteheart);
                    list.get(myHolder.position).numLikes++;
                }
                myHolder.likeNum.setText(String.valueOf(list.get(myHolder.position).getNumLikes()));
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
        if(Categoria.elementos_likes.contains(holder.id+"")){
            holder.corazon.setImageResource(R.drawable.favoriteheart);
        } else {
            holder.corazon.setImageResource(R.drawable.unfavoriteheart);
        }
    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try{
            if(list.size()==0){
                arr = 0;
            }
            else {
                arr = list.size();
            }
        }catch (Exception e){
        }
        return arr;

    }

    class Holder extends RecyclerView.ViewHolder{
        TextView nombre,likeNum,precio;
        ImageView thumbnail, corazon;
        int position;
        int id;


        public Holder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.title);
            likeNum = (TextView) itemView.findViewById(R.id.count);
            precio = (TextView) itemView.findViewById(R.id.price);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            corazon = (ImageView) itemView.findViewById(R.id.corazon);
        }

        private void descargarThumbnail(String url) {
            final String url_download = url;
            Bitmap imagen = CacheImage.obtenerBitmapDeCache(url);
            if(imagen == null){
                AndroidNetworking.get(url.toString())
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsBitmap(new BitmapRequestListener() {
                            @Override
                            public void onResponse(Bitmap response) {
                                thumbnail.setImageBitmap(response);
                                CacheImage.agregarBitmapACache(url_download, response);
                                Log.i("Imagen", "Bien");
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.i("Lectura Imagen", anError.toString());
                            }
                        });
            } else {
                thumbnail.setImageBitmap(imagen);
            }

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
