package cybersoft.java11.group8.pizza_store.role.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
import cybersoft.java11.group8.pizza_store.role.dto.CreateRoleGroupDTO;

import cybersoft.java11.group8.pizza_store.role.model.RoleGroup;
import cybersoft.java11.group8.pizza_store.role.service.RoleGroupService;
import cybersoft.java11.group8.pizza_store.role.validation.annotation.ExistRoleNameAtRoles;
import cybersoft.java11.group8.pizza_store.role.validation.annotation.ExistUserNameAtUsers;


@RestController
@RequestMapping ("api/role_group")
public class RoleGruopController {
	
	private RoleGroupService _service;
	
	@Autowired
	public RoleGruopController(RoleGroupService _service) {
		super();
		this._service = _service;
	}

	@GetMapping("")
	public ResponseEntity<Object> findAllGruops(){
		List<RoleGroup> roleGroups = _service.findAll();
		if (roleGroups.isEmpty())
			return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);
		return ResponseHandler.getResponse(roleGroups, HttpStatus.OK);
	}
	
	@GetMapping("/{group-id}/role")
	public ResponseEntity<Object> findRoleInGruop(@Valid @PathVariable ("group-id") @NotNull Long id, @Valid @RequestBody @NotBlank String roleName, BindingResult error){
		if (error.hasErrors())
			return ResponseHandler.getResponse(error, HttpStatus.BAD_REQUEST);
		
		if (!_service.existRoleGroup(id))
			return ResponseHandler.getResponse("there is no role group id : " + id, HttpStatus.BAD_REQUEST);
		
		boolean result = _service.findRoleInRoleGroup(id, roleName);
		if (!result)
			return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);
		return ResponseHandler.getResponse(HttpStatus.OK);
	}
	
	@GetMapping("/{group-id}/user")
	public ResponseEntity<Object> findUserInGruop(@Valid @PathVariable ("group-id") @NotNull Long id, @Valid @RequestBody @NotBlank String userName, BindingResult error){
		if (error.hasErrors())
			return ResponseHandler.getResponse(error, HttpStatus.BAD_REQUEST);
		
		if (!_service.existRoleGroup(id))
			return ResponseHandler.getResponse("there is no role group id : " + id, HttpStatus.BAD_REQUEST);
		
		boolean result = _service.findUserInRoleGroup(id, userName);
		if (!result)
			return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);
		return ResponseHandler.getResponse(HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> saveRoleGroup (@Valid @ RequestBody CreateRoleGroupDTO dto, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		RoleGroup roleGroup = _service.save(dto);
		return ResponseHandler.getResponse(roleGroup, HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/{group-id}")
	public ResponseEntity<Object>updateRoleGroup ( @Valid  @NotNull @PathVariable("group-id") Long groupId,
		  @Valid @RequestBody CreateRoleGroupDTO dto ,
			 BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_service.existRoleGroup(groupId))
			return ResponseHandler.getResponse("there is no role group id : " + groupId, HttpStatus.BAD_REQUEST);
		
		RoleGroup updateGroup = _service.updateRoleGroup(dto, groupId);
		return ResponseHandler.getResponse(updateGroup, HttpStatus.OK);
	}
	
	@PutMapping("/{group-id}/rolename}")
	public ResponseEntity<Object>addRoleToGroup (@Valid @RequestBody @ExistRoleNameAtRoles String roleName, @Valid @NotNull @PathVariable ("group-id") Long groupId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse( errors, HttpStatus.BAD_REQUEST);
		
		if (!_service.existRoleGroup(groupId))
			return ResponseHandler.getResponse("there is no role group id : " + groupId, HttpStatus.BAD_REQUEST);
		
		RoleGroup updateGroup = _service.addRole(roleName, groupId);
		return ResponseHandler.getResponse(updateGroup, HttpStatus.OK);
	}
	
	@PutMapping("/{group-id}/username")
	public ResponseEntity<Object>addUserToGroup (@Valid @RequestBody @ExistUserNameAtUsers String username, @Valid @ NotNull @PathVariable ("group-id") Long groupId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_service.existRoleGroup(groupId))
			return ResponseHandler.getResponse("there is no role group id : " + groupId, HttpStatus.BAD_REQUEST);
		
		RoleGroup updateGroup = _service.addUsername(username, groupId);
		return new ResponseEntity<>(updateGroup, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{group-id}")
	public ResponseEntity<Object> deleteGroup (@Valid @NotNull @PathVariable ("group-id") Long groupId, BindingResult errors){
		
		if (errors.hasErrors())
			return ResponseHandler.getResponse( errors, HttpStatus.BAD_REQUEST);
		boolean result = _service.deleteRoleGroupById (groupId);
		
		if (result)
			return ResponseHandler.getResponse( HttpStatus.OK);
		return ResponseHandler.getResponse( "role group is not existed",HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{group-id}/role")
	public ResponseEntity<Object>deleteRoleInGroup (@Valid @RequestBody String roleName, @Valid @NotNull @PathVariable ("group-id") Long groupId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse( errors, HttpStatus.BAD_REQUEST);
		
		if (!_service.existRoleGroup(groupId))
			return ResponseHandler.getResponse("there is no role group id : " + groupId, HttpStatus.BAD_REQUEST);
		
		boolean result = _service.deleteRoleInRoleGroup (groupId,roleName);
		if (result)
			return ResponseHandler.getResponse( HttpStatus.OK);
		return ResponseHandler.getResponse("role is not existed", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{group-id}/user")
	public ResponseEntity<Object>deleteUserInGroup (@Valid @RequestBody String userName, @Valid @NotNull @PathVariable ("group-id") Long groupId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse( errors, HttpStatus.BAD_REQUEST);
		
		if (!_service.existRoleGroup(groupId))
			return ResponseHandler.getResponse("there is no role group id : " + groupId, HttpStatus.BAD_REQUEST);
		
		boolean result = _service.deleteUserInRoleGroup (groupId,userName);
		if (result)
			return ResponseHandler.getResponse( HttpStatus.OK);
		return ResponseHandler.getResponse("user is not presented", HttpStatus.BAD_REQUEST);
	}
	
	
	

}
