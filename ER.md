<details>
  <summary>クリックで展開</summary>
  erDiagram

    m_user {
        VARCHAR(50) userId PK
        VARCHAR(50) mailAddress
        VARCHAR(50) name
        VARCHAR(500) password
        VARCHAR(50) role
        INTEGER point
    }

    m_faculty {
        INT faculty_id PK
        VARCHAR(50) faculty_name
    }

    item {
        VARCHAR(50) itemId PK
        VARCHAR(50) itemName
        INTEGER itemPrice
        VARCHAR(50) ornerName
        VARCHAR(50) ornerId
        VARCHAR(50) message
        BOOLEAN inCart
        BOOLEAN isSold
        BOOLEAN isCompletion
        TIMESTAMP salesDateTime
    }

    order_item {
        VARCHAR(50) orderId PK
        VARCHAR(50) itemId FK
        VARCHAR(50) purchaserId FK
        VARCHAR(50) ornerId FK
        INTEGER priceAtOrder
        TIMESTAMP orderDateTime
    }

    orders {
        VARCHAR(50) orderId PK
        VARCHAR(50) itemId FK
        VARCHAR(50) purchaserId FK
        VARCHAR(50) ornerId FK
        TIMESTAMP orderDateTime
    }

    notification {
        VARCHAR(50) notificationId PK
        VARCHAR(50) itemId FK
        VARCHAR(50) ornerId FK
        VARCHAR(50) purchaserId FK
        VARCHAR(50) content
        VARCHAR(50) type
        BOOLEAN read_
        TIMESTAMP dateTime_
    }

    reservingAppt {
        VARCHAR(50) reservingApptId PK
        VARCHAR(50) itemId FK
        VARCHAR(50) place1

</details>
