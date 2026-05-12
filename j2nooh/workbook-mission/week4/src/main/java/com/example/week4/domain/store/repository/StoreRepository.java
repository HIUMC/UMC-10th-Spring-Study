package com.example.week4.domain.store.repository;

import com.example.week4.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Long> {


    @Query(
            value = """
                    select s
                    from Store s
                    where s.storeRegion = :storeRegion
                    order by s.id desc
                    """,
            countQuery = """
                    select count(s)
                    from Store s
                    where s.storeRegion = :storeRegion
                    """
    )
    Page<Store> findStoresByRegion(
            @Param("storeRegion") String storeRegion,
            Pageable pageable
    );
}