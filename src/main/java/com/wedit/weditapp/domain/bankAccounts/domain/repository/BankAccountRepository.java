package com.wedit.weditapp.domain.bankAccounts.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wedit.weditapp.domain.bankAccounts.domain.BankAccounts;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccounts, Long> {
}

