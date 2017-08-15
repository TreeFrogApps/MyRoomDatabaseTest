package innovation.com.moviedatabasetest.movie;


import android.content.Context;

public class MovieSharedModel implements MVP.IMovieModel {

    private final Context context;

    public MovieSharedModel(Context context) {
        this.context = context;
    }

    @Override public void bind() {

    }

    @Override public void unbind(boolean isChangingConfigurations) {

    }
}
