package com.cirrocode.gcp.demo.service;

import io.swagger.model.HolaResponse;
import io.swagger.model.MensajesResponse;
import java.util.List;

/**
 *
 * @author asalas
 */
public interface SaludoService
{
    HolaResponse saludar(String nombre);
    
    List<MensajesResponse> leerMensajesBigQuery();
    
    List<MensajesResponse> leerMensajesPostgres();

	MensajesResponse obtenerMensaje(Long idSaludo);
	
	MensajesResponse eliminarMensaje(Long idSaludo);
	
	MensajesResponse actualizarMensaje(Long idSaludo, String saludo);
}