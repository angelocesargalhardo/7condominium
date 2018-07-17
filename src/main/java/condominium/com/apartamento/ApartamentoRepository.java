package condominium.com.apartamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartamentoRepository extends JpaRepository<Apartamento, ApartamentoId> {
}
