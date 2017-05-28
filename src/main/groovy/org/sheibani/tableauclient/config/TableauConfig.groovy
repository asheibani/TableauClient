package org.sheibani.tableauclient.config

import feign.Feign
import feign.Logger
import feign.Logger.Level
import feign.gson.GsonDecoder
import feign.jackson.JacksonEncoder
import feign.slf4j.Slf4jLogger
import groovy.util.logging.Slf4j
import org.sheibani.tableauclient.client.TableauApiClient
import org.sheibani.tableauclient.client.TableauClient
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.annotation.PostConstruct
import java.nio.file.attribute.GroupPrincipal
import java.nio.file.attribute.UserPrincipal
import java.nio.file.attribute.UserPrincipalLookupService

/**
 * Created by asheibani on 5/28/17.
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix="tableau")
class TableauConfig {

    String url
    String username
    String password
    String siteId
    String workbookId
    String apiVersion
    String contentUrlFilter
    String logLevel

    Level level

    @PostConstruct
    public void init() {
        try {
            level = Level.valueOf(logLevel)
        } catch(Throwable t) {
            log.warn("Invalid log level ${logLevel}, setting to FULL")
        }
    }

    @Bean
    public TableauApiClient tableauApiClient() {
        TableauApiClient client =
                Feign.builder()
                        .logger(new Slf4jLogger())
                        .logLevel(level)
                        .encoder(new JacksonEncoder())
                        .decoder(new GsonDecoder())
                        .target(TableauApiClient.class, url + apiVersion)

        return client
    }

    @Bean
    public TableauClient tableauClient() {
        TableauClient client =
                Feign.builder()
                        .logger(new Slf4jLogger())
                        .logLevel(level)
                        .target(TableauClient.class, url)

        return client
    }

    @Bean
    public UserPrincipalLookupService getUserPrincipalLookupService() {
        return  new UserPrincipalLookupService() {

            @Override
            UserPrincipal lookupPrincipalByName(String name) throws IOException {
                return null
            }

            @Override
            GroupPrincipal lookupPrincipalByGroupName(String group) throws IOException {
                return null
            }
        }
    }
}
