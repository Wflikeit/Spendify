package com.Spendify.Spendify.Expense;

//import com.Spendify.Spendify.Debt.Debt;
//import com.Spendify.Spendify.Debt.DebtRepository;

import com.Spendify.Spendify.Invoice.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    //    private final DebtRepository debtRepository;
    private final InvoiceRepository invoiceRepository;
    private final ExpenseDTOMapper expenseDTOMapper;


    public ExpenseService(ExpenseRepository expenseRepository,
//                          DebtRepository debtRepository,
                          InvoiceRepository invoiceRepository,
                          ExpenseDTOMapper expenseDTOMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseDTOMapper = expenseDTOMapper;
//        this.debtRepository = debtRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public List<ExpenseDTO> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseDTOMapper)
                .collect(Collectors.toList());

    }

    public ExpenseDTO getExpense(Long expenseId) {
        return expenseRepository
                .findById(expenseId)
                .map(expenseDTOMapper)
                .orElseThrow(() -> new IllegalStateException("Expense not found with ID: " + expenseId));
    }

    public void updateExpense(ExpenseUpdateRequest expenseUpdateRequest, Long expenseId) {
        Expense expense = expenseRepository.getReferenceById(expenseId);
        if (expenseUpdateRequest.amountLeft() != null) expense
                .setAmountLeft(expenseUpdateRequest.amountLeft());

        if (expenseUpdateRequest.quantity() != null) expense
                .setQuantity(expenseUpdateRequest.quantity());
        expenseRepository.save(expense);
    }

    public void deleteExpense(Long expenseId) {
        Expense expense = expenseRepository.getReferenceById(expenseId);
        expenseRepository.delete(expense);
    }

    public void addExpense(ExpenseAddRequest addRequest) {
        System.out.println("tet");

        Expense expense = new Expense();
        expense.setQuantity(addRequest.quantity());
        expense.setDate(new Date());
        expense.setAmountLeft(addRequest.amountLeft());
        expenseRepository.save(expense);
    }
}
