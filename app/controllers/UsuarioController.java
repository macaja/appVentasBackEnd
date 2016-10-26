package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Usuario;
import play.libs.Json;
import play.mvc.*;
import repositorios.UsuarioRepositorio;

/**
 * Created by mauricio on 21/10/16.
 */
public class UsuarioController extends Controller{
    public Result registrarUsuario(){
        JsonNode json = request().body().asJson();
        String username = json.get("username").asText();
        String password = json.get("password").asText();
        Usuario usuario = new Usuario(username,password);
        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
        boolean ingreso = usuarioRepositorio.registrarUsuario(usuario);
        ObjectNode result = Json.newObject();
        if(ingreso == true){
            result.put("status","OK");
            result.put("message","El usuario se registro correctamente");
            return  ok(result);
        }else{
            result.put("status","Existe");
            result.put("message","Este nombre de usuario ya existe");
            return  badRequest(result);
        }
    }
}
