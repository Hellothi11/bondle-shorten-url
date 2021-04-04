import {useEffect, useState} from 'react';

export default function useFetchListURLs(trigger) {
  const [urls, setUrls] = useState([]);
  const [error, setError] = useState(null);
  useEffect(() => {
    setUrls([
      {
        id: 1,
        longUrl: 'https://google.com.vn',
        shortUrl: 'https://link.bondle.com/82y1h',
      },
    ]);
  }, [trigger]);
  return [urls, error];
}
