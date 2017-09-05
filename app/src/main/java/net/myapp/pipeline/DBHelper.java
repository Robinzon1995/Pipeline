package net.myapp.pipeline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Wanhe on 8/31/2017.
 */

public class DBHelper extends SQLiteOpenHelper
{
    private final String DB_NAME = "db_pipeline";
    private final int DB_VERSION = 1;

    private Context context;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);

        this.context = context;
    }

    private String fileToString(Context context, String file)
    {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try
        {
            inputStream = context.getAssets().open(file);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line);
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        finally
        {
            try
            {
                if (inputStreamReader != null)
                {
                    inputStreamReader.close();
                }
                if (inputStream != null)
                {
                    inputStream.close();
                }
                if (bufferedReader != null)
                {
                    bufferedReader.close();
                }
            }
            catch (Exception e2)
            {
                e2.getMessage();
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = fileToString(this.context, "init.sql");

        String[] query = sql.split(";");

        for(int i = 0; i < query.length; i++)
        {
            db.execSQL(query[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE ");
        this.onCreate(db);
    }
}
