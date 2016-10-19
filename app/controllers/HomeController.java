package controllers;

import models.Articulo;
import play.mvc.*;
import repositorios.ArticuloRepositorio;
import views.html.*;

import java.util.List;

import static play.libs.Json.toJson;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    ArticuloRepositorio articuloRepositorio = new ArticuloRepositorio();
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
    public Result agregarArticulo(String nombre, Integer precio,String descripcion,String imagen,Integer cantidad,Integer categoria){
        Articulo articulo = new Articulo(nombre,precio,descripcion,imagen,cantidad,categoria);
        articuloRepositorio.agregarArticulo(articulo);
        return redirect(routes.HomeController.index());
    }
    public Result obtenerArticulos(){
        List<Articulo> articulos = articuloRepositorio.obtenerArticulos();
        return ok(toJson(articulos));
    }
}
