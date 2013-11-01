package com.compoment.db.creater;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.compoment.db.helper.XmlDBColumnBean;
import com.compoment.db.helper.XmlDBParser;
import com.compoment.db.helper.XmlDBTableBean;

public class CreaterDBContentProvider {

	List<XmlDBTableBean> tables = null;

	public static void main(String[] args) {
		new CreaterDBContentProvider();
	}

	public CreaterDBContentProvider() {

		String classDir = this.getClass().getResource("/").getPath();
		try {

			XmlDBParser xmlDbParser = new XmlDBParser();
			xmlDbParser.parserXml(classDir + "com/compoment/db/db.uxf");
			tables = xmlDbParser.tables;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String m = "\n";

		m += "import android.content.ContentProvider;\n";
		m += "import android.content.ContentUris;\n";
		m += "import android.content.ContentValues;\n";
		m += "import android.content.Context;\n";
		m += "import android.content.UriMatcher;\n";
		m += "import android.database.Cursor;\n";
		m += "import android.database.SQLException;\n";
		m += "import android.database.sqlite.SQLiteDatabase;\n";
		m += "import android.database.sqlite.SQLiteOpenHelper;\n";
		m += "import android.database.sqlite.SQLiteQueryBuilder;\n";
		m += "import android.net.Uri;\n";
		m += "import android.text.TextUtils;\n";
		m += "import android.util.Log;\n";

		String className = "";
//		for (XmlDBTableBean table : tables) {
//			className += table.tableName + "_";
//		}

		m += "/**<provider\n";
		m += "android:name=\"com." + className + "DBContentProvider\"\n";
		m += "android:authorities=\"" + className + "DBContentProvider\" />\n";
		m += "*/\n";

		// public class
		// UploadComplete_UploadPart_FileDetail_SendDetail_DBContentProvider
		// extends ContentProvider {
		m += "public class " + className
				+ "DBContentProvider extends ContentProvider {\n";

		m += "	private DatabaseHelper openHelper;\n";

		m += "	// UriMatcher用的匹配\n";

		// private static final int MESSAGE_RECORD = 0;
		// private static final int MESSAGE_RECORD_ID = 1;
		int i = 0;
		for (XmlDBTableBean table : tables) {
			m += "	private static final int "
					+ table.getSqliteTableName().toUpperCase() + " = " + i
					+ ";\n";
			i++;
			m += "	private static final int "
					+ table.getSqliteTableName().toUpperCase() + "_ID = " + i
					+ ";\n";
			i++;

		}

		m += "	public static final String FALSE = \"0\";\n";
		m += "	public static final String TRUE = \"1\";\n";

		m += "	private static final UriMatcher uriMatcher;\n";
		m += "	static {\n";
		m += "		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);\n";

		for (XmlDBTableBean table : tables) {
			m += "		// " + table.tableChineseName + "\n";
			m += "		uriMatcher.addURI(" + className
					+ "DBTableDescribe.AUTHORITY, " + className
					+ "DBTableDescribe."
					+ table.getSqliteTableName().toUpperCase() + "_TABLE,\n";
			m += "				" + table.getSqliteTableName().toUpperCase() + ");\n";
			m += "		uriMatcher.addURI(" + className
					+ "DBTableDescribe.AUTHORITY, " + className
					+ "DBTableDescribe."
					+ table.getSqliteTableName().toUpperCase() + "_TABLE\n";
			m += "				+ \"/#\", " + table.getSqliteTableName().toUpperCase()
					+ "_ID);\n";
		}
		m += "	}\n";

		m += "	public boolean onCreate() {\n";
		m += "		openHelper = new DatabaseHelper(getContext());\n";
		m += "		return false;\n";
		m += "	}\n";

		m += "	@Override\n";
		m += "	public int delete(Uri uri, String selection, String[] selectionArgs) {\n";
		m += "		int count = 0;\n";
		m += "        String segment=\"\";\n";
		m += "		SQLiteDatabase db = openHelper.getWritableDatabase();\n";

		m += "		switch (uriMatcher.match(uri)) {\n";

		// m+="		case MESSAGE_RECORD:\n";
		// m+="			count = db.delete(UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.TABLE_MESSAGE_RECORD, selection,\n";
		// m+="					selectionArgs);\n";
		// m+="			break;\n";
		// m+="		case MESSAGE_RECORD_ID:\n";
		// m+="			String segment1 = uri.getPathSegments().get(1);\n";
		// m+="			Log.e(\"\", \"segment1 = \" + segment1);\n";
		// m+="			count = db.delete(\n";
		// m+="					UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.TABLE_MESSAGE_RECORD,\n";
		// m+="					UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageRecordTable._ID\n";
		// m+="							+ \"=\"\n";
		// m+="							+ segment1\n";
		// m+="							+ (!TextUtils.isEmpty(selection) ? \" AND (\" + selection + ')'\n";
		// m+="									: \"\"), selectionArgs);\n";
		// m+="			break;\n";

		for (XmlDBTableBean table : tables) {
			m += "//" + table.tableChineseName + "\n";
			m += "		case " + table.getSqliteTableName().toUpperCase() + ":\n";
			m += "			count = db.delete(" + className + "DBTableDescribe."
					+ table.getSqliteTableName().toUpperCase()
					+ "_TABLE, selection,\n";
			m += "					selectionArgs);\n";
			m += "			break;\n";
			m += "		case " + table.getSqliteTableName().toUpperCase()
					+ "_ID:\n";
			m += "			 segment = uri.getPathSegments().get(1);\n";
			// m+="			Log.e(\"\", \"segment1 = \" + segment1);\n";
			m += "			count = db.delete(\n";
			m += "					" + className + "DBTableDescribe."
					+ table.getSqliteTableName().toUpperCase() + "_TABLE,\n";
			m += "					" + className + "DBTableDescribe." + table.tableName
					+ "Table._ID\n";
			m += "							+ \"=\"\n";
			m += "							+ segment\n";
			m += "							+ (!TextUtils.isEmpty(selection) ? \" AND (\" + selection + ')'\n";
			m += "									: \"\"), selectionArgs);\n";
			m += "			break;\n";

		}

		m += "		default:\n";
		m += "			throw new IllegalArgumentException(\"Unsupported URI: \" + uri);\n";
		m += "		}\n";

		m += "		getContext().getContentResolver().notifyChange(uri, null);\n";
		m += "		return count;\n";
		m += "	}\n";

		m += "	@Override\n";
		m += "	public String getType(Uri uri) {\n";
		m += "		switch (uriMatcher.match(uri)) {\n";

		// m+="		case MESSAGE_RECORD:\n";
		// m+="			return \"vnd.android.cursor.dir/\" + UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.AUTHORITY + \".\"\n";
		// m+="					+ UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.TABLE_MESSAGE_RECORD;\n";
		// m+="		case MESSAGE_RECORD_ID:\n";
		// m+="			return \"vnd.android.cursor.item/\" + UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.AUTHORITY + \".\"\n";
		// m+="					+ UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.TABLE_MESSAGE_RECORD;\n";
		for (XmlDBTableBean table : tables) {
			m += "		case " + table.getSqliteTableName().toUpperCase() + ":\n";
			m += "			return \"vnd.android.cursor.dir/\" + " + className
					+ "DBTableDescribe.AUTHORITY + \".\"\n";
			m += "					+ " + className + "DBTableDescribe."
					+ table.getSqliteTableName().toUpperCase() + "_TABLE;\n";
			m += "		case " + table.getSqliteTableName().toUpperCase()
					+ "_ID:\n";
			m += "			return \"vnd.android.cursor.item/\" + " + className
					+ "DBTableDescribe.AUTHORITY + \".\"\n";
			m += "					+ " + className + "DBTableDescribe."
					+ table.getSqliteTableName().toUpperCase() + "_TABLE;\n";
		}

		m += "		default:\n";
		m += "			throw new IllegalArgumentException(\"Unsupported URI \" + uri);\n";
		m += "		}\n";
		m += "	}\n";

		m += "	@Override\n";
		m += "	public Uri insert(Uri uri, ContentValues values) {\n";
		m += "		SQLiteDatabase db = openHelper.getWritableDatabase();\n";
		m += "         long _id1=0;\n";
		m += "		switch (uriMatcher.match(uri)) {\n";

		// m+="		case MESSAGE_DOWNLOADING:\n";
		// m+="		case MESSAGE_DOWNLOADING_ID:\n";
		// m+="			// Insert the new row, will return the row number if successful\n";
		// m+="			long _id1 = db\n";
		// m+="					.insert(UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.TABLE_MESSAGE_DOWNLOADING, null, values);\n";
		// m+="			if (_id1 > 0) {\n";
		// m+="				Uri newUri = ContentUris.withAppendedId(\n";
		// m+="						UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageDownloadingTable.CONTENT_URI, _id1);\n";
		// m+="				getContext().getContentResolver().notifyChange(newUri, null);\n";
		// m+="				return newUri;\n";
		// m+="			}\n";
		// m+="			break;\n";

		for (XmlDBTableBean table : tables) {
			m += "//" + table.tableChineseName + "\n";
			m += "		case " + table.getSqliteTableName().toUpperCase() + ":\n";
			m += "		case " + table.getSqliteTableName().toUpperCase()
					+ "_ID:\n";
			m += "			// Insert the new row, will return the row number if successful\n";
			m += "			 _id1 = db\n";
			m += "					.insert(" + className + "DBTableDescribe."
					+ table.getSqliteTableName().toUpperCase()
					+ "_TABLE, null, values);\n";
			m += "			if (_id1 > 0) {\n";
			m += "				Uri newUri = ContentUris.withAppendedId(\n";
			m += "						" + className + "DBTableDescribe." + table.tableName
					+ "Table.CONTENT_URI, _id1);\n";
			m += "				getContext().getContentResolver().notifyChange(newUri, null);\n";
			m += "				return newUri;\n";
			m += "			}\n";
			m += "			break;\n";
		}

		m += "		}\n";

		m += "		throw new SQLException(\"Failed to insert row into \" + uri);\n";
		m += "	}\n";

		m += "	@Override\n";
		m += "	public Cursor query(Uri uri, String[] projection, String selection,\n";
		m += "			String[] selectionArgs, String sortOrder) {\n";
		m += "		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();\n";
		m += "		// If no sort order is specified sort by contact id\n";
		m += "		String orderBy;\n";

		m += "		// Generate the body of the query\n";
		m += "		int match = uriMatcher.match(uri);\n";
		m += "		switch (match) {\n";

		// m+="		case MESSAGE_DOWNLOADING:\n";
		// m+="			qb.setTables(UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.TABLE_MESSAGE_DOWNLOADING);\n";
		//
		// m+="			orderBy = UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageDownloadingTable._ID;\n";
		// m+="			break;\n";
		// m+="		case MESSAGE_DOWNLOADING_ID:\n";
		// m+="			qb.setTables(UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.TABLE_MESSAGE_DOWNLOADING);\n";
		// m+="			qb.appendWhere(UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageDownloadingTable._ID + \"=\"\n";
		// m+="					+ uri.getPathSegments().get(1));\n";
		//
		// m+="			orderBy = UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageDownloadingTable._ID;\n";
		// m+="			break;\n";

		for (XmlDBTableBean table : tables) {
			m += "//" + table.tableChineseName + "\n";
			m += "		case " + table.getSqliteTableName().toUpperCase() + ":\n";
			m += "			qb.setTables(" + className + "DBTableDescribe."
					+ table.getSqliteTableName().toUpperCase() + "_TABLE);\n";

			m += "			orderBy = " + className + "DBTableDescribe."
					+ table.tableName + "Table._ID;\n";
			m += "			break;\n";
			m += "		case " + table.getSqliteTableName().toUpperCase()
					+ "_ID:\n";
			m += "			qb.setTables(" + className + "DBTableDescribe."
					+ table.getSqliteTableName().toUpperCase() + "_TABLE);\n";
			m += "			qb.appendWhere(" + className + "DBTableDescribe."
					+ table.tableName + "Table._ID + \"=\"\n";
			m += "					+ uri.getPathSegments().get(1));\n";

			m += "			orderBy = " + className + "DBTableDescribe."
					+ table.tableName + "Table._ID;\n";
			m += "			break;\n";

		}

		m += "		default:\n";
		m += "			throw new IllegalArgumentException(\"Unknown URI \" + uri);\n";
		m += "		}\n";

		m += "		if (!TextUtils.isEmpty(sortOrder)) {\n";
		m += "			orderBy = sortOrder;\n";
		m += "		}\n";

		m += "		// Apply the query to the underlying database.\n";
		m += "		SQLiteDatabase db = openHelper.getWritableDatabase();\n";
		m += "		Cursor c = qb.query(db, projection, selection, selectionArgs, null, null,\n";
		m += "				orderBy);\n";
		m += "		// Register the contexts ContentResolver to be notified if\n";
		m += "		// the cursor result set changes.\n";
		m += "		if (c != null) {\n";
		m += "			c.setNotificationUri(getContext().getContentResolver(), uri);\n";
		m += "		}\n";
		m += "		// Return a cursor to the query result\n";
		m += "		return c;\n";
		m += "	}\n";

		m += "	@Override\n";
		m += "	public int update(Uri uri, ContentValues values, String selection,\n";
		m += "			String[] selectionArgs) {\n";
		m += "		int count = 0;\n";
		m += "        String segment1=\"\";\n";
		m += "		SQLiteDatabase db = openHelper.getWritableDatabase();\n";

		m += "		int match = uriMatcher.match(uri);\n";
		m += "		switch (match) {\n";

		// m+="		case MESSAGE_DOWNLOADING:\n";
		// m+="			count = db.update(UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.TABLE_MESSAGE_DOWNLOADING, values,\n";
		// m+="					selection, selectionArgs);\n";
		// m+="			break;\n";
		// m+="		case MESSAGE_DOWNLOADING_ID:\n";
		// m+="			String segment1 = uri.getPathSegments().get(1);\n";
		// m+="			count = db.update(\n";
		// m+="					UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.TABLE_MESSAGE_DOWNLOADING,\n";
		// m+="					values,\n";
		// m+="					UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageDownloadingTable._ID\n";
		// m+="							+ \"=\"\n";
		// m+="							+ segment1\n";
		// m+="							+ (!TextUtils.isEmpty(selection) ? \" AND (\" + selection + ')'\n";
		// m+="									: \"\"), selectionArgs);\n";
		// m+="			break;\n";

		for (XmlDBTableBean table : tables) {
			   m+="//"+table.tableChineseName+"\n";
			m += "		case " + table.getSqliteTableName().toUpperCase() + ":\n";
			m += "			count = db.update(" + className + "DBTableDescribe."
					+ table.getSqliteTableName().toUpperCase()
					+ "_TABLE, values,\n";
			m += "					selection, selectionArgs);\n";
			m += "			break;\n";
			m += "		case " + table.getSqliteTableName().toUpperCase()
					+ "_ID:\n";
			m += "			segment1 = uri.getPathSegments().get(1);\n";
			m += "			count = db.update(\n";
			m += "					" + className + "DBTableDescribe."
					+ table.getSqliteTableName().toUpperCase() + "_TABLE,\n";
			m += "					values,\n";
			m += "					" + className + "DBTableDescribe." + table.tableName
					+ "Table._ID\n";
			m += "							+ \"=\"\n";
			m += "							+ segment1\n";
			m += "							+ (!TextUtils.isEmpty(selection) ? \" AND (\" + selection + ')'\n";
			m += "									: \"\"), selectionArgs);\n";
			m += "			break;\n";

		}

		m += "		default:\n";
		m += "			throw new IllegalArgumentException(\"Unknown URI \" + uri);\n";
		m += "		}\n";

		m += "		getContext().getContentResolver().notifyChange(uri, null);\n";
		m += "		return count;\n";
		m += "	}\n";

		m += "	private static class DatabaseHelper extends SQLiteOpenHelper {\n";
		m += "		private static final String TAG = DatabaseHelper.class.getCanonicalName();\n";
		m += "		private static final String DATABASE_NAME = \"Message\";\n";
		m += "		private static final int DATABASE_VERSION = 1;\n";

		// m+="		// 创建表的sql语句\n";
		// m+="		private static final String SQL_CREATE_TABLE_MESSAGE_DOWNLOADING = \"CREATE TABLE \"\n";
		// m+="				+ UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.TABLE_MESSAGE_DOWNLOADING\n";
		// m+="				+ \" (\"\n";
		// m+="				+ UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageDownloadingTable._ID\n";
		// m+="				+ \" INTEGER PRIMARY KEY AUTOINCREMENT,\"\n";
		// m+="				+ UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageDownloadingTable.ID\n";
		// m+="				+ \" TEXT,\"\n";
		// m+="				+ UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageDownloadingTable.PATH\n";
		// m+="				+ \" TEXT,\"\n";
		// m+="				+ UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageDownloadingTable.RECEIVER_NUMBER\n";
		// m+="				+ \" TEXT,\"\n";
		// m+="				+ UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageDownloadingTable.URL + \" TEXT)\";\n";

		for (XmlDBTableBean table : tables) {
			m += "		// 创建表（"+table.tableChineseName+"）的sql语句\n";
			m += "		private static final String SQL_CREATE_TABLE_"
					+ table.getSqliteTableName().toUpperCase()
					+ " = \"CREATE TABLE \"\n";
			m += "				+ " + className + "DBTableDescribe."
					+ table.getSqliteTableName().toUpperCase() + "_TABLE\n";
			m += "				+ \" (\"\n";

			int j = 0;
			for (XmlDBColumnBean column : table.columnsName) {
				j++;

				if (j == 1) {
					m += "				+ "
							+ className
							+ "DBTableDescribe."
							+ table.tableName
							+ "Table._ID+\" INTEGER PRIMARY KEY AUTOINCREMENT,\"\n";

					m += "				+" + className + "DBTableDescribe."
							+ table.tableName + "Table."
							+ column.getSqliteColumnName().toUpperCase()
							+ "+\" TEXT,\"\n";
				} else if (j < table.columnsName.size()) {
					m += "				+" + className + "DBTableDescribe."
							+ table.tableName + "Table."
							+ column.getSqliteColumnName().toUpperCase()
							+ "+\" TEXT,\"\n";
				} else {

					m += "				+ " + className + "DBTableDescribe."
							+ table.tableName + "Table."
							+ column.getSqliteColumnName().toUpperCase()
							+ " + \" TEXT";
				}

			}

			m += ")\";\n";

		}

		m += "		DatabaseHelper(Context context) {\n";
		m += "			super(context, DATABASE_NAME, null, DATABASE_VERSION);\n";
		m += "		}\n";

		m += "		@Override\n";
		m += "		public void onCreate(SQLiteDatabase db) {\n";

		for (XmlDBTableBean table : tables) {
			m += "			db.execSQL(SQL_CREATE_TABLE_"
					+ table.getSqliteTableName().toUpperCase() + ");\n";
		}

		m += "		}\n";

		m += "		@Override\n";
		m += "		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {\n";
		m += "		}\n";
		m += "		}\n";
		m += "		}\n";

		System.out.println(m);

		stringToFile("d:\\"+ className
				+ "DBContentProvider.java",m);
	}
	
	public void stringToFile(String fileName,String str)
	{
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			fw.write(str); 
			fw.flush();//加上这句
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
