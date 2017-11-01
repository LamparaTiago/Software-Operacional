package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.sql.DataSource;



@LocalBean
@Stateless
public class ConexionETL {
	
	  
	    protected String datasources = "java:jboss/datasources/ETLDS";
	    protected String user = "postgres"; //usuario de la base de datos
	    protected String password = "admin"; //password de la base de datos
	    protected Connection conexionDB; // variable que permite la conexion
	    protected Statement sentenciaSQL; //permite la ejecucion de sentencias SQL
	    protected ResultSet resultadoDB;//almacena el resultado de una consulta
	
	    
	    /**
	     * Permite la conexion de la base de datos
	    */
	    public void conectar() {
	        try {
	           conexionDB = DriverManager.getConnection(datasources, user, password);//conexion a la base de datos
	           sentenciaSQL = conexionDB.createStatement();//variable que permite ejecutar las sentencias SQL                                
	      } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    /**
	     * Desconecta la conexion de la base de datos
	     */
	    public void desconectar() {
	        try {
	            //sentenciaSQL.close();//cierra la consulta
	            conexionDB.close();//cierra conexion
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    /**
	     * Ejecuta una sentencia sql actualizando la bd
	     * @param sentencia, sentencia
	     * @return true si es verdadera la accion de lo contrario false
	     */
	     public boolean ejecutar(String sentencia) {
	        try {
	            conectar();
	            sentenciaSQL.executeUpdate(sentencia);
	            desconectar();
	        } catch (Exception e) {
	            return false;
	        }
	        return true;
	    }

	     /**
	      * Ejecuta una sentencia sql sin actualizar , pero si guardando informacion
	      * @param sentencia , sentencia 
	      */
	    public void ejecutarRetorno(String sentencia) {
	        try {
	            conectar();
	            resultadoDB = sentenciaSQL.executeQuery(sentencia);
	            desconectar();
	        } catch (Exception e) {

	        }
	    }



}
