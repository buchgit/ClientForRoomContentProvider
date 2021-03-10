package com.example.clientforroomcontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
/*
клиент для подключения к контент провайдеру другого приложения,
сделанного на room и контент провайдере
в рамках курса coursera, course 2, week 2
 */

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int LOADER_ID = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportLoaderManager().initLoader(LOADER_ID,null,this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, Uri.parse("content://com.example.musicprovider/album"),null,null,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (data!=null&&data.moveToFirst()){
            StringBuilder builder = new StringBuilder();
            do {
                String s = data.getString(data.getColumnIndex("name"));
                builder.append(s).append('\n');
            }while (data.moveToNext());
            Toast.makeText(this,builder.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}