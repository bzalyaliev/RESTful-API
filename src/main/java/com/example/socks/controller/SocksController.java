package com.example.socks.controller;

import com.example.socks.model.Sock;
import com.example.socks.repository.SockEntity;
import com.example.socks.repository.SockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * url = server.servlet.context-path + @RequestMapping = /api/socks
 */
@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
public class SocksController {

    private final SockRepository sockRepository;

    @PostMapping(value = "/income")
    public SockEntity incomeSocks(@RequestBody Sock sock) {
        return sockRepository.save(SockEntity
                .builder()
                .color(sock.getColor())
                .cottonPart(sock.getCottonPart())
                .build());
    }

    @GetMapping(value = "/{id}")
    SockEntity one(@PathVariable Long id) {
        return sockRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find sock"));
    }

    @DeleteMapping(value = "/{id}")
    void deleteSock(@PathVariable Long id) {
        sockRepository.deleteById(id);
    }

    @PatchMapping(value = "/{id}")
    public SockEntity patchSocks(@PathVariable Long id, @RequestBody Sock sock) {
        SockEntity sockEntity = sockRepository.findById(id).get();
            sockEntity.setColor(sock.getColor());
            sockEntity.setCottonPart(sock.getCottonPart());
            return sockRepository.save(sockEntity);
        }
    }


