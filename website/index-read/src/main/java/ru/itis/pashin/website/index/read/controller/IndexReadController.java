package ru.itis.pashin.website.index.read.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.pashin.website.index.common.index.LoanIndex;
import ru.itis.pashin.website.index.read.service.IndexReadService;

import java.util.List;

@RestController
@RequestMapping("website/index-read/loan")
@RequiredArgsConstructor
public class IndexReadController {

    private final IndexReadService indexReadService;

    @GetMapping("/created-by")
    public List<LoanIndex> getLoanByCreatedBy(@RequestParam Long createdById) {
        return indexReadService.getByCreatedBy(createdById);
    }

    @GetMapping("/by-ml-status-id")
    public List<LoanIndex> getLoanByMlStatusId(@RequestParam Integer mlStatusId) {
        return indexReadService.getByMlStatusId(mlStatusId);
    }
}
