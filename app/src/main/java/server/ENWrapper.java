package server;

import android.content.Context;

import com.echonest.api.v4.BasicPlaylistParams;
import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.echonest.api.v4.Playlist;

import crm.workshop.echonest.R;

public class ENWrapper {
    private static ENWrapper sInstance;
    private EchoNestAPI mApi;

    public ENWrapper(String key) {
        mApi = new EchoNestAPI(key);
    }

    public static ENWrapper with(Context context) {
        if (sInstance == null) {
            sInstance = new ENWrapper(context.getString(R.string
                    .echo_nest_api));
        }
        return sInstance;
    }

    public Playlist getArtistRadio(int results, String artist) throws
            EchoNestException {
        BasicPlaylistParams params = new BasicPlaylistParams();
        params.addIDSpace("7digital-US");
        params.includeTracks();
        params.addArtist(artist);
        params.setType(BasicPlaylistParams.PlaylistType.ARTIST_RADIO);
        params.setResults(results);
        return mApi.createBasicPlaylist(params);
    }
}

