package com.mmahu.templates.worker.controllers;

import com.mmahu.templates.worker.model.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class WorkerController {

    @RequestMapping("/workers")
    public @ResponseBody List<Worker> getWorkers(@Value("${worker:default}") String type) {
        return Arrays.asList(
                new Worker(type, 1),
                new Worker(type, 2)
        );
    }
}
