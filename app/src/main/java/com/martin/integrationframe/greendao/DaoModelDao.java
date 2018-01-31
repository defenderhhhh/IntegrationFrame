package com.martin.integrationframe.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.martin.integrationframe.model.DaoModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DAO_MODEL".
*/
public class DaoModelDao extends AbstractDao<DaoModel, Void> {

    public static final String TABLENAME = "DAO_MODEL";

    /**
     * Properties of entity DaoModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Test = new Property(0, String.class, "test", false, "TEST");
    }


    public DaoModelDao(DaoConfig config) {
        super(config);
    }
    
    public DaoModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DAO_MODEL\" (" + //
                "\"TEST\" TEXT);"); // 0: test
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DAO_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DaoModel entity) {
        stmt.clearBindings();
 
        String test = entity.getTest();
        if (test != null) {
            stmt.bindString(1, test);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DaoModel entity) {
        stmt.clearBindings();
 
        String test = entity.getTest();
        if (test != null) {
            stmt.bindString(1, test);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public DaoModel readEntity(Cursor cursor, int offset) {
        DaoModel entity = new DaoModel( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0) // test
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DaoModel entity, int offset) {
        entity.setTest(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(DaoModel entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(DaoModel entity) {
        return null;
    }

    @Override
    public boolean hasKey(DaoModel entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}