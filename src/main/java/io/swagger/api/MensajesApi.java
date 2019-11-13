/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.MensajesResponse;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-26T05:27:28.167Z")

@Api(value = "mensajes", description = "the mensajes API")
public interface MensajesApi {

    @ApiOperation(value = "Recupera los mensajes de saludo", notes = "Recupera mensajes", response = MensajesResponse.class, responseContainer = "List", authorizations = {
        @Authorization(value = "api_key")
    }, tags={ "mensajes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operacion exitosa", response = MensajesResponse.class, responseContainer = "List") })
    
    @RequestMapping(value = "/mensajes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<MensajesResponse>> leerMensajes();
    
    @ApiOperation(value = "Edita los mensajes de saludo", nickname = "editMessageById", notes = "Edita mensajes", authorizations = {
            @Authorization(value = "api_key")
        }, tags={ "mensajes", })
        @ApiResponses(value = { 
            @ApiResponse(code = 201, message = "Operacion exitosa"),
            @ApiResponse(code = 400, message = "Error al realizar la operacion"),
            @ApiResponse(code = 404, message = "No encontrado") })
        @RequestMapping(value = "/mensajes/{id_saludo}",
            produces = { "application/json" }, 
            method = RequestMethod.PUT)
        ResponseEntity<MensajesResponse> editMessageById(@ApiParam(value = " ",required=true) @PathVariable("id_saludo") Long idSaludo,@ApiParam(value = "Nuevo saludo" ,required=true )  @Valid @RequestBody String saludo);


    @ApiOperation(value = "Busca un mensaje por su id", nickname = "getMessageById", notes = "Regresa un mensaje", authorizations = {
        @Authorization(value = "api_key")
    }, tags={ "mensajes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operacion exitosa"),
        @ApiResponse(code = 400, message = "Error al realizar la operacion"),
        @ApiResponse(code = 404, message = "Mensaje no encontrado") })
    @RequestMapping(value = "/mensajes/{id_saludo}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<MensajesResponse> getMessageById(@ApiParam(value = "Id del mensaje a regresar",required=true) @PathVariable("id_saludo") Long idSaludo);

    @ApiOperation(value = "Elimina los mensajes de saludo", nickname = "deleteMessageById", notes = "Elimina mensajes", authorizations = {
            @Authorization(value = "api_key")
        }, tags={ "mensajes", })
        @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Operacion exitosa"),
            @ApiResponse(code = 400, message = "Error al realizar la operacion"),
            @ApiResponse(code = 404, message = "No encontrado") })
        @RequestMapping(value = "/mensajes/{id_saludo}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
        ResponseEntity<MensajesResponse> deleteMessageById(@ApiParam(value = " ",required=true) @PathVariable("id_saludo") Long idSaludo);
    
}
