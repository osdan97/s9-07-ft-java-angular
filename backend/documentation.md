# Category:
## List Categories
GET
https://delatinos.up.railway.app/api/category/list

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
https://delatinos.up.railway.app/api/category/create

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
https://delatinos.up.railway.app/api/category/updatebyname/{categoryName}

Example input:

https://delatinos.up.railway.app/api/category/updatebyname/dulces

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
DELETE https://delatinos.up.railway.app/api/category/deletebyname/{categoryName}

Example input:

https://delatinos.up.railway.app/api/category/deletebyname/bebidas

Example output:
```Json
{
  "mensaje": "category deleted successfully"
}
```

## Get by name
GET
https://delatinos.up.railway.app/api/category/detail/{categoryName}

Example input:

https://delatinos.up.railway.app/api/category/detail/dulces

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
https://delatinos.up.railway.app/api/authentication/sign-up

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
## Register admin
POST

https://delatinos.up.railway.app/api/user/sign-up

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
https://delatinos.up.railway.app/api/authentication/verify/{verificationCode}

Example input:

https://delatinos.up.railway.app/api/authentication/verify/BFhUIMZCvLaRGRKAC9g9LqltlXLqzqopJiSY1G2BevotUt6YsfjKtigeUI2mqzSh

Example output:
```Text
Verification Succeeded
```

## Log in
POST
https://delatinos.up.railway.app/api/authentication/sign-in

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
https://delatinos.up.railway.app/api/customer/update/{email}

Example input:

https://delatinos.up.railway.app/api/customer/update/example@example.com

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
https://delatinos.up.railway.app/api/authentication/forgot-password

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
https://delatinos.up.railway.app/api/authentication/change-password

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
## Account Authentication

POST
Example input:
```Json
{
  "accountUuid": "0667fe35-b307-4e19-8cfd-27329f4e5e8e",
  "email": "alfonsoalmonte@gmail.com",
  "password": "$2a$10$.w2H31jDLByPdtqd.7JAruiw/PK2MPQNB9DgoFtYed43uMDG/KG0m",
  "rol": "USER",
  "createdDate": "2023-07-12T11:25:15.838038",
  "lastSessionDate": "2023-07-12T11:25:15.838062",
  "active": true,
  "verificationCode": null,
  "tokenPassword": null,
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGZvbnNvYWxtb250ZUBnbWFpbC5jb20iLCJyb2xlcyI6IlVTRVIiLCJ1c2VySWQiOiIwNjY3ZmUzNS1iMzA3LTRlMTktOGNmZC0yNzMyOWY0ZTVlOGUiLCJleHAiOjE2ODkyNjI2OTR9.lxN86v-9mBzLIl0296XzoRpWT-Nn3QYymnS-VX3Q2DB-8ywOM7DUWau1KkIiFePY_R5ioy22YrKCeQ5SApszzQ",
  "number": "2023-1",
  "name": "Alfonso",
  "lastName": "Almonte",
  "address": "Calle siempre viva",
  "country": "República Dominicana",
  "phonesList": [
    {
      "phoneUuid": "3721e216-9fcf-4853-bd62-a2b055c4ced8",
      "phoneLabel": "CELLPHONE",
      "phoneNumber": "8493590",
      "cityCode": "809",
      "countryCode": "1"
    },
    {
      "phoneUuid": "72132b4e-4656-4618-997d-f6dc67f91272",
      "phoneLabel": "HOME",
      "phoneNumber": "5485914",
      "cityCode": "829",
      "countryCode": "1"
    },
    {
      "phoneUuid": "8e5e007f-e5ac-4fa7-a29e-cd4516e1b975",
      "phoneLabel": "JOB",
      "phoneNumber": "5910612",
      "cityCode": "849",
      "countryCode": "1"
    }
  ],
  "shippingDetailsList": [
    {
      "shippingDetailUuid": "0294bc3f-e705-4f9b-9cd2-4d7ab266e7c7",
      "shippingDetailsName": "Direccion 1",
      "name": "Alfonso",
      "lastName": "Almonte",
      "address1": "Calle Jonas Salk",
      "address2": "Lucerna",
      "postalCode": "11515",
      "provincia": "Santo Domingo",
      "city": "Santo Domingo Este",
      "country": "República Dominicanna",
      "active": true,
      "primaryAddress": true,
      "gift": false
    },
    {
      "shippingDetailUuid": "79840fd8-dfcf-4dc7-8866-e3ba6f773eb7",
      "shippingDetailsName": "Direccion 2",
      "name": "Alfonso",
      "lastName": "Almonte",
      "address1": "Calle Jonas Salk",
      "address2": "Lucerna",
      "postalCode": "11515",
      "provincia": "Santo Domingo",
      "city": "Santo Domingo Este",
      "country": "República Dominicanna",
      "active": true,
      "primaryAddress": false,
      "gift": false
    }
  ]
}

```

