CREATE TABLE IF NOT EXISTS Feeds (
	title VARCHAR(100),
	home_page_url VARCHAR(500),
	feed_url VARCHAR(500) PRIMARY KEY,
	description VARCHAR(1000),
	authors JSONB,
	icon_url VARCHAR(1000),
	favicon_url VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS Posts (
	feed_id VARCHAR(500),
	title VARCHAR(100),
	authors JSONB,
	url VARCHAR(500) PRIMARY KEY,
	summary VARCHAR(1000),
	date_published TIMESTAMP,
	content_html TEXT,
	image_url VARCHAR(1000),
	CONSTRAINT fk_feed FOREIGN KEY (feed_id) REFERENCES Feeds(feed_url)
);