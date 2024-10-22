package com.unicorn.sg.demo.repository;

import com.unicorn.sg.demo.domain.LogRecord;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LogRepository implements PanacheRepository<LogRecord> {

    public int setReverted(long id){
        return update("reverted = true where id = ?1", id);
    }
}
