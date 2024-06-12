package es.tfgdm.dao;

import java.util.List;
import java.util.Optional;

import es.tfgdm.entity.Suministra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SuministraDAO extends CrudRepository<Suministra, Long> {

	List<Suministra> findByRepuestoId(String id);

	List<Suministra> findByProveedorDni(String dni);

	// Método para encontrar un objeto Suministra específico por el DNI del
	// proveedor y el ID del repuesto
	Optional<Suministra> findByProveedorDniAndRepuestoId(String dni, String id);

	// Consulta para verificar si un repuesto y un proveedor específicos están
	// vinculados
	@Query("SELECT COUNT(su) > 0 FROM Suministra su WHERE su.repuesto.nombre = :nombreRepu AND su.proveedor.nombre = :nombreProv")
	boolean vinculados(@Param("nombreRepu") String nombreRepu, @Param("nombreProv") String nombreProv);

}
