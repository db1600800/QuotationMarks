package com.compoment.db.ios;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.compoment.db.helper.XmlDBColumnBean;
import com.compoment.db.helper.XmlDBParser;
import com.compoment.db.helper.XmlDBTableBean;

public class CreaterIosDBBeanAndDao {

	List<XmlDBTableBean> tables = null;

	public static void main(String[] args) {
		new CreaterIosDBBeanAndDao();
	}

	public CreaterIosDBBeanAndDao() {

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
		for (XmlDBTableBean table : tables) {

			// Bean .h文件
			m = "\n";
			m += "#import <Foundation/Foundation.h>\n";
			m += "@interface " + table.tableName + "Bean : NSObject\n";
			m += "{\n";
			m += "    int _id;\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m += "    NSString *" + column.columnName + ";\n";
			}
			m += "}\n";
			m += "@property (nonatomic, assign)int _id;\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m += "@property (nonatomic, retain) NSString *"
						+ column.columnName + ";\n";
			}
			m += "@end\n";

			System.out.println(m);
			stringToFile("" + table.tableName + "Bean.h", m);

			// Bean .m文件
			m = "\n";
			m += "#import \"" + table.tableName + "Bean.h\"\n";

			m += "@implementation " + table.tableName + "Bean\n";
			m += "@synthesize _id;\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m += "@synthesize " + column.columnName + ";\n";
			}

			m += "- (void)dealloc\n";
			m += "{\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m += "    self." + column.columnName + " = nil;\n";
			}
			m += "}\n";

			m += "@end\n";

			System.out.println(m);
			stringToFile(""+ table.tableName + "Bean.m", m);

			// .h文件
			m = "";
			m = "\n";
			m += "#import <Foundation/Foundation.h>\n";
			m += "@class FMDatabaseQueue;\n";
			m += "@class " + table.tableName + "Bean;\n";
			m += "@interface " + table.tableName + "Dao : NSObject\n";
			m += "{\n";
			m += "NSString *dbFile;\n";

			m += "FMDatabaseQueue *dbQueue;\n";
			m += "}\n";
			m += "@property (nonatomic , copy) NSString *dbFile;\n";
			m += "@property (nonatomic, retain)FMDatabaseQueue *dbQueue;\n";

			m += "+(" + table.tableName + "Dao *)getInstance;\n";

			m += "- (void)insert" + table.tableName + ":(" + table.tableName
					+ "Bean *)bean;\n";

			m += "- (NSArray *)query" + table.tableName + ";\n";

			for (XmlDBColumnBean column : table.columnsName) {
				m += "- (NSArray *)query" + table.tableName + "By"
						+ column.columnName + ":(NSString *)"
						+ column.columnName + ";\n";
			}

			m += "- (BOOL)delete" + table.tableName + "By" + table.tableName
					+ "Bean:(" + table.tableName + "Bean *)bean;\n";
			m += "@end\n";

			System.out.println(m);
			stringToFile("" + table.tableName + "Dao.h", m);

			// .m文件
			m = "";
			m = "\n";
			m += "#import \"" + table.tableName + "Dao.h\"\n";
			m += "#import \"DbFileManager.h\"\n";
			m += "#import \"FMDatabase.h\"\n";
			m += "#import \"FMDatabaseAdditions.h\"\n";
			m += "#import \"FMDatabasePool.h\"\n";
			m += "#import \"FMDatabaseQueue.h\"\n";
			m += "#import \"" + table.tableName + "Bean.h\"\n";

			m += "static " + table.tableName + "Dao *gSharedInstance = nil;\n";

			m += "@implementation " + table.tableName + "Dao\n";
			m += "@synthesize dbFile;\n";
			m += "@synthesize dbQueue;\n";

			m += "+(" + table.tableName + "Dao *)getInstance\n";
			m += "{\n";
			m += "    @synchronized(self)\n";
			m += "    {\n";
			m += "        if (gSharedInstance == nil)\n";
			m += "			gSharedInstance = [[" + table.tableName
					+ "Dao alloc] init];\n";
			m += "    }\n";
			m += "    return gSharedInstance;	\n";
			m += "}\n";

			m += "- (void)dealloc\n";
			m += "{\n";
			m += "    self.dbFile=nil;\n";
			m += "    self.dbQueue = nil;\n";
			m += "}\n";

			m += "- (id)init\n";
			m += "{\n";
			m += "    \n";
			m += "    self = [super init];\n";
			m += "    if (self)\n";
			m += "    {\n";
			m += "        self.dbFile = [DbFileManager dbFilePath];\n";
			m += "        self.dbQueue = [FMDatabaseQueue databaseQueueWithPath:self.dbFile];\n";
			m += "        \n";
			m += "        \n";
			m += "    }\n";
			m += "    return  self;\n";
			m += "}\n";

			m += "- (" + table.tableName + "Bean *)rsTo" + table.tableName
					+ ":(FMResultSet*)rs\n";
			m += "{\n";
			m += "    " + table.tableName + "Bean *bean = [[" + table.tableName
					+ "Bean alloc] init] ;\n";
			m += "    bean._id = [rs intForColumn:@\"_id\"];\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m += "    bean." + column.columnName
						+ " = [rs stringForColumn:@\""
						+ column.columnName.toLowerCase() + "\"];\n";
			}
			m += "    return bean;\n";
			m += "}\n";

			m += "- (void)insert" + table.tableName + ":(" + table.tableName
					+ "Bean *)bean\n";
			m += "{\n";
			m += "    [self.dbQueue inTransaction:^(FMDatabase *db, BOOL *rollback) {\n";
			m += "        [db open];\n";

			String columnString = "";
			String columnString2 = "";
			String columnString3 = "";
			for (XmlDBColumnBean column : table.columnsName) {
				columnString += column.columnName.toLowerCase() + ",";
				columnString2 += "bean." + column.columnName.toLowerCase()
						+ ",";
				columnString3 += "?" + ",";
			}
			int p = columnString.lastIndexOf(",");
			if (p != -1) {
				columnString = columnString.substring(0, p);

			}

			int p2 = columnString2.lastIndexOf(",");
			if (p2 != -1) {
				columnString2 = columnString2.substring(0, p2);

			}

			int p3 = columnString3.lastIndexOf(",");
			if (p3 != -1) {
				columnString3 = columnString3.substring(0, p3);

			}

			m += "        NSString *sql = @\"insert into "
					+ table.getSqliteTableName() + "(" + columnString
					+ ") values (" + columnString3 + ")\";\n";
			m += "        [db executeUpdate:sql," + columnString2 + "];\n";
			m += "        [db close];\n";
			m += "    }];  \n";
			m += "}\n";

			m += "- (NSArray *)query" + table.tableName + ";\n";
			m += "{\n";
			m += "    __block NSMutableArray *beans = [[NSMutableArray alloc] init] ;  \n";
			m += "    [self.dbQueue inDatabase:^(FMDatabase *db)   {\n";
			m += "        [db open];\n";
			m += "        NSString *sql = @\"select * from "
					+ table.getSqliteTableName() + " \";\n";
			m += "        FMResultSet *rs = [db executeQuery:sql];\n";
			m += "        while ([rs next])\n";
			m += "        {\n";
			m += "            [beans addObject:[self rsTo" + table.tableName
					+ " :rs]]; \n";
			m += "        }\n";
			m += "        [db close];\n";
			m += "    }];\n";
			m += "    return beans;\n";
			m += "}\n";

			for (XmlDBColumnBean column : table.columnsName) {
				m += "- (NSArray *)query" + table.tableName + "By"
						+ column.columnName + ":(NSString *)"
						+ column.columnName + ";\n";
				m += "{\n";
				m += "    __block NSMutableArray *beans = [[NSMutableArray alloc] init] ;  \n";
				m += "    [self.dbQueue inDatabase:^(FMDatabase *db)   {\n";
				m += "        [db open];\n";
				m += "FMResultSet *rs =[db executeQuery:[NSString stringWithFormat:@\"select * from %@ where "
						+ column.columnName.toLowerCase()
						+ " = ?\","
						+ table.getSqliteTableName()
						+ "], [NSString stringWithString:"
						+ column.columnName
						+ "]];";
				m += "        while ([rs next])\n";
				m += "        {\n";
				m += "            [beans addObject:[self rsTo"
						+ table.tableName + " :rs]]; \n";
				m += "        }\n";
				m += "        [db close];\n";
				m += "    }];\n";
				m += "    return beans;\n";
				m += "}\n";
			}

			m += "- (BOOL)delete" + table.tableName + "By" + table.tableName
					+ "Bean:(" + table.tableName + "Bean *)bean{\n";
			m += "    BOOL success = YES;\n";
			m += "    [self.dbQueue inDatabase:^(FMDatabase *db)   {\n";
			m += "        [db open];\n";
			m += "	[db executeUpdate:\n";
			m += "     [NSString stringWithFormat:@\"delete from %@ where _id = ?\","
					+ table.tableName + "],\n";
			m += "     [NSNumber numberWithInteger:bean._id]];\n";
			m += "	if ([db hadError]) {\n";
			m += "		NSLog(@\"Err %d: %@\", [db lastErrorCode], [db lastErrorMessage]);\n";
			m += "		success = NO;\n";
			m += "}\n";
			m += "        [db close];\n";
			m += "    }];\n";
			m += "	return success;\n";
			m += "    \n";
			m += "}\n";

			m += "@end\n";

			System.out.println(m);

			stringToFile("" + table.tableName + "Dao.m", m);
		}

	}

	public void stringToFile(String fileName, String str) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			fw.write(str);
			fw.flush();// ???�?�????
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
