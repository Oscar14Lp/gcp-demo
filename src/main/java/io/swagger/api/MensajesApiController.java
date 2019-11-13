package io.swagger.api;

import com.cirrocode.gcp.demo.service.SaludoService;
import io.swagger.model.MensajesResponse;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-26T05:27:28.167Z")

@Controller
public class MensajesApiController implements MensajesApi {

    @Autowired SaludoService saludoService;

    public ResponseEntity<List<MensajesResponse>> leerMensajes() {
        final List<MensajesResponse> leerMensajesBigQuery = this.saludoService.leerMensajesPostgres();
        return new ResponseEntity<>(leerMensajesBigQuery, HttpStatus.OK);
    }
    
    public ResponseEntity<MensajesResponse> deleteMessageById(@ApiParam(value = " ",required=true) @PathVariable("id_saludo") Long idSaludo) {
    	final MensajesResponse mensajeObtenido = this.saludoService.eliminarMensaje(idSaludo);
        return new ResponseEntity<MensajesResponse>(mensajeObtenido, HttpStatus.OK);
    }
   public ResponseEntity<MensajesResponse> editMessageById(@ApiParam(value = " ",required=true) @PathVariable("id_saludo") Long idSaludo,@ApiParam(value = "Nuevo saludo" ,required=true )  @Valid @RequestBody String saludo) {
    	final MensajesResponse modificacionMensaje = this.saludoService.actualizarMensaje(idSaludo, saludo);
        return new ResponseEntity<MensajesResponse>(modificacionMensaje,HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<MensajesResponse> getMessageById(@ApiParam(value = "Id del mensaje a regresar",required=true) @PathVariable("id_saludo") Long idSaludo) {
        final MensajesResponse mensajeObtenido = this.saludoService.obtenerMensaje(idSaludo);
        return new ResponseEntity<MensajesResponse>(mensajeObtenido, HttpStatus.OK);
    }
    
}
