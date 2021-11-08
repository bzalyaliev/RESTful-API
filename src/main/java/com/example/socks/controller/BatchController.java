package com.example.socks.controller;

import com.example.socks.model.Batch;
import com.example.socks.repository.BatchRepository;
import com.example.socks.repository.BatchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
public class BatchController {
    private final BatchRepository batchRepository;

    @PostMapping(value = "/income")
    public BatchEntity incomeSocks(@RequestBody Batch batch) {
        return batchRepository.save(BatchEntity
                .builder()
                .color(batch.getColor())
                .cottonPart(batch.getCottonPart())
                .quantity(batch.getQuantity())
                .build());
    }

    @GetMapping(value = "/{id}")
    BatchEntity one(@PathVariable Long id) {
        return batchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find sock"));
    }

    @DeleteMapping(value = "/{id}")
    void deleteSock(@PathVariable Long id) {
        batchRepository.deleteById(id);
    }

    @PatchMapping(value = "/{id}")
    public BatchEntity patchBatch(@PathVariable Long id, @RequestBody Batch batch) {
        BatchEntity batchEntity = batchRepository.findById(id).get()
                .setColor(batch.getColor())
                .setCottonPart(batch.getCottonPart())
                .setQuantity((batch.getQuantity()));
        return batchRepository.save(batchEntity);
    }
}
