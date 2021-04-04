import {useState} from 'react';

export default function useShortenURL(longUrl) {
  const [loading, setLoading] = useState(false);
  const [url, setUrl] = useState(null);
  const shortenUrl = () => {
    setLoading(true);
    setTimeout(() => {
      setUrl({
        id: 1,
        longUrl: 'https://google.com.vn',
        shortUrl: 'https://link.bondle.com/82y1h',
      });
      setLoading(false);
    }, 5000);
  };
  return [url, loading, shortenUrl];
}
