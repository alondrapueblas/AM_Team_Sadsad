package com.example.alondrapueblas.iexpense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Alondra Pueblas on 16/05/2018.
 */

public class Database extends SQLiteOpenHelper {

    public Database(Context context){
        super(context,"iTracker.db",null,1);
    }
    //INSERT Data TO USER (SIGNING UP)
    public void addUserAccounts(String fname, String lname, String add, String user, String pin){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FNAME",fname);
        contentValues.put("LNAME",lname);
        contentValues.put("ADDRESS",add);
        contentValues.put("USERNAME",user);
        contentValues.put("PIN",pin);

        db.insert("ACCOUNTS", null, contentValues);

    }
    //SELECT FROM USER
    public ArrayList<AccountsTable> selectUserAccounts() {
        ArrayList<AccountsTable> result = new ArrayList<AccountsTable>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM ACCOUNTS",null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            String fname= c.getString(c.getColumnIndex("FNAME"));
            String lname = c.getString(c.getColumnIndex("LNAME"));
            String add= c.getString(c.getColumnIndex("ADDRESS"));
            String user = c.getString(c.getColumnIndex("USERNAME"));
            String pin= c.getString(c.getColumnIndex("PIN"));

            AccountsTable at = new AccountsTable(fname,lname,add,user,pin);
            result.add(at);
            c.moveToNext();
        }

        return result;
    }

    public void updateUserAccounts(String user, String fn, String ln, String add,String pin){
        SQLiteDatabase db = this.getReadableDatabase();
        String strSQL = "UPDATE ACCOUNTS SET FNAME ='"+fn+"',LNAME ='"+ln+"',ADDRESS='"+add+"',PIN ='"+pin+"' WHERE USERNAME ='"+ user +"'";
        db.execSQL(strSQL);
        db.close();
    }
    //LogUSER
    public void addLogUser(int id,String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("UNAME",name);
        db.insert("LogUSER", null, contentValues);

    }
    public ArrayList<LogUserTable> selectLogUser() {
        ArrayList<LogUserTable> result = new ArrayList<LogUserTable>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT *FROM LogUSER",null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            int id = c.getInt(c.getColumnIndex("ID"));
            String name= c.getString(c.getColumnIndex("UNAME"));
            LogUserTable at = new LogUserTable(id,name);
            result.add(at);
            c.moveToNext();
        }

        return result;
    }
    public void deleteLogUser(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM LOGUSER WHERE ID ='"+i+"'");
        db.close();
    }

    public void addIncome(int id,String user, String type, double amt, String date, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("USER",user);
        contentValues.put("TYPE",type);
        contentValues.put("AMOUNT",amt);
        contentValues.put("INDATE",date);
        contentValues.put("NOTES",note);

        db.insert("INCOME", null, contentValues);

    }
    public void updateIncome(int id, String type, double amt, String date, String note){
        SQLiteDatabase db = this.getReadableDatabase();
        String strSQL = "UPDATE INCOME SET TYPE ='"+type+"',AMOUNT ='"+amt+"',INDATE='"+date+"',NOTES ='"+note+"' WHERE ID ='"+ id +"'";
        db.execSQL(strSQL);
        db.close();
    }

    public void deleteIncome(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM INCOME WHERE ID= '"+id+"'");
        db.close();
    }
    //SELECT ALL INCOME DATA
    public ArrayList<IncomesTable> selectAll_Income() {
        ArrayList<IncomesTable> result = new ArrayList<IncomesTable>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM INCOME",null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            int id = c.getInt(c.getColumnIndex("ID"));
            String user= c.getString(c.getColumnIndex("USER"));
            String type = c.getString(c.getColumnIndex("TYPE"));
            double amount = c.getDouble(c.getColumnIndex("AMOUNT"));
            String date = c.getString(c.getColumnIndex("INDATE"));
            String note= c.getString(c.getColumnIndex("NOTES"));

            IncomesTable income = new IncomesTable(id,user,type,amount,date,note);
            result.add(income);
            c.moveToNext();
        }

        return result;
    }
    //SELECT BY DATE INCOME DATA
    public ArrayList<IncomesTable> selectByUser_Income(String user) {
        ArrayList<IncomesTable> result = new ArrayList<IncomesTable>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM INCOME WHERE  USER=?",new String[]{user});
        c.moveToFirst();
        while (c.isAfterLast() == false){
            int id = c.getInt(c.getColumnIndex("ID"));
            String us= c.getString(c.getColumnIndex("USER"));
            String type = c.getString(c.getColumnIndex("TYPE"));
            double amount = c.getDouble(c.getColumnIndex("AMOUNT"));
            String date = c.getString(c.getColumnIndex("INDATE"));
            String note= c.getString(c.getColumnIndex("NOTES"));

            IncomesTable income = new IncomesTable(id,us,type,amount,date,note);
            result.add(income);
            c.moveToNext();
        }

        return result;
    }

    //FOR EXPENSES
    public void addExpenses(String user, String type, double amt, String date, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USER",user);
        contentValues.put("TYPE",type);
        contentValues.put("AMOUNT",amt);
        contentValues.put("DATE",date);
        contentValues.put("NOTES",note);

        db.insert("EXPENSES", null, contentValues);

    }
    public void updateExpenses(int id, String type, double amt, String date, String note){
        SQLiteDatabase db = this.getReadableDatabase();
        String strSQL = "UPDATE EXPENSES SET TYPE ='"+type+"',AMOUNT ='"+amt+"',DATE='"+date+"',NOTES ='"+note+"' WHERE ID ='"+ id +"'";
        db.execSQL(strSQL);
        db.close();
    }

    public void deleteExpenses(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM EXPENSES WHERE ID= '"+id+"'");
        db.close();
    }

    //SELECT ALL EXPENSES DATA
    public ArrayList<ExpensesTable> selectAll_Expenses() {
        ArrayList<ExpensesTable> result = new ArrayList<ExpensesTable>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM EXPENSES",null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            int id = c.getInt(c.getColumnIndex("ID"));
            String user= c.getString(c.getColumnIndex("USER"));
            String type = c.getString(c.getColumnIndex("TYPE"));
            double amount = c.getDouble(c.getColumnIndex("AMOUNT"));
            String date = c.getString(c.getColumnIndex("DATE"));
            String note= c.getString(c.getColumnIndex("NOTES"));

            ExpensesTable expenses = new ExpensesTable(id,user,type,amount,date,note);
            result.add(expenses);
            c.moveToNext();
        }

        return result;
    }

