package com.Spendify.Spendify;

import com.Spendify.Spendify.Currency.Currency;
import com.Spendify.Spendify.Currency.CurrencyRepository;
import com.Spendify.Spendify.Debt.Debt;
import com.Spendify.Spendify.Debt.DebtRepository;
import com.Spendify.Spendify.Expense.Expense;
import com.Spendify.Spendify.Expense.ExpenseRepository;
import com.Spendify.Spendify.Invoice.Invoice;
import com.Spendify.Spendify.Invoice.InvoiceRepository;
import com.Spendify.Spendify.User.User;
import com.Spendify.Spendify.User.UserRepository;
import com.Spendify.Spendify.Wallet.Wallet;
import com.Spendify.Spendify.Wallet.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpendifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpendifyApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, ExpenseRepository expenseRepository, InvoiceRepository invoiceRepository, CurrencyRepository currencyRepository, WalletRepository walletRepository, DebtRepository debtRepository){
        return args -> {
//            User Maciek = new User(
//                    "Maciek",
//                    "Kowalski",
//                    "admin",
//                    "m.krajew@gmail.com",
//                    null,
//                    true
//            );
//            userRepository.save(Maciek);
//            Invoice invoice = new Invoice(
//                    10.0,
//                    10.0,
//                    10.0
//            );
//            invoiceRepository.save(invoice);
//            currencyRepository.save(dollar);
//            Wallet wallet1 = new Wallet(
//                    200.0,
//                    Maciek
//            );
//            walletRepository.save(wallet1);
//            Expense drinks = new Expense(
//                    200.0,
//                    dollar,
//                    new Debt(),
//                    invoice
//            );

            Expense drinks = new Expense(
                    200.580279857,
                    currencyRepository.getReferenceById(1L),
                    debtRepository.getReferenceById(1L),
                    invoiceRepository.getReferenceById(1L)
            );
            expenseRepository.save(drinks);
//            drinks.setDebt(debtRepository.getReferenceById(1L));
        };
    }
}
