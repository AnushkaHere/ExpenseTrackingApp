package com.expense.app;

import com.expense.app.controller.ExpenseController;
import com.expense.app.entity.Expense;
import com.expense.app.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ExpenseControllerTest {

    @Mock
    private ExpenseService expenseService;

    @InjectMocks
    private ExpenseController expenseController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(expenseController).build();
    }

    @Test
    public void testGetAllExpenses() throws Exception {
        List<Expense> expenses = Arrays.asList(
                                    new Expense(1L, "Expense1", 100.0, "Category1"),
                                    new Expense(2L, "Expense2", 200.0, "Category2")
                                );

        when(expenseService.getAllExpenses()).thenReturn(expenses);

        mockMvc.perform(get("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].description", is("Expense1")))
                .andExpect(jsonPath("$[1].description", is("Expense2")));

        verify(expenseService, times(1)).getAllExpenses();
    }

    @Test
    public void testGetExpenseById() throws Exception {
        Expense expense = new Expense(1L, "Expense1", 100.0, "Category1");

        when(expenseService.getExpenseById(anyLong())).thenReturn(expense);

        mockMvc.perform(get("/api/expenses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("Expense1")));

        verify(expenseService, times(1)).getExpenseById(1L);
    }

    @Test
    public void testCreateExpense() throws Exception {
        Expense expense = new Expense(1L, "Expense1", 100.0, "Category1");

        when(expenseService.createExpense(any(Expense.class))).thenReturn(expense);

        mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Expense1\",\"amount\":100.0,\"category\":\"Category1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("Expense1")));

        verify(expenseService, times(1)).createExpense(any(Expense.class));
    }

    @Test
    public void testUpdateExpense() throws Exception {
        Expense updatedExpense = new Expense(1L, "Updated Expense", 200.0, "Updated Category");

        when(expenseService.updateExpense(anyLong(), any(Expense.class))).thenReturn(updatedExpense);

        mockMvc.perform(put("/api/expenses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Updated Expense\",\"amount\":200.0,\"category\":\"Updated Category\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("Updated Expense")));

        verify(expenseService, times(1)).updateExpense(anyLong(), any(Expense.class));
    }

    @Test
    public void testDeleteExpense() throws Exception {
        mockMvc.perform(delete("/api/expenses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(expenseService, times(1)).deleteExpense(anyLong());
    }
}
