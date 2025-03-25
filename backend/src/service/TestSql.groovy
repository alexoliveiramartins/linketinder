package service

import groovy.sql.Sql

def url = "jdbc:postgresql://localhost:5432/linketinder-database"
def user = "postgres"
def password = "Alexbenjamim09."
def driver = "org.postgresql.Driver"
def sql = Sql.newInstance(url, user, password, driver)

println "Candidatos:"
sql.eachRow("SELECT * FROM candidatos") { row ->
    println row
}

println "Empresas:"
sql.eachRow("SELECT * FROM empresas") { row ->
    println row
}

println "Vagas:"
sql.eachRow("""
SELECT empresas.nome, vagas.titulo, vagas.descricao FROM vagas
INNER JOIN empresas ON vagas.id_empresa=empresas.id;
""") {
    row -> println row
}

sql.close()
