/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.dao.RolDao;
import modelo.vo.Rol;

/**
 *
 * @author leo
 */
@ManagedBean
@RequestScoped
public class RolBean {

    public RolBean() {
        obtener();
    }
    private Rol rol = new Rol();
    private ArrayList<Rol> listaRol;

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public ArrayList<Rol> getListaRol() {
        return listaRol;
    }

    public void setListaRol(ArrayList<Rol> listaRol) {
        this.listaRol = listaRol;
    }

    public void obtener() {
        RolDao rolDao;

        try {

            rolDao = new RolDao();
            listaRol = rolDao.obtener();

        } catch (Exception e) {

        }

    }

}
