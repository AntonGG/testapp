package com.microimpuls.test.testapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.microimpuls.test.testapp.Skill;
import com.microimpuls.test.testapp.UserInfo;

import java.util.ArrayList;
import java.util.List;

import static com.microimpuls.test.testapp.database.StaticDatabase.HOBBIES_KEY_NAME;
import static com.microimpuls.test.testapp.database.StaticDatabase.HOBBIES_TABLE_NAME;
import static com.microimpuls.test.testapp.database.StaticDatabase.SKILLS_KEY_SKILL_LEVEL;
import static com.microimpuls.test.testapp.database.StaticDatabase.SKILLS_KEY_SKILL_NAME;
import static com.microimpuls.test.testapp.database.StaticDatabase.SKILLS_TABLE_NAME;
import static com.microimpuls.test.testapp.database.StaticDatabase.USERS_HOBBIES_KEY_HOBBIES_ID;
import static com.microimpuls.test.testapp.database.StaticDatabase.USERS_HOBBIES_KEY_USER_ID;
import static com.microimpuls.test.testapp.database.StaticDatabase.USERS_HOBBIES_TABLE_NAME;
import static com.microimpuls.test.testapp.database.StaticDatabase.USERS_KEY_AGE;
import static com.microimpuls.test.testapp.database.StaticDatabase.USERS_KEY_EMAIL;
import static com.microimpuls.test.testapp.database.StaticDatabase.USERS_KEY_FIRST_NAME;
import static com.microimpuls.test.testapp.database.StaticDatabase.USERS_KEY_LAST_NAME;
import static com.microimpuls.test.testapp.database.StaticDatabase.USERS_KEY_PHONE;
import static com.microimpuls.test.testapp.database.StaticDatabase.USERS_KEY_USER_ID;
import static com.microimpuls.test.testapp.database.StaticDatabase.USERS_SKILLS_KEY_SKILLS_ID;
import static com.microimpuls.test.testapp.database.StaticDatabase.USERS_SKILLS_KEY_USER_ID;
import static com.microimpuls.test.testapp.database.StaticDatabase.USERS_SKILLS_TABLE_NAME;
import static com.microimpuls.test.testapp.database.StaticDatabase.USERS_TABLE_NAME;

public class Database implements UsersDataSource<UserInfo>, BaseColumns {
    private static final String DATABASE_NAME = "test_app_database";
    private static final int DATABASE_VERSION = 18;
    private final DatabaseOpenHelper dbHelper;

    public Database(Context context) {
        dbHelper = new DatabaseOpenHelper(context);
    }

