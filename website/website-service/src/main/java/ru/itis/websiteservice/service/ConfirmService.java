package ru.itis.websiteservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmService {


    public boolean confirm(String confirmCode) {
        return true;
    }
}
