package com.red.care.task.util;

import com.red.care.task.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class QueryBuilder {
    private static final String LANGUAGE_FIELD = "language:";
    private static final String CREATED_FIELD_GREATER_THAN_EQUAL = "created:>=";
    private static final String FORKS_FIELD_GREATER_THAN_EQUAL = "forks:>";
    private static final String STARS_FIELD_GREATER_THAN_EQUAL = "stars:>";
    private static final int ZERO = 0;

    public static String buildQueryParameter(final String language, final Date createdFrom) {
        try {
            final LocalDate createdFromlocalDate = createdFrom.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            return Stream.of(Optional.ofNullable(language)
                                    .filter(lang -> !lang.isBlank())
                                    .map(lang -> LANGUAGE_FIELD + lang),
                            Optional.of(CREATED_FIELD_GREATER_THAN_EQUAL + createdFromlocalDate),
                            Optional.of(FORKS_FIELD_GREATER_THAN_EQUAL + ZERO),
                            Optional.of(STARS_FIELD_GREATER_THAN_EQUAL + ZERO)
                    )
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.joining(" "));
        } catch (Exception ex) {
            log.error("Failed to build Query Parameter", ex);
            throw new ApiException("Query Builder Failed");
        }
    }
}
