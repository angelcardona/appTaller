package com.tallercarpro.appTaller.Controllers;

import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.models.domain.Balance;
import com.tallercarpro.appTaller.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/balances")
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @PostMapping
    public ResponseEntity<Balance> createBalance(@RequestBody Balance balance) {
        Balance createdBalance = balanceService.createBalance(balance);
        return new ResponseEntity<>(createdBalance, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Balance>> getAllBalances() {
        List<Balance> balances = balanceService.getAllBalances();
        return new ResponseEntity<>(balances, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Balance> getBalanceById(@PathVariable String id) {
        Balance balance = balanceService.getBalanceById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Balance no encontrado con ID: " + id));
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Balance> updateBalance(@PathVariable String id, @RequestBody Balance balance) {
        Balance updatedBalance = balanceService.updateBalance(id, balance);
        return new ResponseEntity<>(updatedBalance, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBalance(@PathVariable String id) {
        balanceService.deleteBalance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