Example output:
```Json

```



# Products

## Save Product
POST
https://delatinos.up.railway.app/api/products

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
https://delatinos.up.railway.app/api/products/update/{productId}

Example input:

https://delatinos.up.railway.app/api/products/update/b8ab31b0-5bd5-45b1-8abd-b20c9bec3969

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
https://delatinos.up.railway.app/api/products/update-state/{productId}

Example input:

https://delatinos.up.railway.app/api/products/update-state/b8ab31b0-5bd5-45b1-8abd-b20c9bec3969

Example output:
```Text
The product state changed
```

## Delete product
PATCH
https://delatinos.up.railway.app/api/products/delete/{productId}

Example input:

https://delatinos.up.railway.app/api/products/delete/00a2e79a-2e10-4776-a968-04f9864835ea

Example output:
```Text
The product state changed
```

## List Products pageable
GET
https://delatinos.up.railway.app/api/products

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
# Favorites
## Save Favorites
POST

https://delatinos.up.railway.app/api/favorites/create

Example input
https://delatinos.up.railway.app/api/favorites/create

```Json

{
  "customers": "c2e67a5d-a2bb-485b-abfa-9aedc388d651",
  "product": "ae98190b-f1ce-46ab-9896-90e39731c17c"
}
```
Example output
```Json
{
  "id": "75b29a68-5901-4555-8060-58ffc1ea44c2",
  "customers": {
    "accountUuid": "c2e67a5d-a2bb-485b-abfa-9aedc388d651",
    "email": "example@example.com",
    "password": "$2a$10$jddKF6mfC.30UUmei.ZWueQK6xFaox6wNm6RgRHTVfxU99dVgCtLG",
    "rol": "USER",
    "createdDate": "2023-07-07T15:00:39",
    "lastSessionDate": "2023-07-07T15:00:39",
    "active": true,
    "verificationCode": null,
    "tokenPassword": null,
    "token": null,
    "number": "2023-1",
    "name": "Juan",
    "lastName": "Peréz",
    "address": null,
    "country": null,
    "phonesList": []
  },
  "product": {
    "id": "ae98190b-f1ce-46ab-9896-90e39731c17c",
    "name": "product4",
    "description": "description4",
    "stock": 150,
    "image": "imagen4.jpg",
    "price": 2.5,
    "weight": 10.0,
    "country": "country2",
    "state": true,
    "category": {
      "id": "a186c52f-75b3-497c-b31d-5c5523cb2988",
      "name": "bebidas",
      "description": "bebidas regionales de diversos paises",
      "number": "b001",
      "state": true
    }
  }
}
```

## List Favorites
GET
https://delatinos.up.railway.app/api/favorites/list/75b29a68-5901-4555-8060-58ffc1ea44c2


Example input
https://delatinos.up.railway.app/api/favorites/list/75b29a68-5901-4555-8060-58ffc1ea44c2
```Json
{
  "customers": "c2e67a5d-a2bb-485b-abfa-9aedc388d651",
  "product": "ae98190b-f1ce-46ab-9896-90e39731c17c"

}
```
Example Output
[]
```
```

## Delete Favorite
DELETE
https://delatinos.up.railway.app/api/favorites/deletebyid/75b29a68-5901-4555-8060-58ffc1ea44c2

Example input
https://delatinos.up.railway.app/api/favorites/deletebyid/75b29a68-5901-4555-8060-58ffc1ea44c2


Example output
```Json
{
  "mensaje": "favorites deleted successfully"
}
```

# Inventory
## List Inventory
GET
https://delatinos.up.railway.app/api/inventory/list


Example input
https://delatinos.up.railway.app/api/inventory/list


