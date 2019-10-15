package org.otus.akurus.dao;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Value("${csv.file}")
    @Getter
    private String fileName;
}
