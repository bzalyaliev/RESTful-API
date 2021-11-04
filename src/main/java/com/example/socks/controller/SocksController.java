package com.example.socks.controller;

import com.example.socks.model.Sock;
import com.example.socks.repository.SockEntity;
import com.example.socks.repository.SockRepository;
import lombok.RequiredArgsConstructor;
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
}
