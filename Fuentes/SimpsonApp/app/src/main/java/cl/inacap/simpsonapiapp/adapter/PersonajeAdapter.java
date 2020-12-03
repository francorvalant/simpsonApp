package cl.inacap.simpsonapiapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import cl.inacap.simpsonapiapp.R;
import cl.inacap.simpsonapiapp.dao.Personaje;

public class PersonajeAdapter extends ArrayAdapter<Personaje> {
    private List<Personaje> personajes;
    private Activity activity;
    public PersonajeAdapter(@NonNull Activity context, int resource, @NonNull List<Personaje> objects) {
        super(context, resource, objects);
        this.personajes = objects;
        this.activity = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View viewInf = inflater.inflate(R.layout.list_personajes, null, true);
        ImageView imagePer = viewInf.findViewById(R.id.imgPersonaje);
        TextView character = viewInf.findViewById(R.id.character);
        TextView quote = viewInf.findViewById(R.id.quote);
        character.setText(personajes.get(position).getCharacter());
        quote.setText(personajes.get(position).getQuote());
        Picasso.get().load(this.personajes.get(position).getImage()).resize(300,300)
                .centerCrop().into(imagePer);
        return viewInf;
    }
}
