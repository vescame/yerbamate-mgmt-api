# Yerba Mate Mgmt API

Simple API for product management with it's comments

- endpoints:
  - /api/v1/yerbamate (GET, POST)
  - /api/v1/yerbamate/{id} (PUT, GET, DELETE)
  - /api/v1/yerbamate/{id}/comments (POST)
  
JSON:
- Yerba
```json
{
  "name": "yerba 1",
  "yerbaMateType": "ARGENTINIAN",
  "price": 21.99,
  "comments": [
    { "comment": "simple comment 1" },
    { "comment": "simple comment 2" }
  ]
}
```

- YerbaMateType:
  - ARGENTINIAN
  - CHIMARRAO
  - PARAGUAYAN
  - URUGUAYAN
