package com.sy.qfb.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.orhanobut.logger.Logger;
import com.sy.qfb.db.QfbContract;
import com.sy.qfb.db.QfbDbHelper;
import com.sy.qfb.model.MeasureData;

import java.util.List;

/**
 * Created by jshenf on 2017/6/9.
 */

public class SaveController {

    public void saveData(List<MeasureData> lstData) {
        SQLiteDatabase db = QfbDbHelper.getInstance().getWritableDatabase();

        db.beginTransaction();

        for (MeasureData data : lstData) {
            ContentValues content = new ContentValues();
//            content.put(QfbContract.DataEntry.COLUMN_NAME_DATAID, data.dataId);
            content.put(QfbContract.DataEntry.COLUMN_NAME_PROJID, data.projectId);
            content.put(QfbContract.DataEntry.COLUMN_NAME_PROJ_NAME, data.projectName);
            content.put(QfbContract.DataEntry.COLUMN_NAME_PRDID, data.productId);
            content.put(QfbContract.DataEntry.COLUMN_NAME_PRD_NAME, data.productName);
            content.put(QfbContract.DataEntry.COLUMN_NAME_TID, data.targetId);
            content.put(QfbContract.DataEntry.COLUMN_NAME_T_NAME, data.targetName);
            content.put(QfbContract.DataEntry.COLUMN_NAME_T_TYPE, data.targetType);
            content.put(QfbContract.DataEntry.COLUMN_NAME_PGID, data.pageId);
            content.put(QfbContract.DataEntry.COLUMN_NAME_MPOINT, data.measure_point);
            content.put(QfbContract.DataEntry.COLUMN_NAME_DIRECTION, data.direction);
            content.put(QfbContract.DataEntry.COLUMN_NAME_VALUE_1, data.value1);
            content.put(QfbContract.DataEntry.COLUMN_NAME_VALUE_2, data.value2);
            content.put(QfbContract.DataEntry.COLUMN_NAME_VALUE_3, data.value3);
            content.put(QfbContract.DataEntry.COLUMN_NAME_VALUE_4, data.value4);
            content.put(QfbContract.DataEntry.COLUMN_NAME_USERNAME, data.username);
            content.put(QfbContract.DataEntry.COLUMN_NAME_TIMESTAMP, data.timestamp);
            content.put(QfbContract.DataEntry.COLUMN_NAME_UPLOADED, 0);

            long rowId = db.insert(QfbContract.DataEntry.TABLE_NAME, null, content);
            Logger.d("rowId = " + rowId);
        }

        Cursor c1 = db.query(QfbContract.DataEntry.TABLE_NAME, null, null, null, null, null, null);
        Logger.d("c1.getCount() = " + c1.getCount());

        db.setTransactionSuccessful();
        db.endTransaction();


        Cursor c = db.query(QfbContract.DataEntry.TABLE_NAME, null, null, null, null, null, null);
        Logger.d("c.getCount() = " + c.getCount());

        db.close();
    }

}
