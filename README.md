# GMDB

MovieSchema:
 {
 "title": "",
 "director": "",
 "actors": "",
 "release": "",
"description": ""                                                                            
}



| URI               | HTTP Method | HTTP Status |      Description     |       Request        |        Response        | 
|-------------------|-------------|-------------|----------------------|----------------------|------------------------|
| /movies           |    GET      |     200     | Get All Movies       |           -          | `[ MovieSchema ] `     |
| /movies           |    POST     |     201     |    Add movie to DB   |       MovieSchema    |                        |  
| /movies/{title}   |    GET      |     200     |  Get Movie by Title  |          -           |    MovieSchema         |
| /movies/{title}   |    PUT      |     202     |  Update Movie        |       MovieSchema    |    MovieSchema         |
