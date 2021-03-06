package ch.hearc.springwater.security;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>
{
	public Role findByNom(String nom);
}
