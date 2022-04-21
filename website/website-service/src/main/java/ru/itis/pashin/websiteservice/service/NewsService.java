package ru.itis.pashin.websiteservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.pashin.website.common.model.exception.exception.ServiceException;
import ru.itis.pashin.website.common.model.system.dto.NewsDTO;
import ru.itis.pashin.website.common.model.system.mapper.NewsMapper;
import ru.itis.pashin.website.common.service.repository.NewsRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static ru.itis.pashin.website.common.model.exception.enumeration.ServiceErrorCode.ERROR_CODE_0002;
import static ru.itis.pashin.website.common.model.exception.enumeration.ServiceErrorCode.ERROR_CODE_0007;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public List<NewsDTO> getAllNews() {
        return newsRepository.findAll().stream()
                .map(newsMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    public List<NewsDTO> getAllNotDeletedNews() {
        return StreamSupport.stream(
                newsRepository.findAllByDeletedOrderByCreatedAtDesc(false).spliterator(), false)
                .map(newsMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createNews(NewsDTO newsDTO) {
        try {
            newsRepository.save(newsMapper.dtoToEntity(newsDTO));
        } catch (Exception e) {
            log.error("ошибка при создании новости:  {}", e.getMessage());
            throw new ServiceException(ERROR_CODE_0002);
        }
    }

    @Transactional
    public void deleteNews(Long id) {
        try {
            newsRepository.deleteNews(id);
        } catch (Exception e) {
            log.error("ошибка при удалении новости:  {}", e.getMessage());
            throw new ServiceException(ERROR_CODE_0007);
        }
    }
}
