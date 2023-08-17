-- -- ### Создание роли базы данных ###
CREATE ROLE news_user WITH LOGIN PASSWORD 'news_q1';

-- -- ### Создание базы данных ###
CREATE DATABASE news OWNER news_user;
