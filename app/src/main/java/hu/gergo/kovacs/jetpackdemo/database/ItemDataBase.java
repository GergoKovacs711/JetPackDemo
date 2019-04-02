package hu.gergo.kovacs.jetpackdemo.database;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import hu.gergo.kovacs.jetpackdemo.R;
import hu.gergo.kovacs.jetpackdemo.model.Item;
import hu.gergo.kovacs.jetpackdemo.util.ApplicationHelper;
import hu.gergo.kovacs.jetpackdemo.util.ItemData;

/**
 * @author Gergo Kovacs
 * @version 1.0
 * @date 2019.03.30.
 */

@Database(entities = {Item.class}, version = 1, exportSchema = false)
public abstract class ItemDataBase extends RoomDatabase {

    private static volatile ItemDataBase INSTANCE;

    public abstract ItemDao getItemDao();

    public static ItemDataBase getDataBase(final Context context) {
        if (INSTANCE == null) {

            // synchronizing onto the class to avoid thread collision
            synchronized (ItemDataBase.class) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ItemDataBase.class,
                            "item_database").addCallback(
                            sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase database) {
            super.onCreate(database);
            new PopulateDBAsync(INSTANCE).execute();
        }


        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase database) {
            super.onOpen(database);
        }

    };


    public static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {

        private final ItemDao itemDao;

        PopulateDBAsync(ItemDataBase database) {
            itemDao = database.getItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String[] texts = ItemData.INSTANCE.getTexts();
            TypedArray images = ItemData.INSTANCE.getImages();

            for (int i = 0; i < texts.length; i++) {
                    int image = images.getResourceId(i, -1);
                    itemDao.insert(new Item(texts[i], image));
            }

            return null;
        }
    }
}

