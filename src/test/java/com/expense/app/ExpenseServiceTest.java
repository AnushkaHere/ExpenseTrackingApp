package com.expense.app;

import com.expense.app.entity.Expense;
import com.expense.app.exception.ResourceNotFoundException;
import com.expense.app.repository.ExpenseRepository;
import com.expense.app.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    public void testGetAllExpenses() {
        List<Expense> expenses = Arrays.asList(
                new Expense(1L, "Expense1", 100.0, "Category1"),
                new Expense(2L, "Expense2", 200.0, "Category2")
        );

        when(expenseRepository.findAll()).thenReturn(expenses);

        List<Expense> result = expenseService.getAllExpenses();

        assertEquals(2, result.size());
        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    public void testGetExpenseById() {
        Expense expense = new Expense(1L, "Expense1", 100.0, "Category1");

        when(expenseRepository.findById(anyLong())).thenReturn(Optional.of(expense));

        Expense result = expenseService.getExpenseById(1L);

        assertNotNull(result);
        assertEquals("Expense1", result.getDescription());
        verify(expenseRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateExpense() {
        Expense expense = new Expense(1L, "Expense1", 100.0, "Category1");

        when(expenseRepository.save(any(Expense.class))).thenReturn(expense);

        Expense result = expenseService.createExpense(expense);

        assertNotNull(result);
        assertEquals("Expense1", result.getDescription());
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    public void testUpdateExpense() {
        Expense existingExpense = new Expense(1L, "Expense1", 100.0, "Category1");
        Expense updatedExpense = new Expense(1L, "Updated Expense", 200.0, "Updated Category");

        when(expenseRepository.findById(anyLong())).thenReturn(Optional.of(existingExpense));
        when(expenseRepository.save(any(Expense.class))).thenReturn(updatedExpense);

        Expense result = expenseService.updateExpense(1L, updatedExpense);

        assertNotNull(result);
        assertEquals("Updated Expense", result.getDescription());
        verify(expenseRepository, times(1)).findById(1L);
        verify(expenseRepository, times(1)).save(updatedExpense);
    }

    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id " + id));
        expenseRepository.delete(expense);
    }
}