package com.dio.gof.repository;

import com.dio.gof.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {

    @Query("select e from Endereco e where LOWER(e.cep) = LOWER(:cep) and e.numero = :numero and LOWER(e.complemento) = LOWER(:complemento)")
    Endereco existsEndereco(@Param("cep") String cep, @Param("numero") Integer numero,
                            @Param("complemento") String complemento);
}
