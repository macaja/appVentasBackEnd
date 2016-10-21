package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Articulo;
import play.mvc.*;
import repositorios.ArticuloRepositorio;

import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by mauricio on 20/10/16.
 */
public class ArticuloController extends Controller{
    ArticuloRepositorio articuloRepositorio = new ArticuloRepositorio();
    public Result obtenerArticulos(){
        List<Articulo> articulos = articuloRepositorio.obtenerArticulos();
        return ok(toJson(articulos));
    }
    @BodyParser.Of(BodyParser.Json.class)
    public Result agregarArticulo(){
        JsonNode json = request().body().asJson();
        String nombre = json.get("nombre").asText();
        Integer precio = json.get("precio").asInt();
        String descripcion = json.get("descripcion").asText();
        String imagen = json.get("imagen").asText();
        Integer cantidad = json.get("cantidad").asInt();
        Integer categoria = json.get("categoria").asInt();
        if(nombre==null || precio==null || descripcion==null || imagen==null || cantidad==null || categoria==null){
            return  badRequest("Faltan algunos parametros");
        }else{
            Articulo articulo = new Articulo(nombre,precio,descripcion,imagen,cantidad,categoria);
            articuloRepositorio.agregarArticulo(articulo);
            return ok("Se ingreso el articulo:" + nombre);
        }
    }
}
