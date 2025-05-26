-- テーブルが存在したら削除する
DROP TABLE IF EXISTS todos;
DROP TABLE IF EXISTS users;
DROP TYPE IF EXISTS role;

-- todosテーブルの作成
CREATE TABLE todos(
	-- id(TodoId):主キー
	id serial PRIMARY KEY,
	-- ユーザーID:NOT NULL
	user_id int NOT NULL,
	-- 登録日
	registration_date DATE NOT NULL,
	-- 期限日
	expiration_date DATE NOT NULL,
	-- 完了日
	finished_date DATE,
	-- TODO項目
	todo_item VARCHAR(50) NOT NULL,
	-- 削除フラグ
	is_deleted INTEGER DEFAULT 0,
	-- created_at
	created_at timestamp without time zone,
	-- updated_at
	updated_at timestamp without time zone
	);

--　権限用のENUM型
CREATE TYPE role AS ENUM('ADMIN', 'USER');

-- ユーザー情報を格納するテーブル
CREATE TABLE users (
	--ユーザーid : 主キー
	id serial PRIMARY KEY,
	-- ユーザー名：ユニークキー
	username VARCHAR(50) NOT NULL,
	-- 表示名
	displayname VARCHAR(50) NOT NULL,
	-- パスワード
	password VARCHAR(255) NOT NULL,
	-- 権限
	authority role NOT NULL DEFAULT 'ADMIN', 
	-- 作成日
	created_at Timestamp without time zone,
	-- 更新日
	updated_at Timestamp without time zone
);

--ユニークキー設定
ALTER TABLE users add constraint "profiles_username_constraint" unique ("username");

