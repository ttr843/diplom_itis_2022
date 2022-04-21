package ru.itis.pashin.website.indexsync.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.pashin.website.common.model.loan.mapper.LoanApplicationMapper;
import ru.itis.pashin.website.common.service.repository.LoanApplicationRepository;
import ru.itis.pashin.website.index.common.index.LoanIndex;
import ru.itis.pashin.website.index.common.mapper.LoanIndexMapper;
import ru.itis.pashin.website.indexsync.configuration.ReindexProperties;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Slf4j
@Service
@RequiredArgsConstructor
public class SaveLoanIndexService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanApplicationMapper loanApplicationMapper;
    private final ReindexProperties reindexProperties;
    private final LoanIndexMapper loanIndexMapper;
    private final ElasticsearchOperations elasticsearchOperations;

    @Transactional
    public void saveToIndex(int i, AtomicInteger saved) {
        log.info("Сохранение в индекс. Страница {}.", i);
        List<LoanIndex> batch = StreamSupport.stream(
                loanApplicationRepository.findAll(PageRequest.of(i, reindexProperties.getRebuildPageSize())).spliterator(), false)
                .map(loanApplicationMapper::entityToDTO)
                .map(loanIndexMapper::dtoToIndex)
                .collect(Collectors.toList());
        log.info("Получили пачку Заявок размером {} шт.", batch.size());
        if (!batch.isEmpty()) {
            log.info("Отправляем в индекс {} КНМ", batch.size());
            elasticsearchOperations.save(batch);
        }
        log.info("Сохранено: {}, страница: {}", saved.addAndGet(batch.size()), i);
    }
}
