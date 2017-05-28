package org.sheibani.tableauclient.config

import feign.Feign
import feign.Logger
import feign.gson.GsonDecoder
import feign.jackson.JacksonEncoder
import feign.slf4j.Slf4jLogger
import org.sheibani.tableauclient.client.TableauApiClient
import org.sheibani.tableauclient.client.TableauClient
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import java.nio.file.attribute.GroupPrincipal
import java.nio.file.attribute.UserPrincipal
import java.nio.file.attribute.UserPrincipalLookupService

/**
 * Created by asheibani on 5/28/17.
 */
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

    @Bean
    public TableauApiClient tableauApiClient() {
        TableauApiClient client =
                Feign.builder()
                        .logger(new Slf4jLogger())
                        .logLevel(Logger.Level.FULL)
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
                        .logLevel(Logger.Level.FULL)
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
