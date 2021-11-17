package com.example.socks.controller;

import com.example.socks.model.Batch;
import com.example.socks.repository.BatchRepository;
import com.example.socks.repository.BatchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;


@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
public class BatchController {
    private final BatchRepository batchRepository;

    /**
     * on demo purposes
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping(value = "/income")
    @ResponseStatus(HttpStatus.CREATED)
    public void incomeSocks(@Valid @RequestBody Batch batch) {
        List<BatchEntity> batchEntityList = new ArrayList<>();
        for (int i = 0; i < batch.getQuantity(); i++) {
            //saveAll and List
            batchEntityList.add(BatchEntity
                    .builder()
                    .color(batch.getColor())
                    .cottonPart(batch.getCottonPart())
                    .quantity(1)
                    .build());
        }
        batchRepository.saveAll(batchEntityList);
    }

    @PostMapping(value = "/outcome")
    public ResponseEntity<?> outcomeSocks(@Valid @RequestBody Batch batch) {
        List<BatchEntity> batchEntities = batchRepository.findAllByColorAndCottonPart(batch.getColor(), batch.getCottonPart());
        //if we deleted not all batchEntities the return 206
        if (batchEntities.size() >= batch.getQuantity()) {
            deleteBatches(batchEntities, batch.getQuantity());
            return ResponseEntity.status(202).build();
        } else {
            deleteBatches(batchEntities, batchEntities.size());
            return ResponseEntity.status(206).build();
        }
    }

    private void deleteBatches(List<BatchEntity> batchEntities, int size) {
        IntStream.range(0, size)
                .forEach(value -> batchRepository.delete(batchEntities.get(value)));
    }

    @GetMapping(value = "/{id}")
    BatchEntity one(@PathVariable Long id) {
        return batchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find sock"));
    }

    /*
    ?color=red&cottonPart=2

    Response:
       1. empty list
       2. default: operation = equal
       3. exception: response.statusCode
          a. 400 - bad request - best
          b. 417 - expectation fails
      -----
      how to make it clean:
      1. switch
      2. if
      3. Map<Operation, ...function?
      4. Specification pattern: QuerySpecification; QuerySpecification
     */
    @GetMapping()
    List<BatchEntity> search (@RequestParam String color, @RequestParam Integer cottonPart, @RequestParam Operation operation) {
        //что если operation = null ?
        switch (operation) {
            case EQUAL:
                return batchRepository.findBatchEntitiesWithColorPartEqual(color, cottonPart);
            case MORE_THAN:
                return batchRepository.findBatchEntitiesWithColorPartMoreThan(color, cottonPart);
            case LESS_THAN:
                return batchRepository.findBatchEntitiesWithColorPartLessThan(color, cottonPart);
            default:
                //operation == null
                return Collections.emptyList();
        }
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
