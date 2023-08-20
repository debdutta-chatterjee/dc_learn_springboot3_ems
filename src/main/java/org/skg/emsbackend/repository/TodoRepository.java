package org.skg.emsbackend.repository;

import org.skg.emsbackend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long>
{
}
