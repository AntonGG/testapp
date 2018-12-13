package com.microimpuls.test.testapp.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.microimpuls.test.testapp.UserInfo;

import java.util.List;


public class Database implements UsersDataSource, BaseColumns {
    private static final String DATABASE_NAME = "test_app_database";
    private static final int DATABASE_VERSION = 18;

    private static final String USERS_TABLE_NAME = "users";
    private static final String USERS_KEY_ID = _ID;
    private static final String USERS_KEY_USER_ID = "user_id";
    private static final String USERS_KEY_FIRST_NAME = "first_name";
    private static final String USERS_KEY_LAST_NAME = "last_name";
    private static final String USERS_KEY_AGE = "age";
    private static final String USERS_KEY_EMAIL = "email";
    private static final String USERS_KEY_PHONE = "phone";

    private static final String HOBBIES_TABLE_NAME = "hobbies";
    private static final String HOBBIES_KEY_ID = _ID;
    private static final String HOBBIES_KEY_NAME = _ID;

    private static final String SKILLS_TABLE_NAME = "skills";
    private static final String SKILLS_KEY_ID = _ID;
    private static final String SKILLS_KEY_SKILL_NAME = "skill_name";
    private static final String SKILLS_KEY_SKILL_LEVEL = "skill_level";

    private static final String USERS_HOBBIES_TABLE_NAME = "users_hobbies";
    private static final String USERS_HOBBIES_KEY_ID = _ID;
    private static final String USERS_HOBBIES_KEY_USER_ID = "user_id";
    private static final String USERS_HOBBIES_KEY_HOBBIES_ID = "hobbies_id";

    private static final String USERS_SKILLS_TABLE_NAME = "users_skills";
    private static final String USERS_SKILLS_KEY_ID = _ID;
    private static final String USERS_SKILLS_KEY_USER_ID = "user_id";
    private static final String USERS_SKILLS_KEY_SKILLS_ID = "skills_id";

    @Override
    public long addUsers(List users) {
        return 0;
    }

    @Override
    public long addUser(long userId, String username, int age) {
        return 0;
    }

    @Override
    public Cursor getUsersList() {
        return null;
    }

    @Override
    public UserInfo getUserInfo(long id) {
        return null;
    }

    private void createUsersTable(SQLiteDatabase db) {
        String DROP_USERS_TABLE_STRING = "DROP TABLE " + USERS_TABLE_NAME + ";";
        String DROP_HOBBIES_TABLE_STRING = "DROP TABLE " + HOBBIES_TABLE_NAME + ";";
        String DROP_SKILLS_TABLE_STRING = "DROP TABLE " + SKILLS_TABLE_NAME + ";";
        String DROP_USERS_HOBBIES_TABLE_STRING = "DROP TABLE " + USERS_HOBBIES_TABLE_NAME + ";";
        String DROP_USERS_SKILLS_TABLE_STRING = "DROP TABLE " + USERS_SKILLS_TABLE_NAME + ";";

        String CREATE_USERS_TABLE_STRING = "CREATE TABLE " + USERS_TABLE_NAME + " ( " +
                USERS_KEY_ID + " INTEGER PRIMARY KEY, " +
                USERS_KEY_USER_ID + " INTEGER, " +
                USERS_KEY_FIRST_NAME + " TEXT, " +
                USERS_KEY_LAST_NAME + " TEXT, " +
                USERS_KEY_EMAIL + " TEXT, " +
                USERS_KEY_PHONE + " TEXT, " +
                USERS_KEY_AGE + " INTEGER );";

        String CREATE_HOBBIES_TABLE_STRING = "CREATE TABLE " + HOBBIES_TABLE_NAME + " ( " +
                HOBBIES_KEY_ID + " INTEGER PRIMARY KEY, " +
                HOBBIES_KEY_NAME + " TEXT );";

        String CREATE_SKILLS_TABLE_STRING = "CREATE TABLE " + SKILLS_TABLE_NAME + " ( " +
                SKILLS_KEY_ID + " INTEGER PRIMARY KEY, " +
                SKILLS_KEY_SKILL_NAME + " TEXT, " +
                SKILLS_KEY_SKILL_LEVEL + " INTEGER );";

        String CREATE_USER_HOBBIES_TABLE_STRING = "CREATE TABLE " + USERS_HOBBIES_TABLE_NAME + " ( " +
                USERS_HOBBIES_KEY_ID + " INTEGER PRIMARY KEY, " +
                USERS_HOBBIES_KEY_USER_ID + " INTEGER, " +
                USERS_HOBBIES_KEY_HOBBIES_ID + " INTEGER, " +
                "FOREIGN KEY(" + USERS_HOBBIES_KEY_USER_ID + ") REFERENCES " + USERS_TABLE_NAME + "(" + USERS_KEY_ID + "), " +
                "FOREIGN KEY(" + USERS_HOBBIES_KEY_HOBBIES_ID + ") REFERENCES " + HOBBIES_TABLE_NAME + "(" + HOBBIES_KEY_ID + "));";

        String CREATE_USER_SKILLS_TABLE_STRING = "CREATE TABLE " + USERS_SKILLS_TABLE_NAME + " ( " +
                USERS_SKILLS_KEY_ID + " INTEGER PRIMARY KEY, " +
                USERS_SKILLS_KEY_USER_ID + " INTEGER, " +
                USERS_SKILLS_KEY_SKILLS_ID + " INTEGER, " +
                "FOREIGN KEY(" + USERS_SKILLS_KEY_USER_ID + ") REFERENCES " + USERS_TABLE_NAME + "(" + USERS_KEY_ID + "), " +
                "FOREIGN KEY(" + USERS_SKILLS_KEY_SKILLS_ID + ") REFERENCES " + HOBBIES_TABLE_NAME + "(" + SKILLS_KEY_ID + "));";

        db.execSQL(DROP_USERS_HOBBIES_TABLE_STRING);
        db.execSQL(DROP_USERS_SKILLS_TABLE_STRING);
        db.execSQL(DROP_USERS_TABLE_STRING);
        db.execSQL(DROP_HOBBIES_TABLE_STRING);
        db.execSQL(DROP_SKILLS_TABLE_STRING);

        db.execSQL(CREATE_USERS_TABLE_STRING);
        db.execSQL(CREATE_HOBBIES_TABLE_STRING);
        db.execSQL(CREATE_SKILLS_TABLE_STRING);
        db.execSQL(CREATE_USER_HOBBIES_TABLE_STRING);
        db.execSQL(CREATE_USER_SKILLS_TABLE_STRING);
    }
}
