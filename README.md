## 使用技術一覧
<!-- シールド一覧 -->
<p>
    <!-- バックエンドのフレームワーク一覧 -->
    <img src="https://img.shields.io/badge/-Spring%20Framework-FFFFFF.svg?logo=Spring&style=popout">
    <img src="https://img.shields.io/badge/-Thymeleaf-007396.svg?logo=thymeleaf&style=plastic">
    <!-- バックエンドの言語一覧 -->
    <img src="https://img.shields.io/badge/-Java-007396.svg?logo=java&style=popout">
    <!-- フロントエンドのフレームワーク一覧 -->
    <img src="https://img.shields.io/badge/-Jquery-0769AD.svg?logo=jquery&style=plastic">
    <!-- フロントエンドの言語一覧 -->
    <img src="https://img.shields.io/badge/-Javascript-F7DF1E.svg?logo=javascript&style=plastic">
    <!-- ミドルウェア一覧 -->
    <img src="https://img.shields.io/badge/-Mysql-4479A1.svg?logo=mysql&style=plastic">
    <img src="https://img.shields.io/badge/-Apache-D22128.svg?logo=apache&style=plastic">
    <!-- インフラ一覧 -->
    <img src="https://img.shields.io/badge/-AWS%20EC2-232F3E.svg?logo=amazon-aws&style=plastic">
</p>

