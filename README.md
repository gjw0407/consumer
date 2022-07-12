We have seperated servers that are responsible for web/twitter api sender/data processing 

We believed this will help us scale out easily in cloud computing environment

[For system architecture, please check Backend repo] (https://github.com/jh27kim/producer)

### Web Server Components
1. Login
2. Register
3. User-Chart Data
Interestingly, this server is responsible for CRUD actions of new charts.
Obviously, chart data is requested from vue component to Twitter API Server [API Server](https://github.com/jh27kim/producer)
But chart metadata is processed in this server. Since calling Twitter API is a heavy task, we wanted to separate from any other functionalities. 
In this way, we can just replicate Twitter API server alone in order to meet higher demands. 
4. Authentifiaction
