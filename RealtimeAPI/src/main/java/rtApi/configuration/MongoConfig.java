/*
 * Copyright (C) 2024 Rémi Venant
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package rtApi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import rtApi.model.HistoryAction;

/**
 *
 * @author Rémi Venant
 */
@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackageClasses = {HistoryAction.class})
public class MongoConfig {

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(LocalValidatorFactoryBean localValidatorFactoryBean) {
        //a mongo listener to validate data based on javax validation constraints
        return new ValidatingMongoEventListener(localValidatorFactoryBean);
    }
}
