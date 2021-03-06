package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.cliente_dimension;
import co.edu.eam.ingesoft.softOper.entidades.empleado_dimension;
import co.edu.eam.ingesoft.softOper.entidades.producto_dimension;
import co.edu.eam.ingesoft.softOper.entidades.venta_dimension;
import co.edu.eam.ingesoft.softOper.entidades.venta_hecho;

@LocalBean
@Stateless
public class Ventas_hechoEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	/**
	 * 
	 * @param venHecho
	 */
	public void ingresarVentaHecho(venta_hecho venHecho) {
		em.persist(venHecho);
	}

	/**
	 * 
	 * @param proDim
	 */
	public void ingresarProductoDimension(producto_dimension proDim) {
		em.persist(proDim);
	}

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public producto_dimension buscarProductoDimension(int codigo) {
		return em.find(producto_dimension.class, codigo);
	}

	/**
	 * 
	 * @param proDim
	 */
	public void ingresarventaDimension(venta_dimension venDim) {
		em.persist(venDim);
	}

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public venta_dimension buscarVentaDimension(int codigo) {
		return em.find(venta_dimension.class, codigo);
	}

	/**
	 * 
	 * @param proDim
	 */
	public void ingresarempleadoDimension(empleado_dimension empDim) {
		em.persist(empDim);
	}

	/**
	 * 
	 * @param nombre
	 * @return
	 */
	public empleado_dimension buscarEmpleadoDimension(String nombre) {
		List<empleado_dimension> empDimen = em.createNamedQuery(empleado_dimension.BUSCAR_NOMBRE_EMPLEADO)
				.setParameter(1, nombre).getResultList();
		return empDimen.get(0);
	}

	/**
	 * 
	 * @param proDim
	 */
	public void ingresarclienteDimension(cliente_dimension cliDim) {
		em.persist(cliDim);
	}

	/**
	 * 
	 * @param nombre
	 * @return
	 */
	public cliente_dimension buscarClienteDimension(String nombre) {
		List<cliente_dimension> cliDimen = em.createNamedQuery(cliente_dimension.BUSCAR_NOMBRE_CLIENTE)
				.setParameter(1, nombre).getResultList();
		return cliDimen.get(0);
	}

	/**
	 * 
	 * @return
	 */
	public List<venta_hecho> listarVentasHecho() {
		return em.createNamedQuery(venta_hecho.LISTAR_VENTA_HECHO).getResultList();
	}

	/**
	 * 
	 * @return
	 */
	public List<producto_dimension> listarProductos() {
		return em.createNamedQuery(producto_dimension.LISTAR_PRODUCTO_DIMENSION).getResultList();
	}

	/**
	 * 
	 * @return
	 */
	public List<venta_dimension> listarVentas() {
		return em.createNamedQuery(venta_dimension.LISTAR_VENTA_DIMENSION).getResultList();
	}

	/**
	 * 
	 * @return
	 */
	public List<empleado_dimension> listarEmpleados() {
		return em.createNamedQuery(empleado_dimension.LISTAR_EMPLEADO_DIMENSION).getResultList();
	}

	/**
	 * 
	 * @return
	 */
	public List<cliente_dimension> listarClientes() {
		return em.createNamedQuery(cliente_dimension.LISTAR_CLIENTE_DIMENSION).getResultList();
	}

	public boolean borrarMenores0() {
		try {
			System.out.println("entre aqui al metodo antes de la consulta");
			em.createQuery("delete from producto_dimension p where p.precio<0").executeUpdate();
			System.out.println("entre aqui, ejecute la consulta!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean borrarMenores20() {
		try {
			System.out.println("entre aqui al metodo antes de la consulta(20)");
			em.createQuery("delete from producto_dimension p where p.precio<20000").executeUpdate();
			System.out.println("entre aqui, ejecute la consulta(20)!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
