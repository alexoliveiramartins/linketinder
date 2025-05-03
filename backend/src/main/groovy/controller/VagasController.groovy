package controller

import dao.CompetenciasDAO
import dao.VagasDAO
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import model.Competencia
import model.SqlInstance
import model.Vaga

class VagasController extends HttpServlet {
    private static vagasDao = new VagasDAO(SqlInstance.getInstance().sqlConnection)

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getPathInfo()
        resp.setContentType("application/json")

        if(path == null || path == "/"){
            def vagas = vagasDao.vagasData()
            def json = JsonOutput.toJson(vagas)
            resp.writer.write(json)
        }
        else {
            Vaga vaga
            vaga = vagasDao.get(path.substring(1) as Integer)

            if(vaga){
                println vaga
                resp.writer.write(JsonOutput.toJson(vaga))
            }
            else {
                resp.status = HttpServletResponse.SC_NOT_FOUND
                resp.writer.write('{"error":"Vaga não encontrada"}')
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
        else if(!jsonObject.titulo || !jsonObject.id_empresa || !jsonObject.descricao){
            resp.status = HttpServletResponse.SC_BAD_REQUEST
            resp.writer.write('{"error":"campos faltando"}')
            return
        }

        def vaga = new Vaga(titulo: jsonObject.titulo, id_empresa: jsonObject.id_empresa, descricao: jsonObject.descricao)

        try{
            vagasDao.add(vaga)
            resp.status = HttpServletResponse.SC_CREATED
            resp.writer.write('{"message":"Vaga criada!"}')
        }
        catch (Exception e){
            log("Erro" + e)
            resp.status = HttpServletResponse.SC_BAD_REQUEST
            resp.writer.write('{"message": "erro ao criar competencia: ' + "$e.message" + '"')
        }
    }
}
