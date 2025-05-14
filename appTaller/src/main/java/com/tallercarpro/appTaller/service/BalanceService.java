package com.tallercarpro.appTaller.service;

import com.tallercarpro.appTaller.repository.BalanceRepository;
import com.tallercarpro.appTaller.repository.InvoiceRepository;
import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.exception.ValidationException;
import com.tallercarpro.appTaller.models.domain.Balance;
import com.tallercarpro.appTaller.models.domain.Invoice;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

@Service
public class BalanceService {
    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Transactional
    public Balance createBalance(Balance balance) {
        if (balance.getStartDate() == null) {
            throw new ValidationException("La fecha de inicio es obligatoria");
        }
        if (balance.getEndDate() == null) {
            throw new ValidationException("La fecha de fin es obligatoria");
        }
        if (balance.getEndDate().isBefore(balance.getStartDate())) {
            throw new ValidationException("La fecha de fin debe ser posterior a la fecha de inicio");
        }
        if (balance.getExpenses() < 0) {
            throw new ValidationException("Los gastos no pueden ser negativos");
        }

        // Obtener facturas y manejar caso de lista vacía
        List<Invoice> invoices = invoiceRepository.findByDateBetween(balance.getStartDate(), balance.getEndDate());
        double incomes = invoices != null ? invoices.stream()
                .mapToDouble(Invoice::getTotal)
                .sum() : 0.0;

        balance.setIncomes(incomes);
        balance.setBalance(incomes - balance.getExpenses());

        return (Balance) balanceRepository.save(balance);
    }

    public List<Balance> getAllBalances() {
        return balanceRepository.findAll();
    }

    public Optional<Balance> getBalanceById(String id) {
        return balanceRepository.findById(id);
    }

    @Transactional
    public Balance updateBalance(String id, Balance balance) {
        if (!balanceRepository.existsById(id)) {
            throw new ResourceNotfoundException("Balance no encontrado con ID: " + id);
        }
        balance.setId(id);
        return createBalance(balance); // Reutiliza validaciones
    }

    @Transactional
    public void deleteBalance(String id) {
        if (!balanceRepository.existsById(id)) {
            throw new ResourceNotfoundException("Balance no encontrado con ID: " + id);
        }
        balanceRepository.deleteById(id);
    }
}
