# TableauClient
Java client for Tableau server.

Proof of concept to integrate with a Tableau server.

This uses trusted authentication only at this time (https://onlinehelp.tableau.com/current/server/en-us/trusted_auth.htm), 
when I have some time I will try to add SAML as well (https://onlinehelp.tableau.com/current/server/en-us/saml.htm).

This application will pull the views of a given workbook.
It will also generate the embeded code to display a view.

In the application.properties, you must define:
1. tableau.url: The url of the Tableau server
2. tableau.username: The username that can access the server
3. tableau.password: The user's password
4. tableau.siteId: The Tableau site id
5. tableau.workbookId: The Tableau workbook id that contains the views
6. tableau.logLevel: select NONE, BASIC, HEADERS, or FULL

Once you have added all the properies, you can start the application.

To view the views of a given workbook:
`GET http://localhost:8080/tableau-client/views`

To view a particular report, find a contentUrl in the response workbook.views.view.contentUrl.

To load a view:
`GET http://localhost:8080/tableau-client/html?contentUrl=[the contentUrl]`