//SELECT BY DATE INCOME DATA
    public ArrayList<ExpensesTable> selectByUser_Expenses(String user) {
        ArrayList<ExpensesTable> result = new ArrayList<ExpensesTable>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM EXPENSES WHERE USER=?",new String[]{user});
        c.moveToFirst();
        while (c.isAfterLast() == false){
            int id = c.getInt(c.getColumnIndex("ID"));
            String us= c.getString(c.getColumnIndex("USER"));
            String type = c.getString(c.getColumnIndex("TYPE"));
            double amount = c.getDouble(c.getColumnIndex("AMOUNT"));
            String date = c.getString(c.getColumnIndex("DATE"));
            String note= c.getString(c.getColumnIndex("NOTES"));

            ExpensesTable exp = new ExpensesTable(id,us,type,amount,date,note);
            result.add(exp);
            c.moveToNext();
        }

        return result;
    }

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public String getDatabaseName() {
        return super.getDatabaseName();
    }

    @Override
    public void setWriteAheadLoggingEnabled(boolean enabled) {
        super.setWriteAheadLoggingEnabled(enabled);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ACCOUNTS(FNAME TEXT, LNAME TEXT, ADDRESS, TEXT, USERNAME TEXT, PIN TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE LogUSER(ID INTEGER,UNAME TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE INCOME(ID INTEGER,USER TEXT, TYPE TEXT, AMOUNT REAL, INDATE TEXT, NOTES TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE EXPENSES(ID INTEGER,USER TEXT, TYPE TEXT, AMOUNT REAL, DATE TEXT, NOTES TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE BILLS(ID INTEGER,USER TEXT, TYPE TEXT,STAT TEXT,NOTIF TEXT, AMOUNT REAL, DATE TEXT, NOTES TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE BUDGET(ID INTEGER,USER TEXT,AMOUNT REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ACCOUNTS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LogUSER");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS INCOME");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS EXPENSES");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS BILLS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS BUDGET");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
