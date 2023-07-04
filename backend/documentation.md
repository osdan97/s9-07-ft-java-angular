# Category:
## List Categories
GET
http://localhost:8080/api/category/list

Example output

```Json
[
  {
    "id": "814fc71a-790f-4ce8-8b22-6f93f61c50a6",
    "name": "dulces",
    "description": "dulces regionales de diversos paises",
    "number": "d001",
    "state": true
  }
]
```

## Create a new category
POST
http://localhost:8080/api/category/create

Example input:
```Json
{
    "name":"bebidas",
    "description":"bebidas regionales de diversos paises",
    "number":"b001",
    "state":true
}
```
Example output:
```Json
{
"mensaje": "category created successfully"
}
```

## Edit a category
PUT
http://localhost:8080/api/category/updatebyname/{categoryName}

Example input:

http://localhost:8080/api/category/updatebyname/dulces

```Json
{
    "name": "dulces",
    "description": "dulces regionales de diversos paises-lo que cambie",
    "number": "d001",
    "state": true
}
```
Example output:
```Json
{
  "mensaje": "category updated successfully"
}
```

## Delete a category
DELETE http://localhost:8080/api/category/deletebyname/{categoryName}

Example input:

http://localhost:8080/api/category/deletebyname/bebidas

Example output:
```Json
{
  "mensaje": "category deleted successfully"
}
```

## Get by name
GET
http://localhost:8080/api/category/detail/{categoryName}

Example input:

http://localhost:8080/api/category/detail/dulces

Example output:
```Json
{
  "id": "814fc71a-790f-4ce8-8b22-6f93f61c50a6",
  "name": "dulces",
  "description": "dulces regionales de diversos paises",
  "number": "d001",
  "state": true
}
```

# Customers

## Register
POST
http://localhost:8080/api/authentication/sign-up

Example input:
```Json
{
  "email": "example@example.com",
  "password": "password",
  "name": "Juan",
  "lastName": "Peréz"
}
```
Example output:
```Json
{
  "email": "example@example.com",
  "password": "$2a$10$gBAtBVF8zx5dtPRt0X9bru.bpKKnWeuDgpwUvyIbwPLpydo1yWuq2",
  "verificationCode": "BFhUIMZCvLaRGRKAC9g9LqltlXLqzqopJiSY1G2BevotUt6YsfjKtigeUI2mqzSh",
  "fullName": "Juan Peréz",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJleGFtcGxlQGV4YW1wbGUuY29tIiwicm9sZXMiOiJVU0VSIiwidXNlcklkIjoiNzg1YjI5NWYtODJmMi00NWUzLWE0NTUtYWU5MTg4OGRlNWZkIiwiZXhwIjoxNjg4NTc1OTgxfQ.Ql8DQ9xQaepdNng39qtnnUKjQ1dOKFuSvthUsHzClmLDtwKPiI0ejLqCXRrIPnuy4orpm93wbeXDB0b-BiHQ0w"
}
```

## Account verification
GET
http://localhost:8080/api/authentication/verify/{verificationCode}

Example input:

http://localhost:8080/api/authentication/verify/BFhUIMZCvLaRGRKAC9g9LqltlXLqzqopJiSY1G2BevotUt6YsfjKtigeUI2mqzSh

Example output:
```Text
Verification Succeeded
```

## Log in
POST
http://localhost:8080/api/authentication/sign-in

Example input: 
```Json
{
  "email": "example@example.com",
  "password": "password"
}
```
Example Output
```Json
{
  "email": "example@example.com",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJleGFtcGxlQGV4YW1wbGUuY29tIiwicm9sZXMiOiJST0xFX1VTRVIiLCJ1c2VySWQiOiI3ODViMjk1Zi04MmYyLTQ1ZTMtYTQ1NS1hZTkxODg4ZGU1ZmQiLCJleHAiOjE2ODg1NzYzMTd9.yG6xBV4mjCM6hqAB0iVTAkONZJZGf2pf9bZlI1jpZq0L3NOvWvdxLMfphckaLBlUOFIWFKWFFG_dxtIJnffjrw",
  "active": true
}
```

## Update account
PUT
http://localhost:8080/api/customer/update/{email}

Example input:

http://localhost:8080/api/customer/update/example@example.com

```Json
{
    "name": "Fulano",
    "lastName": "Peréz",
    "country":"Serbia",
    "address":"Calle siempre viva"
}
```
Example output:
```Json
{
    "name": "Fulano",
    "lastName": "Peréz",
    "country": "Serbia",
    "address": "Calle siempre viva"
}
```

## Forgot Password
POST
http://localhost:8080/api/authentication/forgot-password

