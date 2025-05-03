package controller

import dao.CandidatosDAO
import dao.CompetenciasDAO
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import model.Candidato
import model.Competencia
import model.SqlInstance

class CompetenciasController extends HttpServlet{
    private static competenciasDao = new CompetenciasDAO(SqlInstance.getInstance().sqlConnection)

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getPathInfo()
        resp.setContentType("application/json")

        if(path == null || path == "/"){
            def competencias = competenciasDao.competenciaData()
            def json = JsonOutput.toJson(competencias)
            resp.writer.write(json)
        }
        else {
            Competencia competencia
            competencia = competenciasDao.get(path.substring(1) as Integer)

            if(competencia){
                println competencia
                resp.writer.write(JsonOutput.toJson(competencia))
            }
            else {
                resp.status = HttpServletResponse.SC_NOT_FOUND
                resp.writer.write('{"error":"Competencia não encontrada"}')
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("application/json")

        String body = req.reader.text
        def jsonSlurper = new JsonSlurper()
        def jsonObject = jsonSlurper.parseText(body)

        if(!body){
            resp.status = HttpServletResponse.SC_BAD_REQUEST
            resp.writer.write('{"error":"Request body is empty"}')
            return
        }
        else if(!jsonObject.nome){
            resp.status = HttpServletResponse.SC_BAD_REQUEST
            resp.writer.write('{"error":"campos faltando"}')
            return
        }

        def competencia = new Competencia(nome: jsonObject.nome)

        try{
            competenciasDao.add(competencia)
            resp.status = HttpServletResponse.SC_CREATED
            resp.writer.write('{"message":"Competencia criada!"}')
        }
        catch (Exception e){
            log("Erro" + e)
            resp.status = HttpServletResponse.SC_BAD_REQUEST
            resp.writer.write('{"message": "erro ao criar competencia: ' + "$e.message" + '"')
        }
    }

}
