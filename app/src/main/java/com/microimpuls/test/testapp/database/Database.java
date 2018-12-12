package com.microimpuls.test.testapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.microimpuls.test.testapp.BasicUserInfo;

import java.util.List;

/**
 * Источник данных https://api.jsonbin.io/b/5c0facf5ec62650f24dd2819
 */
public class Database<T extends BasicUserInfo> implements BaseColumns, UsersDataSource<T> {

	private static final String DATABASE_NAME = "test_app_database";
	private static final int DATABASE_VERSION = 17;

	private static final String USERS_TABLE_NAME = "users";
	private static final String USERS_KEY_ID = _ID;
	private static final String USERS_KEY_USER_ID = "userId";
	private static final String USERS_KEY_NAME = "username";
	private static final String USERS_KEY_AGE = "age";


	public Database( Context context ) {
		DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper( context );
	}

	@Override
	public long addUsers(List<T> users) {
		return 0;
	}

	@Override
	public long addUser( long userId, String username, int age ) {
		//ToDo
		return 0;
	}

	@Override
	public Cursor getUsersList() {
		//ToDo
		return null;
	}

	@Override
	public T getUserInfo( long id ) {
		//ToDo
		return null;
	}

	private void createUsersTable( SQLiteDatabase db ) {
		String DROP_USERS_TABLE_STRING = "DROP TABLE " + USERS_TABLE_NAME + ";";
		String CREATE_USERS_TABLE_STRING = "CREATE TABLE " + USERS_TABLE_NAME + " ( " +
				USERS_KEY_ID + " INTEGER PRIMARY KEY, " +
				USERS_KEY_USER_ID + " INTEGER, " +
				USERS_KEY_NAME + " TEXT, " +
				USERS_KEY_AGE + " TEXT );";
		db.execSQL( DROP_USERS_TABLE_STRING );
		db.execSQL( CREATE_USERS_TABLE_STRING );
	}

	private class DatabaseOpenHelper extends SQLiteOpenHelper {

		private DatabaseOpenHelper( Context context ) {
			super( context, DATABASE_NAME, null, DATABASE_VERSION );
		}

		@Override
		public void onCreate( SQLiteDatabase db ) {
			createUsersTable( db );
		}

		@Override
		public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {

		}
	}
}
