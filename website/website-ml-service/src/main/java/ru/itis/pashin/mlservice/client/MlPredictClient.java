package ru.itis.pashin.mlservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@FeignClient(name = "ml-predict-service", url = "${ml-predict-service-url}")
public interface MlPredictClient {

    @GetMapping("/website/ml/predict")
    String predict(@RequestParam Long industryId,
                    @RequestParam Long creditLimit,
                    @RequestParam Long amountOfLawsuits,
                    @RequestParam Long amountOfProceedings,
                    @RequestParam Long companySizeTypeId,
                    @RequestParam Long amountOfWorkers,
                    @RequestParam Long capital,
                    @RequestParam Long revenue,
                    @RequestParam Long netProfit);
}