    @Override
    public void addUsers(List<UserInfo> users) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (UserInfo user : users) {
            ContentValues cv = new ContentValues();
            cv.put(USERS_KEY_USER_ID, user.getId());
            cv.put(USERS_KEY_FIRST_NAME, user.getFirstName());
            cv.put(USERS_KEY_LAST_NAME, user.getLastName());
            cv.put(USERS_KEY_EMAIL, user.getEmail());
            cv.put(USERS_KEY_PHONE, user.getPhoneNumber());
            cv.put(USERS_KEY_AGE, user.getAge());
            long _id_user = db.insertWithOnConflict(USERS_TABLE_NAME, null,
                    cv, SQLiteDatabase.CONFLICT_REPLACE);

            List<Long> hobbiesIdList = new ArrayList<>();
            for (String hobbie : user.getHobbies()) {
                ContentValues cvHobbie = new ContentValues();
                cvHobbie.put(HOBBIES_KEY_NAME, hobbie);
                long id = db.insertWithOnConflict(HOBBIES_TABLE_NAME, null,
                        cvHobbie, SQLiteDatabase.CONFLICT_REPLACE);
                hobbiesIdList.add(id);
            }

            List<Long> skillsIdList = new ArrayList<>();
            for (Skill skill : user.getSkills()) {
                ContentValues cvSkill = new ContentValues();
                cvSkill.put(SKILLS_KEY_SKILL_NAME, skill.getSkillName());
                cvSkill.put(SKILLS_KEY_SKILL_LEVEL, skill.getSkillLevelAssessment());
                long id = db.insertWithOnConflict(SKILLS_TABLE_NAME, null,
                        cvSkill, SQLiteDatabase.CONFLICT_REPLACE);
                skillsIdList.add(id);
            }

            for (Long hobbieId : hobbiesIdList) {
                ContentValues cvUserHobbie = new ContentValues();
                cvUserHobbie.put(USERS_HOBBIES_KEY_USER_ID, _id_user);
                cvUserHobbie.put(USERS_HOBBIES_KEY_HOBBIES_ID, hobbieId);
                db.insertWithOnConflict(USERS_HOBBIES_TABLE_NAME, null,
                        cvUserHobbie, SQLiteDatabase.CONFLICT_REPLACE);
            }

            for (Long skillId : skillsIdList) {
                ContentValues cvUserSkill = new ContentValues();
                cvUserSkill.put(USERS_SKILLS_KEY_USER_ID, _id_user);
                cvUserSkill.put(USERS_SKILLS_KEY_SKILLS_ID, skillId);
                db.insertWithOnConflict(USERS_SKILLS_TABLE_NAME, null,
                        cvUserSkill, SQLiteDatabase.CONFLICT_REPLACE);
            }
        }
        db.close();
    }

    @Override
    public long addUser(long userId, String username, int age) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERS_KEY_USER_ID, userId);
        values.put(USERS_KEY_FIRST_NAME, username);
        values.put(USERS_KEY_AGE, age);
        long id = db.insert(USERS_TABLE_NAME, null, values);
        db.close();
        return id;
    }

    @Override
    public Cursor getUsersList() {
        return null;
    }

    @Override
    public UserInfo getUserInfo(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor crUserInfo = db.rawQuery("select * from " + USERS_TABLE_NAME +
                " where user_id = " + id + ";", null);

        Cursor crUserHobbies = db.rawQuery("select hobbies.name from hobbies" +
                " inner join users_hobbies on hobbies._id = users_hobbies.hobbies_id" +
                " where user_id = " + id + ";", null);

        Cursor crUserSkills = db.rawQuery("select skills.skill_name, skills.skill_level" +
                " from skills" +
                " inner join users_skills on skills._id = users_skills.skills_id" +
                " where user_id = " + id + ";", null);

        UserInfo userInfo;

        if (crUserInfo.moveToFirst()) {
            int cFN = crUserInfo.getColumnIndex(USERS_KEY_FIRST_NAME);
            int cLN = crUserInfo.getColumnIndex(USERS_KEY_LAST_NAME);
            int cAge = crUserInfo.getColumnIndex(USERS_KEY_AGE);
            int cEmail = crUserInfo.getColumnIndex(USERS_KEY_EMAIL);
            int cPhone = crUserInfo.getColumnIndex(USERS_KEY_PHONE);

            String fname = crUserInfo.getString(cFN);
            String lname = crUserInfo.getString(cLN);
            String email = crUserInfo.getString(cEmail);
            String phone = crUserInfo.getString(cPhone);
            int age = crUserInfo.getInt(cAge);

            List<String> hobbies = new ArrayList<>();
            List<Skill> skills = new ArrayList<>();

            if (crUserHobbies.moveToFirst()) {
                int cName = crUserHobbies.getColumnIndex(HOBBIES_KEY_NAME);
                do {
                    hobbies.add(crUserHobbies.getString(cName));
                } while (crUserHobbies.moveToNext());
            }

            if (crUserSkills.moveToFirst()) {
                int cSkillName = crUserSkills.getColumnIndex(SKILLS_KEY_SKILL_NAME);
                int cSkillLevel = crUserSkills.getColumnIndex(SKILLS_KEY_SKILL_LEVEL);
                do {
                    skills.add(new Skill(crUserSkills.getInt(cSkillLevel),
                            crUserSkills.getString(cSkillName)));
                } while (crUserSkills.moveToNext());
            }


            userInfo = new UserInfo(hobbies, phone,
                    email, skills, age, lname, fname, (int) id);

            crUserSkills.close();
            crUserHobbies.close();
            crUserInfo.close();
            db.close();
            return userInfo;
        }
        db.close();
        return null;
    }

    private void createTables(SQLiteDatabase db) {
        String CREATE_USERS_TABLE_STRING = "CREATE TABLE " + USERS_TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERS_KEY_USER_ID + " INTEGER UNIQUE, " +
                USERS_KEY_FIRST_NAME + " TEXT, " +
                USERS_KEY_LAST_NAME + " TEXT, " +
                USERS_KEY_EMAIL + " TEXT, " +
                USERS_KEY_PHONE + " TEXT, " +
                USERS_KEY_AGE + " INTEGER );";

        String CREATE_HOBBIES_TABLE_STRING = "CREATE TABLE " + HOBBIES_TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HOBBIES_KEY_NAME + " TEXT UNIQUE);";

        String CREATE_SKILLS_TABLE_STRING = "CREATE TABLE " + SKILLS_TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SKILLS_KEY_SKILL_NAME + " TEXT UNIQUE, " +
                SKILLS_KEY_SKILL_LEVEL + " INTEGER );";

        String CREATE_USER_HOBBIES_TABLE_STRING = "CREATE TABLE " + USERS_HOBBIES_TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERS_HOBBIES_KEY_USER_ID + " INTEGER, " +
                USERS_HOBBIES_KEY_HOBBIES_ID + " INTEGER UNIQUE, " +
                "FOREIGN KEY(" + USERS_HOBBIES_KEY_USER_ID + ") REFERENCES " + USERS_TABLE_NAME + "(" + _ID + "), " +
                "FOREIGN KEY(" + USERS_HOBBIES_KEY_HOBBIES_ID + ") REFERENCES " + HOBBIES_TABLE_NAME + "(" + _ID + "));";

        String CREATE_USER_SKILLS_TABLE_STRING = "CREATE TABLE " + USERS_SKILLS_TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERS_SKILLS_KEY_USER_ID + " INTEGER, " +
                USERS_SKILLS_KEY_SKILLS_ID + " INTEGER UNIQUE, " +
                "FOREIGN KEY(" + USERS_SKILLS_KEY_USER_ID + ") REFERENCES " + USERS_TABLE_NAME + "(" + _ID + "), " +
                "FOREIGN KEY(" + USERS_SKILLS_KEY_SKILLS_ID + ") REFERENCES " + HOBBIES_TABLE_NAME + "(" + _ID + "));";

        db.execSQL(CREATE_USERS_TABLE_STRING);
        db.execSQL(CREATE_HOBBIES_TABLE_STRING);
        db.execSQL(CREATE_SKILLS_TABLE_STRING);
        db.execSQL(CREATE_USER_HOBBIES_TABLE_STRING);
        db.execSQL(CREATE_USER_SKILLS_TABLE_STRING);
    }

    private void dropTables(SQLiteDatabase db) {
        String DROP_USERS_TABLE_STRING = "DROP TABLE IF EXISTS " + USERS_TABLE_NAME + ";";
        String DROP_HOBBIES_TABLE_STRING = "DROP TABLE IF EXISTS " + HOBBIES_TABLE_NAME + ";";
        String DROP_SKILLS_TABLE_STRING = "DROP TABLE IF EXISTS " + SKILLS_TABLE_NAME + ";";
        String DROP_USERS_HOBBIES_TABLE_STRING = "DROP TABLE IF EXISTS " + USERS_HOBBIES_TABLE_NAME + ";";
        String DROP_USERS_SKILLS_TABLE_STRING = "DROP TABLE IF EXISTS " + USERS_SKILLS_TABLE_NAME + ";";

        db.execSQL(DROP_USERS_HOBBIES_TABLE_STRING);
        db.execSQL(DROP_USERS_SKILLS_TABLE_STRING);
        db.execSQL(DROP_USERS_TABLE_STRING);
        db.execSQL(DROP_HOBBIES_TABLE_STRING);
        db.execSQL(DROP_SKILLS_TABLE_STRING);
    }

    private class DatabaseOpenHelper extends SQLiteOpenHelper {

        private DatabaseOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            createTables(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            dropTables(db);
            createTables(db);
        }
    }
}
