package cybersoft.java11.group8.pizza_store.warehouse.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java11.group8.pizza_store.common_data.model.ResponseHandler;
import cybersoft.java11.group8.pizza_store.warehouse.dto.CreateRawMaterialDto;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import cybersoft.java11.group8.pizza_store.warehouse.service.RawMaterialService;

@RestController
@RequestMapping("/api/raw-material")
public class RawMaterialController {
	@Autowired
	private RawMaterialService service;
	
	@PostMapping("")
	public ResponseEntity<Object> save(@Valid @RequestBody CreateRawMaterialDto dto, BindingResult errors){
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		RawMaterial newRawMaterial = service.save(dto);
		return ResponseHandler.getResponse(newRawMaterial, HttpStatus.OK);
	}
	
	@GetMapping("/{raw-material-name}")
	public ResponseEntity<Object> findRawMaterialByName(@PathVariable("raw-material-name") String name){
		RawMaterial rawMaterial = service.findRawMaterialByName(name);
		if(rawMaterial == null)
			return ResponseHandler.getResponse("There is no data.", HttpStatus.OK);
		return ResponseHandler.getResponse(rawMaterial, HttpStatus.OK);
	}
	
	@PutMapping("/{raw-material-id}")
	public ResponseEntity<Object> updateRawMaterialInfo(@Valid @RequestBody CreateRawMaterialDto dto,
													@PathVariable ("raw-material-id") Long rawMaterialId,
													BindingResult errors){
		if(rawMaterialId == null)
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		RawMaterial updateRawMaterial = service.updateRawMaterialInfo(dto, rawMaterialId);
		return ResponseHandler.getResponse(updateRawMaterial, HttpStatus.OK);
	}
	
	@DeleteMapping("/{raw-material-id}")
	public ResponseEntity<Object> deleteRawMaterial(@PathVariable("raw-material-id") Long rawMaterialId){
		if(rawMaterialId == null)
			return ResponseHandler.getResponse("Raw Material must be provided.", HttpStatus.BAD_REQUEST);
		service.deleteById(rawMaterialId);
		return ResponseHandler.getResponse(HttpStatus.OK);
	}
}
