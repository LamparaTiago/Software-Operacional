package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;

@Named("auditoriaControl")
@ViewScoped
public class AuditoriaController implements Serializable {

	@EJB
	private AuditoriaEJB audEJB;

	private List<Auditoria> auditorias;
	
	private List<Auditoria> areas;
	
	private List<Auditoria> ventas;
	
	private List<Auditoria> clientes;

	private Auditoria selectedAudi;
	
	
	private ArrayList<Auditoria> filtroAuditoria = new ArrayList<Auditoria>();
	
	
	public List<Auditoria> getVentas() {
		return ventas;
	}

	public void setVentas(List<Auditoria> ventas) {
		this.ventas = ventas;
	}

	public Auditoria getSelectedAudi() {
		return selectedAudi;
	}

	public void setSelectedAudi(Auditoria selectedAudi) {
		this.selectedAudi = selectedAudi;
	}

	public ArrayList<Auditoria> getFiltroAuditoria() {
		return filtroAuditoria;
	}

	public void setFiltroAuditoria(ArrayList<Auditoria> filtroAuditoria) {
		this.filtroAuditoria = filtroAuditoria;
	}

	@PostConstruct
	public void inicializar() {
		auditorias = audEJB.listarAuditoriasIdeUsuarios();
	    areas = audEJB.listarAuditoriasArea();
	    ventas = audEJB.listarAuditoriaVentas();
	    clientes = audEJB.listarAuditoriaClientes();

	}
	
	public List<Auditoria> getAreas() {
		return areas;
	}

	public void setAreas(List<Auditoria> areas) {
		this.areas = areas;
	}

	public List<Auditoria> getAuditorias() {
		return auditorias;
	}

	public void setAuditorias(List<Auditoria> auditorias) {
		this.auditorias = auditorias;
	}

	public AuditoriaEJB getAudEJB() {
		return audEJB;
	}

	public void setAudEJB(AuditoriaEJB audEJB) {
		this.audEJB = audEJB;
	}

	public List<Auditoria> getClientes() {
		return clientes;
	}

	public void setClientes(List<Auditoria> clientes) {
		this.clientes = clientes;
	}
	
	
	

}
