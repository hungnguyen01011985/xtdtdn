package vn.toancauxanh.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import vn.toancauxanh.model.DuAn;

public interface DuAnRepository extends JpaRepository<DuAn, Long>, QueryDslPredicateExecutor<DuAn> {
	@Query("select da from DuAn da where da.daXoa = false and da.id = ?1")
	DuAn getById(Long id);
}
