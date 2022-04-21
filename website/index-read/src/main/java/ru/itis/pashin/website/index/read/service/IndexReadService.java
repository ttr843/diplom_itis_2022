package ru.itis.pashin.website.index.read.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import ru.itis.pashin.website.index.common.index.LoanIndex;
import ru.itis.pashin.website.index.common.util.ElasticQueryBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class IndexReadService {

    private static final String CREATED_BY_ID_FIELD_NAME = "createdById";
    private static final String ML_STATUS_ID_FIELD_NAME = "mlStatusId";
    private final ElasticsearchOperations elasticsearchOperations;

    public List<LoanIndex> getByCreatedBy(Long createdById) {
        ElasticQueryBuilder builder = new ElasticQueryBuilder();
        builder.addToQuery(ElasticQueryBuilder.TermType.MATCH, CREATED_BY_ID_FIELD_NAME, createdById);
        return getLoanIndexes(builder);
    }

    public List<LoanIndex> getByMlStatusId(Integer mlStatusId) {
        ElasticQueryBuilder builder = new ElasticQueryBuilder();
        builder.addToQuery(ElasticQueryBuilder.TermType.MATCH, ML_STATUS_ID_FIELD_NAME, mlStatusId);
        return getLoanIndexes(builder);
    }

    private List<LoanIndex> getLoanIndexes(ElasticQueryBuilder builder) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder()
                .withFilter(builder.getFilterBuilder());
        return elasticsearchOperations.search(queryBuilder.build(), LoanIndex.class)
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
