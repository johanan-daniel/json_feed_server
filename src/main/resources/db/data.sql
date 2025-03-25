INSERT INTO Feeds (title, home_page_url, feed_url, description, authors, icon_url, favicon_url)
VALUES ('xkcd', 'https://xkcd.com', 'https://xkcd.com/info.0.json', 'a web comic',
	'[ {"url": "https://xkcd.com/about/", "name": "Randall Munroe"} ]',
	'https://xkcd.com/icon.png', 'https://xkcd.com/favicon.png');

INSERT INTO Feeds (title, home_page_url, feed_url, description, authors, icon_url, favicon_url)
VALUES ('test1', 'https://example.com/home_url', 'https://example.com/feed_url', 'some stuff', 
	'[ {"url": "https://xkcd.com/about/", "name": "Randall Munroe"} ]',
	'https://example.com/icon.png', 'https://example.com/favicon.png');

INSERT INTO Feeds (title, home_page_url, feed_url, description, authors, icon_url, favicon_url)
VALUES ('asdf1', 'https://example.com/asdf_home_url', 'https://example.com/asdf_feed_url', 'some more stuff', 
	'[ {"home_page_url": "https://asdf.com/about/", "writer": "fdas Munroe"} ]'::jsonb,
	'https://example.com/icon.png', 'https://example.com/favicon.png');

INSERT INTO Posts ("feed_id", "title", "url", "summary", "date_published", "content_html", "image_url")
VALUES ('https://xkcd.com/info.0.json', 'Cosmic Distance Calibration', 'https://xkcd.com/3066', 'This is the biggest breakthrough since astronomers noticed that the little crosshairs around red giant stars starting to burn helium are all the same size.', '2025-03-21 04:00:00', '<div><img src=https://imgs.xkcd.com/comics/cosmic_distance_calibration.png /><p>This is the biggest breakthrough since astronomers noticed that the little crosshairs around red giant stars starting to burn helium are all the same size.</p><a href=""https://www.explainxkcd.com/3066"">Explanation</a><p></p></div>', 'https://imgs.xkcd.com/comics/cosmic_distance_calibration.png')