Example input:
```Json
{
  "email": "example@example.com"
}
```
Example output:
```Json
{
    "mailFrom": "mascotaencasapetshelter@gmail.com",
    "mailTo": "example@example.com",
    "subject": "Password recovery by Ecommerce Team",
    "fullName": "Fulano Peréz",
    "token": "ad023dcb-bd65-43b2-bda9-c45943bff3cb"
}
```

## Reset Password
POST
http://localhost:8080/api/authentication/change-password

Example input:
```Json
{
  "password": "example",
  "confirmPassword": "example",
  "tokenPassword": "ad023dcb-bd65-43b2-bda9-c45943bff3cb"
}
```
Example output:
```Json
{
  "accountUuid": "785b295f-82f2-45e3-a455-ae91888de5fd",
  "email": "example@example.com",
  "password": "$2a$10$KL5A.Fi9oIljiT2XWLd1le7ipY1q8MxZnnKBT64Lpxu7XVePbtNuW",
  "rol": "USER",
  "createdDate": "2023-07-04T11:53:01",
  "lastSessionDate": "2023-07-04T11:53:01",
  "active": true,
  "verificationCode": null,
  "tokenPassword": null,
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJleGFtcGxlQGV4YW1wbGUuY29tIiwicm9sZXMiOiJVU0VSIiwidXNlcklkIjoiNzg1YjI5NWYtODJmMi00NWUzLWE0NTUtYWU5MTg4OGRlNWZkIiwiZXhwIjoxNjg4NTc5MjAzfQ.5Wu-x7zTLxCcoSlWON5PFixvlNwuZ06UjBHQTfAYtxF6W1RffGpTur62XCdhHT4_V6XHGZnhcDsPpxGKlBVyuA",
  "number": "2023-2",
  "name": "Fulano",
  "lastName": "Peréz",
  "address": "Calle siempre viva",
  "country": "Serbia",
  "phonesList": []
}
```

# Products

## Save Product
POST
http://localhost:8080/api/products

Example input:
```Json
{
    "name": "product4",
    "description": "description4",
    "stock": 150, 
    "image": "imagen4.jpg",
    "price": 2.5,
    "weight": 10.0,
    "country": "country2",
    "minStock": 20,
    "category": "bebidas"
}
```

Example output:
```Json
{
  "id": "b8ab31b0-5bd5-45b1-8abd-b20c9bec3969",
  "name": "product4",
  "description": "description4",
  "stock": 150,
  "image": "imagen4.jpg",
  "price": 2.5,
  "weight": 10.0,
  "country": "country2",
  "minStock": 20,
  "state": true,
  "category": {
    "id": "814fc71a-790f-4ce8-8b22-6f93f61c50a6",
    "name": "bebidas",
    "description": "bebidas regionales de diversos paises",
    "number": "b001",
    "state": true
  }
}
```

## Update Product
PUT 
http://localhost:8080/api/products/update/{productId}

Example input:

http://localhost:8080/api/products/update/b8ab31b0-5bd5-45b1-8abd-b20c9bec3969

```Json
{
  "name": "azucar",
  "description": "description",
  "stock": 100,
  "image": "imagen.jpg",
  "price": 2.5,
  "weight": 10.0,
  "country": "country",
  "minStock": 10,
  "category": "bebidas"
}
```

## Change state product
PATCH
http://localhost:8080/api/products/update-state/{productId}

Example input:

http://localhost:8080/api/products/update-state/b8ab31b0-5bd5-45b1-8abd-b20c9bec3969

Example output:
```Text
The product state changed
```

## Delete product
PATCH
http://localhost:8080/api/products/delete/{productId}

Example input:

http://localhost:8080/api/products/delete/00a2e79a-2e10-4776-a968-04f9864835ea

Example output:
```Text
The product state changed
```

## List Products pageable
GET
http://localhost:8080/api/products

Example input without filter
```Json
{
  "page": 1
}
```

Example input with country filter
```Json
{
  "page": 1,
  "country": "Argentina"
}
```

Example input with category filter
```Json
{
  "page": 1,
  "category": "bebidas"
}
```

Example input with both filter
```Json
{
  "page": 1,
  "country": "Argentina",
  "category": "bebidas"
}
```

Example output

```Json
[
  {
    "id": "b8ab31b0-5bd5-45b1-8abd-b20c9bec3969",
    "name": "azucar",
    "description": "description",
    "stock": 100,
    "image": "imagen.jpg",
    "price": 2.5,
    "weight": 10.0,
    "country": "country",
    "minStock": 10,
    "state": true,
    "category": {
      "id": "814fc71a-790f-4ce8-8b22-6f93f61c50a6",
      "name": "bebidas",
      "description": "bebidas regionales de diversos paises",
      "number": "b001",
      "state": true
    }
  }
]
```