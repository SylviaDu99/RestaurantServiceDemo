package org.example.controller;

import org.example.domain.Bill;
import org.example.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/bills")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable Integer billId) {
        return ResponseEntity.ok(billService.getBillById(billId));
    }

    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody @Valid Bill bill, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        Bill createdBill = billService.saveBill(bill);
        return ResponseEntity.status(201).body(createdBill);
    }

    @PreAuthorize("hasRole('RESTAURANT_ADMIN')")
    @PutMapping("/{billId}")
    public ResponseEntity<Bill> updateBill(@PathVariable Integer billId, @RequestBody @Valid Bill bill, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        Bill updatedBill = billService.updateBill(billId, bill);
        if (updatedBill == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedBill);
    }

    @PreAuthorize("hasRole('RESTAURANT_ADMIN')")
    @DeleteMapping("/{billId}")
    public ResponseEntity<Void> deleteBill(@PathVariable Integer billId) {
        billService.deleteBill(billId);
        return ResponseEntity.noContent().build();
    }
}
