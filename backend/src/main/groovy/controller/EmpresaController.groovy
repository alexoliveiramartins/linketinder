package controller

import dao.EmpresasDAO
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import model.Candidato
import model.Empresa
import model.SqlInstance

class EmpresaController extends HttpServlet {
    private static empresasDao = new EmpresasDAO(SqlInstance.getInstance().sqlConnection)

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getPathInfo()
        resp.setContentType("application/json")

        if(path == null || path == "/"){
            def empresas = empresasDao.empresasData()
            def json = JsonOutput.toJson(empresas)
            resp.writer.write(json)
        }
        else {
            Empresa empresa
            empresa = empresasDao.get(path.substring(1) as Integer)

            if(empresa){
                println empresa
                resp.writer.write(JsonOutput.toJson(empresa))
            }
            else {
                resp.status = 404
                resp.writer.write('{"error":"Empresa não encontrada"}')
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
                !jsonObject.email ||
                !jsonObject.descricao ||
                !jsonObject.linkedinLink ||
                !jsonObject.cnpj ||
                !jsonObject.cidade ||
                !jsonObject.estado ||
                !jsonObject.pais ||
                !jsonObject.cep ||
                !jsonObject.senha){
            resp.status = HttpServletResponse.SC_BAD_REQUEST
            resp.writer.write('{"error":"campos faltando"}')
            return
        }
        def empresa = new Empresa(jsonObject)

        try{
            empresasDao.add(empresa)
            resp.status = HttpServletResponse.SC_CREATED
            resp.writer.write('{"message":"empresa criada!"}')
        }
        catch (Exception e){
            log("Erro" + e)
            resp.status = HttpServletResponse.SC_BAD_REQUEST
            resp.writer.write('{"message": "erro ao criar empresa: ' + "$e.message" + '"')
        }
    }
}
