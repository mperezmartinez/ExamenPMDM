package com.example.miguel.examenpmdm;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miguel.examenpmdm.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);


//Instanciamos un boton y lo enlazamos con el boton que lo tenemos en el layout
        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//Recogemos el fragment por el id


                ItemListFragment fra = (ItemListFragment) getFragmentManager().findFragmentById(R.id.item_list);
                //En caso de que el fragment que contiene el texto sea null, y este en el layout , que es al estar apaisado, cerramos la activity
                if (fra == null || isInLayout()) {

                    //Creamos un intent
                    Intent intentresult = new Intent();
                    //intentresult.putExtra()("res");

                    //Pasamos el intent y el tipo de result , en este caso OK
                    getActivity().setResult(Activity.RESULT_OK, intentresult);
                    //cerramos la activity , gual que antes , pero en este caso pasa el result
                    getActivity().finish();


                    //En caso contrario hacemos un setText al item detail para poner su texto a 0 y que quede en blanco
                } else {
                    ((TextView) rootView.findViewById(R.id.item_detail)).setText(" ");
                }

            }
        });

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
        }


        return rootView;
    }
}
