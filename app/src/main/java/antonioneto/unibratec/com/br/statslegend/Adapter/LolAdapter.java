package antonioneto.unibratec.com.br.statslegend.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import antonioneto.unibratec.com.br.statslegend.Model.Champion;
import antonioneto.unibratec.com.br.statslegend.R;

/**
 * Created by AntonioNeto on 02/11/2016.
 */
public class LolAdapter extends ArrayAdapter<Champion>{



    public LolAdapter(Context context, List<Champion> champions){
        super(context,0,champions);

    }
public static final String URL_IMAGE ="http://ddragon.leagueoflegends.com/cdn/6.22.1/img/champion/%s.png";
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
Champion champ = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.activity_champions, parent, false);
        }
        TextView championName= (TextView) convertView.findViewById(R.id.championName);
        TextView championTitle = (TextView) convertView.findViewById(R.id.championTitle);
        TextView championClass = (TextView) convertView.findViewById(R.id.championClass);
        ImageView championImage = (ImageView) convertView.findViewById(R.id.championImage);
        championName.setText(champ.name);
        championTitle.setText(champ.title);
        championClass.setText(champ.tags);
         Picasso.with(getContext()).load(String.format(URL_IMAGE,champ.name.toString()))
                .resize(50,50)
                .into(championImage);
        return convertView;
}
}
