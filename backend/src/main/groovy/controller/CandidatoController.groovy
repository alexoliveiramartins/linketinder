package controller

import dao.CandidatosDAO
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import model.Candidato
import model.SqlInstance

class CandidatoController extends HttpServlet {
    private static candidatosDao = new CandidatosDAO(SqlInstance.getInstance().sqlConnection)

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getPathInfo()
        resp.setContentType("application/json")

        if(path == null || path == "/"){
            def candidatos = candidatosDao.candidatosData()
            def json = JsonOutput.toJson(candidatos)
            resp.writer.write(json)
        }
        else {
            Candidato candidato
            candidato = candidatosDao.get(path.substring(1) as Integer)

            if(candidato){
                println candidato
                resp.writer.write(JsonOutput.toJson(candidato))
            }
            else {
                resp.status = 404
                resp.writer.write('{"error":"Candidato não encontrado"}')
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
        else if(!jsonObject.nome ||
                !jsonObject.cpf ||
                !jsonObject.email ||
                !jsonObject.descricao ||
                !jsonObject.linkedinLink ||
                !jsonObject.dataNascimento ||
                !jsonObject.cidade ||
                !jsonObject.estado ||
                !jsonObject.pais ||
                !jsonObject.cep ||
                !jsonObject.senha ||
                !jsonObject.competencias){
            resp.status = HttpServletResponse.SC_BAD_REQUEST
            resp.writer.write('{"error":"campos faltando"}')
            return
        }

        def candidato = new Candidato(jsonObject)

        try{
            candidatosDao.add(candidato)
            resp.status = HttpServletResponse.SC_CREATED
            resp.writer.write('{"message":"candidato criado!"}')
        }
        catch (Exception e){
            log("Erro" + e)
            resp.status = HttpServletResponse.SC_BAD_REQUEST
            resp.writer.write('{"message": "erro ao criar candidato: ' + "$e.message" + '"')
        }
    }
}