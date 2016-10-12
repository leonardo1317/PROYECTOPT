/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.dao.UsuarioDao;
import modelo.vo.Usuario;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private ArrayList<Usuario> listaUsuario;
    private Usuario usuarioSelecionado;
    private String buscarNombre;
    private String id;
    private String nombre;
    private int rol;
    private char activo;
    private String activoBloqueado; //bloquear botonoes editar y eliminar
    private String activoBloqueadoGuardar; //bloquear boton guardar

    public String getBuscarNombre() {
        return buscarNombre;
    }

    public void setBuscarNombre(String buscarNombre) {
        this.buscarNombre = buscarNombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public char getActivo() {
        return activo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
    }

    public String getActivoBloqueado() {
        return activoBloqueado;
    }

    public void setActivoBloqueado(String activoBloqueado) {
        this.activoBloqueado = activoBloqueado;
    }

    public String getActivoBloqueadoGuardar() {
        return activoBloqueadoGuardar;
    }

    public void setActivoBloqueadoGuardar(String activoBloqueadoGuardar) {
        this.activoBloqueadoGuardar = activoBloqueadoGuardar;
    }

    public UsuarioBean() {

    }

    public ArrayList<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public void guardar() {
        Usuario usuario;
        UsuarioDao usuarioDao;

        try {
            usuario = new Usuario();
            usuarioDao = new UsuarioDao();
            usuario.setNombre(nombre);
            usuario.setIdRol(rol);
            usuario.setActivo(activo);

            if (usuarioDao.obtenerPorNombre(usuario).isEmpty()) {
                usuarioDao.guardar(usuario);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Usuario Registrado");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
        } catch (Exception e) {

        }
    }

    public void obtener() {
        Usuario usuario;
        UsuarioDao usuarioDao;

        try {

            if (buscarNombre.isEmpty()) {
                usuarioDao = new UsuarioDao();
                listaUsuario = usuarioDao.obtener();

            } else {
                usuario = new Usuario();
                usuarioDao = new UsuarioDao();
                usuario.setNombre(buscarNombre);
                listaUsuario = usuarioDao.obtenerPorNombre(usuario);

            }
        } catch (Exception e) {

        }
    }

    public void eliminar() {
        Usuario usuario;
        UsuarioDao usuarioDao;

        try {
            usuario = new Usuario();
            usuarioDao = new UsuarioDao();
            usuario.setIdUsuario(usuarioSelecionado.getIdUsuario());
            usuarioDao.eliminar(usuario);
            obtener();
        } catch (Exception e) {

        }
    }

    public void editar() {
        Usuario usuario;
        UsuarioDao usuarioDao;

        try {
            usuario = new Usuario();
            usuarioDao = new UsuarioDao();
            usuario.setIdUsuario(Integer.parseInt(id));
            usuario.setNombre(nombre);
            usuario.setIdRol(rol);
            usuario.setActivo(activo);
            usuarioDao.editar(usuario);
        } catch (Exception e) {

        }
    }

    public void informacionUsuario(SelectEvent event) {

        setId(String.valueOf(usuarioSelecionado.getIdUsuario()));
        setNombre(usuarioSelecionado.getNombre());
        setRol(usuarioSelecionado.getIdRol());
        setActivo(usuarioSelecionado.getActivo());
        setActivoBloqueadoGuardar("true");
        setActivoBloqueado("false");

    }

    public void crear() {
        setBuscarNombre("");
        setId("");
        setNombre("");
        setRol(0);
        setActivo(' ');
        setActivoBloqueado("true");
        setActivoBloqueadoGuardar("false");

    }

    public void limpiar() {
        setBuscarNombre("");
        setId("");
        setNombre("");
        setRol(0);
        setActivo(' ');

    }

}
