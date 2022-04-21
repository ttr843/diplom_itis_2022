package ru.itis.pashin.websiteservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.pashin.website.index.common.index.LoanIndex;


@FeignClient(name = "loan-index-read-service", url = "${index-read-service-url}")
public interface IndexReadClient {

    @GetMapping("website/index-read/loan/created-by")
    Iterable<LoanIndex> findLoansByCreatedBy(@RequestParam Long createdById);

    @GetMapping("website/index-read/loan/by-ml-status-id")
    Iterable<LoanIndex> findLoansByMlStatus(@RequestParam Integer mlStatusId);
}
