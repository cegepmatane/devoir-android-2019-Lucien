package ca.qc.cgmatane.devoir_android.donnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BaseDeDonnees extends SQLiteOpenHelper {

    private static BaseDeDonnees instance = null;

    public static BaseDeDonnees getInstance(Context contexte)
    {
        instance = new BaseDeDonnees(contexte);
        return instance;
    }

    public static BaseDeDonnees getInstance()
    {
        return instance;
    }

    public BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BaseDeDonnees(Context contexte) {
        super(contexte, "club", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table joueur(id_joueur INTEGER PRIMARY KEY, titre TEXT, auteur TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {

        String DELETE = "delete from joueur where 1 = 1";
        db.execSQL(DELETE);

        String INSERT_1 = "insert into joueur(nom, poste) VALUES('Fournier', 'Meneur')";
        String INSERT_2 = "insert into joueur(nom, poste) VALUES('Leonard', 'Arriere')";
        String INSERT_3 = "insert into joueur(nom, poste) VALUES('Shaq', 'Pivot')";

        db.execSQL(INSERT_1);
        db.execSQL(INSERT_2);
        db.execSQL(INSERT_3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        //String DETRUIRE_TABLE = "drop table joueur";
        //db.execSQL(DETRUIRE_TABLE);
        String CREER_TABLE = "create table joueur(id_joueur INTEGER PRIMARY KEY, nom TEXT, poste TEXT)";
        db.execSQL(CREER_TABLE);
    }
}
