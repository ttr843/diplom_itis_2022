package ru.itis.pashin.website.indexwrite.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import ru.itis.pashin.website.common.model.loan.dto.LoanApplicationDTO;
import ru.itis.pashin.website.index.common.index.LoanIndex;
import ru.itis.pashin.website.index.common.mapper.LoanIndexMapper;


@Slf4j
@RequiredArgsConstructor
@Service
public class IndexService {

    private final LoanIndexMapper loanIndexMapper;
    private final ElasticsearchOperations elasticsearchOperations;

    public void save(LoanApplicationDTO loanApplicationDTO) {
        LoanIndex loanIndex = loanIndexMapper.dtoToIndex(loanApplicationDTO);
        elasticsearchOperations.save(loanIndex);
    }
}
