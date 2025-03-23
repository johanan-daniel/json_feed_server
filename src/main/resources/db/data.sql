INSERT INTO Feeds (title, home_page_url, feed_url, description, authors, icon_url, favicon_url)
VALUES ('test1', 'https://example.com/home_url', 'https://example.com/feed_url', 'some stuff', 
	'[ {"url": "https://xkcd.com/about/", "name": "Randall Munroe"} ]',
	'https://example.com/icon.png', 'https://example.com/favicon.png');

INSERT INTO Feeds (title, home_page_url, feed_url, description, authors, icon_url, favicon_url)
VALUES ('asdf1', 'https://example.com/asdf_home_url', 'https://example.com/asdf_feed_url', 'some more stuff', 
	'[ {"home_page_url": "https://asdf.com/about/", "writer": "fdas Munroe"} ]'::jsonb,
	'https://example.com/icon.png', 'https://example.com/favicon.png');