package com.waterkong.banking_system.repository;

import com.waterkong.banking_system.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

// This interface gives CRUD methods for Account without writing SQL.
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Not adding anything yet.
    // JpaRepository already provides save, findById, findAll, deleteById, etc.
}
