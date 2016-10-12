package modelo.dao;

import conexion.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.vo.Rol;

public class RolDao extends conexion {

    public ArrayList<Rol> obtener() {
        String sQL = "SELECT * FROM ROL";
        ResultSet resultado;
        ArrayList<Rol> listaRol = null;

        try {
            this.Conectar();
            Statement st = this.getConnection().createStatement();
            resultado = st.executeQuery(sQL);
            listaRol = new ArrayList<>();

            while (resultado.next()) {

                Rol rol = new Rol();
                rol.setIdRol(resultado.getInt("Id_Rol"));
                rol.setNombre(resultado.getString("nombre"));
                listaRol.add(rol);

            }

        } catch (ClassNotFoundException | SQLException e) {

        } finally {
            this.Cerrar();
        }

        return listaRol;
    }
}
