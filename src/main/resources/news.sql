DROP TABLE IF EXISTS news;

CREATE TABLE news (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  news_title VARCHAR(200),
  news_link VARCHAR(1000),
  news_description VARCHAR(3000),
  news_language VARCHAR(50),
  news_copyright VARCHAR(100),
  news_pub_date DATETIME,
  news_author VARCHAR(100),
  news_icon VARCHAR(200),
  news_image VARCHAR(200),
  news_docs VARCHAR(200),
  news_created_at  DATETIME
);