package ru.itis.pashin.website.indexsync.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.stereotype.Service;
import ru.itis.pashin.website.common.service.repository.LoanApplicationRepository;
import ru.itis.pashin.website.index.common.index.LoanIndex;
import ru.itis.pashin.website.indexsync.configuration.ReindexProperties;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;


@Service
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(value = "reindex.reindexLoan", havingValue = "true")
public class LoanReindexService implements InitializingBean {

    private final LoanApplicationRepository loanApplicationRepository;
    private final ReindexProperties reindexProperties;
    private final ElasticsearchOperations elasticsearchOperations;
    private final SaveLoanIndexService saveLoanIndexService;

    private AtomicInteger saved;


    @Override
    public void afterPropertiesSet() {
        log.info("Начинаю реиндекс Заявок");
        this.saved = new AtomicInteger(reindexProperties.getLoanSavedBefore());

        log.info("Получаю объект IndexOperations");
        IndexOperations indexOperations = elasticsearchOperations.indexOps(LoanIndex.class);
        log.info("Проверка на существование индекса");
        if (reindexProperties.getLoanStartingPage() == 0 && indexOperations.exists() && Boolean.TRUE.equals(reindexProperties.getRebuildLoan())) {
            indexOperations.delete();
            log.info("Предыдущий индекс Заявок удален");
        }

        log.info("Проверка существования индекса");
        if (!indexOperations.exists()) {
            Document mapping = indexOperations.createMapping(LoanIndex.class);
            indexOperations.create();
            indexOperations.putMapping(mapping);
            log.info("Новый индекс Заявок создан");
        }

        long count = loanApplicationRepository.countLoanApplication();
        log.info("Найдено {} Заявок в базе", count);

        int pageCount = (int) Math.ceil((float) count / reindexProperties.getRebuildPageSize());
        log.info("Расчетное число страниц индекса {}", pageCount);

        log.info("Запускаем пересчет в асинхронных потоках");
        IntStream.range(reindexProperties.getLoanStartingPage(), pageCount)
                .forEachOrdered(page -> {
                    log.info("Начинаю индексирование страницы {}", page);
                    saveLoanIndexService.saveToIndex(page, saved);
                    log.info("Индексирование страницы {} завершено", page);
                });

        log.info("Реиндексация индекса заявок завершена");
    }
}
