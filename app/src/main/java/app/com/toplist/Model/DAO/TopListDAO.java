package app.com.toplist.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import app.com.toplist.DTO.responses.TopListDTO;
import app.com.toplist.Utility.LogManager;
import app.com.toplist.table.TableMaster;

/**
 * Created by Balwinder  on 7/29/2017.
 * Copyright (c) 2017 M800 inc. All rights reserved.
 */
public class TopListDAO extends BaseDAO {
    private static final String TAG = "TopListDAO";

    public TopListDAO(Context context) {
        super(context);
    }

    @Override
    public Object getData() {
        Cursor cursor = null;
        List<TopListDTO>  listDTOs = new ArrayList<>();
        try {
            cursor = mDBManager.selectQuery(TableMaster.tblTopList.TABLE_NAME, null, null,
                    null, null, null, null);
            if (cursor != null)

            while (cursor.moveToNext()) {
                TopListDTO list = new TopListDTO();
                String artistName = cursor.getString(cursor.getColumnIndex(TableMaster.tblTopList.ARTIST_NAME));
                String songName = cursor.getString(cursor.getColumnIndex(TableMaster.tblTopList.SONG_NAME));
                String url = cursor.getString(cursor.getColumnIndex(TableMaster.tblTopList.IMAGE_URL));
                list.setArtist(artistName);
                list.setSongName(songName);
                list.setThumbnail(url);
                listDTOs.add(list);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return listDTOs;
    }

    @Override
    public void insertData(@NonNull Object data) {
        if(data == null) return;

        List<TopListDTO> topListDTOs = null;
        try {
            topListDTOs = (List<TopListDTO>) data;
        }
        catch(ClassCastException e)
        {

        }
         if(topListDTOs==null) return;

        //delete prev data first
        mDBManager.deleteQuery(TableMaster.tblTopList.TABLE_NAME,null,null);
        ContentValues values = new ContentValues();
        for (TopListDTO mdto : topListDTOs
                ) {
            values.clear();
            values.put(TableMaster.tblTopList.ARTIST_NAME, mdto.getArtist());
            values.put(TableMaster.tblTopList.SONG_NAME, mdto.getSongName());
            values.put(TableMaster.tblTopList.IMAGE_URL, mdto.getThumbnail());
            if (mDBManager.insert(TableMaster.tblTopList.TABLE_NAME, null, values) > 0)
                LogManager.D(TAG, "inserted successfully ");
        }

    }


}
