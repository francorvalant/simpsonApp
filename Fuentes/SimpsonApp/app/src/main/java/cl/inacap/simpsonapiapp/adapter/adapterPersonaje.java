package cl.inacap.simpsonapiapp.adapter;

import android.app.Activity;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

import cl.inacap.simpsonapiapp.dao.Personaje;

public class adapterPersonaje extends ArrayAdapter {

    private List<Personaje> personajes;
    private Activity activity;//
    public adapterPersonaje(@NonNull Activity context, int resource, @NonNull List<Personaje> objects) {
        super(context, resource, objects);
        this.personajes = objects;
        this.activity = context;
    }


}
