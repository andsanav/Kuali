package mx.itesm.kuali.kuali;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.BitmapRequestListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class PantallaProducto extends AppCompatActivity {

    ViewPager viewpager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    LinearLayout sliderDots;
    private int dotCount;
    ImageView[] dots;
    ArrayList<String> url_imagenes;
    TextView tvNombre, tvMarca, tvDescripcion, tvPrecio;
    String nombre, marca, descripcion;
    double precio;
    ExpandableListView listView;
    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    private HashMap<String, List<String>> listHashMap;
    ImageView imageView;
    Bitmap current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_producto);


        //imageView = (ImageView) findViewById(R.id.ivProducto);

        tvNombre = findViewById(R.id.tvNombre);
        tvMarca = findViewById(R.id.tvMarca);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvPrecio = findViewById(R.id.tvPrecio);

        viewpager = findViewById(R.id.viewPager);
        sliderDots = findViewById(R.id.sliderDots);
        url_imagenes = getIntent().getStringArrayListExtra("url_imagenes");
        Log.i("url", url_imagenes.get(0));
        nombre = getIntent().getStringExtra("nombre");
        marca = getIntent().getStringExtra("marca");
        descripcion = getIntent().getStringExtra("descripcion");
        precio = getIntent().getDoubleExtra("precio", 0);

        tvNombre.setText(nombre);
        tvMarca.setText(marca.toUpperCase());
        tvDescripcion.setText(descripcion);
        tvPrecio.setText("$"+precio);

        listView = (ExpandableListView)findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHashMap,getIntent().getStringArrayListExtra("linkTiendas"),
                getIntent().getStringArrayListExtra("linkRedes"));
        listView.setAdapter(listAdapter);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, url_imagenes);
        viewpager.setAdapter(viewPagerAdapter);
        dotCount = viewPagerAdapter.getCount();
        dots = new ImageView[dotCount];

        for(int i = 0; i < dotCount; i++){
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dotdot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);
            params.gravity = 0;
            sliderDots.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dotselecteddot));

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("position", position+"");

                for(int i = 0; i < dotCount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dotdot));

                    dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dotselecteddot));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listDataHeader.add("Tiendas");
        listDataHeader.add("Redes sociales");
        listHashMap = new HashMap<>();

        List<String> tiendasList = getIntent().getStringArrayListExtra("nomTiendas");
        List<String> redesList = getIntent().getStringArrayListExtra("nomRedes");
        listHashMap.put(listDataHeader.get(0),tiendasList);
        listHashMap.put(listDataHeader.get(1),redesList);

    }


}
