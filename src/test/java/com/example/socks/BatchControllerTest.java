package com.example.socks;

import com.example.socks.controller.BatchController;
import com.example.socks.model.Batch;
import com.example.socks.repository.BatchEntity;
import com.example.socks.repository.BatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BatchControllerTest {

    private BatchRepository batchRepository;

    private BatchController batchController;

    @BeforeEach
    void setup() {
        batchRepository = mock(BatchRepository.class);
        batchController = new BatchController(batchRepository);
    }

    @Test
    void itIncomesSocks() {
        int batchCounter = 3;
        Batch batch = Batch
                .builder()
                .color("red")
                .quantity(batchCounter)
                .cottonPart(10)
                .build();

        batchController.incomeSocks(batch);
    }
}
