package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.TipoUsuarioEJB;
import co.edu.eam.ingesoft.softOpe.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.TipoUsuario;
import co.edu.eam.ingesoft.softOper.entidades.Venta;

@Named("tipoUsuariocontrolador")
@ViewScoped
public class TipoUsuarioController implements Serializable {

	@Pattern(regexp = "[A-Za-z ]*", message = "solo Letras")
	@Length(min = 3, max = 50, message = "longitud entre 3 y 15")
	private String nombre;

	@Pattern(regexp = "[A-Za-z ]*", message = "solo Letras")
	@Length(min = 10, max = 2000, message = "longitud entre 10 y 2000")
	private String des;

	private int id;

	private List<TipoUsuario> tipos;

	private ArrayList<TipoUsuario> filtroTipoUsu = new ArrayList<TipoUsuario>();

	private boolean busco = false;

	private TipoUsuario tipoUsu;

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the des
	 */
	public String getDes() {
		return des;
	}

	/**
	 * @param des
	 *            the des to set
	 */
	public void setDes(String des) {
		this.des = des;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the tipos
	 */
	public List<TipoUsuario> getTipos() {
		return tipos;
	}

	/**
	 * @param tipos
	 *            the tipos to set
	 */
	public void setTipos(List<TipoUsuario> tipos) {
		this.tipos = tipos;
	}

	/**
	 * @return the filtroTipoUsu
	 */
	public ArrayList<TipoUsuario> getFiltroTipoUsu() {
		return filtroTipoUsu;
	}

	/**
	 * @param filtroTipoUsu
	 *            the filtroTipoUsu to set
	 */
	public void setFiltroTipoUsu(ArrayList<TipoUsuario> filtroTipoUsu) {
		this.filtroTipoUsu = filtroTipoUsu;
	}

	/**
	 * @return the busco
	 */
	public boolean isBusco() {
		return busco;
	}

	/**
	 * @param busco
	 *            the busco to set
	 */
	public void setBusco(boolean busco) {
		this.busco = busco;
	}

	/**
	 * @return the tipoUsu
	 */
	public TipoUsuario getTipoUsu() {
		return tipoUsu;
	}

	/**
	 * @param tipoUsu
	 *            the tipoUsu to set
	 */
	public void setTipoUsu(TipoUsuario tipoUsu) {
		this.tipoUsu = tipoUsu;
	}

	@EJB
	private TipoUsuarioEJB tipousuejb;

	@EJB
	private AuditoriaEJB audEJB;

	@Inject
	private SessionController sesion;

	@PostConstruct
	public void inicializador() {
		tipos = tipousuejb.listarTipoUsuario();
	}

	public void crear() {
		try {

			TipoUsuario a = new TipoUsuario(nombre, des);
			tipousuejb.crear(a);
			System.out.println(id);
			System.out.println(nombre);
			System.out.println(des);
			registrarAuditoria("Guardar");
			limpiar();
			Messages.addFlashGlobalInfo("Tipo de usuario ingresado Correctamente");

		} catch (ExcepcionNegocio e) {
			Messages.addGlobalError(e.getMessage());
		}
	}

	public void eliminar(TipoUsuario tipo) {
		try {
			tipousuejb.eliminarTipoUsuario(tipo.getId());
			tipos = tipousuejb.listarTipoUsuario();
			resetearFitrosTabla();
			registrarAuditoria("Eliminar");
			Messages.addFlashGlobalInfo("Se ha eliminado el tipo de usuario correctamente");
		} catch (Exception e) {
			Messages.addFlashGlobalError("Error al eliminar  el tipo de usuario ");
		}

	}

	public void resetearFitrosTabla(String id) {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('vtWidget').clearFilters()");
	}

	public String procederEditar(TipoUsuario tip) {
		DatosManager.setCodigoTipoUsuario(tip.getId());
		return "/paginas/privado/editarTipoUsuario.xhtml?faces-redirect=true";
	}

	public void resetearFitrosTabla() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('audiTable').clearFilters()");
	}

	public void registrarAuditoria(String accion) {
		try {
			Auditoria audi = new Auditoria();
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			audi.setAccion(accion);
			audi.setRegistroRealizoAccion("TipoUsuario");
			audi.setUsuario(sesion.getUsuario());
			audEJB.registrarAuditoria(audi, browserDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limpiar() {
		nombre = null;
		des = null;
	}

}
