package ru.itis.pashin.website.index.common.util;

import lombok.Getter;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

import static org.elasticsearch.index.query.QueryBuilders.*;


@Getter
public class ElasticQueryBuilder {
    private final BoolQueryBuilder filterBuilder = boolQuery().must(matchAllQuery());
    private int termCount = 0;

    public void addToQuery(TermType type, String fieldName, Object value) {
        if (isEmpty(value) || type == null) {
            return;
        }

        switch (type) {
            case LIKE:
                filterBuilder.must(wildcardQuery(fieldName, "*" + ((String) value).toLowerCase() + "*"));
                break;
            case NOT_LIKE:
                filterBuilder.mustNot(wildcardQuery(fieldName, "*" + ((String) value).toLowerCase() + "*"));
                break;
            case IN:
                filterBuilder.must(termsQuery(fieldName, (Collection<?>) value));
                break;
            case MATCH:
                filterBuilder.must(matchPhraseQuery(fieldName, value));
                break;
            case EXISTS:
                boolean exists = BooleanUtils.isTrue((Boolean) value);
                if (exists) {
                    filterBuilder.must(existsQuery(fieldName));
                } else {
                    filterBuilder.mustNot(existsQuery(fieldName));
                }
                break;
            case FULL_LIKE: {
                filterBuilder.should(wildcardQuery(fieldName, "*" + ((String) value).toLowerCase() + "*"));
                filterBuilder.should(matchPhraseQuery(fieldName, value));
                filterBuilder.minimumShouldMatch(1);
                break;
            }
            case NOT_EQ:
                filterBuilder.mustNot(termQuery(fieldName, value));
                break;
            default:
                filterBuilder.must(termQuery(fieldName, value));
        }
        this.termCount++;
    }

    private boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        }

        boolean isEmpty = false;
        if (value instanceof String) {
            isEmpty = StringUtils.isEmpty((String) value);
        } else if (value instanceof Collection) {
            isEmpty = CollectionUtils.isEmpty((Collection<?>) value);
        }

        return isEmpty;
    }

    public enum TermType {
        LIKE, IN, MATCH, EXISTS, NOT_EQ, FULL_LIKE, NOT_LIKE
    }
}
