package com.Spendify.Spendify.Balance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    boolean existsByCurrencyIdAndWalletId(Long currencyId, Long walletId);
}