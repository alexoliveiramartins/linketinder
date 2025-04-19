package service

import groovy.sql.Sql
import utils.Utils

class SqlInstance {
    private static SqlInstance instance

    private SqlInstance() {
        this.sqlConnection = Sql.newInstance(dbConnParameters)
    }

    private static final dbConnParameters = [
            'url'     : 'jdbc:postgresql://localhost:5432/linketinder-database',
            'user'    : 'postgres',
            'password': 'linketinder',
            'driver'  : 'org.postgresql.Driver'
    ]

    final Sql sqlConnection

    static SqlInstance getInstance(){
        if (instance == null) instance = new SqlInstance()
        return instance
    }
}
