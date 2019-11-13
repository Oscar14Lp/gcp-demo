package com.cirrocode.gcp.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cirrocode.gcp.demo.dao.MensajesBigQueryDAO;
import com.cirrocode.gcp.demo.entity.Saludo;
import com.cirrocode.gcp.demo.repository.SaludoRepository;
import com.cirrocode.gcp.demo.service.SaludoService;
import com.cirrocode.logging.LoggingComponent;

import io.swagger.model.HolaResponse;
import io.swagger.model.MensajesResponse;

/**
 *
 * @author asalas
 */
@Service
public class SaludoServiceImpl implements SaludoService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SaludoServiceImpl.class);
    
    @Autowired private SaludoRepository saludoRepository;
    @Autowired private MensajesBigQueryDAO mensajesBigQueryDAO;
    @Autowired private LoggingComponent loggingComponent;
    
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public HolaResponse saludar(String nombre)
    {
        String mensaje = "Hola " + nombre + ", un gusto saludarte!";
        
        Saludo saludoEntity = new Saludo();
        saludoEntity.setMensaje(mensaje);
        saludoEntity.setFechaCreacion(GregorianCalendar.getInstance().getTime());
        
        // Guardar a la BD
        this.saludoRepository.save(saludoEntity);
        
        HolaResponse response = new HolaResponse();
        response.setMensaje(mensaje);
        
        // Registrar en logging de StackDriver - Exporta a BigQuery
        this.loggingComponent.escribirMensajeLog(response);
        
        return response;
    }

    @Override
    public List<MensajesResponse> leerMensajesBigQuery()
    {
        List<MensajesResponse> results = new ArrayList<>();
        try
        {
            results = this.mensajesBigQueryDAO.leerMensajes();
        }
        catch (Exception e)
        {
            LOGGER.error("Ocurrio un error al recuperar los mensajes", e);
        }
        return results;
    }
    
    @Override
    public List<MensajesResponse> leerMensajesPostgres() 
    {
    	List<MensajesResponse> results = new ArrayList<>();
    	
    	final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYY HH:mm:ss");
    	
        final Iterable<Saludo> allMessages = this.saludoRepository.findAll();
    	if (null != allMessages)
    	{
    		allMessages.forEach((saludo) -> {
    			MensajesResponse mensajesResponse = new MensajesResponse();
    			mensajesResponse.setFregistro(sdf.format(saludo.getFechaCreacion()));
    			mensajesResponse.setMensaje(saludo.getMensaje());
    			
    			results.add(mensajesResponse);
    		});
    	}        
        
    	return results;
    }
    
    @Override
    public MensajesResponse obtenerMensaje(Long idSaludo) {
    	Saludo saludo= this.saludoRepository.findOne(Math.toIntExact(idSaludo));
    	final MensajesResponse mensaje = new MensajesResponse();
    	mensaje.setMensaje(saludo.getMensaje());  	
    	return mensaje;
    }

	@Override
	public MensajesResponse eliminarMensaje(Long idSaludo) {
		try {
			this.saludoRepository.delete(Math.toIntExact(idSaludo));
			MensajesResponse mensaje = new MensajesResponse();
	    	mensaje.setMensaje("Saludo eliminado");
			return mensaje;
		}catch (Exception e) {
			MensajesResponse mensaje = new MensajesResponse();
	    	mensaje.setMensaje("Error al eliminar el saludo" + e.toString());
	    	return mensaje;
		}
	}

	@Override
	public MensajesResponse actualizarMensaje(Long idSaludo, String saludo) {
		Saludo saludoAnterior= this.saludoRepository.findOne(Math.toIntExact(idSaludo));
		saludoAnterior.setMensaje(saludo);
		this.saludoRepository.save(saludoAnterior);
		MensajesResponse mensaje = new MensajesResponse();
    	mensaje.setMensaje("Saludo Actualizado");
    	return mensaje;
	}
	
	
}