package modelo.dao;

import conexion.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.vo.Usuario;

public class UsuarioDao extends conexion {

    public void guardar(Usuario usuario) {
        String sQL = "INSERT INTO USUARIO"
                + "(ID_ROL, "
                + "NOMBRE, "
                + "ACTIVO)"
                + "values(?, ?, ?)";

        try {
            this.Conectar();

            PreparedStatement st = this.getConnection().prepareStatement(sQL);

            st.setInt(1, usuario.getIdRol());
            st.setString(2, usuario.getNombre());
            st.setString(3, String.valueOf(usuario.getActivo()));
            st.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {

        } finally {
            this.Cerrar();
        }

    }

    public ArrayList<Usuario> obtener() {
        String sQL = "SELECT * FROM USUARIO";
        ResultSet resultado;
        ArrayList<Usuario> listaUsuario = null;

        try {
            this.Conectar();
            Statement st = this.getConnection().createStatement();
            resultado = st.executeQuery(sQL);
            listaUsuario = new ArrayList<>();

            while (resultado.next()) {

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultado.getInt("ID_USUARIO"));
                usuario.setIdRol(resultado.getInt("ID_ROL"));
                usuario.setNombre(resultado.getString("NOMBRE"));
                usuario.setActivo(resultado.getString("ACTIVO").charAt(0));
                listaUsuario.add(usuario);

            }

        } catch (ClassNotFoundException | SQLException e) {

        } finally {
            this.Cerrar();
        }

        return listaUsuario;
    }
    
       public ArrayList<Usuario> obtenerPorNombre(Usuario usuarioN) {
        
        String sQL = "SELECT * FROM USUARIO WHERE NOMBRE = " + "'" + usuarioN.getNombre()+ "'";
        ResultSet resultado;
        ArrayList<Usuario> listaUsuario = null;

        try {
            this.Conectar();
            Statement st = this.getConnection().createStatement();
            resultado = st.executeQuery(sQL);
            listaUsuario = new ArrayList<>();

            while (resultado.next()) {

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultado.getInt("ID_USUARIO"));
                usuario.setIdRol(resultado.getInt("ID_ROL"));
                usuario.setNombre(resultado.getString("NOMBRE"));
                usuario.setActivo(resultado.getString("ACTIVO").charAt(0));
                listaUsuario.add(usuario);

            }

        } catch (ClassNotFoundException | SQLException e) {

        } finally {
            this.Cerrar();
        }

        return listaUsuario;
    }

    public void editar(Usuario usuario) {

        String sQL = "UPDATE USUARIO SET "
                + "ID_ROL = ?,"
                + "NOMBRE = ?,"
                + "ACTIVO = ?"
                + "WHERE ID_USUARIO = " + usuario.getIdUsuario();

        try {
            this.Conectar();

            PreparedStatement st = this.getConnection().prepareStatement(sQL);

            st.setInt(1, usuario.getIdRol());
            st.setString(2, usuario.getNombre());
            st.setString(3, String.valueOf(usuario.getActivo()));
            st.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {

        } finally {
            this.Cerrar();
        }

    }

    public void eliminar(Usuario usuario) {

        String sQL = "DELETE FROM USUARIO WHERE ID_USUARIO = " + "'" + usuario.getIdUsuario() + "'";
        try {

            this.Conectar();
            PreparedStatement st = this.getConnection().prepareStatement(sQL);
            st.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {

        } finally {
            this.Cerrar();
        }
    }

}
