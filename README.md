# Server
Java :coffee: Spring framework :leave: based REST backend.

## Configuration
Do not forget about application.properties configuration your database URL, login, password.
Also you could configure custom robot email. <b> Do not use your personal email address for 
this purposes</b>.
If you prefer google do not forget configure remote access for you mail robot, and some time test 
it on remote server, because google can block this access by another location reason.

## Security
Program is using minimal security configuration. By default it allow CORS at <code>/users/</code> and <code>/admin/</code>
also opened for access.
