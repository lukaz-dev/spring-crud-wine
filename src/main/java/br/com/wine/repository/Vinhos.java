package br.com.wine.repository;

import br.com.wine.model.Vinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Vinhos extends JpaRepository<Vinho, Long> {

    List<Vinho> findByNomeContainingIgnoreCase(String nome);
}
