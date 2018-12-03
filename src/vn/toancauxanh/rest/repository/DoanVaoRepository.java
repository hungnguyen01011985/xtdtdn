package vn.toancauxanh.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import vn.toancauxanh.model.DoanVao;

public interface DoanVaoRepository extends JpaRepository<DoanVao, Long>, QueryDslPredicateExecutor<DoanVao> {
	@Query("select dv from DoanVao dv where dv.daXoa = false and dv.id = ?1")
	DoanVao getById(Long id);
	
}
