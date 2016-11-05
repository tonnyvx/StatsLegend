package antonioneto.unibratec.com.br.statslegend.http;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import antonioneto.unibratec.com.br.statslegend.Model.Champion;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LolParser {

    public static final String URL_SEARCH = "http://ddragon.leagueoflegends.com/cdn/6.21.1/data/en_US/champion.json";

    public static List<Champion> searchByName() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL_SEARCH).build();

        List<Champion> champions = new ArrayList<Champion>();
        Response response = client.newCall(request).execute();
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            JSONObject jsonObject = new JSONObject(json);
            JSONObject data = jsonObject.getJSONObject("data");
            Iterator<String> keys = data.keys();


            Champion champ;

                while (keys.hasNext()) {
                String key = keys.next();
                JSONObject personagem = data.getJSONObject(key);
                String title = personagem.getString("title");
                String name = personagem.getString("name");
                String blurb = personagem.getString("blurb");
                String tags = personagem.getString("tags");
               /* Log.d("personagem", personagem.getString("full"));*/
                champ = new Champion();
                champ.title = personagem.getString("title");
                champ.name = personagem.getString("name");
                champ.blurb = personagem.getString("blurb");
                champ.tags = personagem.getString("tags");
                champions.add(champ);
            }


        }
        return champions;
    }
}