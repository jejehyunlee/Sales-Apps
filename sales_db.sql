/*
 Navicat Premium Data Transfer

 Source Server         : SALES-APP
 Source Server Type    : PostgreSQL
 Source Server Version : 120011 (120011)
 Source Host           : localhost:5432
 Source Catalog        : sales_db
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 120000
 File Encoding         : 65001

 Date: 05/12/2024 08:15:13
*/


-- ----------------------------
-- Table structure for m_product
-- ----------------------------
DROP TABLE IF EXISTS "m_product";
CREATE TABLE "m_product" (
  "id_product" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "product_desc" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "product_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "created_at" timestamp(6),
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "deleted_at" timestamp(6),
  "deleted_by" varchar(255) COLLATE "pg_catalog"."default",
  "modified_at" timestamp(6),
  "modified_by" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of m_product
-- ----------------------------
BEGIN;
INSERT INTO "m_product" ("id_product", "product_desc", "product_name", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('7589f934-cf97-4a2e-89bd-42120596df36', 'Electronic', 'TV', '2024-12-05 04:26:30.77784', 'ADMIN123@gmail.com', NULL, NULL, NULL, 'ADMIN123@gmail.com');
INSERT INTO "m_product" ("id_product", "product_desc", "product_name", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('9d288a74-36a5-4e94-9be6-7649a4f7aa6b', 'Electronic', 'LAPTOP', '2024-12-05 04:26:43.318355', 'ADMIN123@gmail.com', NULL, NULL, NULL, 'ADMIN123@gmail.com');
INSERT INTO "m_product" ("id_product", "product_desc", "product_name", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('d9125507-9de4-472f-a1ed-23ea2478b8c0', 'Electronic', 'RADIO', '2024-12-05 04:26:55.230137', 'ADMIN123@gmail.com', NULL, NULL, NULL, 'ADMIN123@gmail.com');
COMMIT;

-- ----------------------------
-- Table structure for m_product_price
-- ----------------------------
DROP TABLE IF EXISTS "m_product_price";
CREATE TABLE "m_product_price" (
  "id_product_price" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "is_active" bool,
  "price" int8,
  "stock" int4,
  "product_id" varchar(255) COLLATE "pg_catalog"."default",
  "created_at" timestamp(6),
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "deleted_at" timestamp(6),
  "deleted_by" varchar(255) COLLATE "pg_catalog"."default",
  "modified_at" timestamp(6),
  "modified_by" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of m_product_price
-- ----------------------------
BEGIN;
INSERT INTO "m_product_price" ("id_product_price", "is_active", "price", "stock", "product_id", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('2ca373db-603e-4ea9-9d76-c4b9212dad8b', 't', 6000, 20, 'd9125507-9de4-472f-a1ed-23ea2478b8c0', '2024-12-05 04:26:55.233135', 'ADMIN123@gmail.com', NULL, NULL, NULL, 'ADMIN123@gmail.com');
INSERT INTO "m_product_price" ("id_product_price", "is_active", "price", "stock", "product_id", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('e82a87c5-5bf8-4a66-880b-100ea34bb8e1', 't', 4000, 16, '7589f934-cf97-4a2e-89bd-42120596df36', '2024-12-05 04:26:30.801839', 'ADMIN123@gmail.com', NULL, NULL, '2024-12-05 04:31:54.084598', 'CASHIER-BARU@gmail.com');
INSERT INTO "m_product_price" ("id_product_price", "is_active", "price", "stock", "product_id", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('f9d56295-4b1f-4933-aa5c-c45063f2aecf', 't', 8000, 17, '9d288a74-36a5-4e94-9be6-7649a4f7aa6b', '2024-12-05 04:26:43.321358', 'ADMIN123@gmail.com', NULL, NULL, '2024-12-05 04:31:54.084598', 'CASHIER-BARU@gmail.com');
COMMIT;

-- ----------------------------
-- Table structure for m_role
-- ----------------------------
DROP TABLE IF EXISTS "m_role";
CREATE TABLE "m_role" (
  "id_role" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "role" varchar(255) COLLATE "pg_catalog"."default",
  "created_at" timestamp(6),
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "deleted_at" timestamp(6),
  "deleted_by" varchar(255) COLLATE "pg_catalog"."default",
  "modified_at" timestamp(6),
  "modified_by" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of m_role
-- ----------------------------
BEGIN;
INSERT INTO "m_role" ("id_role", "role", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('8a40c035-9880-4aed-b84c-a91f4b47d7d4', 'ROLE_ADMIN', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "m_role" ("id_role", "role", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('4d8c3f12-6ea7-404f-8a81-ec7c57faf11c', 'ROLE_CASHIER', NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for m_transaction
-- ----------------------------
DROP TABLE IF EXISTS "m_transaction";
CREATE TABLE "m_transaction" (
  "id_trx" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "trans_date" timestamp(6),
  "user_id" varchar(255) COLLATE "pg_catalog"."default",
  "created_at" timestamp(6),
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "deleted_at" timestamp(6),
  "deleted_by" varchar(255) COLLATE "pg_catalog"."default",
  "modified_at" timestamp(6),
  "modified_by" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of m_transaction
-- ----------------------------
BEGIN;
INSERT INTO "m_transaction" ("id_trx", "trans_date", "user_id", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('7bf8e1bc-adcc-4256-bea8-e93c0adc66b3', '2024-12-05 04:29:43.428923', 'ce6cbd9b-e5b8-4c8b-8ece-e4f95a24de3a', '2024-12-05 04:29:43.430922', 'ADMIN123@gmail.com', NULL, NULL, NULL, 'ADMIN123@gmail.com');
INSERT INTO "m_transaction" ("id_trx", "trans_date", "user_id", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('6e04ef37-0f6b-4c40-80a0-cfec3ecfc4ab', '2024-12-05 04:31:54.073599', '1a3d9d70-291d-4b8a-9081-41842c47dd14', '2024-12-05 04:31:54.074598', 'CASHIER-BARU@gmail.com', NULL, NULL, NULL, 'CASHIER-BARU@gmail.com');
COMMIT;

-- ----------------------------
-- Table structure for m_transaction_detail
-- ----------------------------
DROP TABLE IF EXISTS "m_transaction_detail";
CREATE TABLE "m_transaction_detail" (
  "id_order_detail" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "quantity" int4,
  "product_price_id" varchar(255) COLLATE "pg_catalog"."default",
  "trx_id" varchar(255) COLLATE "pg_catalog"."default",
  "created_at" timestamp(6),
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "deleted_at" timestamp(6),
  "deleted_by" varchar(255) COLLATE "pg_catalog"."default",
  "modified_at" timestamp(6),
  "modified_by" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of m_transaction_detail
-- ----------------------------
BEGIN;
INSERT INTO "m_transaction_detail" ("id_order_detail", "quantity", "product_price_id", "trx_id", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('0906fafe-a163-4e94-a4cb-cc525cde0f74', 3, 'e82a87c5-5bf8-4a66-880b-100ea34bb8e1', '7bf8e1bc-adcc-4256-bea8-e93c0adc66b3', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "m_transaction_detail" ("id_order_detail", "quantity", "product_price_id", "trx_id", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('0ee56175-34c4-48bd-adf3-c58e104944a2', 2, 'f9d56295-4b1f-4933-aa5c-c45063f2aecf', '7bf8e1bc-adcc-4256-bea8-e93c0adc66b3', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "m_transaction_detail" ("id_order_detail", "quantity", "product_price_id", "trx_id", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('898d44ff-63e5-4df8-a8b9-09aaad7c100b', 1, 'e82a87c5-5bf8-4a66-880b-100ea34bb8e1', '6e04ef37-0f6b-4c40-80a0-cfec3ecfc4ab', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "m_transaction_detail" ("id_order_detail", "quantity", "product_price_id", "trx_id", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('1f21fdf5-9d4a-4699-8df7-2e570a082499', 1, 'f9d56295-4b1f-4933-aa5c-c45063f2aecf', '6e04ef37-0f6b-4c40-80a0-cfec3ecfc4ab', NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for m_user
-- ----------------------------
DROP TABLE IF EXISTS "m_user";
CREATE TABLE "m_user" (
  "id_user" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "address" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "email" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "is_active" bool,
  "mobile_phone" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "user_credential_id" varchar(255) COLLATE "pg_catalog"."default",
  "created_at" timestamp(6),
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "deleted_at" timestamp(6),
  "deleted_by" varchar(255) COLLATE "pg_catalog"."default",
  "modified_at" timestamp(6),
  "modified_by" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of m_user
-- ----------------------------
BEGIN;
INSERT INTO "m_user" ("id_user", "address", "email", "is_active", "mobile_phone", "name", "user_credential_id", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('ce6cbd9b-e5b8-4c8b-8ece-e4f95a24de3a', 'Jakarta', 'ADMIN123@gmail.com', 't', '23306444323264546', 'Jefri', '26ee5029-91a6-4ac1-a6d6-ef10512f3e74', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "m_user" ("id_user", "address", "email", "is_active", "mobile_phone", "name", "user_credential_id", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('62b7061c-554b-44d0-858d-957fcc50673f', 'Jakarta', 'CASHIER1234@gmail.com', 'f', '23306444343434323264546', 'Jefri', 'b07c853a-8170-4e57-b5ce-4ce296f56e97', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "m_user" ("id_user", "address", "email", "is_active", "mobile_phone", "name", "user_credential_id", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('9378e4b3-1120-4ae2-a5a8-67e00ad4ac36', 'Jakarta', 'CASHIER-BARU123@gmail.com', 'f', '0813862367234423', 'AMEL', 'ab239544-9edf-4806-b3e5-5ec605e07216', '2024-12-05 03:47:24.521214', 'ADMIN123@gmail.com', NULL, NULL, NULL, 'ADMIN123@gmail.com');
INSERT INTO "m_user" ("id_user", "address", "email", "is_active", "mobile_phone", "name", "user_credential_id", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('1a3d9d70-291d-4b8a-9081-41842c47dd14', 'Jakarta', 'CASHIER-BARU@gmail.com', 't', '08138623672323', 'AKU SAYANG KAMU', '9017253d-cb5e-4af5-b666-47ea0db74a2f', NULL, NULL, NULL, NULL, '2024-12-05 04:06:31.5989', 'ADMIN123@gmail.com');
COMMIT;

-- ----------------------------
-- Table structure for m_user_role
-- ----------------------------
DROP TABLE IF EXISTS "m_user_role";
CREATE TABLE "m_user_role" (
  "id_user_credential" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "role_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of m_user_role
-- ----------------------------
BEGIN;
INSERT INTO "m_user_role" ("id_user_credential", "role_id") VALUES ('26ee5029-91a6-4ac1-a6d6-ef10512f3e74', '8a40c035-9880-4aed-b84c-a91f4b47d7d4');
INSERT INTO "m_user_role" ("id_user_credential", "role_id") VALUES ('b07c853a-8170-4e57-b5ce-4ce296f56e97', '4d8c3f12-6ea7-404f-8a81-ec7c57faf11c');
INSERT INTO "m_user_role" ("id_user_credential", "role_id") VALUES ('9017253d-cb5e-4af5-b666-47ea0db74a2f', '4d8c3f12-6ea7-404f-8a81-ec7c57faf11c');
INSERT INTO "m_user_role" ("id_user_credential", "role_id") VALUES ('ab239544-9edf-4806-b3e5-5ec605e07216', '4d8c3f12-6ea7-404f-8a81-ec7c57faf11c');
COMMIT;

-- ----------------------------
-- Table structure for user_credential
-- ----------------------------
DROP TABLE IF EXISTS "user_credential";
CREATE TABLE "user_credential" (
  "user_credential_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "created_at" timestamp(6),
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "deleted_at" timestamp(6),
  "deleted_by" varchar(255) COLLATE "pg_catalog"."default",
  "modified_at" timestamp(6),
  "modified_by" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of user_credential
-- ----------------------------
BEGIN;
INSERT INTO "user_credential" ("user_credential_id", "email", "password", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('26ee5029-91a6-4ac1-a6d6-ef10512f3e74', 'ADMIN123@gmail.com', '$2a$10$fFKCLexMdOhn0u/LFJ55c.Tn6rSpT5O7hyDu89Nk7TRl8kqKn3Px6', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "user_credential" ("user_credential_id", "email", "password", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('b07c853a-8170-4e57-b5ce-4ce296f56e97', 'CASHIER1234@gmail.com', '$2a$10$.DU8.tBlnuG2rPJOa6flO.LPjywb9AeF/GJQ1jayy7uw1GCCVdWde', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "user_credential" ("user_credential_id", "email", "password", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('9017253d-cb5e-4af5-b666-47ea0db74a2f', 'CASHIER-BARU@gmail.com', '$2a$10$oMQHDnf6VnO2o0onopz38.WGPJiSLG7PSvwuJd8UO5LZO8nvGzT5i', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "user_credential" ("user_credential_id", "email", "password", "created_at", "created_by", "deleted_at", "deleted_by", "modified_at", "modified_by") VALUES ('ab239544-9edf-4806-b3e5-5ec605e07216', 'CASHIER-BARU123@gmail.com', '$2a$10$xckINKZpH1JxmeRqAZRy4eS97/GZh0HmDCq7cJ3amg/EBzwy..O9i', '2024-12-05 03:47:24.494211', 'ADMIN123@gmail.com', NULL, NULL, NULL, 'ADMIN123@gmail.com');
COMMIT;

-- ----------------------------
-- Primary Key structure for table m_product
-- ----------------------------
ALTER TABLE "m_product" ADD CONSTRAINT "m_product_pkey" PRIMARY KEY ("id_product");

-- ----------------------------
-- Checks structure for table m_product_price
-- ----------------------------
ALTER TABLE "m_product_price" ADD CONSTRAINT "m_product_price_price_check" CHECK (price > 0);
ALTER TABLE "m_product_price" ADD CONSTRAINT "m_product_price_stock_check" CHECK (stock > 0);

-- ----------------------------
-- Primary Key structure for table m_product_price
-- ----------------------------
ALTER TABLE "m_product_price" ADD CONSTRAINT "m_product_price_pkey" PRIMARY KEY ("id_product_price");

-- ----------------------------
-- Checks structure for table m_role
-- ----------------------------
ALTER TABLE "m_role" ADD CONSTRAINT "m_role_role_check" CHECK (role::text = ANY (ARRAY['ROLE_CASHIER'::character varying, 'ROLE_ADMIN'::character varying, 'ROLE_SELLER'::character varying]::text[]));

-- ----------------------------
-- Primary Key structure for table m_role
-- ----------------------------
ALTER TABLE "m_role" ADD CONSTRAINT "m_role_pkey" PRIMARY KEY ("id_role");

-- ----------------------------
-- Primary Key structure for table m_transaction
-- ----------------------------
ALTER TABLE "m_transaction" ADD CONSTRAINT "m_transaction_pkey" PRIMARY KEY ("id_trx");

-- ----------------------------
-- Primary Key structure for table m_transaction_detail
-- ----------------------------
ALTER TABLE "m_transaction_detail" ADD CONSTRAINT "m_transaction_detail_pkey" PRIMARY KEY ("id_order_detail");

-- ----------------------------
-- Uniques structure for table m_user
-- ----------------------------
ALTER TABLE "m_user" ADD CONSTRAINT "ukrycw44p7cruupkosx3ibmj9q3" UNIQUE ("email");
ALTER TABLE "m_user" ADD CONSTRAINT "ukfibur5640s82ptxtqlxi16qvs" UNIQUE ("mobile_phone");
ALTER TABLE "m_user" ADD CONSTRAINT "ukhybef2eea2kxemmpy93889866" UNIQUE ("user_credential_id");

-- ----------------------------
-- Primary Key structure for table m_user
-- ----------------------------
ALTER TABLE "m_user" ADD CONSTRAINT "m_user_pkey" PRIMARY KEY ("id_user");

-- ----------------------------
-- Uniques structure for table user_credential
-- ----------------------------
ALTER TABLE "user_credential" ADD CONSTRAINT "ukja400kbsfopl13c5pu8rhbo09" UNIQUE ("email");

-- ----------------------------
-- Primary Key structure for table user_credential
-- ----------------------------
ALTER TABLE "user_credential" ADD CONSTRAINT "user_credential_pkey" PRIMARY KEY ("user_credential_id");

-- ----------------------------
-- Foreign Keys structure for table m_product_price
-- ----------------------------
ALTER TABLE "m_product_price" ADD CONSTRAINT "fk9hka9e5jr0e43jneqykp9w6uw" FOREIGN KEY ("product_id") REFERENCES "m_product" ("id_product") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table m_transaction
-- ----------------------------
ALTER TABLE "m_transaction" ADD CONSTRAINT "fkffvh56jjp4nn7w2lw4fnquk19" FOREIGN KEY ("user_id") REFERENCES "m_user" ("id_user") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table m_transaction_detail
-- ----------------------------
ALTER TABLE "m_transaction_detail" ADD CONSTRAINT "fk8f2umojn8opofk4wf84rascv" FOREIGN KEY ("product_price_id") REFERENCES "m_product_price" ("id_product_price") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "m_transaction_detail" ADD CONSTRAINT "fk8ru8xcpcm3rgdygssyfgtm6ga" FOREIGN KEY ("trx_id") REFERENCES "m_transaction" ("id_trx") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table m_user
-- ----------------------------
ALTER TABLE "m_user" ADD CONSTRAINT "fke0epuo6ensl1hjvc96q6rw5l1" FOREIGN KEY ("user_credential_id") REFERENCES "user_credential" ("user_credential_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table m_user_role
-- ----------------------------
ALTER TABLE "m_user_role" ADD CONSTRAINT "fk5myajstt3aviu653dg1r529d4" FOREIGN KEY ("id_user_credential") REFERENCES "user_credential" ("user_credential_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "m_user_role" ADD CONSTRAINT "fk8r0vgj4oh9yadj6wx62tk0p5m" FOREIGN KEY ("role_id") REFERENCES "m_role" ("id_role") ON DELETE NO ACTION ON UPDATE NO ACTION;
