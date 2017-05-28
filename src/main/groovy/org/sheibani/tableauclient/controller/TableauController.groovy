package org.sheibani.tableauclient.controller

import org.sheibani.tableauclient.model.TableauView
import org.sheibani.tableauclient.service.TableauService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import javax.annotation.Resource
import javax.servlet.http.HttpServletResponse
import java.nio.file.attribute.UserPrincipalLookupService

/**
 * Created by asheibani on 5/28/17.
 */
@RestController
@RequestMapping("/tableau-client")
class TableauController {

    @Resource TableauService tableauService
    @Resource UserPrincipalLookupService userService

    @RequestMapping(value = "/views", method = RequestMethod.GET)
    public List<TableauView> getViews() {
        List<TableauView> views = tableauService.getWorkbook()
        return views
    }

    @RequestMapping(value="/html", method=RequestMethod.GET)
    public void getViewHtml(@RequestParam(value="contentUrl") String contentUrl, HttpServletResponse response) {
        String embeded = tableauService.getEmbededCode(contentUrl)

        String html =
                    """
                    |<html>
                    |   <head></head>
                    |   <body>
                    |       ${embeded}
                    |   </body>        
                    |</html>
                    """.trim().stripMargin()

        response.contentType = "text/html"
        response.writer.println(html)
    }
}