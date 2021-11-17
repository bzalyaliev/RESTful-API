package com.example.socks.controller;

import com.example.socks.model.Batch;
import com.example.socks.repository.BatchRepository;
import com.example.socks.repository.BatchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
public class BatchController {
    private final BatchRepository batchRepository;

    @PostMapping(value = "/income")
    public Integer incomeSocks(@Valid @RequestBody Batch batch) {
        for (int i = 0; i < batch.getQuantity(); i++){
            batchRepository.save(BatchEntity
                    .builder()
                    .color(batch.getColor())
                    .cottonPart(batch.getCottonPart())
                    .quantity(1)
                    .build());
        }
        batchRepository.saveAll(batchEntityList);
    }

    @PostMapping(value = "/outcome")
    public Integer outcomeSocks(@Valid @RequestBody Batch batch) {
        int count = 0;
            for (BatchEntity batchEntity : batchRepository.findAll()) {
                if (batch.getCottonPart() == batchEntity.getCottonPart() & batch.getColor().equals(batchEntity.getColor())) {
                    batchRepository.delete(batchEntity);
                    count ++;
                    if (count==batch.getQuantity()) {
                        break;
                    }
                }
            }
        return 10;
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
