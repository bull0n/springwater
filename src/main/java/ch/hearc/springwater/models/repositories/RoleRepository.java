package ch.hearc.springwater.models.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.springwater.models.entities.Role;

public interface RoleRepository  extends CrudRepository<Role, Long>
{

}
