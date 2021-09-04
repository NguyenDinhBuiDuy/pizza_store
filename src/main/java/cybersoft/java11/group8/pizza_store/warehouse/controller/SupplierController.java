package cybersoft.java11.group8.pizza_store.warehouse.controller;

import java.util.Optional;

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
import cybersoft.java11.group8.pizza_store.warehouse.dto.CreateSupplierDto;
import cybersoft.java11.group8.pizza_store.warehouse.dto.UpdateSupplierDto;
import cybersoft.java11.group8.pizza_store.warehouse.model.Supplier;
import cybersoft.java11.group8.pizza_store.warehouse.service.SupplierService;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	
	@PostMapping("")
	public ResponseEntity<Object> save(@Valid @RequestBody CreateSupplierDto dto, BindingResult errors){
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		Supplier newSupplier = supplierService.save(dto);
		return ResponseHandler.getResponse(newSupplier, HttpStatus.CREATED);
	}
	
	@GetMapping("/{supplier-name}")
	public ResponseEntity<Object> findSupplierByName(@PathVariable("supplier-name") String name){
		if(name == null)
			return ResponseHandler.getResponse("Please enter supplier name.", HttpStatus.BAD_REQUEST);
		Optional<Supplier> supplier = supplierService.findSupplierByName(name);
		if(supplier.isEmpty())
			return ResponseHandler.getResponse("Supplier name is not found.", HttpStatus.NOT_FOUND);
		return ResponseHandler.getResponse(supplier, HttpStatus.OK);
	}
	
	@PutMapping("/{supplier-id}")
	public ResponseEntity<Object> updateSupplierInfo(@Valid @RequestBody UpdateSupplierDto dto,
													@PathVariable ("supplier-id") Long supplierId,
													BindingResult errors){
		if(supplierId == null)
			return ResponseHandler.getResponse("Please enter supplier id.", HttpStatus.BAD_REQUEST);
		if(!supplierService.existById(supplierId))
			return ResponseHandler.getResponse("Supplier id is not found.", HttpStatus.NOT_FOUND);
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		Supplier updateSupplier = supplierService.updateSupplierInfo(dto, supplierId);
		return ResponseHandler.getResponse(updateSupplier, HttpStatus.OK);
	}
	
	@DeleteMapping("/{supplier-id}")
	public ResponseEntity<Object> deleteSupplier(@PathVariable("supplier-id") Long supplierId){
		if(supplierId == null)
			return ResponseHandler.getResponse("Please enter supplier id.", HttpStatus.BAD_REQUEST);
		if(!supplierService.existById(supplierId))
			return ResponseHandler.getResponse("Supplier id does not exist.", HttpStatus.BAD_REQUEST);
		supplierService.deleteById(supplierId);
		return ResponseHandler.getResponse("Remove the supplier successfully.", HttpStatus.OK);
	}
}
