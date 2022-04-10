package ru.itis.websiteservice.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@FeignClient(name = "mail-integration-service", url = "${mail-integration-service-url}")
public class MailServiceClient {


}
