package org.libventary.projection.reader;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ReaderDao extends JpaRepository<Reader, UUID> {

}
