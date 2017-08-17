package innovation.com.moviedatabasetest.di.module;


import android.arch.persistence.room.Room;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import innovation.com.moviedatabasetest.di.scope.ApplicationScope;
import innovation.com.moviedatabasetest.provider.db.MovieDatabase;

@Module public class MovieDatabaseModule {

    private static final String DATABASE_NAME = "movies_database";

    @Provides @ApplicationScope MovieDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, DATABASE_NAME).build();
    }
}
