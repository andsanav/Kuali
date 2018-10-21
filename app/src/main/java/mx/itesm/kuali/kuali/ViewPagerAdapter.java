package mx.itesm.kuali.kuali;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.BitmapRequestListener;

import java.util.ArrayList;

/**
 * Created by Andrea on 10/13/18.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    ArrayList<String> url_imagenes;
    ArrayList<Integer> imagenes;
    Bitmap current;

    public ViewPagerAdapter(Context context, ArrayList<String> url_imagenes) {
        this.context = context;
        this.url_imagenes = url_imagenes;
        imagenes = new ArrayList<Integer>();
        Log.i("context", context.toString());
    }

    @Override
    public int getCount() {
        return url_imagenes.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pager_layout, container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.ivProducto);
        new DownloadImageInBackground(imageView).execute(url_imagenes.get(position));
        imageView.setImageResource(R.drawable.dotselecteddot);
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    public void setImages() {
        for (int i = 0; i < url_imagenes.size(); i++) {
            descargarThumbnail(url_imagenes.get(i));
        }
    }

    private void descargarThumbnail(String imagen) {
        AndroidNetworking.get(imagen.toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsBitmap(new BitmapRequestListener() {
                    @Override
                    public void onResponse(Bitmap response) {
                        current = response;
                        Log.i("Imagenprodview", "Bien");
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.i("Lectura Imagen", anError.toString());
                    }
                });
    }

    private class DownloadImageInBackground extends AsyncTask<String, Void, Bitmap> {

        ImageView iv;

        public DownloadImageInBackground(ImageView iv) {
            this.iv = iv;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            AndroidNetworking.get(params.toString())
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsBitmap(new BitmapRequestListener() {
                        @Override
                        public void onResponse(Bitmap response) {
                            current = response;
                            Log.i("Imagenprodview", "Bien");
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.i("Lectura Imagen", anError.toString());
                        }
                    });
            return current;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            iv.setImageBitmap(result);
        }
    }
}