## 目次
1. [プロジェクトについて](#プロジェクトについて)
2. [環境 ](#環境)
3. [ディレクトリ構成](#ディレクトリ構成)

## プロジェクトについて
プロジェクト名: shopping
概要: 中古教科書を取り扱うフリマアプリ.

## 環境
| 言語・フレームワーク    | バージョン  |
| --------------------- | ---------- |
| Java                  | 17.0.13    |
| Spring Boot           | 3.3.5      |
| Maven                 | 3.9.6      |
| jquery                | 3.5.1      |
| mysql                 | 8.4.4      |
| Apache                | 2.4.62     |

## ディレクトリ構成
```
src
└── main
    ├── java
    │   └── com
    │       └── example
    │           └── demo
    │               ├── ShoppingApplication.java
    │               ├── config
    │               │   ├── JavaConfig.java
    │               │   └── SecurityConfig.java
    │               ├── controller
    │               │   ├── AdminController.java
    │               │   ├── AppDetailsController.java
    │               │   ├── CartController.java
    │               │   ├── DepositController.java
    │               │   ├── ImageController.java
    │               │   ├── IndexController.java
    │               │   ├── ItemController.java
    │               │   ├── LoginController.java
    │               │   ├── MypageController.java
    │               │   ├── NotificationController.java
    │               │   ├── PaymentController.java
    │               │   ├── SignupController.java
    │               │   ├── SoldItemController.java
    │               │   ├── UserDetailController.java
    │               │   └── UserListController.java
    │               ├── entity
    │               │   ├── Item.java
    │               │   ├── MUser.java
    │               │   ├── Notification.java
    │               │   ├── OrderItem.java
    │               │   ├── Orders.java
    │               │   └── ReservingAppt.java
    │               ├── exception
    │               │   ├── DataNotFoundException.java
    │               │   └── StockShortageException.java
    │               ├── input
    │               │   ├── CartInput.java
    │               │   ├── CartItemInput.java
    │               │   ├── DepositInput.java
    │               │   ├── ItemInput.java
    │               │   ├── ReservingApptInput.java
    │               │   ├── SignupForm.java
    │               │   └── UserDetailForm.java
    │               ├── repository
    │               │   ├── ItemMapper.java
    │               │   ├── NotificationMapper.java
    │               │   ├── OrderItemRepository.java
    │               │   ├── OrdersMapper.java
    │               │   ├── ReservingApptMapper.java
    │               │   └── UserMapper.java
    │               └── service
    │                   ├── CartService.java
    │                   ├── ItemService.java
    │                   ├── NotificationService.java
    │                   ├── OrdersService.java
    │                   ├── PaymentService.java
    │                   ├── ReservingApptService.java
    │                   ├── UserService.java
    │                   └── impl
    │                       ├── UserDetailsServiceImpl.java
    │                       └── UserServiceImpl.java
    ├── resources
    │   ├── ValidationMessages.properties
    │   ├── mapper
    │   │   └── h2
    │   │       └── UserMapper.xml
    │   ├── messages.properties
    │   ├── sample_application.properties
    │   ├── static
    │   │   ├── css
    │   │   │   ├── appDetails
    │   │   │   │   └── appDetails.css
    │   │   │   ├── cart
    │   │   │   │   ├── addConfirmation.css
    │   │   │   │   ├── cart.css
    │   │   │   │   ├── removeConfirmation.css
    │   │   │   │   └── reservingAppt.css
    │   │   │   ├── deposit
    │   │   │   │   ├── completion.css
    │   │   │   │   ├── confirm.css
    │   │   │   │   └── deposit.css
    │   │   │   ├── index
    │   │   │   │   └── index.css
    │   │   │   ├── item
    │   │   │   │   ├── itemDetail.css
    │   │   │   │   └── itemList.css
    │   │   │   ├── layout
    │   │   │   │   └── layout.css
    │   │   │   ├── mypage
    │   │   │   │   └── mypage.css
    │   │   │   ├── payment
    │   │   │   │   ├── paymentCheck.css
    │   │   │   │   └── paymentSuccess.css
    │   │   │   ├── sales
    │   │   │   │   ├── salesCompletion.css
    │   │   │   │   ├── salesConfirmation.css
    │   │   │   │   └── salesForm.css
    │   │   │   ├── signup
    │   │   │   │   └── signup.css
    │   │   │   ├── transaction
    │   │   │   │   ├── transaction_check.css
    │   │   │   │   ├── transaction_confirm.css
    │   │   │   │   └── transaction_list.css
    │   │   │   └── user
    │   │   │       ├── detail.css
    │   │   │       └── list.css
    │   │   └── image
    │   │       ├── P01.png
    │   │       ├── P02.png
    │   │       ├── P03.png
    │   │       ├── bottan
    │   │       │   ├── bottan_appDetails.png
    │   │       │   ├── bottan_cart.png
    │   │       │   ├── bottan_items.png
    │   │       │   ├── bottan_mypage.png
    │   │       │   ├── bottan_notification.png
    │   │       │   ├── bottan_sails.png
    │   │       │   └── bottan_transaction.png
    │   │       └── icon
    │   │           └── icon.png
    │   └── templates
    │       ├── admin
    │       │   └── admin.html
    │       ├── appDetails
    │       │   ├── appDetails.html
    │       │   └── appDetails_index.html
    │       ├── cart
    │       │   ├── addConfirmation.html
    │       │   ├── cart.html
    │       │   ├── removeConfirmation.html
    │       │   ├── reservingAppt.html
    │       │   └── reservingApptConfirmation.html
    │       ├── deposit
    │       │   ├── completion.html
    │       │   ├── confirm.html
    │       │   └── deposit.html
    │       ├── error.html
    │       ├── index
    │       │   └── index.html
    │       ├── layout
    │       │   ├── footer.html
    │       │   ├── header.html
    │       │   ├── layout.html
    │       │   └── menu.html
    │       ├── login
    │       │   └── login.html
    │       ├── notification
    │       │   ├── confirmation.html
    │       │   ├── details.html
    │       │   ├── notFind.html
    │       │   ├── notification.html
    │       │   ├── recall.html
    │       │   ├── reselect.html
    │       │   ├── reselectConfirmation.html
    │       │   ├── response.html
    │       │   └── success.html
    │       ├── payment
    │       │   ├── paymentCheck.html
    │       │   ├── paymentSuccess.html
    │       │   ├── transaction_check.html
    │       │   ├── transaction_confirm.html
    │       │   └── transaction_list.html
    │       ├── sales
    │       │   ├── salesCompletion.html
    │       │   ├── salesConfirmation.html
    │       │   └── salesForm.html
    │       ├── signup
    │       │   └── signup.html
    │       ├── soldItem
    │       │   ├── itemDetails.html
    │       │   └── itemList.html
    │       └── user
    │           ├── detail.html
    │           ├── list.html
    │           └── mypage.html
    └── webapp
```