Example output
```json
[
    {
        "id": "5c199f7d-1ee9-4f83-a8c4-94003b89b792",
        "name": "Inventory Product",
        "purchase_price": 10.0,
        "selling_price": 2.5,
        "image": "image.jpg",
        "stock_inventory": 50,
        "updateDate": "2023-07-17T15:08:01.278913",
        "product": {
            "id": "391ec16a-6375-4668-a59f-d99510254722",
            "name": "product",
            "description": "description",
            "stock": 50,
            "image": "imagen.jpg",
            "price": 2.5,
            "weight": 10.0,
            "country": "country",
            "minStock": 20,
            "state": "U",
            "category": {
                "id": "be3cf616-5a59-4f48-bd73-08308db891a3",
                "name": "bebidas",
                "description": "bebidas regionales de diversos paises",
                "number": "b001",
                "state": true
            }
        }
    }
]
```


## Create Inventory
POST
https://delatinos.up.railway.app/api/inventory/create


Example input
https://delatinos.up.railway.app/api/inventory/create
```Json
{
  "name":"Inventory Product7",
  "stock_inventory":50,
  "product_name":"product7",
  "image":"image.jpg",
  "purchase_price":10
}
```

Example output
```Json
{
    "id": "5c199f7d-1ee9-4f83-a8c4-94003b89b792",
    "name": "Inventory Product",
    "purchase_price": 10.0,
    "selling_price": 2.5,
    "image": "image.jpg",
    "stock_inventory": 50,
    "updateDate": "2023-07-17T15:08:01.278912855",
    "product": {
        "id": "391ec16a-6375-4668-a59f-d99510254722",
        "name": "product",
        "description": "description",
        "stock": 50,
        "image": "imagen.jpg",
        "price": 2.5,
        "weight": 10.0,
        "country": "country",
        "minStock": 20,
        "state": "U",
        "category": {
            "id": "be3cf616-5a59-4f48-bd73-08308db891a3",
            "name": "bebidas",
            "description": "bebidas regionales de diversos paises",
            "number": "b001",
            "state": true
        }
    }
}
```


## Update Inventory
PUT
https://delatinos.up.railway.app/api/inventory/update/aa788793-3607-40dd-b26c-1d6d00107e30
Example input
```Json
{
  "purchase_price":30,
  "image":"image5"
}
```

Example output
```Json
{
    "id": "5c199f7d-1ee9-4f83-a8c4-94003b89b792",
    "name": "Inventory Product",
    "purchase_price": 30.0,
    "selling_price": 2.5,
    "image": "image5",
    "stock_inventory": 50,
    "updateDate": "2023-07-17T15:08:01.278913",
    "product": {
        "id": "391ec16a-6375-4668-a59f-d99510254722",
        "name": "product",
        "description": "description",
        "stock": 50,
        "image": "imagen.jpg",
        "price": 2.5,
        "weight": 10.0,
        "country": "country",
        "minStock": 20,
        "state": "U",
        "category": {
            "id": "be3cf616-5a59-4f48-bd73-08308db891a3",
            "name": "bebidas",
            "description": "bebidas regionales de diversos paises",
            "number": "b001",
            "state": true
        }
    }
}
```

##  Create Inventory Transaction
https://delatinos.up.railway.app/api/transaction/inventory
POST

Example input
https://delatinos.up.railway.app/api/transaction/inventory
```Json
{
  "quantity":15,
  "inventory":"Inventory Product7",
  "transaction_type":"COMPRA",
  "description":"Compra de product6 por motivo de agotamiento de stock"
}
```

Example output

```


```
##  List Inventory Transaction
https://delatinos.up.railway.app/api/transaction/inventory/listPOST
GET

Example input
https://delatinos.up.railway.app/api/transaction/inventory/list```Json



Example output
```

```

# Orders
## Create Order
https://delatinos.up.railway.app/api/orders
POST

Example input
https://delatinos.up.railway.app/api/orders

```JSON
{
  "customers": {
    "accountUuid":"45f60e43-619d-4031-a3c7-aacca8ed26e0"
  },
  "shippingCost": 15,
  "orderDetailsList": [
    {
      "product": {
        "id": "f0bcc4f4-d8f7-44c4-92d9-4c066dfe742e"
      },
      "quantity": 2
    },
    {
      "product": {
        "id": "99968e9d-725c-4951-bf00-8275f0a8d266"
      },
      "quantity": 4
    }
  ],
  "shippingDetails": {
    "name": "Ana",
    "lastName": "Reyes",
    "company": "",
    "address1": "Calle Mercedes No. 24",
    "address2": "Zona Colonial",
    "postalCode": "22243",
    "provincia": "Santo Domingo",
    "city": "Distrito Nacional",
    "country": "Republica Dominicana",
    "gift": false
  }
}
```

