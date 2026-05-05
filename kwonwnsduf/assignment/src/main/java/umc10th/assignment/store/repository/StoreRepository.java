package umc10th.assignment.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc10th.assignment.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
