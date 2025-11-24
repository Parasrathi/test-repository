# RedCare Assignment

## Overview
A Spring Boot application that integrates with the GitHub REST API to search public repositories, calculate their popularity score based on stars, forks and recent activity and return results sorted by score. The project demonstrates the use of Feign client, custom error decoding, concurrent data fetching asynchronously using CompletableFuture and ExecutorService.

## Running Application
- In the **application.yml** file, replace **${GITHUB_TOKEN:}** with your github personal token **OR** use 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
- Clone the main branch and run the main class file (**RedCareTaskApplication.java**)

## API Endpoint
- **GET** /v1/repositories-search
- Parameters: **createdDate** (Format: **DD-MM-YYYY**) , **language** (Example: Java, Python, Javascript)
- Example Curl Request : curl -X GET "http://localhost:8080/v1/repositories-search?language=java&createdDate=19-08-2025"
- Example Reponse :
```json
{
  "repositories": [
    {
      "id": "123",
      "name": "demo-repo",
      "language": "Java",
      "starsCount": 120,
      "forksCount": 30,
      "updatedAt": "2025-11-21T00:00:00Z",
      "popularityScore": 0.524
    }
  ]
}
```
## Tech Stack
- Language (Java 21)
- Framework (Spring Boot 3.5.x)
- Bulid Tool (Maven)
- HTTP Client (OpenFeign)
- Testing (Junit, Mockito)

## Future Enhancements
- Introduce a database layer for storing GitHub repositories. The dataset is extensive and loosely structured, a NoSQL solution is better suited than a traditional relational database.
- Implement a data ingestion layer to ingest repositories data into database, based on a cron Job scheduled once a day.
- All user requests must fetch data from the database instead of calling to the Github API, improving response time.
- Add Caching layer (**Redis**) to reduce load on the database, improving resopnse time significantly.
- For production deployment, Docker support will be added, and the application will be deployed either on an AWS EC2 instance or through a Kubernetes cluster.

## Github API Limitations
- Github search API reutrns max 100 repositories per page and in total max 1000 results. 
- We can explore alternative or paid APIs that provide complete repository data and persist it in our database to significantly enhance the REST API response time.
- Github does not allow pushing github access token, in the future we can replace with environment variable or encrypt using sops.
