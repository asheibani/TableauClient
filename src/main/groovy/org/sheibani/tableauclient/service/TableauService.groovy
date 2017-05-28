package org.sheibani.tableauclient.service

import org.sheibani.tableauclient.client.TableauApiClient
import org.sheibani.tableauclient.client.TableauClient
import org.sheibani.tableauclient.config.TableauConfig
import org.sheibani.tableauclient.model.CredentialsResponse
import org.sheibani.tableauclient.model.TableauView
import org.sheibani.tableauclient.model.WorkbookResponse
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct
import javax.annotation.Resource

/**
 * Created by asheibani on 5/28/17.
 */
@Service
class TableauService {

    @Resource TableauApiClient apiClient
    @Resource TableauClient client
    @Resource TableauConfig config

    String token



    @Scheduled(fixedDelay = 3600000L)
    @PostConstruct
    private void getApiToken() {
        CredentialsResponse response = apiClient.signin(config.username, config.password)
        token = response.credentials.token
    }

    public List<TableauView> getWorkbook() {
        WorkbookResponse response = apiClient.getWorkbook(token, config.siteId, config.workbookId)
        List<TableauView> views = response.workbook.views.view.collect { WorkbookResponse.View view ->
            TableauView tableauView = new TableauView()
            tableauView.name = view.name
            tableauView.contentUrl = view.contentUrl
            return tableauView
        }
        return views
    }

    public String getEmbededCode(String contentUrl) {
        return getEmbededCode(contentUrl, (String) null)
    }

    public String getEmbededCode(String contentUrl, String filter) {
        String ticket = client.getToken(config.username)
        String hostUrl = config.url.endsWith("/") ? config.url : config.url + "/"
        String filterParam = filter ? "<param name='filter' value='${filter}'/>" : null
        String template =
                """
                |<script type='text/javascript' src='${config.url}/javascripts/api/viz_v1.js'></script>
                |<div class='tableauPlaceholder' style='width: 100%; height: 100%;'>
                |   <object class='tableauViz' width='100%' height='100%' style='display:none;'>
                |       <param name='host_url' value='${URLEncoder.encode(hostUrl, "UTF-8")}'/>
                |       <param name='site_root' value=''/>
                |       <param name='name' value='${contentUrl.replace(config.contentUrlFilter, "").replaceAll("/", "&#47;")}'/>
                |       <param name='tabs' value='no'/>
                |       <param name='toolbar' value='yes'/>
                |       <param name='showShareOptions' value='true'/>
                |       <param name='ticket' value='${ticket}'/>
                |       ${filterParam}
                |   </object>
                |</div>
                |</div>
                """.trim().stripMargin()
        return template
    }
}