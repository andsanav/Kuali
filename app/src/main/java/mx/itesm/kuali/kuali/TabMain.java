package mx.itesm.kuali.kuali;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TabMain extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    SearchView busqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_tab_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabTextColors(Color.parseColor("#FFB3B3B3"), Color.parseColor("#FFFF8226"));

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        busqueda = findViewById(R.id.busqueda_completa);
        busqueda.setQueryHint("Buscar");
        busqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Categoria.elemento_busqueda = query;
                busqueda.setQuery("", false);
                busqueda.clearFocus();
                Intent tabMain = new Intent(getBaseContext(), BusquedaProductos.class);
                startActivity(tabMain);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.acerca_de) {
            Intent tabMain = new Intent(getBaseContext(), AcercaDe.class);
            startActivity(tabMain);
        } else if (id == R.id.registro_productos) {
            Intent tabMain = new Intent(getBaseContext(), Formulario_Producto.class);
            startActivity(tabMain);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tab_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    PopularesFrag popularesFrag = new PopularesFrag();
                    return popularesFrag;
                case 1:
                    CategoriasFrag categoriasFrag = new CategoriasFrag();
                    return categoriasFrag;
                case 2:
                    FavoritosFrag favoritosFrag = new FavoritosFrag();
                    return favoritosFrag;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "POPULARES";
                case 1:
                    return "CATEGORIAS";
                case 2:
                    return "FAVORITOS";
            }
            return null;
        }
    }

    @Override
    protected void onPause() {
        guardarPreferencias();
        if(ConexionAInternet.obtenerConexion(this, false)){
            actualizarConteoLikes();
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        CacheImage.vaciarCache();
        super.onStop();
    }

    private void guardarPreferencias(){
        SharedPreferences sharedPref = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor = editor.remove("favoritos");
        editor = editor.clear();
        editor.commit();
        editor = editor.putStringSet("favoritos", Categoria.elementos_likes);
        Log.i("Editor", editor.commit()+"");
        Log.i("Salvando", Categoria.elementos_likes.toString());
    }

    private void actualizarConteoLikes(){
        try{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef;
            for(Producto producto: Producto.lista_productos){
                myRef = database.getReference("Productos/" + producto.id + "/");
                myRef.child("numLikes").setValue(producto.numLikes);
                Log.i("Firebase", producto.id+"");
            }
        } catch (Exception e){
            Log.i("Error guardado Firebase", e.toString());
        }
    }
}
