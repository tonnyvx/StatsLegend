package antonioneto.unibratec.com.br.statslegend;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import antonioneto.unibratec.com.br.statslegend.Adapter.LolAdapter;
import antonioneto.unibratec.com.br.statslegend.Model.Champion;
import antonioneto.unibratec.com.br.statslegend.http.LolParser;

public class MainActivity extends AppCompatActivity {

    ListView mListChampions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListChampions = (ListView) findViewById(R.id.list_champions);

        new UserSearchTask().execute();
    }

    class UserSearchTask extends AsyncTask<String, Void, List<Champion>> {

        @Override
        protected List<Champion> doInBackground(String... params) {

            try {
                List<Champion> champions = LolParser.searchByName();
                for (Champion champion: champions) {
                    Log.wtf("NECO", "Nome: " + champion.name);
                }
                return champions;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

                return null;
        }

        @Override
        protected void onPostExecute(List<Champion> champions) {
            super.onPostExecute(champions);
            if(champions != null){
                mListChampions.setAdapter(new LolAdapter(MainActivity.this, champions));
            }
        }
    }
}

