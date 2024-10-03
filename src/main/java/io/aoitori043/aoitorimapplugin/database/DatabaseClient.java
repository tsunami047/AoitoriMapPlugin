package io.aoitori043.aoitorimapplugin.database;

import com.tuershen.nbtlibraryfix.NBTLibraryMain;
import io.aoitori043.aoitoriproject.CanaryClientImpl;
import io.aoitori043.aoitoriproject.database.orm.SQLClient;

public class DatabaseClient {

    public static SQLClient sqlClient = CanaryClientImpl.sqlClient;

    public static void init() {

    }

}