Example output
```JSON
{
  "number": "2023-2",
  "fullName": "Felipe Castro",
  "shippingCost": null,
  "amountTaxes": 2.85,
  "amountTotal": 15.0,
  "total": 17.85,
  "createdDate": "2023-07-11T15:05:24.496416873",
  "transactionState": "ON_HOLD",
  "orderDetailsRegistrationList": [
    {
      "productName": "azucar",
      "quantity": 2,
      "price": 2.5,
      "totalAmount": 5.0,
      "taxesAmount": 0.95,
      "total": 5.95
    },
    {
      "productName": "product2",
      "quantity": 4,
      "price": 2.5,
      "totalAmount": 10.0,
      "taxesAmount": 1.9,
      "total": 11.9
    }
  ],
  "shippingDetailsRegistration": {
    "fullName": "Ana Reyes",
    "company": "",
    "address": "Calle Mercedes No. 24",
    "address2": "Zona Colonial",
    "postalCode": "22243",
    "provincia": "Santo Domingo",
    "city": "Distrito Nacional",
    "country": "Republica Dominicana"
  }
}

```
# Shipping Details

## List shipping detail
GET
http://delatinos.up.railway.app/api/shipping-details/customer/%7Bcustomer_uuid%7D

Example input
http://delatinos.up.railway.app/api/shipping-details/customer/%7Bcustomer_uuid%7D
```JSON
{
  "shippingDetailsName":"Direccion ",
  "name":"Alfonso",
  "lastName":"Almonte",
  "address1":"Carretera  Mella 7",
  "address2":"Lucerna",
  "postalCode":"11516",
  "provincia":"Santo Domingo",
  "city": "Santo Domingo Este",
  "country": "República Dominicanna",
  "active": true,
  "primaryAddress":true
}

```
Example output
```JSON
[
  {
    "shippingDetailsCustomerName": "Direccion 1"
  },
  {
    "shippingDetailsCustomerName": "Direccion 3"
  },
  {
    "shippingDetailsCustomerName": "Direccion 2"
  }
]
```

## Add shipping detail
POST
https://delatinos.up.railway.app/api/shipping-details/65d5e792-b16f-498b-bf1a-41e4442d91c0
Example input
https://delatinos.up.railway.app/api/shipping-details/65d5e792-b16f-498b-bf1a-41e4442d91c0

```JSON
{
  "shippingDetailsName":"Direccion 3",
  "name":"Alfonso",
  "lastName":"Almonte",
  "address1":"Carretera Mella",
  "address2":"Lucerna",
  "postalCode":"11516",
  "provincia":"Santo Domingo",
  "city": "Santo Domingo Este",
  "country": "República Dominicanna",
  "active": true,
  "primaryAddress":true
}

```
Example output
```JSON
{
  "name": "Alfonso",
  "lastName": "Almonte",
  "shippingDetailsName": "Direccion 3",
  "address1": "Carretera Mella",
  "address2": "Lucerna",
  "postalCode": "11516",
  "provincia": "Santo Domingo",
  "city": "Santo Domingo Este",
  "country": "República Dominicanna",
  "active": true,
  "primaryAddress": true,
  "gift": false
}
```

## Change shipping detail primary address
POST
https://delatinos.up.railway.app/api/shipping-details/change-primary/{accountUuid}/{shippingDetailUuid}

Example input
https://delatinos.up.railway.app/api/shipping-details/change-primary/ad5e8b88-315b-4864-bf41-e473c0c7cb54/f5a282f6-8ceb-4123-8a77-793857f4e2d5

Example output
```Text
    Address changed to primary successfully.
```

# PAY
## Create payment
POST
Example input
https://delatinos.up.railway.app/api/pay

```JSON
{
    "cardNumber": "4242424242424242",
    "expirationDate": "0823",
    "cardCode": "900",
    "orders": {
        "transactionUuid": ""
    },
    "transaction_type": "PAID"
}

```
Example output
```JSON


